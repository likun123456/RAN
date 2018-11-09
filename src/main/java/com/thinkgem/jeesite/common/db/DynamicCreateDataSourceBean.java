package com.thinkgem.jeesite.common.db;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;

import com.thinkgem.jeesite.modules.collectset.entity.TCollectordeploy;
import com.thinkgem.jeesite.modules.netconfig.entity.TNewnetelement;

public class DynamicCreateDataSourceBean  implements ApplicationContextAware,ApplicationListener{
	private ConfigurableApplicationContext app;  
	  
    private DefaultSqlSessionFactory sqlSessionFactory;  
    
  
    public DefaultSqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(DefaultSqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	private DynamicDataSource dynamicDataSource;  
      
    public void setDynamicDataSource(DynamicDataSource dynamicDataSource) {  
        this.dynamicDataSource = dynamicDataSource;  
    }  
  
    public void setApplicationContext(ApplicationContext app) throws BeansException {  
        this.app = (ConfigurableApplicationContext)app;  
    }  
  
      
    public void onApplicationEvent(ApplicationEvent event) {  
        // 如果是容器刷新事件OR Start Event  
        if (event instanceof ContextRefreshedEvent) {  
             
                regDynamicBean();  
           
            //System.out.println(event.getClass().getSimpleName()+" 事件已发生！");  
        }  
          
    }  
  
    private void regDynamicBean() {  
        // 解析属性文件，得到数据源Map  
        Map<String, DataSourceInfo> mapCustom = parsePropertiesFile();  
        // 把数据源bean注册到容器中  
        addSourceBeanToApp(mapCustom);  
    }
    
    /*private void netDynamicBean() {  
        // 解析属性文件，得到数据源Map  
        Map<Long, String> mapCustom = parseNetPropertiesFile();  
        // 把数据源bean注册到容器中  
        addNetSourceBeanToApp(mapCustom);  
    }*/
    
  /*  private void regDynamicBean() throws IOException {  
        // 解析属性文件，得到数据源Map  
        Map<String, DataSourceInfo> mapCustom = parsePropertiesFile();  
        // 把数据源bean注册到容器中  
        addSourceBeanToApp(mapCustom);  
    } */ 
  
    

	/** 
     * 功能说明：根据DataSource创建bean并注册到容器中 
     *  
     * @param acf 
     * @param mapCustom 
     */  
    private void addSourceBeanToApp(Map<String, DataSourceInfo> mapCustom) {  
        DefaultListableBeanFactory acf = (DefaultListableBeanFactory) app.getAutowireCapableBeanFactory();  
  
        String DATASOURCE_BEAN_CLASS = "org.apache.commons.dbcp.BasicDataSource";  
        BeanDefinitionBuilder bdb;  
          
        Iterator<String> iter = mapCustom.keySet().iterator();  
          
        Map<Object, Object> targetDataSources = new LinkedHashMap<Object, Object>();  
          
        //baseBeanComm = new ChildBeanDefinition("dataSource");  
        
        //  将默认数据源放入 targetDataSources map中  
        targetDataSources.put("dataSource", app.getBean("dataSource"));  
  
        // 根据数据源得到数据，动态创建数据源bean 并将bean注册到applicationContext中去  
        while (iter.hasNext()) {  
              
            //  bean ID  
            String beanKey = iter.next();  
            //  创建bean  
            bdb = BeanDefinitionBuilder.rootBeanDefinition(DATASOURCE_BEAN_CLASS);  
            bdb.getBeanDefinition().setAttribute("id", beanKey);  
            bdb.addPropertyValue("driverClassName", mapCustom.get(beanKey).getDriverClassName());  
            bdb.addPropertyValue("url", mapCustom.get(beanKey).getUrl());  
            bdb.addPropertyValue("username", mapCustom.get(beanKey).getUsername());  
            bdb.addPropertyValue("password", mapCustom.get(beanKey).getPassword());  
            bdb.addPropertyValue("timeBetweenEvictionRunsMillis", mapCustom.get(beanKey).getTimeBetweenEvictionRunsMillis());  
            bdb.addPropertyValue("minEvictableIdleTimeMillis", mapCustom.get(beanKey).getMinEvictableIdleTimeMillis());  
            //  注册bean  
            acf.registerBeanDefinition(beanKey, bdb.getBeanDefinition());  
              
            //  放入map中，注意一定是刚才创建bean对象  
            targetDataSources.put(beanKey, app.getBean(beanKey));  
              
        }  
        //  将创建的map对象set到 targetDataSources；  
        dynamicDataSource.setTargetDataSources(targetDataSources);
          
		// 必须执行此操作，才会重新初始化AbstractRoutingDataSource 中的 resolvedDataSources，也只有这样，动态切换才会起效  
        dynamicDataSource.afterPropertiesSet();  
          
    }  
    
    
   /* private void addNetSourceBeanToApp(Map<Long, String> mapCustom) {
		// TODO Auto-generated method stub
    	DefaultListableBeanFactory acf = (DefaultListableBeanFactory) app.getAutowireCapableBeanFactory();  
    	  
        String DATASOURCE_BEAN_CLASS = "org.apache.commons.dbcp.BasicDataSource";  
        BeanDefinitionBuilder bdb;  
          
        Iterator<Long> iter = mapCustom.keySet().iterator();  
          
        Map<Object, Object> targetDataSources = new LinkedHashMap<Object, Object>();  
          
        //baseBeanComm = new ChildBeanDefinition("dataSource");  
        
        //  将默认数据源放入 targetDataSources map中  
        targetDataSources.put("dataSource", app.getBean("dataSource"));  
  
        // 根据数据源得到数据，动态创建数据源bean 并将bean注册到applicationContext中去  
        while (iter.hasNext()) {  
              
            //  bean ID  
            String beanKey = String.valueOf(iter.next());  
            //  创建bean  
            bdb = BeanDefinitionBuilder.rootBeanDefinition(DATASOURCE_BEAN_CLASS);  
            bdb.getBeanDefinition().setAttribute("id", beanKey);  
            //  注册bean  
            acf.registerBeanDefinition(beanKey, bdb.getBeanDefinition());  
              
            //  放入map中，注意一定是刚才创建bean对象  
            targetDataSources.put(beanKey, app.getBean(beanKey));  
              
        }  
        //  将创建的map对象set到 targetDataSources；  
        dynamicDataSource.setTargetDataSources(targetDataSources);
          
		// 必须执行此操作，才会重新初始化AbstractRoutingDataSource 中的 resolvedDataSources，也只有这样，动态切换才会起效  
        dynamicDataSource.afterPropertiesSet();  
	}*/
  
    /** 
     * 功能说明：GET ALL SM_STATIONS FROM DB1 
     *  
     * @return 
     * @throws IOException 
     */  
    private Map<String, DataSourceInfo> parsePropertiesFile() {  
    	
    	Map<String, DataSourceInfo> mds = new HashMap<String, DataSourceInfo>();   
 
    	SqlSession session = sqlSessionFactory.openSession();
    	List<TCollectordeploy> list = session.selectList("com.thinkgem.jeesite.modules.collectset.dao.TCollectordeployDao.findAllList");
    	for(TCollectordeploy c : list) {
    		if(c.getCoredataebmlog() == 1) {
    			DataSourceInfo dsi  = new DataSourceInfo();
	       		dsi.setDriverClassName("com.mysql.jdbc.Driver");
	       		dsi.setUrl("jdbc:mysql://"+c.getIp()+":3306/core_data_ebmlog?useUnicode=true&characterEncoding=utf-8");
	       		dsi.setUsername("root");
	       		dsi.setPassword("1qaz#EDC");
	       		mds.put(c.getIp() + "-core_data_ebmlog", dsi);
	       		System.out.println(c.getIp() + "-core_data_ebmlog");
    		}
    		if(c.getCoredatasub() == 1) {
    			DataSourceInfo dsi  = new DataSourceInfo();
	       		dsi.setDriverClassName("com.mysql.jdbc.Driver");
	       		dsi.setUrl("jdbc:mysql://"+c.getIp()+":3306/core_data_sub?useUnicode=true&characterEncoding=utf-8");
	       		dsi.setUsername("root");
	       		dsi.setPassword("1qaz#EDC");
	       		mds.put(c.getIp() + "-core_data_sub", dsi);
	       		System.out.println(c.getIp() + "-core_data_sub");
    		}
    		if(c.getCoreebmlog() == 1) {
    			DataSourceInfo dsi  = new DataSourceInfo();
	       		dsi.setDriverClassName("com.mysql.jdbc.Driver");
	       		dsi.setUrl("jdbc:mysql://"+c.getIp()+":3306/ebmlog?useUnicode=true&characterEncoding=utf-8");
	       		dsi.setUsername("root");
	       		dsi.setPassword("1qaz#EDC");
	       		mds.put(c.getIp() + "-ebmlog", dsi);
	       		System.out.println(c.getIp() + "-ebmlog");
    		}
    		
    	}
    	
        return mds;  
  
    }
    
    /*private Map<Long, String> parseNetPropertiesFile() {  
    	Map<Long, String> mds = new HashMap<Long, String>();   
    	SqlSession session = sqlSessionFactory.openSession();
    	List<TNewnetelement> list = session.selectList("com.thinkgem.jeesite.modules.netconfig.dao.TNewnetelementDao.findList");
    	for(int i=0;i<list.size();i++) {
    		mds.put(list.get(i).getFnid(), list.get(i).getFname());
    	}    	
        return mds;  
    }*/
    
    
    
}

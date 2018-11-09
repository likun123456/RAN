package com.thinkgem.jeesite.common.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
	    
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();    
         
    /**  
     *   
     * @author  chb  
     * @date 2017-5-9 下午4:06:44  
     * @return the currentLookupKey  
     */    
    public static String getCurrentLookupKey() {    
        return (String) contextHolder.get();    
    }    
     
    /**  
     *   
     * @author chb  
     * @date 2017-5-9 下午4:06:44 
     * @param currentLookupKey  
     *            the currentLookupKey to set  
     */    
    public static void setCurrentLookupKey(String currentLookupKey) {    
        contextHolder.set(currentLookupKey);    
    }    
     
    @Override    
    protected Object determineCurrentLookupKey() {    
        return getCurrentLookupKey();    
    }    
} 
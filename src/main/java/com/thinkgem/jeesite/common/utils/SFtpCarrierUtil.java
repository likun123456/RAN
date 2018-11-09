package com.thinkgem.jeesite.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;



import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.thinkgem.jeesite.common.entity.FTPInfo;
import com.jcraft.jsch.ChannelSftp.LsEntry;


/**    
 * SFTP工具类
 * @author shenyang    
 * @version 1.0    
 * @created 2015-9-18 上午09:31:28   
 */    
public class SFtpCarrierUtil {
	
	private Logger log;
	private FTPInfo ftpConf;
	
	private int port = 22;
	private ChannelSftp sftp = null;
	private Session sshSession = null;

	public SFtpCarrierUtil() {
	}
	
	public SFtpCarrierUtil(FTPInfo ftpConf) {
		this.ftpConf = ftpConf;
	}


	public SFtpCarrierUtil(FTPInfo ftpConf,Logger log, int port) {
		this.ftpConf = ftpConf;
		this.log = log;
		this.port = port;
	}

	public SFtpCarrierUtil(FTPInfo ftpConf,Logger log) {
		this.ftpConf = ftpConf;
		this.log = log;
	}

//	public static void main(String[] args) {
//		FTPInfoVO ftp = new FTPInfoVO();
//		ftp.setHost("10.117.42.1");
//		ftp.setUserName("ericsson");
//		ftp.setPassword("ZAQ!1dxhys");
//		SFtpCarrierUtil sftp = new SFtpCarrierUtil(ftp,null);
//		String localPath = "D:\\ebmlog\\";
//		String remotePath = "/logs/ebs/ready/";
//		sftp.connect();
//		sftp.batchDownLoadFile(remotePath, localPath, "A20151104.1220", "A20151104.1320",false);
//		sftp.disconnect();
//		System.exit(0);
//	}
	
	public static void main(String[] args) {
		FTPInfo  ftp = new FTPInfo();
	ftp.setHost("10.14.199.51");
	ftp.setUserName("root");
	ftp.setPassword("1qaz#EDC");
	SFtpCarrierUtil sftp = new SFtpCarrierUtil(ftp,null);
	String localPath = "D:\\ebmlog\\";
	String remotePath = "/logs/ebs/ready/";
	sftp.connect();
	System.out.println("11111111111111111111");
	//sftp.batchDownLoadFile(remotePath, localPath, "A20151104.1220", "A20151104.1320",false);
	sftp.disconnect();
	System.exit(0);
   }
	
	/** * connect server via sftp */
	public void connect() {
		try {
			JSch jsch = new JSch();
			jsch.getSession(ftpConf.getUserName(), ftpConf.getHost(), port);
			sshSession = jsch.getSession(ftpConf.getUserName(), ftpConf.getHost(),port);
			sshSession.setPassword(ftpConf.getPassword());
			Properties sshConfig = new Properties();
			sshConfig.put("StrictHostKeyChecking", "no");
			sshSession.setConfig(sshConfig);
			sshSession.connect();
			Channel channel = sshSession.openChannel("sftp");
			channel.connect();
			sftp = (ChannelSftp) channel;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** * 关闭资源 */
	public void disconnect() {
		if (this.sftp != null) {
			if (this.sftp.isConnected()) {
				this.sftp.disconnect();
			}
		}
		if (this.sshSession != null) {
			if (this.sshSession.isConnected()) {
				this.sshSession.disconnect();
			}
		}
	}

	/**
	 * * 批量下载文件 
	 * * @param remotPath * 远程下载目录(以路径符号结束)
	 * * @param localPath * 本地保存目录(以路径符号结束)
	 * * @param fileFormat * 下载文件格式(以特定字符开头,为空不做检验)
	 * * @param del * 下载后是否删除sftp文件
	 * * @return
	 */
	public List<String> batchDownLoadFile(String remotPath, String localPath,String startTime,String endTime, boolean del,String netId) {
		List<String> fileListDownloaded = new ArrayList<String>();
		try {
			connect();
			Vector v = listFiles(remotPath);
			if (v.size() > 0) {
				
				Iterator it = v.iterator();
				while (it.hasNext()) {
					LsEntry entry = (LsEntry) it.next();
					String filename = entry.getFilename();
					SftpATTRS attrs = entry.getAttrs();
					if (!attrs.isDir()) {
						if (startTime != null && !"".equals(startTime.trim())&&endTime != null && !"".equals(endTime.trim())) {
							if(filename.length()>40&&filename.indexOf("ebs")>0&&filename.substring(0,14).compareTo(startTime)>=0&&filename.substring(0,14).compareTo(endTime)<0){
								if (this.downloadFile(remotPath, filename, localPath, filename)) {
									log.info("====="+filename+"===== netId = " + netId);
									fileListDownloaded.add(filename);
									if(del){
										deleteSFTP(remotPath, filename);
									}
								}
							}
						} else {
							if (this.downloadFile(remotPath, filename, localPath, filename)) {
								log.info("====="+filename+"===== netId = " + netId);
								fileListDownloaded.add(filename);
								if(del){
									deleteSFTP(remotPath, filename);
								}
							}
						}
					}
				}
			}
		} catch (SftpException e) {
			e.printStackTrace();
		} finally {
			this.disconnect();
		}
		return fileListDownloaded;
	}

	/**
	 * * 下载单个文件 * * 
	 * @param remotPath * 远程下载目录(以路径符号结束) * 
	 * @param remoteFileName  下载文件名 * 
	 * @param localPath * 本地保存目录(以路径符号结束) * 
	 * @param localFileName * 保存文件名
	 * * @return
	 */
	public boolean downloadFile(String remotePath, String remoteFileName, String localPath, String localFileName) {
		try {
			sftp.cd(remotePath);
			File file = new File(localPath + localFileName);
			mkdirs(localPath + localFileName);
			sftp.get(remoteFileName, new FileOutputStream(file));
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * * 上传单个文件 * * 
	 * @param remotePath * 远程保存目录 * 
	 * @param remoteFileName * 保存文件名 * 
	 * @param localPath * 本地上传目录(以路径符号结束) * 
	 * @param localFileName * 上传的文件名
	 * @return
	 */
	public boolean uploadFile(String remotePath, String remoteFileName, String localPath, String localFileName) {
		FileInputStream in = null;
		try {
			createDir(remotePath);
			File file = new File(localPath + localFileName);
			in = new FileInputStream(file);
			sftp.put(in, remoteFileName);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	
	public boolean uploadEbmXmlFile(String remotePath, String remoteFileName, String localPathlocalFileName,String ffv) {
		FileInputStream in = null;
		try {
			createDir(remotePath);
			File file = new File(localPathlocalFileName);
			in = new FileInputStream(file);
			System.out.println("========"+remoteFileName);
			sftp.put(in, "ebm_"+ffv+".xml");
			sftp.cd(remotePath);
			sftp.chmod(755, "ebm_"+ffv+".xml");
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	
	public boolean uploadFile(String remotePath, String remoteFileName, String localPathlocalFileName) {
		FileInputStream in = null;
		try {
			createDir(remotePath);
			File file = new File(localPathlocalFileName);
			in = new FileInputStream(file);
			System.out.println("========"+remoteFileName);
			sftp.put(in, remoteFileName);
			sftp.cd(remotePath);
			sftp.chmod(755, remoteFileName);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	/**
	 * * 批量上传文件
	 * * @param remotePath * 远程保存目录
	 * * @param localPath * 本地上传目录(以路径符号结束)
	 * * @param del * 上传后是否删除本地文件
	 * * @return
	 */
	public boolean bacthUploadFile(String remotePath, String localPath, boolean del) {
		try {
			
			File file = new File(localPath);
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile() && files[i].getName().indexOf("bak") == -1) {
					if (this.uploadFile(remotePath, files[i].getName(), localPath, files[i].getName()) && del) {
						deleteFile(localPath + files[i].getName());
					}
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//this.disconnect();
		}
		return false;
	}

	/** * 删除本地文件
	 * * @param filePath 
	 * * @return */
	public boolean deleteFile(String filePath) {
		File file = new File(filePath);
		if (!file.exists()) {
			return false;
		}
		if (!file.isFile()) {
			return false;
		}
		return file.delete();
	}

	/** * 创建目录 * * @param createpath * @return */
	public boolean createDir(String createpath) {
		try {
			if (isDirExist(createpath)) {
				this.sftp.cd(createpath);
				return true;
			}
			String pathArry[] = createpath.split("/");
			StringBuffer filePath = new StringBuffer("/");
			for (String path : pathArry) {
				if (path.equals("")) {
					continue;
				}
				filePath.append(path + "/");
				if (isDirExist(filePath.toString())) {
					sftp.cd(filePath.toString());
				} else {
					// 建立目录
					System.out.println("================="+filePath.toString());
					sftp.mkdir(filePath.toString());
					// 进入并设置为当前目录
					sftp.cd(filePath.toString());
				}
			}
			this.sftp.cd(createpath);
			return true;
		} catch (SftpException e) {
			e.printStackTrace();
		}
		return false;
	}

	/** * 判断目录是否存在 * * @param directory * @return */
	public boolean isDirExist(String directory) {
		boolean isDirExistFlag = false;
		try {
			SftpATTRS sftpATTRS = sftp.lstat(directory);
			isDirExistFlag = true;
			return sftpATTRS.isDir();
		} catch (Exception e) {
			if (e.getMessage().toLowerCase().equals("no such file")) {
				isDirExistFlag = false;
			}
		}
		return isDirExistFlag;
	}

	/**
	 * * 删除stfp文件 
	 * * @param directory * 要删除文件所在目录 
	 * * @param deleteFile * 要删除的文件
	 * * @param sftp
	 */
	public void deleteSFTP(String directory, String deleteFile) {
		try {
			sftp.cd(directory);
			sftp.rm(deleteFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**  如果目录不存在就创建目录 * 
	 * @param path */
	public void mkdirs(String path) {
		File f = new File(path);
		String fs = f.getParent();
		f = new File(fs);
		if (!f.exists()) {
			f.mkdirs();
		}
	}

	/**
	 * * 列出目录下的文件
	 * * @param directory * 要列出的目录
	 * * @param sftp 
	 * * @return 
	 * * @throws
	 * SftpException
	 */
	public Vector listFiles(String directory) throws SftpException {
		return sftp.ls(directory);
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public ChannelSftp getSftp() {
		return sftp;
	}

	public void setSftp(ChannelSftp sftp) {
		this.sftp = sftp;
	}

}
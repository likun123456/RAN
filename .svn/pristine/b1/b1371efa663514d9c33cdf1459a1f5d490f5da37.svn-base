package com.thinkgem.jeesite.common.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;

import com.thinkgem.jeesite.common.entity.FTPInfo;


/**
 * 
 * @author zhuguangrui
 *
 */
public class FtpCarrierUtils {
	private Logger logger;
	private FTPInfo ftpConf;
	private FTPClient ftpClient = new FTPClient();
	private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	public FtpCarrierUtils(FTPInfo ftpConf, Logger log) {
		this.ftpConf = ftpConf;
		this.logger = log;
	}
	
	public FtpCarrierUtils(FTPInfo ftpConf) {
		this.ftpConf = ftpConf;
	}


	/**
	 * Configuration
	 * 
	 * @return
	 */
	private FTPClientConfig getConfigure() {
		FTPClientConfig configure = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
		return configure;
	}

	/**
	 * Open the FTP connection
	 * 
	 */
	public void connect() throws Exception {
		int reply = 0;
		if (!ftpClient.isConnected()) {
			ftpClient = new FTPClient();
			ftpClient.connect(this.ftpConf.getHost(), 21);
			ftpClient.setControlEncoding("ISO-8859-1");
			ftpClient.configure(getConfigure());
			ftpClient.setBufferSize(1024 * 1024 * 10);
			boolean f = ftpClient.login(this.ftpConf.getUserName(),
					this.ftpConf.getPassword());
			reply = ftpClient.getReplyCode();
			if (!this.ftpConf.isSsl()) {
				if (!FTPReply.isPositiveCompletion(reply)) {
					ftpClient.disconnect();
					return;
				}
			}
		}
	}

	/**
	 * Close the current connection
	 */
	public void close() throws IOException {
		if (ftpClient.isConnected()) {
			this.ftpClient.logout();
			this.ftpClient.disconnect();
		}
	}


	
	public void downUserSearchCdrFiles(String workingDir,String localDir,String fileName,List<String> dateList){
		try {
			System.out.println("========= Ftp download cdr files start ========= "+fileName);
			for(String date : dateList){
				ftpClient.changeWorkingDirectory(analysiPath(workingDir,date));
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				FTPFile[] ftpFiles = ftpClient.listFiles();
				for(int i=0;i<ftpFiles.length;i++){
					FTPFile ftpFile = ftpFiles[i];
					if(ftpFile.getName().equals(fileName.replaceAll("/", ":"))){
						File localFile = new File(localDir, ftpFile.getName());
						if (!localFile.exists()) {
							OutputStream outputStream = new FileOutputStream(localFile);
							ftpClient.retrieveFile(ftpFile.getName(), outputStream);
							outputStream.close();
						}
					}else{
						continue;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("exception:"+e.getMessage());
		}
		
	}
	/**
	 * 上传文件
	 * @param localFilePath
	 * @param remoteFilePath
	 * @param remoteFileName
	 * @return
	 */
	public boolean uploadXmlFile(String localFilePath,String remoteFilePath,String remoteFileName,String remoteFileDir){
		FileInputStream in = null;
		try {
			//linux创建文件夹
			int i = ftpClient.sendCommand("chmod","777 " + remoteFileDir); 
			System.out.println(i);
			boolean b0 = ftpClient.makeDirectory(remoteFilePath);
			System.out.println("b0=true,创建成功;b0=false,创建失败: "+b0);
			int j = ftpClient.sendCommand("chmod","777 " + remoteFilePath); 
			System.out.println(j);
			boolean b1 = ftpClient.changeWorkingDirectory(remoteFilePath);
			System.out.println("切换工作目录："+b1);
			boolean b2 = ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			in = new FileInputStream(localFilePath);
			boolean b3 = ftpClient.storeFile(remoteFileName, in);
			System.out.println("上传是否成功："+b3);
			return b1&&b2&&b3;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("exception:"+e.getMessage());
			return false;
		}finally{
			if(in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	
	/**
	 * 上传文件
	 * @param localFilePath
	 * @param remoteFilePath
	 * @param remoteFileName
	 * @return
	 */
	public boolean uploadFile(String localFilePath, String remoteFilePath, String remoteFileName) {
		FileInputStream in = null;
		try {
			ftpClient.changeWorkingDirectory(remoteFilePath);
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			in = new FileInputStream(localFilePath);
			return ftpClient.storeFile(remoteFileName, in);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("exception:" + e.getMessage());
			return false;
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}
	
	/**
	 * 话单下载路径辅助方法
	 * 
	 * @param path
	 * @return
	 */
	public String analysiPath(String path, String date) {
		String[] strings = path.split("/");
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 1; i < strings.length; i++) {
			if (i == strings.length - 1) {
				sBuilder.append("/").append(date.replaceAll("-", "") + "-")
						.append(strings[i]);
			} else {
				sBuilder.append("/").append(strings[i]);
			}
		}
		return sBuilder.toString();
	}
	
}

package com.thinkgem.jeesite.common.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.thinkgem.jeesite.common.entity.InputStreamChannelExec;

/**
 * SSH工具类
 * 
 * @author zhuguangrui 2016-12-26
 */
public class SSHUtil {

	private String host = null;
	private String user = null;
	private String password = null;
	private Session session = null;

	public SSHUtil(String host, String user, String password) {
		this.host = host;
		this.user = user;
		this.password = password;
	}

	public void connect() throws JSchException {
		Session openSession = null;
		JSch jsch = new JSch();
		openSession = jsch.getSession(user, host, 22);
		openSession.setPassword(password);
		java.util.Properties config = new java.util.Properties();
		config.put("StrictHostKeyChecking", "no");
		openSession.setConfig(config);
		openSession.connect();
		this.session = openSession;
		System.out.println("ssh2 create connect success...");
	}

	/**
	 * 远程 执行命令并返回结果调用过程 是同步的（执行完才会返回）
	 * 
	 * @param host
	 *            主机名
	 * @param user
	 *            用户名
	 * @param psw
	 *            密码
	 * @param port
	 *            端口
	 * @param command
	 *            命令
	 * @return
	 */
	public String execCommand(String command) {
		String result = "";
		ChannelExec openChannel = null;
		String charset = "UTF-8";
		try {
			if (session == null) {
				// 重新连接一次
				connect();
			}
			openChannel = (ChannelExec) session.openChannel("exec");
			openChannel.setCommand(command);
			openChannel.setErrStream(System.err);
			openChannel.connect();
			InputStream in = openChannel.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName(charset)));
			String buf = null;
			while ((buf = reader.readLine()) != null) {
				result += buf + System.getProperty("line.separator");
			}

		} catch (Exception e) {
			result += e.getMessage();
		} finally {
			if (openChannel != null && !openChannel.isClosed()) {
				openChannel.disconnect();
			}
		}
		return result;
	}

	public InputStreamChannelExec execCommandReturnStream(String command) {
		InputStreamChannelExec Isce = new InputStreamChannelExec();
		String charset = "UTF-8";
		ChannelExec openChannel = null;
		InputStreamReader is = null;
		try {
			if (session == null) {
				// 重新连接一次
				connect();
			}
			openChannel = (ChannelExec) session.openChannel("exec");
			openChannel.setCommand(command);
			openChannel.setErrStream(System.err);
			openChannel.connect();
			InputStream in = openChannel.getInputStream();
			is = new InputStreamReader(in, Charset.forName(charset));
		} catch (Exception e) {
			e.getMessage();
		} finally {
			Isce.setIs(is);
			Isce.setCe(openChannel);
		}
		return Isce;
	}

	public void disconnect() {
		if (session != null && session.isConnected()) {
			session.disconnect();
			System.out.println("ssh2 session disconnect success...");
		}
	}

	public String sendCommand(String cmd, String end) throws Exception {

		PrintWriter printWriter = new PrintWriter(out);
		printWriter.println(cmd);
		printWriter.flush();
		return readUntil(end);
	}
	
	public String sendCommand(String cmd, String end,int count) throws Exception {

		PrintWriter printWriter = new PrintWriter(out);
		printWriter.println(cmd);
		printWriter.flush();
		return readUntil(end,count);
	}
	private ChannelShell channel = null;
	OutputStream out = null;
	InputStream in =null;
	public void getShellChannel() throws Exception{
		 channel = (ChannelShell) session.openChannel("shell");
		 channel.connect(30*1000);
		 out = channel.getOutputStream();
		 in = channel.getInputStream();
	}
	
	public String readUntil(String pattern) {
		try {
			char lastChar = pattern.charAt(pattern.length() - 1);
			StringBuffer sb = new StringBuffer();
			char ch = (char) in.read();
			while (true) {
				sb.append(ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(pattern)) {
						return sb.toString();
					}
				}
				ch = (char) in.read();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String readUntil(String pattern,int count) {
		try {
			int i =0;
			char lastChar = pattern.charAt(pattern.length() - 1);
			StringBuffer sb = new StringBuffer();
			char ch = (char) in.read();
			while (true) {
				sb.append(ch);
				if (ch == lastChar) {
					i++;
					if (i==count && sb.toString().endsWith(pattern)) {
						return sb.toString();
					}
				}
				ch = (char) in.read();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String args[]) throws JSchException {
		SSHUtil ssh = new SSHUtil("10.14.54.6", "EricHC", "gprs&2WY");
		ssh.connect();
		try {
			ssh.getShellChannel();
			ssh.sendCommand("terminal length 0","#");
			String a = ssh.sendCommand("show port counters", "#",3);
			System.out.println(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ssh.disconnect();
	}
}
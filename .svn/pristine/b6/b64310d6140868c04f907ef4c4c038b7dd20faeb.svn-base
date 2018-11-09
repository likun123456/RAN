package com.thinkgem.jeesite.common.entity;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import com.thinkgem.jeesite.common.socket.SocketObj;
import com.thinkgem.jeesite.modules.collectset.entity.TCollectordeploy;

public class Client {
	
	private SocketObj socketvo;
	
	private TCollectordeploy collector;
	
	public Client(SocketObj socketvo,TCollectordeploy collector) {
		this.socketvo = socketvo;
		this.collector = collector;
	}
	
	public String send(int port) throws UnknownHostException, java.io.IOException,
			ClassNotFoundException {
		Socket socket = null;
		ObjectOutputStream os = null;
		ObjectInputStream is = null;
		String result = "error";
		
		socket = new Socket(collector.getIp(), port);
		
		os = new ObjectOutputStream(socket.getOutputStream());
		os.writeObject(socketvo);
		os.flush();
		
		is = new ObjectInputStream(new BufferedInputStream(
				socket.getInputStream()));
		Object obj = is.readObject();
		if (obj != null) {
			result = (String) obj;
			System.out.println(result);
		}
		is.close();
		os.close();
		socket.close();
		return result;
	}

}

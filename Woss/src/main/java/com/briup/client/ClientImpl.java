package com.briup.client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Properties;

import com.briup.bean.Environment;

public class ClientImpl implements Client{
	
	//实现init方法，获取propertires内的所需的值
	String ipNum=null;
	String portNum=null;
	public void init(Properties properties) throws Exception {
		ipNum = (String)properties.get("ip");
		portNum=(String)properties.get("port");
		
	}

	public void EnvClient(List<Environment> list){
		ObjectOutputStream oos = null;
	// 声明客户端
	Socket socket = null;
	// 获取本机IP地址
	String ip = ipNum;
	// 服务器端口号
	int port = Integer.parseInt(portNum);
	try {
		socket = new Socket(ip, port);
		System.out.println("连接服务器成功！");

		
		//将list集合传给客户端

		
		oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(list);
		oos.flush();
		//		//存数据库
		//	    ServerMain.init();


	} catch (Exception e) {
		e.printStackTrace();
}  finally{
	if(oos!=null) {
	 try {
		oos.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
  }
	if(socket!=null) {
		 try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	  }

 }
}		
}

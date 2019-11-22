package com.briup.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Properties;

import com.briup.bean.Environment;

public class ServerImpl implements Server{
	//实现init方法，获取propertires内的所需的值
	String portNum=null;
	public void init(Properties properties) throws Exception {
		portNum=(String) properties.get("port");
		
	}

	@SuppressWarnings("unchecked")
	public List<Environment> EnvServer(){
		ObjectInputStream ois = null;
		List<Environment> list = null;
		//服务器端
		ServerSocket server = null;
		//客户端
		Socket client = null;
		//端口号
		int port = Integer.parseInt(portNum);
		try {
			//初始化客户端
			server = new ServerSocket(port);
			System.out.println("服务器启动，端口号：" + port);
			//等待客户端的连接
			client = server.accept();
			System.out.println("客户端已连接，client=" + client);

			
			ois = new ObjectInputStream(client.getInputStream());
			Object obj=ois.readObject();
			list = (List<Environment>)obj;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (client != null) {
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (server != null) {
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return list;
	}
	
		
}

package com.briup.main;

import java.util.List;

import com.briup.bean.Environment;
import com.briup.server.DBStoreImpl;
import com.briup.server.ServerImpl;
import com.briup.util.ConfigurationImpl;
//服务器端
public class ServerMain {	
//public static void main(String[] args) {
//	//通过ConfigrationImpl获取ServerImpl对象，获取客户端的list;
//	List<Environment> list=null;
//	ConfigurationImpl impl1 = new ConfigurationImpl();
//	ServerImpl server = impl1.getServer();
//	list = server.EnvServer();
//	System.out.println(list.size());
//	//将list存入数据库
//	ConfigurationImpl impl2 = new ConfigurationImpl();
//	DBStoreImpl dbStore = impl2.getDbStore();
//	dbStore.DBinit(list);
//	
//	}

	public void severTest() {
		//通过ConfigrationImpl获取ServerImpl对象，获取客户端的list;
		List<Environment> list=null;
		ConfigurationImpl impl1 = new ConfigurationImpl();
		ServerImpl server = impl1.getServer();
		list = server.EnvServer();
		System.out.println(list.size());
		
		//将list存入数据库
		ConfigurationImpl impl2 = new ConfigurationImpl();
		DBStoreImpl dbStore = impl2.getDbStore();
		dbStore.DBinit(list);
	}
}

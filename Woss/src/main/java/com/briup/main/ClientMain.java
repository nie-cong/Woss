package com.briup.main;

import java.util.List;

import com.briup.bean.Environment;
import com.briup.client.ClientImpl;
import com.briup.client.GatherImpl;
import com.briup.util.ConfigurationImpl;

public class ClientMain {

//	public static void main(String[] args) {
//		//通过ConfigurationImpl获取GatherImpl对象后获取list集合
//			List<Environment> list=null;
//				ConfigurationImpl impl1 = new ConfigurationImpl();
//				GatherImpl gather = impl1.getGather();
//				try {
//					list = gather.gatherDate();
//					System.out.println(list.size());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//		
//		//通过ConfigurationImpl获取ClientImple对象传list
//		ConfigurationImpl impl2=new ConfigurationImpl();
//		ClientImpl client = impl2.getClient();
//		client.EnvClient(list);
//	}
	//gui调用方法
	public void clientTest() {
		//通过ConfigurationImpl获取GatherImpl对象后获取list集合
		List<Environment> list=null;
			ConfigurationImpl impl1 = new ConfigurationImpl();
			GatherImpl gather = impl1.getGather();
			try {
				list = gather.gatherDate();
				System.out.println(list.size());
			} catch (Exception e) {
				e.printStackTrace();
			}
	
	//通过ConfigurationImpl获取ClientImple对象传list
	ConfigurationImpl impl2=new ConfigurationImpl();
	ClientImpl client = impl2.getClient();
	client.EnvClient(list);	
	}
}

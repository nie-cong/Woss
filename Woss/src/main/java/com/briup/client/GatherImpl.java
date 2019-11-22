package com.briup.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.briup.bean.Environment;
import com.briup.util.ConfigurationImpl;
import com.briup.util.LogImpl;

public class GatherImpl implements Gather {
	List<Environment> list=new ArrayList<Environment>();
	//实现init方法，获取propertires内的所需的值
	String filePath=null;
	public void init(Properties properties) throws Exception {
		filePath=(String)properties.get("path");
	}
	
	public List<Environment> gatherDate() throws Exception {
		//使用日志
		//用count1,2,3记录个数据的采集情况
			int count1=0;
			int count2=0;
			int count3=0;
		LogImpl log=new ConfigurationImpl().getLog();
		
		
			//声明流
			Reader r=null;
			//创建流
			File file=new File(filePath);
			
			r=new FileReader(file);
			BufferedReader br=new BufferedReader(r);
			//使用流
			String date=null;
			while((date=br.readLine())!=null) {
				String[] str=date.split("[|]");
				//新建对象
				Environment env=new Environment();
				//发送端id
				env.setSendId(str[0]);
				//树莓派id
				env.setSmId(str[1]);
				//板子模块id
				env.setQmId(str[2]);
				//板子模块具体感应器id，16：温度湿度 256：光照 1280：二氧化碳
				if(str[3].equals("16")) {
					//日志温湿度count1
					count1++;
				env.setAddress(str[3]);
				env.setName("温度");
				//控制传感器个数
				env.setCount(Integer.parseInt(str[4]));
				//发送的命令
				if(str[5].equals("3")) {
				env.setOrdernumber("3");
				}
				if(str[5].equals("16")) {
					env.setOrdernumber("16");
					}
				//温度
				float Temperature = (float) ((Integer.parseInt(str[6].substring(0, 4), 16) * 0.00268127) - 46.85);
				env.setData(Temperature);
				//状态
				env.setStatus(Integer.parseInt(str[7]));
				//采集时间
				Timestamp time = new Timestamp(Long.parseLong(str[8]));
				env.setGather_date(time);
				list.add(env);
				//湿度
				env.setAddress(str[3]);
				env.setName("湿度");
				float Humidity = (float) ((Integer.parseInt(str[6].substring(4, 8), 16)*0.00190735)-6);
				env.setData(Humidity);
				list.add(env);
				}else if(str[3].equals("256"))	{
					//日志光照count2
					count2++;
					
					env.setAddress(str[3]);
					env.setName("光照");
					//控制传感器个数
					env.setCount(Integer.parseInt(str[4]));
					//发送的命令
					if(str[5].equals("3")) {
					env.setOrdernumber("3");
					}
					if(str[5].equals("16")) {
						env.setOrdernumber("16");
						}
					//光照
					float value = Integer.valueOf(str[6].substring(0, 4), 16);
					env.setData(value);
					//状态
					env.setStatus(Integer.parseInt(str[7]));
					//采集时间
					Timestamp time = new Timestamp(Long.parseLong(str[8]));
					env.setGather_date(time);
					list.add(env);
				}else if(str[3].equals("1280")) {
					//二氧化碳count3
					count3++;
					
					env.setAddress(str[3]);
					env.setName("二氧化碳");
					//控制传感器个数
					env.setCount(Integer.parseInt(str[4]));
					//发送的命令
					if(str[5].equals("3")) {
					env.setOrdernumber("3");
					}
					if(str[5].equals("16")) {
						env.setOrdernumber("16");
						}
					//二氧化碳
					float value = Integer.valueOf(str[6].substring(0, 4), 16);
					env.setData(value);
					//状态
					env.setStatus(Integer.parseInt(str[7]));
					//采集时间
					Timestamp time = new Timestamp(Long.parseLong(str[8]));
					env.setGather_date(time);
					list.add(env);
				}
			}
			if(r!=null) {
				r.close();
			}
			if(br!=null) {
				br.close();
			}
			log.info("温度湿度采集次数："+count1);
			log.info("光照强度采集次数："+count2);
			log.info("二氧化碳采集次数："+count3);
			log.info("环境数据采集完成！");

		return list;
	}
}

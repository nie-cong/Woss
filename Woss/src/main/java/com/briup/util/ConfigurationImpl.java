package com.briup.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.briup.client.ClientImpl;
import com.briup.client.GatherImpl;
import com.briup.server.DBStoreImpl;
import com.briup.server.ServerImpl;
//解析配置文件
public class ConfigurationImpl implements Configuration{
	Map<String,Object> map=new HashMap<String,Object>();
	Properties properties=new Properties();	

	public ConfigurationImpl() {
		this("src/main/resources/config.xml");
	}
	
	public ConfigurationImpl(String filePath) {
		WossModuleInit obj=null;
		//Dom4j解析config.xml文件
		//获取一个SAXReader对象
		SAXReader reader = new SAXReader();
		try {
			//读取解析文件src/main/resources/config.xml
			Document document = reader.read(filePath);
			//获取根节点
			Element rootElement = document.getRootElement();
			//获取所有子节点将字节点放入list集合
			@SuppressWarnings("unchecked")
			List<Element> elements=rootElement.elements();
			for(Element e1:elements) {
				//获取class属性值，即类的全限定名
				String ClassName = e1.attributeValue("class");
				//通过全限定名获取类
					@SuppressWarnings("rawtypes")
					Class userclass=Class.forName(ClassName);
				//初始化类获取类对象
					obj = (WossModuleInit) userclass.newInstance();
				//判断对象所属类是否是实现WossModuleInit	
				if(obj instanceof WossModuleInit) {
						//将类的对象和对应的子节点名存入map集合如Gather和"com.briup.client.ClientImpl"存入map集合
						map.put(e1.getName(),obj);	
					}
				/**config.xml获取所有标签名ip等和标签的文本值ip的值等，存入到properties中，如（ip，ip的值）
				*例如clientImpl类中实现的init方法，获取ip和port	
				*public void init(Properties properties) throws Exception {
				*	String ip=properties.getProperty("ip");
				*   String port=(String)properties.getProperty("port");	用的时候在把port转换成int类型	}
			   */
					@SuppressWarnings("unchecked")
					List<Element> elements2=e1.elements();
					for(Element e2:elements2) {
						properties.put(e2.getName(), e2.getTextTrim());
					}	
					obj.init(properties);
			}
			//调用init方法，拿到如ip，port等的值
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//初始化GatherImple对象
	public GatherImpl getGather(){
		return ((GatherImpl)map.get("Gather"));
	}

	
	//初始化ServerImpl对象
	public ServerImpl getServer() {
		return ((ServerImpl)map.get("Server"));

	}
	//初始化ClientImpl对象
	public ClientImpl getClient() {
		return ((ClientImpl)map.get("Client"));
	}
	//初始化LogImpl对象
	public LogImpl getLog() {
		return ((LogImpl)map.get("Log"));
	}
	//初始化DBStoreImpl对象
	public DBStoreImpl getDbStore() {
		return ((DBStoreImpl)map.get("DBStore"));
	}

	public void init(Properties properties) throws Exception {
		// TODO Auto-generated method stub		
	}

}

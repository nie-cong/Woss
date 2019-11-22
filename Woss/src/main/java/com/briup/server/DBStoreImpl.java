package com.briup.server;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import com.briup.bean.Environment;

public class DBStoreImpl implements DBStore{
	//实现init方法，获取propertires内的所需的值
	String batchNum=null;
	public void init(Properties properties) throws Exception {
		batchNum=(String) properties.get("batchSize");
		
	}
	private static String driver;
	private static String url;
	private static String user;
	private static String password;
	static {
		try {
			Properties p = new Properties();
			InputStream in = DBStoreImpl.class.getResourceAsStream("/jdbc.properties");
			p.load(in);
			
			driver = p.getProperty("driver");
			url 		= p.getProperty("url");
			user 		= p.getProperty("username");
			password 	= p.getProperty("password");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//将list集合存入数据库
	public void DBinit(List<Environment> list){
		//控制批处理的次数
		int countNum=Integer.parseInt(batchNum);
				Connection conn=null;
				PreparedStatement ps = null;
				try {
				//加载驱动
					Class.forName(driver);
				//获取数据库连接接对象
					conn=DriverManager.getConnection(url,user,password);
					int parDay=0;//和day比较
					int count=0;//记录批处理
					for(int i=0;i<list.size();i++) {
						count++;
						//获取Calender实例，不能new是内部类
						Calendar cal=Calendar.getInstance();
						cal.setTime(list.get(i).getGather_date());
						int day=cal.get(Calendar.DAY_OF_MONTH);
//						String sql="insert into"+" e_detail_"+day+" values(?,?,?,?,?,?,?,?,?)";
						//判断只有day和前一天的day不相同直接执行已经预处理的sql语句
//						if(parDay!=day) {
//							//创建prepareStatement类型对象
//							ps=conn.prepareStatement(sql);
//							if(parDay==0) {	
//							}else {
//								ps.executeBatch();
//							    ps.close();	
//							}
//							
//						}
						//判断只有day和前一天的day不相同直接执行已经预处理的sql语句，sql语句表名也改变
						if(parDay!=day) {
							if(ps!=null) {
								ps.executeBatch();
							    ps.close();
							}
							String sql="insert into"+" e_detail_"+day+" values(?,?,?,?,?,?,?,?,?)";
							//创建prepareStatement类型对象
							ps=conn.prepareStatement(sql);
							
						}
						parDay=day;
						//执行SQL语句
						ps.setString(1, list.get(i).getName());
						ps.setString(2,list.get(i).getSmId());
						ps.setString(3, list.get(i).getQmId());
						ps.setString(4, list.get(i).getAddress());
						ps.setInt(5, list.get(i).getCount());
						ps.setString(6, list.get(i).getOrdernumber());
						ps.setInt(7, list.get(i).getStatus());
						ps.setInt(8,(int)list.get(i).getData());
						long time=cal.getTimeInMillis(); 
					    Date date=new Date(time); 
						ps.setDate(9,date);
						ps.addBatch();
//						parDay=day;
						if(count%countNum==0) {
							ps.executeBatch();
							ps.clearBatch();
						}
						}
					ps.executeBatch();
					ps.close();

				}
					catch (Exception e) {
					e.printStackTrace();
				}finally {
					if(conn!=null) {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					System.out.println("添加数据成功");
				}		
				
				
				
			}
}

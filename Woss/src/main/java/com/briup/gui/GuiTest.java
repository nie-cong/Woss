package com.briup.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.briup.bean.Environment;
import com.briup.client.GatherImpl;
import com.briup.server.DBStoreImpl;
import com.briup.util.ConfigurationImpl;

public class GuiTest extends JFrame{
	/**
	 * 界面，包括数据入库，删除数据，打印日志
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel jLabel;
	private JPanel jPanel;
	private JButton but1,but2,but3;
	public GuiTest() {
		setTitle("环境信息采集入库");
		jLabel=new JLabel("环境信息采集入库系统",JLabel.CENTER);
		jLabel.setFont(new Font("宋体", Font.PLAIN, 26));
		add(jLabel);
		
		jPanel=new JPanel();
		but1=new JButton("数据入库");
		but2=new JButton("打印日志");
		but3=new JButton("数据清空");
		jPanel.add(but1);
		jPanel.add(but2);
		jPanel.add(but3);
		add(jPanel);
		
		setLayout(new GridLayout(2, 1));
		setSize(920, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		but1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//通过ConfigurationImpl获取GatherImpl对象后获取list集合
				List<Environment> list=null;
					ConfigurationImpl impl1 = new ConfigurationImpl();
					GatherImpl gather = impl1.getGather();
					try {
						list = gather.gatherDate();
						System.out.println(list.size());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					//将list存入数据库
					ConfigurationImpl impl2 = new ConfigurationImpl();
					DBStoreImpl dbStore = impl2.getDbStore();
					dbStore.DBinit(list);
					//提示成功
					new SuccessGui("数据入库","数据入库成功，请前往查看");
			}
		});
		but2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				StringBuilder message=new StringBuilder();
				FileReader fr = null;
				BufferedReader br=null;
				//读取日志文件
				try {
					
					fr = new FileReader("D:\\A.briup.study\\sts.workspace\\Woss\\test.txt");
					br = new BufferedReader(fr);
					String str=null;
					while((str=br.readLine())!=null) {
						message.append(str+"\r\n");
					}
					//将message传给成功窗口的JLabel
					new SuccessGui("日志信息",message.toString());
						
				} catch (Exception e1) {
					e1.printStackTrace();
				}finally {
					if(fr!=null) {
						try {
							fr.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					if(br!=null) {
						try {
							br.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				
			}
		});
		
		but3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//清空数据库
				String driverClass = "oracle.jdbc.driver.OracleDriver";
				String url  = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
				String user = "briup";
				String password = "briup";
				
				Connection conn = null;
				Statement stmt = null;
				try {
					Class.forName(driverClass);
					conn=DriverManager.getConnection(url,user,password);
					for(int i=1;i<31;i++) {
						stmt=conn.createStatement();
						String sql="delete from e_detail_"+i;
						stmt.execute(sql);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}finally {
					if(stmt!=null) {
						try {
							stmt.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					if(conn!=null) {
						try {
							conn.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
				//提示成功
				new SuccessGui("数据删除","数据删除成功，请前往查看");
			}
		});
		
	}
	public static void main(String[] args) {
		new GuiTest();
	}
}

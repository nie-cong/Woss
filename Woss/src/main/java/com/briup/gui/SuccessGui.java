package com.briup.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SuccessGui {
public SuccessGui() {
		
	}
public  SuccessGui(String title,String msg) {
	JFrame jFrame2 = new JFrame();
	JPanel jPanel2 = new JPanel();
//	JScrollPane jPanel2 = new JScrollPane();
	jFrame2.setLayout(new FlowLayout());
	jPanel2.setLayout(new GridLayout(1, 1));
	jFrame2.setSize(600, 600);
	jFrame2.setLocationRelativeTo(null);
	jFrame2.setVisible(true);
	jFrame2.setTitle(title);
//	JLabel jLabel2 = new JLabel(msg);
//	jPanel2.add(jLabel2);
	
	JTextArea text = new JTextArea(msg);
	jFrame2.add(text);
	jFrame2.add(jPanel2);

}
}

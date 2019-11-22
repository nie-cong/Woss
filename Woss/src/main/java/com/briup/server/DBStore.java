package com.briup.server;

import java.util.List;

import com.briup.bean.Environment;
import com.briup.util.WossModuleInit;

public interface DBStore extends WossModuleInit {
		//执行sql语句存数据库
		public void DBinit(List<Environment> list);
}

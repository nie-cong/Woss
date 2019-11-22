package com.briup.server;

import java.util.List;

import com.briup.bean.Environment;
import com.briup.util.WossModuleInit;

public interface Server extends WossModuleInit {
	public List<Environment> EnvServer();
}

package com.briup.client;

import java.util.List;

import com.briup.bean.Environment;
import com.briup.util.WossModuleInit;

public interface Client extends WossModuleInit{
	
	public void EnvClient(List<Environment> list);
}

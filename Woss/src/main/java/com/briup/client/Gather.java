package com.briup.client;

import java.util.List;

import com.briup.bean.Environment;
import com.briup.util.WossModuleInit;

public interface Gather extends WossModuleInit{
	public List<Environment> gatherDate()throws Exception;
}

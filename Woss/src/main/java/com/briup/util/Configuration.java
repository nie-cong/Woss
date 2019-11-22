package com.briup.util;

import com.briup.client.ClientImpl;
import com.briup.client.GatherImpl;
import com.briup.server.DBStoreImpl;
import com.briup.server.ServerImpl;

public interface Configuration extends WossModuleInit{
	public ServerImpl getServer();
	public GatherImpl getGather();
	public ClientImpl getClient();
	public LogImpl getLog();
	public DBStoreImpl getDbStore();
}

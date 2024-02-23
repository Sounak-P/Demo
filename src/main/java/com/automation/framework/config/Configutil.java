package com.automation.framework.config;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.automation.framework.core.BaseClass;

public class Configutil extends BaseClass{

	public Configutil() throws IOException {
		
		// TODO Auto-generated constructor stub
	}
	
	 public static long Page_load_timeOut= 60;
	 public static long defaultWait=Long.parseLong(prop.getProperty("DefaultWait"));
	
	 public static long high= Long.parseLong(globalConfig.globalParaMap.get("wait.high"));
	 public static long medium=Long.parseLong(globalConfig.globalParaMap.get("wait.medium"));
	 public static long low= Long.parseLong(globalConfig.globalParaMap.get("wait.low"));
	 public static long Vrlow= Long.parseLong(globalConfig.globalParaMap.get("wait.vrlow"));
	
	 public final static Logger logger= LogManager.getLogger(Configutil.class);
	
}

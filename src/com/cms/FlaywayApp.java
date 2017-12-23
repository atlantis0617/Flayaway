package com.cms;

import java.util.Properties;

import org.flywaydb.core.Flyway;

public class FlaywayApp {
	 // 读取数据库配置参数  
	private static Properties config = new Properties();
	static{
		try {
			config.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("activerecord.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 // 执行数据库版本升级  
	public static void migration(){
		// Create the Flyway instance  
		Flyway fw = new Flyway();
		 // Point it to the database  
		fw.setDataSource(config.getProperty("url"),config.getProperty("username"),config.getProperty("password"));
//		fw.clean();
				fw.setBaselineOnMigrate(true);
//		fw.repair();
		fw.migrate();
	}
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		new FlaywayApp().migration();
	}
}

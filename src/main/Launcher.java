package main;
import java.io.File;

import org.apache.catalina.startup.Tomcat;


public class Launcher {

	public static void main(String[] args) throws Exception{

		String webappDirLocation = "WebContent";
		Tomcat tomcat = new Tomcat();
		
		String webPort = System.getenv("PORT");
		if(webPort==null || webPort.isEmpty()){
			webPort = "8888";
		}

		tomcat.setPort(Integer.valueOf(webPort));

		tomcat.addWebapp("/gestion", new File(webappDirLocation).getAbsolutePath());
		
		System.out.println("Configuration de l'application avec baseDir"+new File(webappDirLocation).getAbsolutePath());

		tomcat.start();

		tomcat.getServer().await();
	}

}

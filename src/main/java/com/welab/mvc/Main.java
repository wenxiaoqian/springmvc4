package com.welab.mvc;

import com.welab.mvc.tomcat.EmbededContextConfig;
import com.welab.mvc.tomcat.EmbededStandardJarScanner;
import com.welab.mvc.tomcat.TomcatUtil;
import com.welab.mvc.tomcat.WebXmlMountListener;
import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 *
 * @author hengyunabc
 *
 */
public class Main {

	public static void main(String[] args) throws ServletException, LifecycleException, IOException {

		String hostName = "localhost";
		int port = 8080;
		String contextPath = "";

		String tomcatBaseDir = TomcatUtil.createTempDir("tomcat", port).getAbsolutePath();
		String contextDocBase = TomcatUtil.createTempDir("tomcat-docBase", port).getAbsolutePath();

		Tomcat tomcat = new Tomcat();
		tomcat.setBaseDir(tomcatBaseDir);

		tomcat.setPort(port);
		tomcat.setHostname(hostName);

		Host host = tomcat.getHost();
		Context context = tomcat.addWebapp(host, contextPath, contextDocBase, new EmbededContextConfig());

		context.setJarScanner(new EmbededStandardJarScanner());

		ClassLoader classLoader = Main.class.getClassLoader();
		context.setParentClassLoader(classLoader);

		// context load WEB-INF/web.xml from classpath
		context.addLifecycleListener(new WebXmlMountListener());

		tomcat.start();
		tomcat.getServer().await();
	}
}

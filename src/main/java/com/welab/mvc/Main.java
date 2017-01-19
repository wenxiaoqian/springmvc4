package com.welab.mvc;

import com.welab.mvc.tomcat.EmbededContextConfig;
import com.welab.mvc.tomcat.EmbededStandardJarScanner;
import com.welab.mvc.tomcat.TomcatUtil;
import com.welab.mvc.tomcat.WebXmlMountListener;
import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 *
 * @author hengyunabc
 *
 */
public class Main {

	private static final String BANNER =
			"  .   ____          _            __ _ _\n" +
			" /\\\\ / ___'_ __ _ _(_)_ __  __ _ \\ \\ \\ \\\n" +
			"( ( )\\___ | '_ | '_| | '_ \\/ _` | \\ \\ \\ \\\n" +
			" \\\\/  ___)| |_)| | | | | || (_| |  ) ) ) )\n" +
			"  '  |____| .__|_| |_|_| |_\\__, | / / / /\n" +
			" =========|_|==============|___/=/_/_/_/\n" +
			" :: Spring Boot ::  (v2.0.0.BUILD-SNAPSHOT)\n";


	public static void main(String[] args) throws ServletException, LifecycleException, IOException {

		System.out.println(BANNER);

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

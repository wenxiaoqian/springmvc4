package com.welab.mvc.tomcat;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import javax.servlet.http.HttpServlet;
import java.util.Set;

/**
 * 
 * @author hengyunabc
 *
 */
@HandlesTypes({ HttpServlet.class })
public class CustomServletContainerInitializer implements ServletContainerInitializer {

	@Override
	public void onStartup(Set<Class<?>> set, ServletContext ctx) throws ServletException {
		System.out.println("CustomServletContainerInitializer.onStartup");
		if (set != null) {
			for (Class c : set) {
				System.out.println(c.getName());
			}
		}

	}

}

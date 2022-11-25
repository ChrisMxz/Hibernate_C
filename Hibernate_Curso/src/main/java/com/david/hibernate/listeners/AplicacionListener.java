package com.david.hibernate.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

import com.david.hibernate.dao.PaisesDAO;

@WebListener
public class AplicacionListener implements ServletContextListener, ServletRequestListener {

	private ServletContext servletContext;

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		sce.getServletContext().log("¡Inicializando la aplicacion!");
		PaisesDAO paises = new PaisesDAO();
		paises.listar();
		servletContext = sce.getServletContext();
		servletContext.setAttribute("paisesLista", paises.getPaises());
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		servletContext.log("¡Destruyendo la aplicacion!");
	}

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

package service;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.servlet.ServletContainer;

public class WebserviceRunner {

	private Server server;
	private int port;
	private String host;

	public WebserviceRunner(String host, int port) {
		this.server = new Server(port);
		this.host = host;
		this.port = port;
	}

	public void start() throws Exception {
		HandlerCollection handlers = new HandlerCollection();
		WebAppContext handler = new WebAppContext();

		handler.setContextPath("/");
		handler.setResourceBase("./");
		handler.setClassLoader(Thread.currentThread().getContextClassLoader());
		
		ServletHolder restServlet = handler.addServlet(ServletContainer.class,  "/*");
		restServlet.setInitOrder(0);
		restServlet.setInitParameter(ServerProperties.PROVIDER_PACKAGES,"resource");
		
		handlers.addHandler(handler);

		server.setHandler(handlers);
		server.start();
		System.out.println("API started... at 'http://" + getHostAndPort() + "'");
	}
	
	private String getHostAndPort() {
		return host + ":" + port;
	}
	
	public static void main(String[] args) throws Exception {
		new WebserviceRunner("localhost", 8315).start();
	}
}

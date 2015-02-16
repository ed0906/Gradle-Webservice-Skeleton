package service;

import java.io.File;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

public class WebserviceRunner {

	private Server server;
	private int port;
	private String host;

	public WebserviceRunner(String host, int port) {
		this.server = new Server(port);
		this.host = host;
		this.port = port;
	}

	public String getWebXml() {
		return "WEB-INF/web.xml";
	}

	public String getResourceBase() {
		return "./";
	}

	public void start() throws Exception {
		HandlerCollection handlers = new HandlerCollection();
		WebAppContext handler = new WebAppContext();
		handler.setResourceBase(getResourceBase());

		final String webXmlAddress = getWebXml();
		final File webXml = new File(webXmlAddress);
		if (!webXml.exists()) {
			throw new RuntimeException("web.xml not found at: " + webXml.getAbsolutePath());
		}

		handler.setDescriptor(webXmlAddress);
		handler.setContextPath("/");
		handler.setClassLoader(Thread.currentThread().getContextClassLoader());

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

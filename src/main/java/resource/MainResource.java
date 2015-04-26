package resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/rest")
public class MainResource {
	private Logger log = LoggerFactory.getLogger(MainResource.class);

	@GET
	@Path("/healthcheck")
	public Response healthCheck() {
		log.debug("Incoming Request: Healthcheck");
		return Response.ok().build();
	}

	@GET
	@Path("/talk/{var}")
	public Response command(@PathParam("var") String msg) {
		log.debug("Incoming Request: Talk");
		return Response.ok(msg).build();
	}
	
	@GET
	@Path("/talk")
	public Response commandQuery(@QueryParam("msg") String msg){
		log.debug("Incoming Request: Talk");
		return Response.ok(msg).build();
	}

}

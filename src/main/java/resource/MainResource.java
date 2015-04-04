package resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/rest")
public class MainResource {

	@GET
	@Path("/healthcheck")
	public Response healthCheck() {
		return Response.ok().build();
	}

	@GET
	@Path("/talk/{var}")
	public Response command(@PathParam("var") String msg) {
		return Response.ok(msg).build();
	}
	
	@GET
	@Path("/talk")
	public Response commandQuery(@QueryParam("msg") String msg){
		return Response.ok(msg).build();
	}

}

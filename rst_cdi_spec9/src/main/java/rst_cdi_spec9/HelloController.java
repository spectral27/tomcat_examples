package rst_cdi_spec9;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class HelloController {
	
	@Inject
	private HelloService helloService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public MessageModel getHello() {
		return helloService.getMessageModel();
	}

}

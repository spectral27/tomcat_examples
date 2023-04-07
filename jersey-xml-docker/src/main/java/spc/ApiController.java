package spc;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

@Path("/api")
public class ApiController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response api() {
        RecordObject record = new RecordObject();
        record.setIdentifier(UUID.randomUUID().toString());
        return Response.status(Response.Status.OK).entity(record).build();
    }

}

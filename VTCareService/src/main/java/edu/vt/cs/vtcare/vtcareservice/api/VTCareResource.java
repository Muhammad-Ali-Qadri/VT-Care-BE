package edu.vt.cs.vtcare.vtcareservice.api;

import edu.vt.cs.vtcare.vtcareservice.models.Provider;
import edu.vt.cs.vtcare.vtcareservice.services.LoginService;
import edu.vt.cs.vtcare.vtcareservice.services.ProviderService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.*;

@Path("/")
public class VTCareResource {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("providers")
    public Provider createProvider(Provider provider) throws Exception {
        try {
            ProviderService providerService = new ProviderService();
            return providerService.persistProvider(provider);
        } catch(Exception e) {
            throw new Exception("An exception occurred while persisting the provider details.");
        }
    }

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(String json) throws Exception {
        JsonObject o = new Gson().fromJson(json, JsonObject.class);
        try {
            String email = o.get("email").getAsString();
            String password = o.get("password").getAsString();
            boolean isProviderLogin = o.get("isProviderLogin").getAsBoolean();

            LoginService loginService = new LoginService();
            int id = loginService.login(email, password,
                    isProviderLogin);

            if(id > 0){
                return Response.ok(id).build();
            }
            else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch(Exception e) {
            throw new Exception("An exception occurred while logging in.");
        }
    }
}
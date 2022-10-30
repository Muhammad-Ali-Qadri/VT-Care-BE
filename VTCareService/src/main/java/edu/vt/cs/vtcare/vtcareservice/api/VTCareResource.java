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

    /**
     *Attempts to log-in the user and returns his ID.
     * @param loginInfo: JSON string containing email, password, and boolean
     *                 value representing whether the user is a provider or
     *                 a patient.
     * @return Response object, success with user ID, 0 for invalid credentials.
     * @throws Exception
     */
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(String loginInfo) throws Exception {
        JsonObject keyValueMapping = new Gson().fromJson(loginInfo,
                JsonObject.class);

        try {
            String email = keyValueMapping.get("email").getAsString();
            String password = keyValueMapping.get("password").getAsString();
            boolean isProviderLogin = keyValueMapping.get("isProviderLogin").getAsBoolean();

            LoginService loginService = new LoginService();
            int id = loginService.login(email, password,
                    isProviderLogin);

            return Response.ok(id).build();
        } catch(Exception e) {
            throw new Exception("An exception occurred while logging in.");
        }
    }
}
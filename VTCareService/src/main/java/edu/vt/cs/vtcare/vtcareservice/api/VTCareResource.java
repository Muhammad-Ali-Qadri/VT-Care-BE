package edu.vt.cs.vtcare.vtcareservice.api;

import edu.vt.cs.vtcare.vtcareservice.models.Provider;
import edu.vt.cs.vtcare.vtcareservice.services.ProviderService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.util.List;

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
            throw e;
        }
    }

    /**
     *
     * @param providerid to search for a specific provider
     * @return the provider
     * @throws Exception if cascaded from DAO.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("providers")
    public List<Provider> getProvider(String providerid) throws Exception {
        try {
            ProviderService providerService = new ProviderService();
            return providerService.getProviders();
        } catch(Exception e) {
            System.out.println("Issue getting provider(s)");
            throw e;
        }
    }
}
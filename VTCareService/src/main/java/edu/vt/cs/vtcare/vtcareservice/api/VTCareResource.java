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
            throw new Exception("An exception occurred while persisting the provider details.");
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
    public List<Provider> getProvider(@QueryParam("providerid") String providerid) throws Exception {
        try {
            ProviderService providerService = new ProviderService();
            return providerid != null ? providerService.getProvider(providerid) : providerService.getProviders();
        } catch(Exception e) {
            throw new Exception("An exception occurred while persisting the provider details.");
        }
    }
}
package edu.vt.cs.vtcare.vtcareservice.services;

import edu.vt.cs.vtcare.vtcareservice.dao.ProviderDao;
import edu.vt.cs.vtcare.vtcareservice.models.Provider;

import java.sql.SQLException;
import java.util.List;

/**
 * Holds the business logic for providers.
 */
public class ProviderService {

    private ProviderDao providerDao;

    public ProviderService() throws Exception {
        providerDao = new ProviderDao();
    }

    /**
     * Persists the given provider entity to database.
     *
     * @param provider the provider to be persisted.
     * @return Provider with the generated ID.
     */
    public Provider persistProvider(Provider provider) throws SQLException {
        long providerId = providerDao.persistProvider(provider);
        provider.setProviderId(providerId);
        return provider;
    }

    /**
     * @param providerId id of the provider to be found.
     * @return the matching Provider from the database.
     */
    public Provider findProviderById(long providerId) {
        return providerDao.findProviderById(providerId);
    }

    public List<Provider> getProviders() throws Exception {
        try {
            return providerDao.getProviders();
        } catch (Exception e) {
            System.out.println("Fetching providers list, had an issue.");
            throw e;
        }
    }

}

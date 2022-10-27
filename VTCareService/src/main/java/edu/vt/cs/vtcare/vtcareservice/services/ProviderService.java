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
     * Only persists provider info.
     * Need to persist associated
     * AppointmentSlots and Appointments separately.
     * @param provider
     * @return
     */
    public Provider persistProvider(Provider provider) throws SQLException {
        long providerId = providerDao.persistProvider(provider);
        provider.setProviderId(providerId);
        return provider;
    }

    public List<Provider> getProvider(String id) throws Exception{
        long num_id =Long.parseLong(id);
        try {
            return providerDao.getProvider(num_id);
        }
        catch(Exception e){
            System.out.println(e);
            throw new Exception();
        }
    }

    /**
     *
     * @return List of providers, empty list if no providers
     * @throws Exception if cascaded from DAO.
     */
    public List<Provider> getProviders() throws Exception{
        try {
            return providerDao.getProviders();
        }
        catch(Exception e){
            System.out.println(e);
            throw new Exception();
        }
    }
}

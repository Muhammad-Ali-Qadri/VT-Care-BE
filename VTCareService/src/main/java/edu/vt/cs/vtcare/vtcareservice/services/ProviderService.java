package edu.vt.cs.vtcare.vtcareservice.services;

import edu.vt.cs.vtcare.vtcareservice.dao.ProviderDao;
import edu.vt.cs.vtcare.vtcareservice.models.Provider;

import java.sql.SQLException;

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
}

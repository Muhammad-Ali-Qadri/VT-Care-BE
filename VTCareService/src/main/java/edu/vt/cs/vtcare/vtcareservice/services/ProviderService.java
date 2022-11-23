package edu.vt.cs.vtcare.vtcareservice.services;

import edu.vt.cs.vtcare.vtcareservice.dao.ProviderDao;
import edu.vt.cs.vtcare.vtcareservice.models.Appointment;
import edu.vt.cs.vtcare.vtcareservice.models.Provider;
import edu.vt.cs.vtcare.vtcareservice.models.AppointmentSlot;

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
            List<Provider> providerList = providerDao.getProviders();
            fetchAppointmentsAndSchedule(providerList);
            return providerList;
        } catch (Exception e) {
            System.out.println("Fetching providers list, had an issue.");
            throw e;
        }
    }

    /**
     *
     * Searches providers by the specified parameters.
     *
     * @param name
     * @param gender
     * @param specialization
     * @param location
     * @return
     */
    public List<Provider> searchProviders(String name, String gender, String specialization, String location) throws Exception {
        List<Provider> providerList = providerDao.searchProviders(name,  gender, specialization, location);
        fetchAppointmentsAndSchedule(providerList);
        return providerList;
    }

    /**
     * utility method to add appointments and availability schedule to provider list.
     * @param providerList
     * @throws Exception
     */
    private void fetchAppointmentsAndSchedule(List<Provider> providerList) throws Exception {
        AppointmentSlotService apptSlotService = new AppointmentSlotService();
        AppointmentService apptService = new AppointmentService();
        for(Provider doctor : providerList) {
            List<AppointmentSlot> proAvailSchedule = apptSlotService.getAppointmentList(doctor.getProviderId() );
            doctor.setAvailabilitySchedule(proAvailSchedule);
            doctor.setUpcomingAppointments( apptService.getAppointmentList( (int) doctor.getProviderId() ) );
        }
    }
}

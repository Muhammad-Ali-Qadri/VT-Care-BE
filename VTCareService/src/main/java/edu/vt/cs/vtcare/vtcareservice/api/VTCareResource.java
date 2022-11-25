package edu.vt.cs.vtcare.vtcareservice.api;

import edu.vt.cs.vtcare.vtcareservice.models.*;
import edu.vt.cs.vtcare.vtcareservice.services.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.*;

import java.util.List;


/**
 * Holds the business APIs for VTCare Application.
 */
@Path("/")
public class VTCareResource {
    /**
     * This API creates a provider entity and persists it into the database.
     * Generates an ID, appends it to the entity and returns.
     *
     * @param provider : provider entity being received from the call.
     * @return the provider entity just persisted with the generated provider Id
     * @throws Exception
     */
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
     * Finds and returns the provider with given ID.
     * @param providerId: Id of the provider to be searched.
     * @return Matching provider entity.
     * @throws Exception
     */
    @GET
    @Path("providers/{provider-id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Provider getProvider(@PathParam("provider-id")  long providerId) throws Exception {
            ProviderService providerService = new ProviderService();
            return providerService.findProviderById(providerId);
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

    /**
     *Schedule an appointment with the provided information.
     * @param appointment: the appointment object containing information for
     *                   booking.
     * @return Appointment Object with ids and URLs.
     * @throws Exception
     */
    @POST
    @Path("scheduleappointment")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response scheduleAppointment(Appointment appointment) throws Exception {
        try {
            AppointmentService appointmentService = new AppointmentService();
            appointment = appointmentService.scheduleAppointment(appointment);

            return Response.ok(appointment).build();
        } catch(Exception e) {
            throw new Exception("An exception occurred while scheduling an " +
                                "appointment");
        }
    }

    /**
     *
     * @return the providers
     * @throws Exception if cascaded from DAO.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("providers")
    public List<Provider> getProviders() throws Exception {
        try {
            ProviderService providerService = new ProviderService();
            return providerService.getProviders();
        } catch(Exception e) {
            System.out.println("Issue getting provider(s)");
            throw e;
        }
    }

    /**
     * This API creates a patient entity and persists it into the database.
     * Generates an ID, appends it to the entity and returns.
     *
     * @param patient : patient entity being received from the call.
     * @return the patient entity just persisted with the generated patient Id
     * @throws Exception
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("patients")
    public Patient createPatient (Patient patient) throws Exception {
        try {
            PatientService patientService = new PatientService();
            return patientService.persistPatient(patient);
        } catch(Exception e) {
            throw new Exception("An exception occurred while persisting the patient details.");
        }
    }

    /**
     *
     * Adds the supplied patient visit history to the patients history table.
     *
     * @param patientVisitHistory
     * @return
     * @throws Exception
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("patients/addHistory")
    public PatientVisitHistory addPatientHistory (PatientVisitHistory patientVisitHistory) throws Exception {
        try {
            PatientVisitHistoryService patienthistoryService = new PatientVisitHistoryService();
            return patienthistoryService.persistPatientHistory(patientVisitHistory);
        } catch(Exception e) {
            throw new Exception("An exception occurred while persisting the patient history.");
        }
    }

    /**
     * Finds and returns the patient with given ID.
     * @param patientId: Id of the patient to be searched.
     * @return Matching patient entity.
     * @throws Exception
     */
    @GET
    @Path("patients/{patient-id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Patient getPatient(@PathParam("patient-id")  long patientId) throws Exception {
        PatientService patientService = new PatientService();
        return patientService.findPatientById(patientId);
    }

    /**
     * Searches providers based on the provided parameters.
     *
     * @return the providers
     * @throws Exception if cascaded from DAO.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("searchproviders")
    public List<Provider> searchProviders(@QueryParam("name")  String name,
                                          @QueryParam("gender")  String gender,
                                          @QueryParam("specialization")  String specialization,
                                          @QueryParam("location")  String location) throws Exception {
        try {
            ProviderService providerService = new ProviderService();
            return providerService.searchProviders(name, gender, specialization, location);
        } catch(Exception e) {
            System.out.println("An exception occurred while searching provider(s)");
            throw e;
        }
    }

    /**
     * Update appointment status.
     * @param id id of appointment to change the status
     * @param status updated status
     *
     */
    @PATCH
    @Path("appointments/{appointment-id}/updatestatus")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAppointmentStatus(@PathParam("appointment-id") long id,
                                            AppointmentStatus status) throws Exception {
        try {
            AppointmentService appointmentService = new AppointmentService();
            appointmentService.updateAppointmentStatus(id, status);

            return Response.ok().build();
        } catch(Exception e) {
            throw new Exception("An exception occurred while updating " +
                                "appointment status.");
        }
    }

    /**
     * Gets a particular patients appointments.
     * @param patientId: Id of the patient to be searched.
     * @return Matching patient appointments.
     * @throws Exception
     */
    @GET
    @Path("patients/{patient-id}/appointments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatientAppointments(@PathParam("patient-id")
                                                   long patientId) throws Exception {
        AppointmentService apptService = new AppointmentService();
        return Response.ok(apptService.getAppointmentListByPatientId(patientId))
                .build();
    }

    /**
     * Gets a particular providers appointments.
     * @param providerId: Id of the patient to be searched.
     * @return Matching provider appointments.
     * @throws Exception
     */
    @GET
    @Path("providers/{provider-id}/appointments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProviderAppointments(@PathParam("provider-id")
                                                   int providerId) throws Exception {
        AppointmentService apptService = new AppointmentService();
        return Response.ok(apptService.getAppointmentList(providerId))
                .build();
    }

    /**
     * Updates appointment schedule
     * @param appointmentId: Id of the appointment to reschedule
     * @throws Exception
     */
    @PATCH
    @Path("appointments/{appointment-id}/reschedule")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProviderAppointments(@PathParam("appointment-id")
                                                    int appointmentId,
                                            Appointment appointment) throws Exception {

        try{
            AppointmentService apptService = new AppointmentService();
            appointment.setId(appointmentId);
            apptService.rescheduleAppointment(appointment);
            return Response.ok().build();
        }
        catch(Exception e) {
            throw new Exception("An exception occurred while rescheduling " +
                                "appointment.");
        }
    }
}
package edu.vt.cs.vtcare.vtcareservice.api;

import edu.vt.cs.vtcare.vtcareservice.models.Appointment;
import edu.vt.cs.vtcare.vtcareservice.models.Provider;
import edu.vt.cs.vtcare.vtcareservice.services.AppointmentService;
import edu.vt.cs.vtcare.vtcareservice.services.LoginService;
import edu.vt.cs.vtcare.vtcareservice.services.ProviderService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.*;


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
     * @return Appointment ID.
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
}
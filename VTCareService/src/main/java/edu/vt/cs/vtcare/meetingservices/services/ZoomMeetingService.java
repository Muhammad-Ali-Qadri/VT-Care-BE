package edu.vt.cs.vtcare.meetingservices.services;
import com.google.gson.Gson;
import edu.vt.cs.vtcare.meetingservices.MeetingService;
import edu.vt.cs.vtcare.meetingservices.models.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.TimeZone;

public class ZoomMeetingService implements MeetingService {

    private final String USER_ID = "QoFc3laeQ56PV1KqmOcBPg";
    private final String API_KEY = "9hgoSgtXS2SFqqLD1ufDSQ";
    private final String API_SECRET = "VMhN8rpXYLAvJPR1yYDJRpjDXIwzhRtNKlNu";
    private final String CHAT_HISTORY_TOKEN =
            "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJRb0ZjM2xhZVE1NlBWMUtxbU9jQlBnIn0.qSpx89Mwxqz8etEAHYMcK7idZr51Njj1rDpascJd4os";
    private final String SECRET_TOKEN = "_Mv7NB-WS6iYi8ebHJHVPQ";
    private final String VERIFICATION_TOKEN = "Zx2jEyrLQV2uf5l4j_Qkvw";
    private final String JWT_TOKEN =
            "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOm51bGwsImlzcyI6IjloZ29TZ3RYUzJTRnFxTEQxdWZEU1EiLCJleHAiOjE2NzExMjM2MDAsImlhdCI6MTY2NzE3MTU1N30.YR568Vw5JplHrhlISysBMBSbu3aoo9huLNSanVzk6Qc";

    private final HttpWebService httpService;

    public ZoomMeetingService() {
        this.httpService = new HttpWebService();
    }

    @Override
    public MeetingResponse createMeeting(MeetingDetails details) throws IOException {
        ZoomMeetingDTO zoomMeetingDTO = createZoomDTO(details);

        String apiUrl =
                String.format("https://api.zoom.us/v2/users/%s/meetings",
                        URLEncoder.encode(USER_ID, "UTF-8"));
        String authValue = String.format("Bearer %s", JWT_TOKEN);

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", authValue);

        ZoomMeetingResponse res = httpService.sendRequest(apiUrl,
                zoomMeetingDTO, headers, RequestType.POST,
                ZoomMeetingResponse.class);

        return new MeetingResponse(res.getId(), res.getJoin_url());
    }

    @Override
    public void rescheduleMeeting(String meetingId, String newDate) throws IOException {
        String apiUrl =
                String.format("https://api.zoom.us/v2/meetings/%s",
                        URLEncoder.encode(meetingId, "UTF-8"));
        String authValue = String.format("Bearer %s", JWT_TOKEN);

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", authValue);

        ZoomMeetingRescheduleDTO dto = new ZoomMeetingRescheduleDTO(newDate,
                TimeZone.getDefault().getID());

        httpService.sendRequest(apiUrl, dto, headers, RequestType.PATCH,
                Object.class);
    }

    @Override
    public void deleteMeeting(String meetingId) throws IOException {
        String apiUrl =
                String.format("https://api.zoom.us/v2/meetings/%s",
                        URLEncoder.encode(meetingId, "UTF-8"));
        String authValue = String.format("Bearer %s", JWT_TOKEN);

        HashMap<String, String> headers = new HashMap<>();
        headers.put("Authorization", authValue);

        httpService.sendRequest(apiUrl, null, headers, RequestType.DELETE,
                Object.class);
    }

    private ZoomMeetingDTO createZoomDTO(MeetingDetails details){
        ZoomMeetingSettings settings = new ZoomMeetingSettings();
        settings.addAttendant(details.getPatientEmail(), details.getPatientName());
        settings.addAttendant(details.getProviderEmail(), details.getProviderName());

        return new ZoomMeetingDTO(details.getAgenda(), details.getAgenda(),
                details.getDuration(), details.getPassword(),
                details.getScheduleDate(), TimeZone.getDefault().getID(),
                settings);
    }
}

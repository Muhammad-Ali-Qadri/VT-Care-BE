package edu.vt.cs.vtcare.meetingservices;

import edu.vt.cs.vtcare.meetingservices.models.MeetingDetails;
import edu.vt.cs.vtcare.meetingservices.models.MeetingResponse;

import java.io.IOException;

public interface MeetingService {
    MeetingResponse createMeeting(MeetingDetails details) throws IOException;

    void rescheduleMeeting(String meetingId, String newDate) throws IOException;
}

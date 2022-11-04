package edu.vt.cs.vtcare.meetingservices;

import edu.vt.cs.vtcare.meetingservices.models.MeetingDetails;

import java.io.IOException;

public interface MeetingService {
    String createMeeting(MeetingDetails details) throws IOException;
}

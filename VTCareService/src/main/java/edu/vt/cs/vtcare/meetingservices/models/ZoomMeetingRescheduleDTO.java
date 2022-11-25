package edu.vt.cs.vtcare.meetingservices.models;

public class ZoomMeetingRescheduleDTO {
    private String start_time;
    private String timezone;

    public ZoomMeetingRescheduleDTO(String start_time, String timezone) {
        this.start_time = start_time;
        this.timezone = timezone;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

}

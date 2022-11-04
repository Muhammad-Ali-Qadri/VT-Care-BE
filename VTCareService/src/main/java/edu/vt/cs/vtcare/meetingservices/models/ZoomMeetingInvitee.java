package edu.vt.cs.vtcare.meetingservices.models;

public class ZoomMeetingInvitee {
    public ZoomMeetingInvitee(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

}

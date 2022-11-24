package edu.vt.cs.vtcare.meetingservices.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ZoomMeetingSettings {
    private int approval_type;
    private int jbh_time;
    private int registration_type;

    private boolean allow_multiple_devices;
    private boolean alternative_hosts_email_notification;
    private boolean email_notification;
    private boolean host_video;
    private boolean meeting_authentication;
    private boolean join_before_host;
    private boolean registrants_confirmation_email;
    private boolean registrants_email_notification;
    private boolean show_share_button;
    private boolean host_save_video_order;
    private boolean alternative_host_update_polls;

    private boolean participant_video;
    private boolean waiting_room;


    private String authentication_domains;

    private final List<ZoomAuthenticationException> authentication_exception;
    private final List<ZoomMeetingInvitee> meeting_invitees;

    private final Set<String> hosts;

    public ZoomMeetingSettings() {
        this.approval_type = 2;
        this.jbh_time = 0;
        this.registration_type = 1;
        this.allow_multiple_devices = true;
        this.alternative_hosts_email_notification = true;
        this.email_notification = true;
        this.host_video = true;
        this.participant_video = true;
        this.meeting_authentication = false;
        this.join_before_host = true;
        this.registrants_confirmation_email = true;
        this.registrants_email_notification = true;
        this.show_share_button = true;
        this.host_save_video_order = true;
        this.alternative_host_update_polls = true;
        this.waiting_room = false;

        authentication_exception = new ArrayList<>();
        meeting_invitees = new ArrayList<>();
        hosts = new HashSet<>();
    }

    public void addAttendant(String email, String name){
        meeting_invitees.add(new ZoomMeetingInvitee(email));
        authentication_exception.add(new ZoomAuthenticationException(name,
                email));
        hosts.add(email.split("[@]")[1]);
    }

    public List<ZoomAuthenticationException> getAuthentication_exception() {
        return authentication_exception;
    }

    public List<ZoomMeetingInvitee> getMeeting_invitees() {
        return meeting_invitees;
    }

    public String getAuthentication_domains() {
        return String.join(",", hosts);
    }

    public int getApproval_type() {
        return approval_type;
    }

    public void setApproval_type(int approval_type) {
        this.approval_type = approval_type;
    }

    public int getJbh_time() {
        return jbh_time;
    }

    public void setJbh_time(int jbh_time) {
        this.jbh_time = jbh_time;
    }

    public int getRegistration_type() {
        return registration_type;
    }

    public void setRegistration_type(int registration_type) {
        this.registration_type = registration_type;
    }

    public boolean isAllow_multiple_devices() {
        return allow_multiple_devices;
    }

    public void setAllow_multiple_devices(boolean allow_multiple_devices) {
        this.allow_multiple_devices = allow_multiple_devices;
    }

    public boolean isAlternative_hosts_email_notification() {
        return alternative_hosts_email_notification;
    }

    public void setAlternative_hosts_email_notification(boolean alternative_hosts_email_notification) {
        this.alternative_hosts_email_notification = alternative_hosts_email_notification;
    }

    public boolean isEmail_notification() {
        return email_notification;
    }

    public void setEmail_notification(boolean email_notification) {
        this.email_notification = email_notification;
    }

    public boolean isHost_video() {
        return host_video;
    }

    public void setHost_video(boolean host_video) {
        this.host_video = host_video;
    }

    public boolean isParticipant_video() {
        return participant_video;
    }

    public void setParticipant_video(boolean participant_video) {
        this.participant_video = participant_video;
    }

    public boolean isMeeting_authentication() {
        return meeting_authentication;
    }

    public void setMeeting_authentication(boolean meeting_authentication) {
        this.meeting_authentication = meeting_authentication;
    }

    public boolean isJoin_before_host() {
        return join_before_host;
    }

    public void setJoin_before_host(boolean join_before_host) {
        this.join_before_host = join_before_host;
    }

    public boolean isRegistrants_confirmation_email() {
        return registrants_confirmation_email;
    }

    public void setRegistrants_confirmation_email(boolean registrants_confirmation_email) {
        this.registrants_confirmation_email = registrants_confirmation_email;
    }

    public boolean isRegistrants_email_notification() {
        return registrants_email_notification;
    }

    public void setRegistrants_email_notification(boolean registrants_email_notification) {
        this.registrants_email_notification = registrants_email_notification;
    }

    public boolean isShow_share_button() {
        return show_share_button;
    }

    public void setShow_share_button(boolean show_share_button) {
        this.show_share_button = show_share_button;
    }

    public boolean isHost_save_video_order() {
        return host_save_video_order;
    }

    public void setHost_save_video_order(boolean host_save_video_order) {
        this.host_save_video_order = host_save_video_order;
    }

    public boolean isAlternative_host_update_polls() {
        return alternative_host_update_polls;
    }

    public void setAlternative_host_update_polls(boolean alternative_host_update_polls) {
        this.alternative_host_update_polls = alternative_host_update_polls;
    }

    public boolean isWaiting_room() {
        return waiting_room;
    }

    public void setWaiting_room(boolean waiting_room) {
        this.waiting_room = waiting_room;
    }
}

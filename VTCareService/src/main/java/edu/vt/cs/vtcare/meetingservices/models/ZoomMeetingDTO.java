package edu.vt.cs.vtcare.meetingservices.models;

/***
 * See Documentation for object:
 * https://marketplace.zoom.us/docs/api-reference/zoom-api/methods/#operation/meetingCreate
 */
public class ZoomMeetingDTO {
    private String agenda;
    private String topic;
    private int duration;
    private String password;
    private String start_time;
    private String timezone;
    private ZoomMeetingSettings settings;

    public ZoomMeetingDTO(String agenda, String topic, int duration,
                          String password,
                          String start_time, String timezone,
                          ZoomMeetingSettings settings) {
        this.agenda = agenda;
        this.topic = topic;
        this.duration = duration;
        this.password = password;
        this.start_time = start_time;
        this.timezone = timezone;
        this.settings = settings;
    }


    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public ZoomMeetingSettings getSettings() {
        return settings;
    }

    public void setSettings(ZoomMeetingSettings settings) {
        this.settings = settings;
    }
}

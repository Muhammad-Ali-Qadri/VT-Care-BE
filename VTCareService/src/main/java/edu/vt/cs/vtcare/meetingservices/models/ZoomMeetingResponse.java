package edu.vt.cs.vtcare.meetingservices.models;

public class ZoomMeetingResponse {
    private long id;
    private String uuid;
    private String start_url;
    private String join_url;

    public ZoomMeetingResponse(long id, String uuid, String start_url,
                               String join_url) {
        this.id = id;
        this.uuid = uuid;
        this.start_url = start_url;
        this.join_url = join_url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getStart_url() {
        return start_url;
    }

    public void setStart_url(String start_url) {
        this.start_url = start_url;
    }

    public String getJoin_url() {
        return join_url;
    }

    public void setJoin_url(String join_url) {
        this.join_url = join_url;
    }
}

package edu.vt.cs.vtcare.meetingservices.models;

public class MeetingResponse {
    private String id;
    private String url;

    public MeetingResponse(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

package edu.vt.cs.vtcare.vtcareservice.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.sql.Time;

public class AppointmentSlot {
    private long appointmentSlotId;
    private long providerId;
    private int day;
    private String startTime;
    private String endTime;
    private int slotDuration;

    @JsonCreator
    public AppointmentSlot(@JsonProperty("providerId") long providerId,
                    @JsonProperty("day") int day,
                    @JsonProperty("startTime") String startTime,
                    @JsonProperty("endTime") String endTime,
                    @JsonProperty("slotDuration") int slotDuration) {
        this.setProviderId(providerId);
        this.setDay(day);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        this.setSlotDuration(slotDuration);
    }

    public AppointmentSlot(long id,
            long providerId,
            int day,
            String startTime,
            String endTime,
            int slotDuration) {
        this.appointmentSlotId = id;
        this.setProviderId(providerId);
        this.setDay(day);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        this.setSlotDuration(slotDuration);
    }

    public void setProviderId(long providerId) {
        this.providerId = providerId;
    }

    public void setDay(int day){
        this.day = day;
    }

    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    public void setEndTime(String endTime){
        this.endTime = endTime;
    }

    public void setSlotDuration(int slotDuration){
        this.slotDuration = slotDuration;
    }

    public long getProviderId() {
        return providerId;
    }

    public int getDay() {
        return day;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public int getSlotDuration() {
        return slotDuration;
    }

}

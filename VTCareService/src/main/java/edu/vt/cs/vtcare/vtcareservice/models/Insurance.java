package edu.vt.cs.vtcare.vtcareservice.models;

/**
 * Encapsulates the insurance to be added to patient's profile.
 */
public class Insurance {
    private long id;
    private long policyNo;
    private long networkId;

    public Insurance() {
    }

    public Insurance(long policyNo, long networkId) {
        this.policyNo = policyNo;
        this.networkId = networkId;
    }

    public Insurance(long id, long policyNo, long networkId) {
        this.id = id;
        this.policyNo = policyNo;
        this.networkId = networkId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(long policyNo) {
        this.policyNo = policyNo;
    }

    public long getNetworkId() {
        return networkId;
    }

    public void setNetworkId(long networkId) {
        this.networkId = networkId;
    }
}

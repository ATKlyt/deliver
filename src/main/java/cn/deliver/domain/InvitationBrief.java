package cn.deliver.domain;

public class InvitationBrief {
    private DriverRouteRelated driverRouteRelated;
    private String invitationStatus;

    public DriverRouteRelated getDriverRouteRelated() {
        return driverRouteRelated;
    }

    public void setDriverRouteRelated(DriverRouteRelated driverRouteRelated) {
        this.driverRouteRelated = driverRouteRelated;
    }

    public String getInvitationStatus() {
        return invitationStatus;
    }

    public void setInvitationStatus(String invitationStatus) {
        this.invitationStatus = invitationStatus;
    }
}

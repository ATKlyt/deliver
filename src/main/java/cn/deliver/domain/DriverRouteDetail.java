package cn.deliver.domain;

public class DriverRouteDetail {

    private DriverRoute driverRoute;
    private Area destinationArea;
    private Area originalArea;

    public DriverRoute getDriverRoute() {
        return driverRoute;
    }

    public void setDriverRoute(DriverRoute driverRoute) {
        this.driverRoute = driverRoute;
    }

    public Area getDestinationArea() {
        return destinationArea;
    }

    public void setDestinationArea(Area destinationArea) {
        this.destinationArea = destinationArea;
    }

    public Area getOriginalArea() {
        return originalArea;
    }

    public void setOriginalArea(Area originalArea) {
        this.originalArea = originalArea;
    }
}

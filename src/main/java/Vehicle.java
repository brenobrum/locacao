public class Vehicle {

    String licensePlate;
    Long renavamVehicle;
    String chassisVehicle;
    String colorVehicle;
    int carDoorNumber;
    int fuelType;
    long mileageVehicle;
    double locationValue;

    public Vehicle(String licensePlate, Long renavamVehicle, String chassisVehicle,
                   String colorVehicle, int carDoorNumber, int fuelType, long mileageVehicle,
                   double locationValue) {
        this.licensePlate = licensePlate;
        this.renavamVehicle = renavamVehicle;
        this.chassisVehicle = chassisVehicle;
        this.colorVehicle = colorVehicle;
        this.carDoorNumber = carDoorNumber;
        this.fuelType = fuelType;
        this.mileageVehicle = mileageVehicle;
        this.locationValue = locationValue;
    }

    public String vehicleConsultation(){
        return null;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Long getRenavamVehicle() {
        return renavamVehicle;
    }

    public void setRenavamVehicle(Long renavamVehicle) {
        this.renavamVehicle = renavamVehicle;
    }

    public String getChassisVehicle() {
        return chassisVehicle;
    }

    public void setChassisVehicle(String chassisVehicle) {
        this.chassisVehicle = chassisVehicle;
    }

    public String getColorVehicle() {
        return colorVehicle;
    }

    public void setColorVehicle(String colorVehicle) {
        this.colorVehicle = colorVehicle;
    }

    public int getCarDoorNumber() {
        return carDoorNumber;
    }

    public void setCarDoorNumber(int carDoorNumber) {
        this.carDoorNumber = carDoorNumber;
    }

    public int getFuelType() {
        return fuelType;
    }

    public void setFuelType(int fuelType) {
        this.fuelType = fuelType;
    }

    public long getMileageVehicle() {
        return mileageVehicle;
    }

    public void setMileageVehicle(long mileageVehicle) {
        this.mileageVehicle = mileageVehicle;
    }

    public double getLocationValue() {
        return locationValue;
    }

    public void setLocationValue(double locationValue) {
        this.locationValue = locationValue;
    }

}

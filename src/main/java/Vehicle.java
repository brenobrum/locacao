public class Vehicle {

    String plate;
    Long renavamVehicle;
    String chassisVehicle;
    String colorVehicle;
    int carDoorNumber;
    int fuelType;
    long vehicleMileage;
    double locationValue;

    public Vehicle(String plate, Long renavamVehicle, String chassisVehicle,
                   String colorVehicle, int carDoorNumber, int fuelType, long vehicleMileage,
                   double locationValue) {
        this.plate = plate;
        this.renavamVehicle = renavamVehicle;
        this.chassisVehicle = chassisVehicle;
        this.colorVehicle = colorVehicle;
        this.carDoorNumber = carDoorNumber;
        this.fuelType = fuelType;
        this.vehicleMileage = vehicleMileage;
        this.locationValue = locationValue;
    }

    public String vehicleConsultation(){
        return null;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String licensePlate) {
        this.plate = licensePlate;
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

    public long getVehicleMileage() {
        return vehicleMileage;
    }

    public void setVehicleMileage(long vehicleMileage) {
        this.vehicleMileage = vehicleMileage;
    }

    public double getLocationValue() {
        return locationValue;
    }

    public void setLocationValue(double locationValue) {
        this.locationValue = locationValue;
    }
}

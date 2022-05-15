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
}

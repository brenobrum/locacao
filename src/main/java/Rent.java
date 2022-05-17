import java.util.Date;

public class Rent {
    private final Date rentDate;
    private Date returnDate;
    private Date foreseenReturnDate;
    private Long rentMileage;
    private Long returnMileage;
    private double bailValue;
    private double rentValue;
    private Boolean returned;
    private final String rentVehiclePlate;
    private String clientCPF;
    private final String rentPurpose;
    private final String destination;


    public Rent(Date rentDate, Date returnDate, Date foreseenReturnDate, Long rentMileage, Long returnMileage, double bailValue,
                double rentValue, Boolean returned, String rentVehiclePlate, String clientCPF, String rentPurpose, String destination) {
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.foreseenReturnDate = foreseenReturnDate;
        this.rentMileage = rentMileage;
        this.returnMileage = returnMileage;
        this.bailValue = bailValue;
        this.rentValue = rentValue;
        this.returned = returned;
        this.rentVehiclePlate = rentVehiclePlate;
        this.clientCPF = clientCPF;
        this.rentPurpose = rentPurpose;
        this.destination = destination;
    }

    public String getRentPurpose() {
        return rentPurpose;
    }

    public String getDestination() {
        return destination;
    }

    public String getVehicle() {
        return rentVehiclePlate;
    }

    public String getClientCPF() {
        return clientCPF;
    }

    public void setClientCPF(String clientCPF) {
        this.clientCPF = clientCPF;
    }

    public Date getForeseenReturnDate() {
        return foreseenReturnDate;
    }

    public void setForeseenReturnDate(Date foreseenReturnDate) {
        this.foreseenReturnDate = foreseenReturnDate;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Long getRentMileage() {
        return rentMileage;
    }

    public void setRentMileage(Long rentMileage) {
        this.rentMileage = rentMileage;
    }

    public Long getReturnMileage() {
        return returnMileage;
    }

    public void setReturnMileage(Long returnMileage) {
        this.returnMileage = returnMileage;
    }

    public double getBailValue() {
        return bailValue;
    }

    public void setBailValue(double bailValue) {
        this.bailValue = bailValue;
    }

    public double getRentValue() {
        return rentValue;
    }

    public void setRentValue(double rentValue) {
        this.rentValue = rentValue;
    }

    public Boolean isReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }
}

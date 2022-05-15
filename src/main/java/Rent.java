import java.sql.Time;
import java.util.Date;

public class Rent {
    private Date rentDate;
    private Time rentTime;
    private Date returnDate;
    private Time returnTime;
    private Long rentMileage;
    private Long returnMileage;
    private double bailValue;
    private String rentValue;
    private Boolean returned;

    public Rent(Date rentDate, Time rentTime) {
        this.rentDate = rentDate;
        this.rentTime = rentTime;
        this.returned = false;
    }

    public Date getRentDate() {
        return rentDate;
    }

    public Time getRentTime() {
        return rentTime;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Time getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(Time returnTime) {
        this.returnTime = returnTime;
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

    public String getRentValue() {
        return rentValue;
    }

    public void setRentValue(String rentValue) {
        this.rentValue = rentValue;
    }

    public Boolean getReturned() {
        return returned;
    }

    public void setReturned(Boolean returned) {
        this.returned = returned;
    }
}

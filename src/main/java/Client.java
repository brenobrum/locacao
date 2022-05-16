import java.util.ArrayList;

public class Client {
    private String cpf;
    private String name;
    private String address;
    private String phone;
    private String email;
    private ArrayList<Rent> rents;

    public Client(String cpf, String name, String address, String phone, String email) {
        this.cpf = cpf;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public ArrayList<Rent> getRents() {
        return rents;
    }

    public void addRent(Rent rent) {
        this.rents.add(rent);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

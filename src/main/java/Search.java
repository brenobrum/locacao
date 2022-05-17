import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Search {

    public static void main(String[] args) {
        ArrayList<Vehicle> vehicleList = getAvailableVehiclesList();
        for (Vehicle vehicle : vehicleList) {
            System.out.println(vehicle.getPlate());
        }
        System.out.println("A");
    }

    public static ArrayList<Model> getModelList() {
        File file = new File("src\\main\\java\\db\\models.json");
        ArrayList<Model> modelList = new ArrayList<>();
        try {
            JsonElement jsonElement = JsonParser.parseReader(new FileReader(file));
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement object : jsonArray) {
                JsonObject model = object.getAsJsonObject();
                String modelDescription = model.get("modelDescription").getAsString();
                String brand = model.get("brand").getAsString();
                modelList.add(new Model(modelDescription, brand));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo \"models.json\" não foi encontrado.");
        }
        return modelList;
    }

    public static ArrayList<Brand> getBrandList() {
        File file = new File("src\\main\\java\\db\\brands.json");
        ArrayList<Brand> brandList = new ArrayList<>();
        try {
            JsonElement jsonElement = JsonParser.parseReader(new FileReader(file));
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement object : jsonArray) {
                JsonObject brand = object.getAsJsonObject();
                String brandDescription = brand.get("brandDescription").getAsString();
                ArrayList<Model> models = new ArrayList<>();
                JsonArray jsonModels = brand.get("models").getAsJsonArray();
                for (JsonElement modelObject : jsonModels) {
                    JsonObject model = modelObject.getAsJsonObject();
                    String modelDescription = model.get("modelDescription").getAsString();
                    models.add(new Model(modelDescription, brandDescription));
                }
                brandList.add(new Brand(brandDescription, models));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo \"brands.json\" não foi encontrado.");
        }
        return brandList;
    }

    public static ArrayList<Client> getClientList() {
        File file = new File("src\\main\\java\\db\\clients.json");
        ArrayList<Client> clientList = new ArrayList<>();
        try {
            JsonElement jsonElement = JsonParser.parseReader(new FileReader(file));
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement object : jsonArray) {
                JsonObject client = object.getAsJsonObject();
                String name = client.get("name").getAsString();
                String cpf = client.get("cpf").getAsString();
                String address = client.get("address").getAsString();
                String phone = client.get("phone").getAsString();
                String email = client.get("email").getAsString();

                clientList.add(new Client(cpf, name, address, phone, email));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo \"clients.json\" nao foi encontrado.");
        }
        return clientList;
    }

    public static ArrayList<Vehicle> getVehicleList() {
        File file = new File("src\\main\\java\\db\\vehicles.json");
        ArrayList<Vehicle> vehiclesList = new ArrayList<>();
        try {
            JsonElement jsonElement = JsonParser.parseReader(new FileReader(file));
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement object : jsonArray) {
                JsonObject client = object.getAsJsonObject();
                String plate = client.get("plate").getAsString();
                Long renavamVehicle = client.get("renavamVehicle").getAsLong();
                String chassisVehicle = client.get("chassisVehicle").getAsString();
                String colorVehicle = client.get("colorVehicle").getAsString();
                int carDoorNumber = client.get("carDoorNumber").getAsInt();
                int fuelType = client.get("fuelType").getAsInt();
                long vehicleMileage = client.get("vehicleMileage").getAsLong();
                double locationValue = client.get("locationValue").getAsDouble();

                vehiclesList.add(new Vehicle(plate, renavamVehicle, chassisVehicle,
                        colorVehicle, carDoorNumber, fuelType, vehicleMileage, locationValue));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo \"vehicles.json\" nao foi encontrado.");
        }
        return vehiclesList;
    }

    public static ArrayList<Rent> getRentsList() {
        File file = new File("src\\main\\java\\db\\rents.json");
        ArrayList<Rent> rentList = new ArrayList<>();
        try {
            JsonElement jsonElement = JsonParser.parseReader(new FileReader(file));
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement object : jsonArray) {
                JsonObject rent = object.getAsJsonObject();
                Date rentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(rent.get("rentDate").getAsString());
                Date returnDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(rent.get("returnDate").getAsString());
                Date foreseenReturnDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(rent.get("foreseenReturnDate").getAsString());
                Long rentMileage = rent.get("rentMileage").getAsLong();
                Long returnMileage = rent.get("rentMileage").getAsLong();
                double bailValue = rent.get("rentMileage").getAsDouble();
                double rentValue = rent.get("rentValue").getAsDouble();
                Boolean returned = rent.get("returned").getAsBoolean();
                String rentVehiclePlate = rent.get("rentVehiclePlate").getAsString();
                String clientCPF = rent.get("clientCPF").getAsString();
                String rentPurpose = rent.get("rentPurpose").getAsString();
                String destination = rent.get("destination").getAsString();


                rentList.add(new Rent(rentDate, returnDate, foreseenReturnDate, rentMileage, returnMileage, bailValue,
                        rentValue, returned, rentVehiclePlate, clientCPF, rentPurpose, destination));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo \"vehicles.json\" nao foi encontrado.");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return rentList;
    }

    public static ArrayList<Vehicle> getAvailableVehiclesList() {
        ArrayList<Rent> rentList = getRentsList();
        ArrayList<Vehicle> vehiclesList = getVehicleList();

        for (Rent rent : rentList) {
            vehiclesList.removeIf(vehicle -> rent.getVehicle().equals(vehicle.getPlate()) && !rent.isReturned());
        }
        return vehiclesList;
    }

    public static ArrayList<Vehicle> getRentVehiclesByClient(Client client) {
        ArrayList<Rent> rentList = getRentsList();
        ArrayList<Vehicle> vehiclesList = getVehicleList();
        ArrayList<Vehicle> rentVehiclesByClient = new ArrayList<>();

        for (Rent rent : rentList) {
            for (Vehicle vehicle : vehiclesList) {
                if (rent.getClientCPF().equals(client.getCpf()) && rent.getVehicle().equals(vehicle.getPlate()) && !rent.isReturned()) {
                    rentVehiclesByClient.add(vehicle);
                }
            }
        }
        return rentVehiclesByClient;
    }

    public static ArrayList<Rent> getRentByClient(Client client) {
        ArrayList<Rent> rentList = getRentsList();
        ArrayList<Vehicle> vehiclesList = getVehicleList();
        ArrayList<Rent> rentByClient = new ArrayList<>();

        for (Rent rent : rentList) {
            for (Vehicle vehicle : vehiclesList) {
                if (rent.getClientCPF().equals(client.getCpf()) && rent.getVehicle().equals(vehicle.getPlate()) && !rent.isReturned()) {
                    rentByClient.add(rent);
                }
            }
        }
        return rentByClient;
    }
}

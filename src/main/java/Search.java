import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Search {

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
            System.out.println("Arquivo \"clients.json\" não foi encontrado.");
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
                long mileageVehicle = client.get("mileageVehicle").getAsLong();
                double locationValue = client.get("locationValue").getAsDouble();

                vehiclesList.add(new Vehicle(plate, renavamVehicle, chassisVehicle,
                        colorVehicle, carDoorNumber, fuelType, mileageVehicle, locationValue));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo \"vehicles.json\" não foi encontrado.");
        }
        return vehiclesList;
    }
}

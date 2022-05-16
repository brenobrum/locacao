import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    public static ArrayList<Client> getClientList(){
        File file = new File("src\\main\\java\\db\\clients.json");
        ArrayList<Client> clientList = new ArrayList<>();
        try {
            JsonElement jsonElement = JsonParser.parseReader(new FileReader(file));
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement object : jsonArray){
                JsonObject client = object.getAsJsonObject();
                String name = client.get("name").getAsString();
                String cpf = client.get("cpf").getAsString();
                String address = client.get("address").getAsString();
                String phone = client.get("phone").getAsString();
                String email = client.get("email").getAsString();

                clientList.add(new Client(cpf, name, address, phone, email));
            }
        }catch (FileNotFoundException e){
            System.out.println("Arquivo \"words.json\" não foi encontrado.");
        }
        return clientList;
    }

    public static ArrayList<Vehicle> getVehicleList(){
        File file = new File("src\\main\\java\\db\\vehicles.json");
        ArrayList<Vehicle> vehiclesList = new ArrayList<>();
        try {
            JsonElement jsonElement = JsonParser.parseReader(new FileReader(file));
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement object : jsonArray){
                JsonObject client = object.getAsJsonObject();
                String name = client.get("name").getAsString();
                String cpf = client.get("cpf").getAsString();
                String address = client.get("address").getAsString();
                String phone = client.get("phone").getAsString();
                String email = client.get("email").getAsString();

                vehiclesList.add(new Vehicle(cpf, name, address, phone, email));
            }
        }catch (FileNotFoundException e){
            System.out.println("Arquivo \"words.json\" não foi encontrado.");
        }
        return vehiclesList;
    }

    public static void main(String[] args) {
        ArrayList<Client> clientList = getClientList();

        System.out.println("Lista de Clientes:");
        for (Client client : clientList){
            System.out.println(client.getName());
        }



    }
}

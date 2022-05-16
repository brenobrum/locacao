import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Model {
    String modelDescription;
    Brand brand;

    public Model(String modelDescription) {
        this.modelDescription = modelDescription;
    }

    public String getModelDescription() {
        return modelDescription;
    }

    public void setModelDescription(String modelDescription) {
        this.modelDescription = modelDescription;
    }

//    public Model getModels() {
//        File file = new File("src\\main\\java\\db\\clients.json");
//        ArrayList<Model> modelList = new ArrayList<>();
//        try {
//            JsonElement jsonElement = JsonParser.parseReader(new FileReader(file));
//            JsonArray jsonArray = jsonElement.getAsJsonArray();
//            for (JsonElement object : jsonArray) {
//                JsonObject client = object.getAsJsonObject();
//                String name = client.get("name").getAsString();
//                String cpf = client.get("cpf").getAsString();
//                String address = client.get("address").getAsString();
//                String phone = client.get("phone").getAsString();
//                String email = client.get("email").getAsString();
//
//                clientList.add(new Client(cpf, name, address, phone, email));
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("Arquivo \"clients.json\" não foi encontrado.");
//        }
//        return clientList;
//    }
//        return
//}
}

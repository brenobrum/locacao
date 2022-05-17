import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.*;

public class Main {

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

    public static void printClientList() {
        ArrayList<Client> clientList = getClientList();
        System.out.println("Lista de veiculos:");

        for (int i = 0; i < clientList.size(); i++) {
            System.out.println(i + " - " + clientList.get(i).getName());
        }
    }

    public static void printVehicleList() {
        ArrayList<Vehicle> vehicleList = getVehicleList();
        System.out.println("Lista de Clientes:");

        for (int i = 0; i < vehicleList.size(); i++) {
            System.out.println(i + " - " + vehicleList.get(i).getPlate());
        }
    }

    public static void createClient() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o CPF do cliente:");
        String cpf = scan.nextLine();
        System.out.println("Digite o nome do cliente:");
        String name = scan.nextLine();
        System.out.println("Digite o endereco do cliente:");
        String address = scan.nextLine();
        System.out.println("Digite o telefone do cliente:");
        String phone = scan.nextLine();
        System.out.println("Digite o e-mail do cliente:");
        String email = scan.nextLine();

        addToJson(cpf, name, address, phone, email);
    }

    public static void printClientDetails(Client client) {
        System.out.println("CPF do cliente: " + client.getCpf());
        System.out.println("Nome do cliente: " + client.getName());
        System.out.println("Endereco do cliente: " + client.getAddress());
        System.out.println("Telefone do cliente: " + client.getPhone());
        System.out.println("Email do cliente: " + client.getEmail());
    }

    public static void editClient(int index) {
        ArrayList<Client> clientList = getClientList();
        Client client = clientList.get(index);
        Scanner scan = new Scanner(System.in);
        boolean isValid = true;
        while (isValid) {
            printClientDetails(client);
            System.out.println("O que deseja editar? (Para concluir digite X)\n");
            String option = scan.nextLine();
            switch (option.toUpperCase()) {
                case "CPF" -> {
                    System.out.println("Digite o novo CPF do cliente: ");
                    String cpf = scan.nextLine();
                    clientList.get(index).setCpf(cpf);
                }
                case "NOME" -> {
                    System.out.println("Digite o novo nome do cliente: ");
                    String name = scan.nextLine();
                    clientList.get(index).setName(name);
                }
                case "ENDERECO" -> {
                    System.out.println("Digite o novo endereco do cliente: ");
                    String address = scan.nextLine();
                    clientList.get(index).setAddress(address);
                }
                case "TELEFONE" -> {
                    System.out.println("Digite o novo telefone do cliente: ");
                    String phone = scan.nextLine();
                    clientList.get(index).setPhone(phone);
                }
                case "EMAIL" -> {
                    System.out.println("Digite o novo e-mail do cliente: ");
                    String email = scan.nextLine();
                    clientList.get(index).setEmail(email);
                }
                case "X" -> {
                    System.out.println("Edição concluida.");
                    isValid = false;
                }
                default -> System.out.println("Opção inválida.");
            }
            editClientList(clientList);
        }
    }


    public static void addToJson(String cpf, String name, String address, String phone, String email) {
        Client client = new Client(cpf, name, address, phone, email);
        ArrayList<Client> clientList = getClientList();
        clientList.add(client);
        editClientList(clientList);
    }

    public static void editClientList(ArrayList<Client> clientList) {
        Gson gson = new Gson();
        String json = gson.toJson(clientList);
        try {
            BufferedWriter saida = new BufferedWriter(new FileWriter("src\\main\\java\\db\\clients.json"));
            saida.write(json);
            saida.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao escrever no arquivo");
        }
    }

    public static void main(String[] args) {

        System.out.println("1 - Locar veiculo");
        System.out.println("2 - Devolver veiculo");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        if (option == 1) {
            System.out.println("1 - Cadastrar cliente:");
            System.out.println("2 - Editar dados de cliente:");

            boolean valid = true;
            while (valid) {
                option = scanner.nextInt();
                if (option == 1) {
                    createClient();
                    System.out.println("Cliente cadastrado com sucesso!");
                    valid = false;
                } else if (option == 2) {
                    printClientList();
                    System.out.println("Qual cliente deseja editar?");
                    option = scanner.nextInt();
                    editClient(option);
                    System.out.println("Cliente editado com sucesso!");
                    valid = false;
                } else {
                    System.out.println("Opcao invalida!");
                }
            }

            printVehicleList();
        } else if (option == 2) {
            System.out.println("Devolver veiculo:");

        } else {
            System.out.println("Opcao invalida!");
        }
    }
}

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.google.gson.*;

public class Main {



    public static void printClientList() {
        ArrayList<Client> clientList = Search.getClientList();
        System.out.println("Lista de clientes:");

        for (int i = 0; i < clientList.size(); i++) {
            System.out.println(i + " - " + clientList.get(i).getName());
        }
    }

    public static void printVehicleList() {
        ArrayList<Vehicle> vehicleList = Search.getVehicleList();
        System.out.println("Lista de veiculos:");

        for (int i = 0; i < vehicleList.size(); i++) {
            System.out.println(i + " - " + vehicleList.get(i).getPlate());
        }
    }

    public static void printAvailableVehiclesList() {
        ArrayList<Vehicle> vehicleList = Search.getAvailableVehiclesList();
        System.out.println("Lista de veiculos disponiveis:");

        for (int i = 0; i < vehicleList.size(); i++) {
            System.out.println(i + " - " + vehicleList.get(i).getPlate());
        }
    }

    public static Client createClient() {
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

        return addClientToJson(cpf, name, address, phone, email);
    }

    public static Rent createRent(Vehicle vehicle, Client client) throws ParseException {
        Scanner scan = new Scanner(System.in);
        Date rentDate = new Date();
        long rentMileage = vehicle.getVehicleMileage();

        System.out.println("Em que data fara a devolução: Ex(25/11/2022 12:00)");
        String foreseenReturnDateS = scan.nextLine();
        Date foreseenReturnDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(foreseenReturnDateS);

        System.out.println("Valor da caucao:");
        double bailValue = scan.nextDouble();

        System.out.println("Valor da locação:");
        double rentValue = scan.nextDouble();

        System.out.println("Finalidade da locacao:");
        String rentPurpose = scan.nextLine();

        System.out.println("Por onde andara com o veiculo:");
        String destination = scan.nextLine();

        Rent rent = new Rent(rentDate, rentDate, foreseenReturnDate, rentMileage, 0L,
                bailValue, rentValue, false, vehicle.getPlate(), client.getCpf(), rentPurpose, destination);

        addRentToJson(rent);

        return rent;
    }

    public static void printClientDetails(Client client) {
        System.out.println("CPF do cliente: " + client.getCpf());
        System.out.println("Nome do cliente: " + client.getName());
        System.out.println("Endereco do cliente: " + client.getAddress());
        System.out.println("Telefone do cliente: " + client.getPhone());
        System.out.println("Email do cliente: " + client.getEmail());
    }

    public static Client editClient(int index) {
        ArrayList<Client> clientList = Search.getClientList();
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
            editJsonList(clientList, "src\\main\\java\\db\\clients.json");
        }
        return client;
    }


    public static Client addClientToJson(String cpf, String name, String address, String phone, String email) {
        Client client = new Client(cpf, name, address, phone, email);
        ArrayList<Client> clientList = Search.getClientList();
        clientList.add(client);
        editJsonList(clientList, "src\\main\\java\\db\\clients.json");

        return client;
    }

    public static void addRentToJson(Rent rent) {
        ArrayList<Rent> rentList = Search.getRentsList();
        rentList.add(rent);
        editJsonList(rentList, "src\\main\\java\\db\\rents.json");
    }

    public static void editJsonList(Object list, String src) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm").create();
        String json = gson.toJson(list);
        try {
            BufferedWriter saida = new BufferedWriter(new FileWriter(src));
            saida.write(json);
            saida.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erro ao escrever no arquivo");
        }
    }

    public static void main(String[] args) throws ParseException {

        System.out.println("1 - Locar veiculo");
        System.out.println("2 - Devolver veiculo");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        if (option == 1) {
            System.out.println("1 - Cadastrar cliente:");
            System.out.println("2 - Editar dados de cliente:");

            boolean valid = true;
            Client client = null;
            while (valid) {
                option = scanner.nextInt();
                if (option == 1) {
                    client = createClient();
                    System.out.println("Cliente cadastrado com sucesso!");
                    valid = false;
                } else if (option == 2) {
                    printClientList();
                    System.out.println("Qual cliente deseja editar?");
                    option = scanner.nextInt();
                    client = editClient(option);
                    valid = false;
                } else {
                    System.out.println("Opcao invalida!");
                }
            }
            System.out.println();
            printAvailableVehiclesList();

            System.out.println("Qual veiculo deseja locar?");
            option = scanner.nextInt();
            Vehicle vehicle = Search.getVehicleList().get(option);

            Rent rent = createRent(vehicle, client);

            System.out.println("Veículo " + vehicle.getPlate() + " Alugado.");
            System.out.println("O veículo deve ser devolvido em: " + rent.getReturnDate() + ".");
        } else if (option == 2) {
            System.out.println("Devolver veiculo:");

        } else {
            System.out.println("Opcao invalida!");
        }
    }
}

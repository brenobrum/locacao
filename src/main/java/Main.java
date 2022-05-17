import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.google.gson.*;

public class Main {

    public static void rentACar(Scanner scanner) throws ParseException {
        int option;
        boolean valid = true;
        Client client = null;
        while (valid) {
            System.out.println("\n1 - Cadastrar cliente: ");
            System.out.println("2 - Editar dados de cliente: ");
            System.out.println("3 - Selecionar cliente existente: ");
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
            }else if (option == 3) {
                printClientList();
                System.out.println("Qual cliente deseja selecionar?");
                option = scanner.nextInt();
                client = selectClient(option);
                System.out.println("Selecionado " + client.getName());
                valid = false;
            }else if (option == -1){
                break;
            } else {
                System.out.println("Opcao invalida! Escolha outra opcao.");
            }
        }
        System.out.println();
        printAvailableVehiclesList();

        System.out.println("Qual veiculo deseja locar?");
        option = scanner.nextInt();
        Vehicle vehicle = Search.getAvailableVehiclesList().get(option);

        Rent rent = createRent(vehicle, client);

        System.out.println("Veiculo " + vehicle.getPlate() + " Alugado.");
        System.out.println("O veículo deve ser devolvido em: " + rent.getReturnDate() + ".");
    }

    public static void returnACar(Scanner scanner) {
        boolean valid = true;
        Client client = null;
        while (valid) {
            System.out.println("Qual cliente devolvera o veiculo?");
            printClientList();
            int clientId = scanner.nextInt();
            client = selectClient(clientId);

            if (Search.getRentByClient(client).isEmpty()) {
                System.out.println("Cliente nao possui veiculos locados. Tente novamente.");
            }else {
                valid = false;
            }
        }
        printRentVehiclesByClient(client);

        System.out.print("Qual veiculo devolvera? ");
        int vehicleId = scanner.nextInt();


        ArrayList<Vehicle> vehicles = Search.getRentVehiclesByClient(client);
        Vehicle vehicle = vehicles.get(vehicleId);

        ArrayList<Rent> rentList = Search.getRentByClient(client);
        Rent rent = rentList.get(vehicleId);

        System.out.println("Selecionado " + vehicle.getPlate());

        valid = true;

        long returnMileage = 0;
        while (valid) {
            System.out.print("Quilometragem atual do veiculo (km da locacao: " + rent.getRentMileage() + "): ");
            returnMileage = scanner.nextLong();
            if (returnMileage > rent.getRentMileage()) {
                valid = false;
            }else {
                System.out.println("Quilometragem atual do veiculo nao pode ser menor que a quilometragem inicial.");
            }
        }

        System.out.print("Veiculo danificado? (N/S)");
        double sum = 0;
        if (scanner.next().equals("S")) {
            sum = vehicle.getLocationValue() * 0.1;
            System.out.println("Adicionado R$" + sum + " ao valor total da locacao.");
        }

        Date returnDate = new Date();

        if (returnDate.after(rent.getForeseenReturnDate())) {
            sum += vehicle.getLocationValue() * 0.2;
            System.out.println("Adicionado R$" + vehicle.getLocationValue() * 0.2 + " ao valor total da locacao.");
        }else if (returnDate.before(rent.getForeseenReturnDate())) {
            sum -= vehicle.getLocationValue() * 0.1;
            System.out.println("Retirado R$" + vehicle.getLocationValue() * 0.1 + " do valor total da locacao.");
        }

        System.out.println("Valor total da locacao: R$" + (vehicle.getLocationValue() + sum));
        System.out.print("Deseja realizar a devolucao? (N/S)");
        if (scanner.next().toUpperCase().equals("S")) {
            rent.setReturnDate(returnDate);
            rent.setReturnMileage(returnMileage);
            rent.setReturned(true);
            vehicle.setVehicleMileage(returnMileage);
            editJsonList(rentList, "src\\main\\java\\db\\rents.json");
            System.out.println("Devolucao realizada com sucesso!");
        } else {
            System.out.println("Devolucao cancelada!");
        }
    }

    public static void printClientList() {
        ArrayList<Client> clientList = Search.getClientList();
        System.out.println("Lista de clientes:");

        for (int i = 0; i < clientList.size(); i++) {
            System.out.println(i + " - " + clientList.get(i).getName() + " - " + clientList.get(i).getCpf());
        }
    }

    public static void printClientListDetaild() {
        ArrayList<Client> clientList = Search.getClientList();
        System.out.println("Lista de clientes:");

        for (int i = 0; i < clientList.size(); i++) {
            System.out.println("-----------------------------");
            System.out.println("Nome: " + clientList.get(i).getName());
            System.out.println("CPF " + clientList.get(i).getName() + " - " + clientList.get(i).getCpf());
            System.out.println("Telefone: " + clientList.get(i).getPhone());
            System.out.println("Endereco: " + clientList.get(i).getAddress());
            System.out.println("Email: " + clientList.get(i).getEmail());
            System.out.println("-----------------------------");
        }
        System.out.println();
    }

    public static void printVehicleList() {
        ArrayList<Vehicle> vehicleList = Search.getVehicleList();
        System.out.println("Lista de veiculos:");

        for (int i = 0; i < vehicleList.size(); i++) {
            System.out.println("-----------------------------");
            System.out.println("Placa: " + vehicleList.get(i).getPlate());
            System.out.println("Modelo: " + vehicleList.get(i).getModel().getModelDescription());
            System.out.println("Marca: " + vehicleList.get(i).getModel().getBrand());
            System.out.println("Renavam: " + vehicleList.get(i).getRenavamVehicle());
            System.out.println("Chassi: " + vehicleList.get(i).getChassisVehicle());
            System.out.println("Quilometragem: " + vehicleList.get(i).getVehicleMileage());
            System.out.println("Cor: " + vehicleList.get(i).getColorVehicle());
            System.out.println("Valor da locacao: R$" + vehicleList.get(i).getLocationValue());
            System.out.println("-----------------------------");
        }
        System.out.println();
    }

    public static void printAvailableVehiclesList() {
        ArrayList<Vehicle> vehicleList = Search.getAvailableVehiclesList();
        System.out.println("Lista de veiculos disponiveis:");

        for (int i = 0; i < vehicleList.size(); i++) {
            System.out.println(i + " - " + vehicleList.get(i).getPlate() + " - R$" + vehicleList.get(i).getLocationValue());
        }
    }

    public static void printRentVehiclesByClient(Client client) {
        ArrayList<Vehicle> vehicleList = Search.getRentVehiclesByClient(client);
        System.out.println("Lista de veiculos alugados por " + client.getName() + ":");

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

        System.out.println("Por onde andara com o veiculo:");
        String destination = scan.nextLine();

        System.out.println("Finalidade da locacao:");
        String rentPurpose = scan.nextLine();

        boolean valid = true;
        double bailValue = 0;
        while (valid) {
            System.out.println("Valor da caucao:");
            bailValue = scan.nextDouble();

            if (bailValue > vehicle.getLocationValue() + 50) {
                valid = false;
            }else {
                System.out.println("Valor da caucao nao pode ser menor que o valor da locacao do veiculo + 50");
            }
        }
        Rent rent = new Rent(rentDate, rentDate, foreseenReturnDate, rentMileage, 0L, bailValue,
                vehicle.getLocationValue(), false, vehicle.getPlate(), client.getCpf(), rentPurpose, destination);

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

    public static Client selectClient(int index) {
        ArrayList<Client> clientList = Search.getClientList();
        return clientList.get(index);
    }

    public static Client editClient(int index) {
        ArrayList<Client> clientList = Search.getClientList();
        Client client = clientList.get(index);
        ArrayList<Rent> rentList = Search.getRentByClient(client);
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
                    for (Rent rent : rentList) {
                        rent.setClientCPF(cpf);
                    }
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
            editJsonList(rentList,"src\\main\\java\\db\\rents.json" );
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

    public static void editVehicles(ArrayList<Vehicle> vehicleList) {
        editJsonList(vehicleList, "src\\main\\java\\db\\vehicles.json");
    }

    public static void main(String[] args) throws ParseException {
        boolean isValid = true;
        while (isValid) {
            System.out.println("1 - Locar veiculo");
            System.out.println("2 - Devolver veiculo");
            System.out.println("3 - Lista de veiculos");
            System.out.println("4 - Lista de clientes");
            Scanner scanner = new Scanner(System.in);

            int option = scanner.nextInt();

            if (option == 1) {
                rentACar(scanner);

            } else if (option == 2) {
                returnACar(scanner);
            } else if (option == 3) {
                System.out.println();
                printVehicleList();
            } else if (option == 4) {
                System.out.println();
                printClientListDetaild();
            } else if (option == -1) {
                isValid = false;
            } else {
                System.out.println("Opcao invalida!");
            }
        }
    }
}

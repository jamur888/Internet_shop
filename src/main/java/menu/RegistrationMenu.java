package menu;

import domain.Client;
import servise.ClientServise;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class RegistrationMenu implements Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final ClientServise clientServise = ClientServise.getClientServise();
    private static RegistrationMenu menu;

    public static RegistrationMenu getInstance() throws SQLException {
        if (menu == null) {
            menu = new RegistrationMenu();
        }
        return menu;
    }

    @Override
    public void getMenu() throws SQLException {

        String clientLogin = scanner.next();
        searchClientInDb(clientLogin);
    }

    private Client searchClientInDb(String clientName) throws SQLException {
        Client client;
        final List<Client> collect = ClientServise.getClientServise().getAll().stream().filter(name -> name.getName().equals(clientName)).collect(Collectors.toList());
        if (collect.size() == 0) {
            client = new Client();
            client.setName(clientName);
            ClientServise.getClientServise().create(client);
            System.out.println("Вы успешно зарегистрированы");
        } else {
            client = collect.get(0);
            System.out.println("C таким именем уже существует");
        }
        return client;
    }
}

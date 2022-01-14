package menu;

import json.ClientSerializer;
import json.DeserializeClient;
import json.OrdersSerializer;
import utils.NumberValidUtil;

import java.io.IOException;
import java.sql.SQLException;

public class AdminMenu implements Menu {
    private static final String NO_OPERATION = "There is no such operation. Try again";
    private static final String EXIT = "Closing the program";
    private static int operationNumber;
    private static NumberValidUtil integerInput = new NumberValidUtil();
    private static final String MAIN_MENU = "Admin Menu:\n" +
            "1.New order\n" +
            "2. Registration\n" +
            "3. List of all clients\n" +
            "4. List of all orders\n" +
            "5. Экспортировать(Сериализация) все заказы в JSON файл\n" +
            "6. Имортировать(Дисериализация) данные о заказах в бд\n" +
            "7. Выход";


    private static AdminMenu adminMenu;

    private AdminMenu() {
    }

    public static AdminMenu getInstance() {
        if (adminMenu == null) {
            adminMenu = new AdminMenu();
        }
        return adminMenu;
    }

    @Override
    public void getMenu() throws SQLException {
        Menu menu = null;
        boolean exit = false;
        do {
            System.out.println(MAIN_MENU);
            operationNumber = integerInput.intNumberValid(operationNumber);
            switch (operationNumber) {
                case 1:
                    menu = OrderCreation.getInstance();
                    break;
                case 2:
                    menu = RegistrationMenu.getInstance();
                    break;
                case 3:
                    menu = ListOfAllClients.getInstance();
                    break;
                case 4:
                    menu = ListOfAllOrders.getInstance();
                    break;
                case 5:
                    menu = OrdersSerializer.getInstance();
                    break;
                case 6:
                     menu = DeserializeClient.getInstance();
                    break;
                case 7:
                    exit = true;
                    menu = null;
                    System.out.println(EXIT);
                    break;
                default:
                    System.out.println(NO_OPERATION);
                    menu = null;
                    break;
            }
            if (menu != null) {
                try {
                    menu.getMenu();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } while (!exit);
    }
}
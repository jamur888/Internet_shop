package menu;

import utils.NumberValidUtil;

import java.sql.SQLException;

public class AdminMenu implements Menu {
    private static final String NO_OPERATION = "Такой операции нету. Повторите попытку";
    private static final String EXIT = "Закрытие программы";
    private static int operationNumber;
    private static NumberValidUtil integerInput = new NumberValidUtil();
    private static final String MAIN_MENU = "Меню администратора:\n" +
            "1. Новый заказ\n" +
            "2. Регистрасия\n" +
            "3. Список всех клиентов\n" +
            "4. Список всех заказов\n" +
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
                    //    menu = DeserializeTours.getInstance();
                    break;
                case 6:
                    // menu = SerializeClientsToJsonMenu();
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
                menu.getMenu();
            }

        } while (!exit);
    }
}
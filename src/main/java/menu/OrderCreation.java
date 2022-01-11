package menu;

import domain.Client;
import domain.Commodity;
import domain.Order;
import servise.ClientServise;
import servise.CommodityServise;
import servise.OrderServise;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class OrderCreation implements Menu {
    private final ClientServise clientServise = ClientServise.getClientServise();
    private final OrderServise orderServise = OrderServise.getOrderServise();
    private final Scanner scanner = new Scanner(System.in);
    private int operationNumber;
    private static OrderCreation menu;
    Client inputClient = new Client();
    List<Client> clients = null;

    public static OrderCreation getInstance() throws SQLException {
        if (menu == null) {
            menu = new OrderCreation();
        }
        return menu;
    }

    @Override
    public void getMenu() throws SQLException {
        boolean exit = false;
        boolean clientValid = false;
        String user;
        System.out.println("Enter a name:");
        user = scanner.nextLine();
        inputClient.setName(user);
        clients = clientServise.findByName(inputClient);
        if (clients.size() >= 1) {
            clientValid = true;
            inputClient = clients.get(0);
        }

        if (clientValid) {
            exit = false;
            int choseClient;
            System.out.println("Loged in successfuly!\n" +
                    "1.каталог товаров:\n" +
                    "2.мои заказы:\n" +
                    "3.выход:");
            do {
                choseClient = scanner.nextInt();
                switch (choseClient) {
                    case 1:
                        if (inputClient.isBlocked() == true) {
                            System.out.println("client blocked");
                        } else {
                            List<Commodity> commodityList = (List<Commodity>) new CommodityServise().getAll();
                            commodityList.stream().forEach(System.out::println);
                            ordersClient();
                        }
                        //  clientServise.blackList(inputClient.getId());
                        break;
                    case 2:
                        orderServise.findAllOrdersByClient(inputClient).stream().forEach(System.out::println);
                        break;
                    case 3:
                        AdminMenu.getInstance().getMenu();
                        break;
                    default:
                        System.out.println("There is no such operation. Try again");
                        break;
                }
            } while (!exit);

        }


    }

    public void ordersClient() throws SQLException {
        clients = clientServise.findByName(inputClient);
        inputClient = clients.get(0);//ce
        System.out.println("make an order:\n");
        Order or = new Order();
        int chose = scanner.nextInt();
        or.setCommodity_id(new Commodity(chose));
        or.setClient_id(new Client(inputClient.getId()));
        System.out.println("1:yes\n" +
                "2:no\n" +
                "3:back");
        int pay = scanner.nextInt();
        switch (pay) {
            case 1:
                orderServise.create(or);
                break;
            case 2:
                clientServise.addToBlackList(inputClient.getId());
                break;
            case 3:
                getMenu();
                break;
            default:
                System.out.println("There is no such operation. Try again");
                break;
        }
    }


}

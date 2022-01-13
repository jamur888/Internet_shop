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
    private final CommodityServise commodityServise = CommodityServise.getCommodityServise();
    private final OrderServise orderServise = OrderServise.getOrderServise();
    private final Scanner scanner = new Scanner(System.in);
    private static OrderCreation menu;
    Client inputClient = new Client();
    List<Client> clients = null;

    public static OrderCreation getInstance() {
        if (menu == null) {
            menu = new OrderCreation();
        }
        return menu;
    }

    @Override
    public void getMenu() throws SQLException {
        boolean exit;
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
                    "1.Catalog:\n" +
                    "2.My orders:\n" +
                    "3.Exit:");
            do {
                choseClient = scanner.nextInt();
                switch (choseClient) {
                    case 1:
                        if (inputClient.isBlocked() == true) {
                            System.out.println("\n" +
                                    "you are blocked");
                        } else {
                            List<Commodity> commodityList = commodityServise.getAll();
                            commodityList.forEach(System.out::println);
                            ordersClient();
                        }
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
        inputClient = clients.get(0);
        System.out.println("make an order:\n");
        Order or = new Order();
        int chose = scanner.nextInt();
        or.setCommodity_id(new Commodity((long) chose));
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

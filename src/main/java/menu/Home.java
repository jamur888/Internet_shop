package menu;

import domain.Client;
import domain.Commodity;
import domain.Order;
import servise.ClientServise;
import servise.CommodityServise;
import servise.OrderServise;
import utils.NumberValidUtil;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Home {
    private final Scanner scanner = new Scanner(System.in);
    private final NumberValidUtil numberValidUtil = NumberValidUtil.getOperationNumberUtil();
    private final CommodityServise commodityServise = CommodityServise.getCommodityServise();
    private final ClientServise clientServise = ClientServise.getClientServise();
    private final OrderServise orderServise = OrderServise.getOrderServise();
    private static Home home = null;
    boolean exitMenu = false;
    boolean clientValid = false;
    private static int operationNumber;
    Client inputClient = new Client();
    List<Client> clients = null;

    public static Home getHome() {
        if (home == null) {
            home = new Home();
        }
        return home;
    }


    public void menu() throws SQLException {

        System.out.println(
                "1.Login:\n" +
                        "2.Registration:\n" +
                        "3.close:");
        do {
            operationNumber = numberValidUtil.intNumberValid(operationNumber);
            switch (operationNumber) {
                case 1:
                    input();
                    break;
                case 2:
                    registration();
                    break;
                case 3:
                    exitMenu = true;
                    System.out.println("Close...");
                    break;
                default:
                    System.out.println("There is no such operation. Try again");
                    break;
            }
        } while (!exitMenu);
    }

    private void userRegistrationMenu() {


    }


    void registration() throws SQLException {
        Client newUser;
        String clientLogin;
        boolean clientLoginValid = true;
        System.out.println("Enter login...");
        clientLogin = scanner.next();

        for (Client user : clientServise.getAll()) {
            if (clientLogin.equals(user.getName())) {
                clientLoginValid = false;
                break;
            }
        }
        newUser = new Client();
        newUser.setName(clientLogin);
        clientServise.clientRegistration(newUser);

//        System.out.println("wedite imya");
//        Client re = new Client();
//        Scanner reg = new Scanner(System.in);
//        String registor = reg.nextLine();
//        re.setName(registor);
//        clientServise.create(re);
//     //   new Home().input();
    }

    void input() throws SQLException {
        String user;
        System.out.println("Enter a name:");
        user = scanner.nextLine();
        inputClient.setName(user);
        clients = clientServise.findByName(inputClient);
        if (clients.size() >= 1) {
            exitMenu = true; //Co eta
            clientValid = true;
            inputClient = clients.get(0);
        }

        if (clientValid) {
            exitMenu = false;
            int choseClient;
            System.out.println("Loged in successfuly!\n" +
                    "1.каталог товаров:\n" +
                    "2.мои заказы:\n" +
                    "3.выход:");
            do {
                choseClient = scanner.nextInt();
                switch (choseClient) {
                    case 1:
                        if(inputClient.isBlocked()==true){
                            System.out.println("client blocked");
                        }
                        else {
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
                     menu();
                        break;
                    default:
                        System.out.println("There is no such operation. Try again");
                        break;
                }
            } while (!exitMenu);

        } else {
            System.out.println("wrong name!");
            menu();
        }

    }

    public void ordersClient() throws SQLException {
        clients = clientServise.findByName(inputClient);
        if (clients.size() >= 1) {
            exitMenu = true; //Co eta
            clientValid = true;
            inputClient = clients.get(0);
        }
        System.out.println("make an order:\n");
        Order or = new Order();
        int chose = scanner.nextInt();
        or.setCommodity_id(new Commodity(chose));
        or.setClient_id(new Client(inputClient.getId()));
        orderServise.create(or);

    }
}


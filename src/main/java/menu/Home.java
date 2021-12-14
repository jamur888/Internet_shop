package menu;

import dao.CommodityDao;
import domain.Client;
import domain.Commodity;
import domain.Order;
import servise.ClientServise;
import servise.CommodityServise;
import servise.OrderServise;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Home {

    private final CommodityServise commodityServise = CommodityServise.getCommodityServise();
    private final ClientServise clientServise = ClientServise.getClientServise();
    private final OrderServise orderServise = OrderServise.getOrderServise();
    private Scanner scanner = new Scanner(System.in);
    private static Home home = null;

    List<Client> cll = null;

    public static Home getHome() {
        if (home == null) {
            home = new Home();
        }
        return home;
    }


    public void menu() throws SQLException {
        boolean exitMenu = false;
        System.out.println("1.войти:\n" +
                "2.Регистрасия:\n" +
                "3.выход:");
        do {
            int clientIn = scanner.nextInt();

            switch (clientIn) {
                case 1:
                    input();
                    break;
                case 2:
                    registration();  //  ClientRegistrationMenu();
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

    void registration() {

        System.out.println("wedite imya");
        Client re = new Client();
        Scanner reg = new Scanner(System.in);
        String registor = reg.nextLine();
        re.setName(registor);
        clientServise.create(re);

    }

    void input() throws SQLException {
        boolean exitMenu = false;
        boolean clientValid = false;
        Client input_client = new Client();
        System.out.println("Enter a name:");

        Scanner in = new Scanner(System.in);
        String sc = in.nextLine();
        input_client.setName(sc);
        cll = clientServise.findByName(input_client);
        if (cll.size() >= 1) {
            exitMenu = true;
            clientValid = true;
            input_client = cll.get(0);
        }

        if (clientValid) {

            System.out.println("Loged in successfuly!\n" +
                    "1.каталог товаров:\n" +
                    "2.мои заказы:\n" +
                    "3.выход:");
            do {

                 int choseClient = in.nextInt();

                switch (choseClient) {
                    case 1:
                        if(cll.get(0).isBlocked()==true){
                            System.out.println("client blocked");
                        }
                       else {
                            List<Commodity> commodityList = (List<Commodity>) new CommodityServise().getAll();
                            commodityList.stream().forEach(System.out::println);
                            orderClient();
                        }
                        break;
                    case 2:
                        System.out.println(new OrderServise().read(cll.get(0).getId()));
                        break;
                    case 3:
                        input();
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

    void orderClient() {
        System.out.println("chose commodity:");
        Order or = new Order();
        Scanner scanner1 = new Scanner(System.in);
        int chose = scanner1.nextInt();
        or.setCommodity_id(new Commodity(chose));
        or.setClient_id(new Client(cll.get(0).getId()));
        orderServise.create(or);
        System.out.println("");
    }

}


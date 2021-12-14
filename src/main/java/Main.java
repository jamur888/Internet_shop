import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ClientDao;
import dao.CommodityDao;
import dao.OrderDao;
import domain.Client;
import domain.Commodity;
import domain.Order;
import menu.Home;
import servise.ClientServise;
import servise.OrderServise;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        //
        Home home = Home.getHome();
        home.menu();
       // new ClientServise().create(new Client(-1l, "sgfddfgd", false));
//new ClientServise().delete(new Client(7l));
        // ObjectMapper objectMapper = new ObjectMapper();

//        List<Order> orderArrayList = new OrderDao().getAll();
//        orderArrayList.forEach(System.out::println);
//        System.out.println();
//        System.out.println(new OrderDao().read(1L));
//        ClientServise clientServise = new ClientServise();
//        clientServise.addToBlackList(3L);

//        ClientServise clientServise = new ClientServise();
//        Client client = new Client(1l,"Max", false);
//        clientServise.addToBlackList(1l);
        /*
        Order order = new Order(1l, 2656, new Client(1l ),new Commodity(2l));
        orderDao.create(order);
*/

        // System.out.println(commodityDao.create());
        //        System.out.println(productDao.read(3l));
        //       System.out.println(productDao.create(product));
//        AdminDao adminDao = new AdminDao();
//        Admin ad = new Admin(1l,"njkn","jihui");
//        System.out.println(adminDao.create(ad));
        // System.out.println(adminDao);
//        System.out.println(adminDao.read(1l));read
        ////////////////////////////
        /*ClientDao clientDao = new ClientDao();
        Client client = new Client(1,"nasm", false);
        clientDao.create(client);
        Order order = new Order(131,211231233,client,product);
        Order order12 = new Order(132,211232133,client,product1);
        Order order2 = new Order(133,212331313,client,product2);
*/
//  clientServise.create(1l,"sd", false);
        //  ClientDao cl = new ClientDao();
        //   Client c1 = new Client(1l, "jij", );
        //    System.out.println(cl.getAll());

        // spisok
//        ArrayList<Commodity> commodityList = (ArrayList<Commodity>) new  CommodityDao().getAll();
//        commodityList.stream().forEach(System.out::println);
//
//        ArrayList<Client> clientArrayList = (ArrayList<Client>) new ClientDao().getAll();
//        clientArrayList.stream().forEach(System.out::println);


    }
}


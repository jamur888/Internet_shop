import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dao.ClientDao;
import dao.OrderDao;
import domain.Client;
import domain.Commodity;
import domain.Order;
import menu.AdminMenu;
import menu.Home;
import servise.ClientServise;
import servise.CommodityServise;
import servise.OrderServise;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {

        AdminMenu menu = AdminMenu.getInstance();
        menu.getMenu();

    }
}


package menu;

import domain.Client;
import utils.NumberValidUtil;
import servise.ClientServise;
import java.sql.SQLException;
import java.util.List;

public class ListOfAllClients implements Menu{
    private static int operationNumber;
    private static ListOfAllClients instance;

    private ListOfAllClients() {
    }

    public static ListOfAllClients getInstance() {
        if (instance == null) {
            instance = new ListOfAllClients();
        }
        return instance;
    }

    @Override
    public void getMenu() throws SQLException {
        try {
            boolean exit = false;
            final List<Client> allClients = ClientServise.getClientServise().getAll();
            if (allClients.size() > 0) {
                allClients.forEach(System.out::println);
            }
            do {
                operationNumber = NumberValidUtil.getOperationNumberUtil().intNumberValid(operationNumber);
                if (operationNumber == 1) {
                    exit = true;
                }
            } while (!exit);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

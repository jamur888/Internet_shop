package menu;

import domain.Client;
import servise.ClientServise;
import utils.NumberValidUtil;

import java.sql.SQLException;
import java.util.List;

public class ListOfAllClients implements Menu {
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
    public void getMenu(){
        try {
            boolean exit = false;
            final List<Client> allClients = ClientServise.getClientServise().getAll();
            allClients.forEach(System.out::println);

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

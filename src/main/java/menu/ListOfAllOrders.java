package menu;

import domain.Order;
import servise.OrderServise;
import utils.NumberValidUtil;

import java.util.List;

public class ListOfAllOrders implements Menu{
    private static int operationNumber;
    private static ListOfAllOrders instance;

    private ListOfAllOrders() {
    }

    public static ListOfAllOrders getInstance() {
        if (instance == null) {
            instance = new ListOfAllOrders();
        }
        return instance;
    }

    @Override
    public void getMenu() {
        boolean exit = false;
        final List<Order> allOrders = OrderServise.getOrderServise().getAll();
        if (allOrders.size() > 0) {
            allOrders.forEach(System.out::println);
        }
        do {
            operationNumber = NumberValidUtil.getOperationNumberUtil().intNumberValid(operationNumber);
            if (operationNumber == 1) {
                exit = true;
            }
        } while (!exit);
    }
}
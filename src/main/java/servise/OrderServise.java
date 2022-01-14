package servise;

import dao.OrderDao;
import domain.Client;
import domain.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderServise {
    private final OrderDao orderDao = OrderDao.getOrderDao();
    private static OrderServise orderServise;

    public OrderServise() throws SQLException {
    }

    public static OrderServise getOrderServise() {
        if (orderServise == null) {
            try {
                orderServise = new OrderServise();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return orderServise;
    }
    /*
     * Список всех заказов
     * */
    public List<Order> getAll() {
        return orderDao.getAll();
    }
    /*
     * Создание нового заказа
     * */
    public void create(Order orders) {
        orderDao.create(orders);
    }
    /*
     * Список всех заказов определеного клиента
     * */
    public List<Order> findAllOrdersByClient(Client clients) {
        return orderDao.findAllOrdersByClient(clients);
    }
}

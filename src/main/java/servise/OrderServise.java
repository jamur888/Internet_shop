package servise;

import dao.CommodityDao;
import dao.OrderDao;
import domain.Client;
import domain.Order;

import java.sql.SQLException;

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

    public Order read(Long id){
        return orderDao.read(id);
    }

    public void create (Order orders){
        orderDao.create(orders);
    }

}

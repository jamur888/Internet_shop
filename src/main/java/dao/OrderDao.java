package dao;

import domain.Client;
import domain.Commodity;
import domain.Order;
import utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements Dao<Order> {


    public static OrderDao orderDao;

    public static OrderDao getOrderDao() throws SQLException {
        if (orderDao == null) {
            orderDao = new OrderDao();
        }
        return orderDao;
    }
    @Override
    public Order read(Long id) {
        String query= "SELECT * FROM orders INNER JOIN client ON orders.client_id = client.id INNER  JOIN commodity ON orders.commodity_id = commodity.id where id = ?";
        Order orders = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                orders = retrieveDataFromDB(resultSet);
            }
        } catch (SQLException ex) {
            System.out.println("No such order  with id " + id);
        }
        return orders;
    }

    @Override
    public List<Order> getAll() {
        String query= "SELECT id FROM orders";

        List<Order> orderList = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                orderList.add(read(resultSet.getLong("id")));
            }
        } catch (SQLException ex) {
            System.out.println("There are no orders in DB");
        }
        return orderList;
    }

    @Override
    public void create(Order orders) {
        String query = "INSERT INTO orders(number, client_id, commodity_id) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, orders.getNumber());
            statement.setLong(2, orders.getClient_id().getId());
            statement.setLong(3, orders.getCommodity_id().getId());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                orders.setId(resultSet.getLong(1));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            System.out.println("Order " + orders.toString() + " was not created");
        }

    }

    @Override
    public void update(Order orders) {
        String query = "UPDATE order SET number = ?, client_id = ?, commodity_id = ? WHERE id = ? ";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, orders.getNumber());
            statement.setLong(2, orders.getClient_id().getId());
            statement.setLong(3, orders.getCommodity_id().getId());
            statement.setLong(4, orders.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Not able to update " + orders.toString());
        }
    }

    @Override
    public void delete(Order orders) {
        String query = "DELETE from orders WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, orders.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Delete of orders with id = "
                    + orders + "is failed");
        }
    }

    private Order retrieveDataFromDB(ResultSet resultSet) throws SQLException {
        return new Order(resultSet.getLong("id"), resultSet.getInt("number"),
                new Client(resultSet.getLong("client_id"), resultSet.getString("name"), resultSet.getBoolean("isblocked")),
                new Commodity(resultSet.getLong("commodity_id"),resultSet.getInt("art"), resultSet.getDouble("price"),resultSet.getString("description")));

    }

}

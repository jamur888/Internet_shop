package dao;

import domain.Commodity;
import utils.db.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommodityDao implements Dao<Commodity> {

    private static CommodityDao commodityDao;

    public static CommodityDao getCommodityDao() throws SQLException {
        if (commodityDao == null) {
            commodityDao = new CommodityDao();
        }
        return commodityDao;
    }

    @Override
    public Commodity read(Long id) {
        String query = "SELECT * FROM commodity WHERE id = ?";
        Commodity commodity = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                commodity = retrieveDataFromDB(resultSet);
            }
        } catch (SQLException ex) {
            System.out.println("No such product with id " + id);
        }
        return commodity;
    }


    @Override
    public List<Commodity> getAll() {
        String query = "SELECT * FROM commodity";
        List<Commodity> commodityList = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                commodityList.add(retrieveDataFromDB(resultSet));
            }
        } catch (SQLException ex) {
            System.out.println("There are no products in DB");
        }
        return commodityList;
    }

    @Override
    public void create(Commodity commodity) {
        String query = "INSERT INTO commodity (art, price, description) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, commodity.getArt());
            statement.setDouble(2, commodity.getPrice());
            statement.setString(3, commodity.getDescription());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Product " + commodity.toString() + " was not created");
        }

    }

    @Override
    public void update(Commodity commodity) {
        String query = "UPDATE commodity SET art = ?, price = ?, description = ?  WHERE id = ? ";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, commodity.getArt());
            statement.setDouble(2, commodity.getPrice());
            statement.setString(3, commodity.getDescription());
            statement.setLong(4, commodity.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Not able to update " + commodity.toString());
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Commodity commodity) {
        String query = "DELETE from commodity WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, commodity.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Delete of product with id = "
                    + commodity + "is failed");
        }

    }

    private Commodity retrieveDataFromDB(ResultSet resultSet) throws SQLException {
        return new Commodity(resultSet.getLong("id"),resultSet.getInt("art"), resultSet.getDouble("price"), resultSet.getString("description"));
    }
}

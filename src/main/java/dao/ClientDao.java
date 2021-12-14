package dao;

import domain.Client;
import utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao implements Dao<Client> {

    private static ClientDao clientDao;

    public static ClientDao getClientDao() throws SQLException {
        if (clientDao == null) {
            clientDao = new ClientDao();
        }
        return clientDao;
    }

    @Override
    public Client read(Long id) {
        String query = "SELECT * FROM client WHERE id = ?";
        Client client = null;
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                client = retrieveDataFromDB(resultSet);
            }
        } catch (SQLException ex) {
            System.out.println("No such client with id " + id);
        }
        return client;

    }

    public List<Client>  findByName(Client client) {
        String name = client.getName();
        Client clientDb = new Client();
        List<Client> cll=new ArrayList<Client>();
        String query = "SELECT * FROM client WHERE name = ?";
            try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                cll.add(retrieveDataFromDB(resultSet));
            }
        } catch (SQLException ex) {
            System.out.println("No such client with name " + name);
        }
        return cll;

    }

    @Override
    public List<Client> getAll() {
        String query = "SELECT  id, name, isblocked, FROM client";
        List<Client> clientList = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                clientList.add(retrieveDataFromDB(resultSet));
            }
        } catch (SQLException ex) {
            System.out.println("There are no clients in DB");
        }
        return clientList;
    }

    @Override
    public void create(Client client) {
        String query = "INSERT INTO  client(name, isBlocked) VALUES (?, ?)";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, client.getName());
            statement.setBoolean(2, client.isBlocked());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Client " + client.toString() + " was not created");
        }

    }

    @Override
    public void update(Client client) {
        String query = "UPDATE client SET name = ?, isblocked = ? WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, client.getName());
            statement.setBoolean(2, client.isBlocked());
            statement.setLong(3, client.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            //System.out.println("Not able to update " + client.toString());
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Client client) {
        String query = "DELETE from client WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setLong(1, client.getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Delete of client with id = "
                    + client + "is failed");
        }
    }


    private Client retrieveDataFromDB(ResultSet resultSet) throws SQLException {
        return new Client(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getBoolean("isBlocked"));
    }


}

package servise;

import dao.ClientDao;
import domain.Client;

import java.sql.SQLException;
import java.util.List;

public class ClientServise {
    private final ClientDao clientDao = ClientDao.getClientDao();
    private static ClientServise clientServise;

    public ClientServise() throws SQLException {
    }

    public static ClientServise getClientServise() {
        if (clientServise == null) {
            try {
                clientServise = new ClientServise();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return clientServise;
    }

    public List<Client> findByName(Client client) {

        return clientDao.findByName(client);
    }

    // список всех клиентов

    public List<Client> getAll() {
        return clientDao.getAll();
    }

    public void update(Client client) {
        clientDao.update(client);
    }

    public void create(Client client) {
        if (client.getId() > 0) {
            clientDao.create(client);
        } else System.out.println("bratan tagowa newozmojna");
    }

    public void delete(Client client) {
        if (client.getId() > 0) {
            clientDao.delete(client);
        }

    }

    public void addToBlackList(Long id) {
        Client client = clientDao.read(id);
        client.setBlocked(true);
        clientDao.update(client);
        if (client.isBlocked() == true) {
            System.out.println("вы в чёрном списке");
        }
    }


}

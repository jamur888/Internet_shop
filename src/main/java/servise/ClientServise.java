package servise;

import dao.ClientDao;
import domain.Client;
import domain.Commodity;
import menu.Home;

import java.sql.SQLException;
import java.util.List;

public class ClientServise {
    private static ClientServise clientServise;

    public static ClientServise getClientServise() {
        if (clientServise == null) {
            clientServise = new ClientServise();
        }
        return clientServise;
    }

    public List<Client> findByName(Client clients) throws SQLException {
        return ClientDao.getClientDao().findByName(clients);
    }

    public List<Client> getAll() throws SQLException {
        return ClientDao.getClientDao().getAll();
    }


    public void create(Client clients) throws SQLException {
        if (clients != null) {
            ClientDao.getClientDao().create(clients);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void delete(Client clients) throws SQLException {
        if (clients.getId() > 0) ClientDao.getClientDao().delete(clients);
    }

    public void clientRegistration(Client client) throws SQLException {

        if (client.getId() != 0) {
            client.setId(client.getId() + 1);
        } else {
            client.setId(1l);
        }
        ClientDao.getClientDao().create(client);
    }

    public void addToBlackList(Long id) throws SQLException {
        Client client = ClientDao.getClientDao().read(id);
        client.setBlocked(true);
        ClientDao.getClientDao().update(client);
    }


}

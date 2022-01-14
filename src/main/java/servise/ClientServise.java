package servise;

import dao.ClientDao;
import domain.Client;

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

    /*
     * Список клиентов по имени
     * */
    public List<Client> findByName(Client clients) {
        return ClientDao.getClientDao().findByName(clients);
    }

    public List<Client> getAll() throws SQLException {
        return ClientDao.getClientDao().getAll();
    }

    /*
     * Регистрация нового клиента
     * */
    public void create(Client clients) throws SQLException {
        if (clients != null) {
            ClientDao.getClientDao().create(clients);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /*
     * Добавление клиента в черный список
     * */
    public void addToBlackList(Long id) {
        Client client = ClientDao.getClientDao().read(id);
        client.setBlocked(true);
        ClientDao.getClientDao().update(client);
    }


}

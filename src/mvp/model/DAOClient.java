package mvp.model;

import magasin.metier.Client;

import java.util.List;

public interface DAOClient {
    Client addClient(Client client);

    boolean removeClient(Client client);

    List<Client> getClients();
}

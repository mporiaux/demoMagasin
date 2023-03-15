package mvp.model;

import magasin.metier.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientModel implements DAOClient {
    private int numcli =0;
    private List<Client> clients = new ArrayList<>();

    @Override
    public Client addClient(Client client) {
        if(!clients.contains(client)) {
            numcli++;
            client.setIdclient(numcli);
            clients.add(client);
            return client;
        }
        else return null;
    }

    @Override
    public boolean removeClient(Client client) {
        return clients.remove(client);
    }

    @Override
    public List<Client> getClients() {
        return clients;
    }
}


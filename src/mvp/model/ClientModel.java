package mvp.model;

import magasin.metier.Client;

import java.util.ArrayList;
import java.util.List;

import magasin.metier.ComFact;
import magasin.metier.Produit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ClientModel implements DAOClient, ClientSpecial{
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
    public Client updateClient(Client client) {
        //TODO method to code
        return null;
    }

    @Override
    public Client readClient(int idClient) {
        //TODO method to code
        return null;
    }

    @Override
    public List<Client> getClients() {
        return clients;
    }

    @Override
    public List<ComFact> factPayees(Client client) {
        return null;
    }

    @Override
    public List<ComFact> factRetard(Client client) {
        return null;
    }

    @Override
    public List<ComFact> factNonPayees(Client client) {
        return null;
    }

    @Override
    public List<ComFact> commandes(Client client) {
        return null;
    }

    @Override
    public List<Produit> produits(Client client) {
        return null;
    }
}


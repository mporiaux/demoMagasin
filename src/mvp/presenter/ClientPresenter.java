package mvp.presenter;

import magasin.metier.Client;
import mvp.model.DAOClient;
import mvp.view.ClientViewInterface;
import mvp.model.ClientModel;

import java.util.List;

public class ClientPresenter {
    private DAOClient model;
    private ClientViewInterface view;

    public ClientPresenter(DAOClient model, ClientViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {
        List<Client> clients = model.getClients();
        view.setListDatas(clients);
    }

    public void addClient(Client client) {
       Client cl = model.addClient(client);
       if(cl!=null) view.affMsg("création de :"+cl);
       else view.affMsg("erreur de création");
        List<Client> clients = model.getClients();
        view.setListDatas(clients);
    }


    public void removeClient(Client client) {
        boolean ok = model.removeClient(client);
        if(ok) view.affMsg("client effacé");
        else view.affMsg("client non effacé");
        List<Client> clients = model.getClients();
        view.setListDatas(clients);
    }
}


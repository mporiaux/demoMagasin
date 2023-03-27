package mvp.presenter;

import jdk.jshell.spi.SPIResolutionException;
import magasin.metier.Client;
import magasin.metier.ComFact;
import magasin.metier.Produit;
import mvp.model.ClientSpecial;
import mvp.model.DAOClient;
import mvp.view.ClientViewInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ClientPresenter {
    private DAOClient model;
    private ClientViewInterface view;

    private static final Logger logger = LogManager.getLogger(ClientPresenter.class);

    public ClientPresenter(DAOClient model, ClientViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {
        view.setListDatas(getAll());
    }

    public List<Client> getAll(){
        return model.getClients();
    }
    public void addClient(Client client) {
       Client cl = model.addClient(client);
       if(cl!=null) view.affMsg("création de :"+cl);
       else view.affMsg("erreur de création");
        List<Client> clients = model.getClients();
       // view.setListDatas(clients);//désactivé pour éviter appels imbriqués
    }


    public void removeClient(Client client) {
        boolean ok = model.removeClient(client);
        if(ok) view.affMsg("client effacé");
        else view.affMsg("client non effacé");
        List<Client> clients = model.getClients();
       // view.setListDatas(clients);//désactivé pour éviter appels imbriqués
    }

    public Client selectionner() {
        logger.info("appel de sélection");
        Client cl = view.selectionner(model.getClients());
        return cl;
    }

    public void update(Client client) {
        Client cl =model.updateClient(client);
        if(cl==null) view.affMsg("mise à jour infrucueuse");
        else view.affMsg("mise à jour effectuée : "+cl);
        //view.setListDatas(model.getClients());//désactivé pour éviter appels imbriqués
    }

    public Client search(int idClient) {
        Client cl = model.readClient(idClient);
        if(cl==null) view.affMsg("recherche infructueuse");
        else view.affMsg(cl.toString());
        return cl;
    }

    public void commandes(Client client) {
     List<ComFact> lcf =   ((ClientSpecial)model).commandes(client);
        if(lcf==null || lcf.isEmpty()) view.affMsg("aucune commande trouvée");
        else view.affList(lcf);
    }

    public void factNonPayees(Client client) {
        List<ComFact> lcf =   ((ClientSpecial)model).factNonPayees(client);
        if(lcf==null || lcf.isEmpty()) view.affMsg("aucune commande trouvée");
        else view.affList(lcf);
    }

    public void factRetard(Client client) {
        List<ComFact> lcf =   ((ClientSpecial)model).factRetard(client);
        if(lcf==null || lcf.isEmpty()) view.affMsg("aucune commande trouvée");
        else view.affList(lcf);
    }

    public void factPayees(Client client) {
        List<ComFact> lcf =  ((ClientSpecial)model).factPayees(client);
        if(lcf==null || lcf.isEmpty()) view.affMsg("aucune commande trouvée");
        else view.affList(lcf);
    }

    public void produits(Client client) {
        List<Produit> lpr = ((ClientSpecial)model).produits(client);
        if(lpr==null || lpr.isEmpty()) view.affMsg("aucun produit trouvé");
        else view.affList(lpr);
    }
}


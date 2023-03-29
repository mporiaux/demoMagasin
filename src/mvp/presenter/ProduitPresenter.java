package mvp.presenter;

import magasin.metier.Client;
import magasin.metier.ComFact;
import magasin.metier.Produit;
import mvp.model.ClientSpecial;
import mvp.model.DAOClient;
import mvp.model.DAOProduit;
import mvp.view.ClientViewInterface;
import mvp.view.ProduitViewInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ProduitPresenter {
    private DAOProduit model;
    private ProduitViewInterface view;

    private static final Logger logger = LogManager.getLogger(ProduitPresenter.class);

    public ProduitPresenter(DAOProduit model, ProduitViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {
        view.setListDatas(getAll());
    }

    public List<Produit> getAll(){
        return model.getProduits();
    }
    public void addProduit(Produit produit) {
       Produit pr = model.addProduit(produit);
       if(pr!=null) view.affMsg("création de :"+pr);
       else view.affMsg("erreur de création");
    }


    public void removeProduit(Produit pr) {
        boolean ok = model.removeProduit(pr);
        if(ok) view.affMsg("produit effacé");
        else view.affMsg("produit non effacé");
    }

    public Produit selectionner() {
        logger.info("appel de sélection");
        Produit pr = view.selectionner(model.getProduits());
        return pr;
    }

    public void update(Produit produit) {
        Produit pr =model.updateProduit(produit);
        if(pr==null) view.affMsg("mise à jour infrucueuse");
        else view.affMsg("mise à jour effectuée : "+pr);
    }

    public Produit search(int idProduit) {
        Produit pr = model.readProduit(idProduit);
        if(pr==null) view.affMsg("recherche infructueuse");
        else view.affMsg(pr.toString());
        return pr;
    }

}


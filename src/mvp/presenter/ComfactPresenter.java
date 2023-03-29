package mvp.presenter;

import magasin.metier.Client;
import magasin.metier.ComFact;
import magasin.metier.Produit;
import mvp.model.ClientSpecial;
import mvp.model.ComfactSpecial;
import mvp.model.DAOClient;
import mvp.model.DAOComfact;
import mvp.view.ClientViewInterface;
import mvp.view.ComfactViewInterface;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ComfactPresenter {
    private DAOComfact model;
    private ComfactViewInterface view;
    private ClientPresenter clientPresenter;

    private ProduitPresenter produitPresenter;

    public void setProduitPresenter(ProduitPresenter produitPresenter) {
        this.produitPresenter = produitPresenter;
    }

    public void setClientPresenter(ClientPresenter clientPresenter) {
        this.clientPresenter = clientPresenter;
    }

    private static final Logger logger = LogManager.getLogger(ComfactPresenter.class);

    public ComfactPresenter(DAOComfact model, ComfactViewInterface view) {
        this.model = model;
        this.view = view;
        this.view.setPresenter(this);
    }

    public void start() {
        view.setListDatas(getAll());
    }

    public List<ComFact> getAll(){
        return model.getComfacts();
    }
    public void addComfact() {
        Client cl = clientPresenter.selectionner();
        ComFact cf = new ComFact();
        cf.setClient(cl);
        cf = model.addComfact(cf);
       if(cf!=null) view.affMsg("création de :"+cf);
       else view.affMsg("erreur de création");
        List<ComFact> comfacts = model.getComfacts();
        //view.setListDatas(comfacts);//désactivé pour éviter appels imbriqués
    }


    public void removeComfact(ComFact comfact) {
        boolean ok = model.removeComfact(comfact);
        if(ok) view.affMsg("commande effacée");
        else view.affMsg("commande non effacée");
        List<ComFact> comfacts = model.getComfacts();
       // view.setListDatas(comfacts); //désactivé pour éviter appels imbriqués
    }

    public void selectionner(ComFact cf) {
       logger.info("appel de sélection");
    }

    public void update(ComFact comfact) {
        ComFact cf =model.updateComfact(comfact);
        if(cf==null) view.affMsg("mise à jour infrucueuse");
        else view.affMsg("mise à jour effectuée : "+cf);
       // view.setListDatas(model.getComfacts());//désactivé pour éviter appels imbriqués
    }

    public void search(int idCommande) {
        ComFact cf  = model.readComfact(idCommande);
        if(cf==null) view.affMsg("recherche infructueuse");
        else view.affMsg(cf.toString());
    }

    public void addLigne(ComFact cf,int q){
        Produit pr = produitPresenter.selectionner();
       boolean ok =  ((ComfactSpecial)model).addProd(cf,pr,q);
       if(ok) view.affMsg("produit ajouté");
       else view.affMsg("erreur lors de l'ajout du produit");
    }

}


package mvp;

import mvp.model.*;
import mvp.presenter.ClientPresenter;
import mvp.presenter.ComfactPresenter;
import mvp.presenter.ProduitPresenter;
import mvp.view.*;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestMagasin {
    private DAOClient cm;
    private DAOComfact cfm;
    private DAOProduit pm;
    private ClientPresenter cp;
    private ComfactPresenter cfp;
    private ProduitPresenter pp;
    private ClientViewInterface cv;
    private ComfactViewInterface cfv;
    private ProduitViewInterface pv;


    public void gestion(){
        //cm = new ClientModelDB();
        cm = new ClientModelHyb();
        cfm = new ComfactModelDB();
        pm=new ProduitModelDB();

        cv = new ClientViewConsole();
        cfv = new ComfactViewConsole();
        pv =  new ProduitViewConsole();

        cp = new ClientPresenter(cm,cv);//création et injection de dépendance
        cfp = new ComfactPresenter(cfm,cfv);
        pp= new ProduitPresenter(pm,pv);

        cfp.setClientPresenter(cp);
        cfp.setProduitPresenter(pp);

        List<String> loptions = Arrays.asList("clients","commandes","produits","fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch){
                case 1: cp.start();
                        break;
                case 2 : cfp.start();
                        break;
                case 3: pp.start();
                        break;
                case 4: System.exit(0);
            }
        }while(true);
    }
    public static void main(String[] args) {
      GestMagasin gm = new GestMagasin();
       gm.gestion();
    }
}

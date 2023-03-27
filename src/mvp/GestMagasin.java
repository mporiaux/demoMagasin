package mvp;

import mvp.model.*;
import mvp.presenter.ClientPresenter;
import mvp.presenter.ComfactPresenter;
import mvp.view.ClientViewConsole;
import mvp.view.ClientViewInterface;
import mvp.view.ComfactViewConsole;
import mvp.view.ComfactViewInterface;
import utilitaires.Utilitaire;

import java.util.Arrays;
import java.util.List;

public class GestMagasin {
    private DAOClient cm;
    private DAOComfact cfm;
    private ClientPresenter cp;
    private ComfactPresenter cfp;
    private ClientViewInterface cv;
    private ComfactViewInterface cfv;


    public void gestion(){
        //cm = new ClientModelDB();
        cm = new ClientModelHyb();
        cfm = new ComfactModelDB();
        cv = new ClientViewConsole();
        cfv = new ComfactViewConsole();
        cp = new ClientPresenter(cm,cv);//création et injection de dépendance
        cfp = new ComfactPresenter(cfm,cfv);
        cfp.setClientPresenter(cp);
        List<String> loptions = Arrays.asList("clients","commandes","fin");
        do {
            int ch = Utilitaire.choixListe(loptions);
            switch (ch){
                case 1: cp.start();
                        break;
                case 2 : cfp.start();
                        break;
                case 3 : System.exit(0);
            }
        }while(true);
    }
    public static void main(String[] args) {
      GestMagasin gm = new GestMagasin();
       gm.gestion();
    }
}

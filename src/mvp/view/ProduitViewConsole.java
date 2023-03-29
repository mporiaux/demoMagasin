package mvp.view;

import magasin.metier.Produit;
import mvp.presenter.ProduitPresenter;
import static utilitaires.Utilitaire.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ProduitViewConsole implements ProduitViewInterface {
    private ProduitPresenter presenter;
    private List<Produit>lp;
    private Scanner sc = new Scanner(System.in);
    public ProduitViewConsole() {

    }

    @Override
    public void setPresenter(ProduitPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List<Produit> produits) {

        this.lp=produits;
        affListe(lp);
        menu();
    }

    @Override
    public void affMsg(String msg) {
        System.out.println("information:"+msg);
    }

    @Override
    public void affList(List infos) {
        affListe(infos);
    }


    public void menu(){
        do{
           int ch = choixListe(Arrays.asList("ajout", "retrait", "rechercher", "modifier", "fin"));

            switch(ch){
                case 1: ajouter();
                        break;
                case 2 : retirer();
                        break;
                case 3: rechercher();
                    break;
                case 4 : modifier();
                    break;
                case 5 : return;
            }
        }while(true);
    }


    private void modifier() {
        int nl = choixElt(lp);

            Produit pr = lp.get(nl-1);
            String numprod= modifyIfNotBlank("numéro de produit",pr.getNumprod());
            String description = modifyIfNotBlank("description",pr.getDescription());
            BigDecimal phtva = new BigDecimal(modifyIfNotBlank("prix HTVA",""+pr.getPhtva()));
            int stock = Integer.parseInt(modifyIfNotBlank("stock",""+pr.getStock()));
            int stockMin= Integer.parseInt(modifyIfNotBlank("stock min",""+pr.getStockMin()));
           presenter.update(new Produit(pr.getIdproduit(),numprod,description,phtva,stock,stockMin));
            lp=presenter.getAll();//rafraichissement
            affListe(lp);
    }

    private void rechercher() {
        System.out.println("idProduit : ");
        int idProduit = sc.nextInt();
        presenter.search(idProduit);
    }

    private void retirer() {

    int nl = choixElt(lp);
    Produit pr = lp.get(nl-1);
     presenter.removeProduit(pr);
     lp=presenter.getAll();//rafraichissement
     affListe(lp);
    }

    private void ajouter() {
        String numprod= modifyIfNotBlank("numéro de produit","");
        String description = modifyIfNotBlank("description","");
        BigDecimal phtva = new BigDecimal(modifyIfNotBlank("prix HTVA",""));
        int stock = Integer.parseInt(modifyIfNotBlank("stock",""));
        int stockMin= Integer.parseInt(modifyIfNotBlank("stock min",""));
        presenter.addProduit(new Produit(0,numprod,description,phtva,stock,stockMin)) ;
        lp=presenter.getAll();//rafraichissement
        affListe(lp);
    }

    @Override
    public Produit selectionner(List<Produit> lp){
         int nl =  choixListe(lp);
         Produit pr = lp.get(nl-1);
            return pr;
        }
}


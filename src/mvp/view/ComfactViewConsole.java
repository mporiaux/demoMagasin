package mvp.view;

import magasin.metier.Client;
import magasin.metier.ComFact;
import mvp.presenter.ClientPresenter;
import mvp.presenter.ComfactPresenter;
import utilitaires.Utilitaire;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ComfactViewConsole implements ComfactViewInterface {
    private ComfactPresenter presenter;

    private List<ComFact >lcf;
    private Scanner sc = new Scanner(System.in);
    public ComfactViewConsole() {

    }

    @Override
    public void setPresenter(ComfactPresenter presenter) {
        this.presenter = presenter;
    }


    @Override
    public void setListDatas(List<ComFact> comfacts) {

        this.lcf=comfacts;
        Utilitaire.affListe(lcf);
        menu();
    }

    @Override
    public void affMsg(String msg) {
        System.out.println("information:"+msg);
    }

    @Override
    public void affList(List infos) {
        Utilitaire.affListe(infos);
    }


    public void menu(){
        do{
            System.out.println("1.ajout 2.retrait 3.rechercher 4.modifier 5.fin");

            int ch = sc.nextInt();
            sc.skip("\n");
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
        int nl = Utilitaire.choixElt(lcf);
            ComFact cf = lcf.get(nl-1);
            Integer numfact = Integer.parseInt(modifyIfNotBlank("numéro de facture ",cf.getNumfact()+""));
            String date = modifyIfNotBlank("date de facturation ",cf.getDateFacturation()+"");
            LocalDate datefact = !date.equals("null")?LocalDate.parse(date):null;
            date=modifyIfNotBlank("date de payement ",cf.getDatePayement()+"");
            LocalDate datepay = !date.equals("null")?LocalDate.parse(date):null;
            BigDecimal montant = new BigDecimal(modifyIfNotBlank("montant",cf.getMontant().toString())).setScale(2,RoundingMode.HALF_UP);
            char etat  = modifyIfNotBlank("état",""+cf.getEtat()).charAt(0);
            ComFact ncf = new ComFact(cf.getIdcommande(),numfact,cf.getDatecom(),etat,montant,cf.getClient());
            ncf.setDateFacturation(datefact);
            ncf.setDatePayement(datepay);
            presenter.update(ncf);
            lcf=presenter.getAll();//rafraichissement
            Utilitaire.affListe(lcf);

    }

    private void rechercher() {
        System.out.println("idcommande: ");
        int idCommande = sc.nextInt();
        presenter.search(idCommande);
    }

    private void retirer() {
            int nl = Utilitaire.choixElt(lcf);
            ComFact cf = lcf.get(nl-1);
            presenter.removeComfact(cf);
        lcf=presenter.getAll();//rafraichissement
        Utilitaire.affListe(lcf);
    }

    private void ajouter() {
        presenter.addComfact();
        lcf=presenter.getAll();//rafraichissement
        Utilitaire.affListe(lcf);
    }
    public String modifyIfNotBlank(String label,String oldValue){
        System.out.println(label+" : "+oldValue);
        System.out.print("nouvelle valeur (enter si pas de changement) : ");
        String newValue= sc.nextLine();
        if(newValue.isBlank()) return oldValue;
        return newValue;
    }
}


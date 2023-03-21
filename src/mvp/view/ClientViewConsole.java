package mvp.view;

import magasin.metier.Client;
import mvp.presenter.ClientPresenter;
import utilitaires.Utilitaire;

import java.util.List;
import java.util.Scanner;

public class ClientViewConsole implements ClientViewInterface {
    private ClientPresenter presenter;
    private List<Client>lc;
    private Scanner sc = new Scanner(System.in);
    public ClientViewConsole() {

    }

    @Override
    public void setPresenter(ClientPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void setListDatas(List<Client> clients) {

        this.lc=clients;
        Utilitaire.affListe(lc);
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
            System.out.println("1.ajout 2.retrait 3.rechercher 4.modifier 5.special 6.fin");

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
                case 5: special();
                    break;
                case 6 : return;
            }
        }while(true);
    }
    private void special() {
        System.out.println("numéro de ligne : ");
        int nl =  sc.nextInt()-1;
        sc.skip("\n");
        if (nl >= 0) {
            Client client = lc.get(nl);
            do {
                System.out.println("1.commandes en cours\n2.factures non payees\n3.factures en retard\n4.factures payees\n5.produits achetés\n6.menu principal");
                System.out.println("choix : ");
                int ch = sc.nextInt();
                sc.skip("\n");
                switch (ch) {
                    case 1:
                        presenter.commandes(client);
                        break;
                    case 2:
                        presenter.factNonPayees(client);
                        break;
                    case 3:
                        presenter.factRetard(client);
                        break;
                    case 4:
                        presenter.factPayees(client);
                        break;
                    case 5:
                        presenter.produits(client);
                        break;

                    case 6: return;
                    default:
                        System.out.println("choix invalide recommencez ");
                }
            } while (true);


        }
    }

    private void modifier() {
        int nl = Utilitaire.choixElt(lc);

            Client client = lc.get(nl-1);
            String nom = modifyIfNotBlank("nom",client.getNom());
            String prenom = modifyIfNotBlank("prénom",client.getPrenom());
            int cp = Integer.parseInt(modifyIfNotBlank("cp",""+client.getCp()));
            String localite = modifyIfNotBlank("localité",client.getLocalite());
            String rue = modifyIfNotBlank("rue",client.getRue());
            String num = modifyIfNotBlank("num",client.getNum());
            String tel = modifyIfNotBlank("nom",client.getTel());
            presenter.update(new Client(client.getIdclient(),nom,prenom,cp,localite,rue,num,tel));
            lc=presenter.getAll();//rafraichissement
            Utilitaire.affListe(lc);

    }

    private void rechercher() {
        System.out.println("idclient : ");
        int idClient = sc.nextInt();
        presenter.search(idClient);
    }

    private void retirer() {

    int nl = Utilitaire.choixElt(lc);
    Client client = lc.get(nl-1);
     presenter.removeClient(client);
     lc=presenter.getAll();//rafraichissement
     Utilitaire.affListe(lc);
    }

    private void ajouter() {
        System.out.print("nom : ");
        String nom = sc.nextLine();
        System.out.print("prenom: ");
        String prenom = sc.nextLine();
        System.out.print("cp: ");
        int cp = Integer.parseInt(sc.nextLine());
        System.out.print("localité : ");
        String loc = sc.nextLine();
        System.out.print("rue: ");
        String rue= sc.nextLine();
        System.out.print("numéro: ");
        String num= sc.nextLine();
        System.out.print("téléphone: ");
        String tel= sc.nextLine();
        presenter.addClient(new Client(0,nom,prenom,cp,loc,rue,num,tel));
        lc=presenter.getAll();//rafraichissement
        Utilitaire.affListe(lc);
    }
    public String modifyIfNotBlank(String label,String oldValue){
        System.out.println(label+" : "+oldValue);
        System.out.print("nouvelle valeur (enter si pas de changement) : ");
        String newValue= sc.nextLine();
        if(newValue.isBlank()) return oldValue;
        return newValue;
    }
    @Override
    public Client selectionner(List<Client> lc){
         int nl =  Utilitaire.choixListe(lc);
         Client client = lc.get(nl-1);
            return client;
        }
}


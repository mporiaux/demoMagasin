package demodb;

import myconnections.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Michel
 */
public class GestClients {

    private Scanner sc = new Scanner(System.in);
    private Connection dbConnect;

    public void gestion() {
        dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        do {
            System.out.println("1.ajout\n2.recherche\n3.modification\n4.suppression\n5.fin");
            System.out.println("choix : ");
            int ch = sc.nextInt();
            sc.skip("\n");
            switch (ch) {
                case 1:
                    ajout();
                    break;
                case 2:
                    recherche();
                    break;
                case 3:
                    modification();
                    break;
                case 4:
                    suppression();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("choix invalide recommencez ");
            }
        } while (true);

    }

    public void ajout() {

        System.out.print("nom :");
        String nom = sc.nextLine();
        System.out.print("prénom :");
        String prenom = sc.nextLine();
        System.out.print("cp :");
        Integer cp = sc.nextInt();
        sc.skip("\n");
        System.out.print("localite :");
        String loc = sc.nextLine();
        System.out.print("rue :");
        String rue = sc.nextLine();
        System.out.print("numéro :");
        String num = sc.nextLine();
        System.out.print("tel :");
        String tel = sc.nextLine();
        String query1 = "insert into APICLIENT(nom,prenom,cp,localite,rue,num,tel) values(?,?,?,?,?,?,?)";
        String query2 = "select idclient from APICLIENT where nom= ? and prenom =? and tel =?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
            ){
            pstm1.setString(1,nom);
            pstm1.setString(2,prenom);
            pstm1.setInt(3,cp);
            pstm1.setString(4,loc);
            pstm1.setString(5,rue);
            pstm1.setString(6,num);
            pstm1.setString(7,tel);
            int n = pstm1.executeUpdate();
            System.out.println(n+" ligne insérée");
            if(n==1){
                pstm2.setString(1,nom);
                pstm2.setString(2,prenom);
                pstm2.setString(3,tel);
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int idclient= rs.getInt(1);
                    System.out.println("idclient = "+idclient);
                }
                else System.out.println("record introuvable");
                rs.close();
            }

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }
      }


    public void recherche() {

        System.out.println("id du client recherché ");
        int idrech = sc.nextInt();
        String query = "select * from APICLIENT where idclient = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idrech);
            ResultSet rs = pstm.executeQuery();
            if(rs.next()){
                String nom = rs.getString(2);
                String prenom = rs.getString(3);
                int cp = rs.getInt(4);
                String loc = rs.getString(5);
                String rue= rs.getString(6);
                String num = rs.getString(7);
                String tel = rs.getString(8);
                System.out.printf("%d %s %s %d %s %s %s %s\n",idrech,nom,prenom,cp,loc,rue,num,tel);
            }
            else System.out.println("record introuvable");
            rs.close();
        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }

    }

    public void modification() {
        System.out.println("id du client recherché ");
        int idrech = sc.nextInt();
        sc.skip("\n");
        System.out.println("nouveau téléphone ");
        String ntel = sc.nextLine();
        String query = "update APICLIENT set tel=? where idclient = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setString(1,ntel);
            pstm.setInt(2,idrech);
            int n = pstm.executeUpdate();
            if(n!=0) System.out.println(n+ "ligne mise à jour");
            else System.out.println("record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :" + e);
        }
    }
    public void suppression() {
        System.out.println("id du client recherché ");
        int idrech = sc.nextInt();
        String query = "delete from APICLIENT where idclient = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,idrech);
            int n = pstm.executeUpdate();
            if(n!=0) System.out.println(n+ "ligne supprimée");
            else System.out.println("record introuvable");

        } catch (SQLException e) {
            System.out.println("erreur sql :"+e);
        }

    }



    public static void main(String[] args) {

        GestClients g = new GestClients();
        g.gestion();
    }

}

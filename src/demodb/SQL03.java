package demodb;

import java.sql.*;
import myconnections.DBConnection;
import java.util.*;

public class SQL03 {

    public void demo() {

        Scanner sc = new Scanner(System.in);
        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        System.out.println("Nom du client recherche : ");
        String nomrech = sc.nextLine();
        System.out.println("prenom du client recheche : ");
        String pnomrech = sc.nextLine();

        try (Statement stmt = dbConnect.createStatement();
             ResultSet rs = stmt.executeQuery(
                        "SELECT *  FROM APICLIENT WHERE NOM = '"
                        + nomrech + "' AND PRENOM = '" + pnomrech + "'");) {
            boolean trouve = false;
            while (rs.next()) {
                trouve = true;
                String localite = rs.getString("LOCALITE");
                int n = rs.getInt("IDCLIENT");
                System.out.println(nomrech + " " + pnomrech + ":" + n + " " + localite);
            }
            if (!trouve) {
                System.out.println("client inconnu");
            }

        } catch (SQLException e) {
            System.out.println("erreur SQL =" + e);
        }
        DBConnection.closeConnection();
    }

    public static void main(String[] args) {
        SQL03 pgm = new SQL03();
        pgm.demo();
    }
}

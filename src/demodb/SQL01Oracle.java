package demodb;
import java.sql.*;

public class SQL01Oracle {

    public static void main(String args[]) {
        String userid = "ora5";
        String password = "oracle";
        String server = "mons-oracle19.condorcet.be";
        String port = "1521";
        String database = "orcl.condorcet.be";
        String url = "jdbc:oracle:thin:@//" + server + ":" + port + "/" + database;//construit l'URL de la base de données
        Connection dbConnect=null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            dbConnect = DriverManager.getConnection(url, userid, password);
            // connexion avec le login et le password
            // et récupération d'un objet connection
        }
        catch (Exception e){
            System.out.println("erreur : "+e);
            System.exit(0);
        }

       try(
            Statement stmt = dbConnect.createStatement();
            //représente une requête SQL
            ResultSet rs = stmt.executeQuery("select * from apiclient ");
            //récupération des données à partir de la table client
            //ensemble des lignes répondant à la requête
         )
        {
           while (rs.next()) {
                String nom = rs.getString("NOM");
                //ou rs.getString(2);
                String prenom = rs.getString("PRENOM");
                //ou rs.getString(3);
                int n = rs.getInt("IDCLIENT");
                //ou   int n=  rs.getInt(1) ;
                System.out.println(nom + " " + prenom + " " + n);
            }
        } catch (SQLException e) {
            System.out.println("erreur SQL " + e);
        } catch (Exception e) {
            System.out.println("erreur " + e);
        }
        try{
            dbConnect.close();
        } catch (SQLException e) {
            System.out.println("erreur de fermeture de connexion "+e);
        }
    }
}

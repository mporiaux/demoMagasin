package mvp.model;

import magasin.metier.Client;
import myconnections.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClientModelDB implements DAOClient{
    private Connection dbConnect;

    public ClientModelDB(){
        dbConnect = DBConnection.getConnection();
            if (dbConnect == null) {
                System.err.println("erreur de connexion");
                System.exit(1);
            }
    }

    @Override
    public Client addClient(Client client) {
        String query1 = "insert into APICLIENT(nom,prenom,cp,localite,rue,num,tel) values(?,?,?,?,?,?,?)";
        String query2 = "select idclient from APICLIENT where nom= ? and prenom =? and tel =?";
        try(PreparedStatement pstm1= dbConnect.prepareStatement(query1);
            PreparedStatement pstm2= dbConnect.prepareStatement(query2);
        ){
            pstm1.setString(1,client.getNom());
            pstm1.setString(2,client.getPrenom());
            pstm1.setInt(3,client.getCp());
            pstm1.setString(4,client.getLocalite());
            pstm1.setString(5,client.getRue());
            pstm1.setString(6,client.getNum());
            pstm1.setString(7,client.getTel());
            int n = pstm1.executeUpdate();
              if(n==1){
                pstm2.setString(1,client.getNom());
                pstm2.setString(2,client.getPrenom());
                pstm2.setString(3,client.getTel());
                ResultSet rs= pstm2.executeQuery();
                if(rs.next()){
                    int idclient= rs.getInt(1);
                     client.setIdclient(idclient);
                     return client;
                }
                else {
                    System.err.println("record introuvable");
                    return null;
                }
            }
              else return null;

        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return null;
        }
    }

    @Override
    public boolean removeClient(Client client) {
        String query = "delete from APICLIENT where idclient = ?";
        try(PreparedStatement pstm = dbConnect.prepareStatement(query)) {
            pstm.setInt(1,client.getIdclient());
            int n = pstm.executeUpdate();
            if(n!=0) return true;
            else return false;

        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return false;
        }
    }

    @Override
    public List<Client> getClients() {
        List<Client> lc = new ArrayList<>();
        String query="select * from APICLIENT";
        try(Statement stm = dbConnect.createStatement()) {
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int idclient = rs.getInt(1);
                String nom = rs.getString(2);
                String prenom = rs.getString(3);
                int cp = rs.getInt(4);
                String loc = rs.getString(5);
                String rue= rs.getString(6);
                String num = rs.getString(7);
                String tel = rs.getString(8);
                Client cl = new Client(idclient,nom,prenom,cp,loc,rue,num,tel);
               lc.add(cl);
            }
          return lc;
        } catch (SQLException e) {
            System.err.println("erreur sql :"+e);
            return null;
        }
    }
}

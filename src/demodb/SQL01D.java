package demodb;

import myconnections.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL01D {

    public void demo(){

        Connection dbConnect = DBConnection.getConnection();
        if (dbConnect == null) {
            System.exit(1);
        }
        System.out.println("connexion établie");
        String query = "select * from APIPRODUIT";
        try (Statement stmt = dbConnect.createStatement();
                ResultSet rs = stmt.executeQuery(query);) {

            while (rs.next()) {
                String code = rs.getString(2);
                System.out.println(code.trim()+"|");
            }
        } catch (SQLException e) {
            System.out.println("erreur SQL " + e);
        }

        DBConnection.closeConnection();
    }

    public static void main(String[] args) {
        SQL01D pgm = new SQL01D();
        pgm.demo();
    }
}

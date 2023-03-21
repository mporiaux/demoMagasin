package mvp.model;

import magasin.metier.Client;
import magasin.metier.ComFact;
import magasin.metier.Produit;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ClientSpecial {
    public List<ComFact> factPayees(Client client);

    public List<ComFact> factRetard(Client client);

    public List<ComFact> factNonPayees(Client client);


    public List<ComFact> commandes(Client client);

    public List<Produit> produits(Client client);
}

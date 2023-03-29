package mvp.model;

import magasin.metier.ComFact;
import magasin.metier.Produit;

import java.util.List;

public interface DAOProduit {
    Produit addProduit(Produit produit);

    boolean removeProduit(Produit produit);

    Produit updateProduit(Produit produit);

   Produit readProduit(int idProduit);

    List<Produit> getProduits();
}

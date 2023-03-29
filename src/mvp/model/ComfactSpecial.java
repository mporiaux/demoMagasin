package mvp.model;

import magasin.metier.Client;
import magasin.metier.ComFact;
import magasin.metier.Produit;

import java.util.List;

public interface ComfactSpecial {
  public boolean addProd(ComFact cf,Produit pr,int q);
}

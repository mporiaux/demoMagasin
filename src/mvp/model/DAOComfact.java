package mvp.model;

import magasin.metier.Client;
import magasin.metier.ComFact;

import java.util.List;

public interface DAOComfact {
    ComFact addComfact(ComFact comFact);

    boolean removeComfact(ComFact comFact);

    ComFact updateComfact(ComFact comFact);

    ComFact readComfact(int idComfact);

    List<ComFact> getComfacts();
}

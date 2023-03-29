package mvp.view;

import magasin.metier.Client;
import magasin.metier.Produit;
import mvp.presenter.ClientPresenter;
import mvp.presenter.ProduitPresenter;

import java.util.List;

public interface ProduitViewInterface {
    public void setPresenter(ProduitPresenter presenter);

    public void setListDatas(List<Produit> produits);

    public void affMsg(String msg);

    public void affList(List infos);

    public Produit selectionner(List<Produit>lp);
}

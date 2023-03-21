package mvp.view;

import magasin.metier.Client;
import magasin.metier.ComFact;
import mvp.presenter.ClientPresenter;
import mvp.presenter.ComfactPresenter;

import java.util.List;

public interface ComfactViewInterface {
    public void setPresenter(ComfactPresenter presenter);

    public void setListDatas(List<ComFact> comfacts);

    public void affMsg(String msg);

    public void affList(List infos);
}

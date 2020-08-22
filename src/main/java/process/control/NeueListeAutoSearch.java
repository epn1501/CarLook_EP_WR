package process.control;

import model.dao.AutoDAO;
import model.dao.NeueListeDAO;
import model.objects.dto.Auto;
import model.objects.dto.NeueListeAuto;

import java.util.List;

public class NeueListeAutoSearch {

    public static NeueListeAutoSearch search = null;

    private NeueListeAutoSearch(){

    }

    public static NeueListeAutoSearch getInstance(){
        if (search == null){
            search = new NeueListeAutoSearch();
        }
        return search;
    }

    public List<NeueListeAuto> getAutoById (String marke){

        return NeueListeDAO.getInstance().getAutoById(marke);



    }
}

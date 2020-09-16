package process.control;

import model.dao.AutoDAO;
import model.objects.dto.Auto;
import java.util.List;

public class AutoSearch {
    /*
    Auto auto1 = new Auto("BMW", 1, 195, 2002, "Gepflegtes Auto");
    Auto auto2 = new Auto("BMW", 2, 150, 2005, "unfallfrei");
    Auto auto3 = new Auto("BMW", 3, 265, 2015, "mit vielen Extras");
    Auto auto4 = new Auto("BMW", 4, 135, 2012, "nur 1 Fahrzeughalter");
    Auto auto5 = new Auto("Audi", 5, 252, 2020, "Gepflegtes Auto");
    Auto auto6 = new Auto("Audi", 6, 201, 2013, "unfallfrei");
    */
    public static AutoSearch search = null;

    private AutoSearch(){
    }

    public static AutoSearch getInstance(){
        if (search == null){
            search = new AutoSearch();
        }
        return search;
    }

    public List<Auto> getAutoByMarke (String marke){
        return AutoDAO.getInstance().getAutoByBrand(marke);

        /*
        ArrayList<Auto> liste = new ArrayList<Auto>();
        if(marke.equals("BMW") || marke.equals("bmw")) liste.add(auto1);
        if(marke.equals("BMW") || marke.equals("bmw")) liste.add(auto2);
        if(marke.equals("BMW") || marke.equals("bmw")) liste.add(auto3);
        if(marke.equals("BMW") || marke.equals("bmw")) liste.add(auto4);
        if(marke.equals("Audi") || marke.equals("audi")) liste.add(auto5);
        if(marke.equals("Audi") || marke.equals("audi")) liste.add(auto6);
        return liste;
        */
    }

    public List<Auto> getAutoAll (){
        return AutoDAO.getInstance().getAutoByAll();
    }
}
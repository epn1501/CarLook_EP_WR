package process.control;

import model.dao.AutoDAO;
import model.objects.dto.Auto;

import java.util.ArrayList;
import java.util.List;

public class AutoSearch {
    /*
    Auto auto1 = new Auto("BMW", 1, 195, 2002, "Gepflegtes Auto");
    Auto auto2 = new Auto("BMW", 2, 150, 2005, "unfallfrei");
    Auto auto3 = new Auto("BMW", 3, 265, 2015, "mit vielen Extras");
    Auto auto4 = new Auto("BMW", 4, 135, 2012, "nur 1 Fahrzeughalter");

    Auto auto5 = new Auto("Audi", 5, 252, 2020, "Gepflegtes Auto");
    Auto auto6 = new Auto("Audi", 6, 201, 2013, "unfallfrei");
    Auto auto7 = new Auto("Audi", 7, 190, 2016, "mit vielen Extras");
    Auto auto8 = new Auto("Audi", 8, 116, 2019, "nur 1 Fahrzeughalter");

    Auto auto9 = new Auto("Mercedes Benz", 9, 315, 2007, "Gepflegtes Auto");
    Auto auto10 = new Auto("Mercedes Benz", 10, 272, 2011, "unfallfrei");
    Auto auto11 = new Auto("Mercedes Benz", 11, 210, 2018, "mit vielen Extras");
    Auto auto12 = new Auto("Mercedes Benz", 12, 225, 2020, "nur 1 Fahrzeughalter");
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
        if(marke.equals("Audi") || marke.equals("audi")) liste.add(auto7);
        if(marke.equals("Audi") || marke.equals("audi")) liste.add(auto8);
        if(marke.equals("Mercedes Benz") || marke.equals("benz")) liste.add(auto9);
        if(marke.equals("Mercedes Benz") || marke.equals("benz")) liste.add(auto10);
        if(marke.equals("Mercedes Benz") || marke.equals("benz")) liste.add(auto11);
        if(marke.equals("Mercedes Benz") || marke.equals("benz")) liste.add(auto12);

        return liste;
        */
    }

    public List<Auto> getAutoAll (){
        return AutoDAO.getInstance().getAutoByAll();
    }

}

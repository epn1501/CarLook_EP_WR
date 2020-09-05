package process.control;


import model.dao.NeueListeDAO;
import model.objects.dto.NeueListe;
import java.util.List;

public class NeueListeSearch {

    public static NeueListeSearch search = null;

    private NeueListeSearch(){

    }

    public static NeueListeSearch getInstance(){
        if (search == null){
            search = new NeueListeSearch();
        }
        return search;
    }


    public  List<NeueListe> getNeueListeAll(){
        return NeueListeDAO.getInstance().getNeueListeByAll();
    }

}

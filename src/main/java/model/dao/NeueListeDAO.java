package model.dao;

import model.objects.dto.NeueListe;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NeueListeDAO extends AbstractDAO {

    public static NeueListeDAO dao = null;

    private NeueListeDAO(){

    }

    public static NeueListeDAO getInstance(){
        if(dao == null){
            dao = new NeueListeDAO();
        }

        return dao;
    }

    /*
    public List<NeueListeAuto> getAutoById (String marke){
        Statement statement = this.getStatement();
        ResultSet rs = null;

        try{
            rs = statement.executeQuery("SELECT *"
                    + "FROM carlookew.neueListe ");
        }catch (SQLException ex){

        }

        if( rs == null ){
            return null;
        }

        List<NeueListeAuto> liste = new ArrayList<NeueListeAuto>();
        NeueListeAuto neueListeAuto = null;

        try{
            while (rs.next()){
                neueListeAuto = new NeueListeAuto();
                neueListeAuto.setId(rs.getInt(1));
                neueListeAuto.setMarke(rs.getString(2));
                neueListeAuto.setPs(rs.getInt(3));
                neueListeAuto.setBaujahr(rs.getInt(4));
                neueListeAuto.setDescription(rs.getString(5));
                liste.add(neueListeAuto);
            }
        }catch (SQLException ex){

        }
        return liste;

    }
    */

    public List<NeueListe> getNeueListeByAll(){

        Statement statement = this.getStatement();

        ResultSet rs = null;

        try{
            rs = statement.executeQuery("SELECT *"
                    + "FROM carlookwr.neueliste ");
        }catch (SQLException ex){

        }

        if( rs == null ){
            return null;
        }

        List<NeueListe> liste = new ArrayList<NeueListe>();
        NeueListe neueListe = null;

        try{
            while (rs.next()){
                neueListe = new NeueListe();
                neueListe.setId(rs.getInt(1));
                neueListe.setMarke(rs.getString(2));
                neueListe.setPs(rs.getInt(3));
                neueListe.setBaujahr(rs.getInt(4));
                neueListe.setDescription(rs.getString(5));
                liste.add(neueListe);
            }
        }catch (SQLException ex){

        }
        return liste;


    }

}

package model.dao;

import model.objects.dto.Auto;
import model.objects.dto.NeueListeAuto;

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


    public List<NeueListeAuto> getAutoById (String marke){
        Statement statement = this.getStatement();
        ResultSet rs = null;

        try{
            rs = statement.executeQuery("SELECT *"
                    + "FROM carlookew.neueListe "
                    + "WHERE carlookew.neueListe.marke = \'" + marke + "\'");
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

}

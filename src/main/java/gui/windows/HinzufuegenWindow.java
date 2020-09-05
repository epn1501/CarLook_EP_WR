package gui.windows;

import com.vaadin.navigator.View;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import model.objects.dto.Auto;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import com.vaadin.ui.*;
import process.control.AutoSearch;
import services.util.Views;

public class HinzufuegenWindow extends Window {


    public HinzufuegenWindow(Auto auto) throws SQLException {
        super("Autos der CarLook ltd.");
        center();
        //setSizeFull();

        VerticalLayout content = new VerticalLayout();




    /*
        Auto autos = new Auto();
        autos.setId(100);
        autos.setMarke("Porsche");
        autos.setPs(450);
        autos.setBaujahr(2014);
        autos.setDescription("Unfallfrei");
*/

        List<Auto> liste = AutoSearch.getInstance().getAutoAll();
        Grid<Auto> grid = new Grid<>("Liste aller Autos");
        grid.addColumn(Auto::getId).setCaption("ID").setMaximumWidth(70);
        grid.addColumn(Auto::getMarke).setCaption("Marke");
        grid.addColumn(Auto::getPs).setCaption("PS");
        grid.addColumn(Auto::getBaujahr).setCaption("Baujahr");
        grid.addColumn(Auto::getDescription).setCaption("Description");

        //Changing the local affects
        grid.setLocale(Locale.GERMANY);



        grid.setItems(liste);
        grid.setSizeFull();



        // new feature goind into vaadin: column reordering
        grid.setColumnReorderingAllowed(true);


        content.addComponent(grid);
        content.setMargin(true);
        setContent(content);
        setClosable(true);



        content.setSizeFull();





    }
}

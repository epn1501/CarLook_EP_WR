package gui.windows;

import com.vaadin.ui.Window;
import model.objects.dto.Auto;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import com.vaadin.ui.*;
import process.control.AutoSearch;

public class HinzufuegenWindow extends Window {


    public HinzufuegenWindow(Auto auto) throws SQLException {
        super("Autos der CarLook Ltd.");
        center();

        VerticalLayout content = new VerticalLayout();

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
        // new feature going into vaadin: column reordering
        grid.setColumnReorderingAllowed(true);

        content.addComponent(grid);
        content.setMargin(true);
        setContent(content);
        setClosable(true);

        content.setSizeFull();
    }
}
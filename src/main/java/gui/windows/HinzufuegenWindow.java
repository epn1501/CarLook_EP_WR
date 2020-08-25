package gui.windows;

import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import model.objects.dto.Auto;

import java.awt.*;
import java.sql.SQLException;

import com.vaadin.ui.*;
import model.objects.dto.NeueListeAuto;

public class HinzufuegenWindow extends Window {


    public HinzufuegenWindow(Auto auto) throws SQLException {
        super("Hinzufügen von neuen Autos");
        center();
        //setSizeFull();

        VerticalLayout content = new VerticalLayout();
        //Grid<NeueListeAuto> grid = new Grid<>("Add");


        Grid<Auto> grid = new Grid<>("Add");
        grid.addColumn(Auto::getId).setCaption("ID");
        grid.addColumn(Auto::getMarke).setCaption("Marke");
        grid.addColumn(Auto::getPs).setCaption("PS");
        grid.addColumn(Auto::getBaujahr).setCaption("Baujahr");
        grid.addColumn(Auto::getDescription).setCaption("Description");

        grid.setItems();

        // new feature goind into vaadin: column reordering
        grid.setColumnReorderingAllowed(true);

        grid.getDataCommunicator().fetchItemsWithRange(0, grid.getDataCommunicator().getDataProviderSize());

        content.addComponent(grid);
        content.setMargin(true);
        setContent(content);
        setClosable(true);

        Button add = new Button("Add");
        add.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Notification.show("Ein neues Auto Objekt wurde Hinzugefügt!");
            }
        });


        content.addComponent(add);
        content.setComponentAlignment(add, Alignment.MIDDLE_CENTER);



    }
}

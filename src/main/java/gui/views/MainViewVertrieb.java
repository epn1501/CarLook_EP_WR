package gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import model.objects.dto.Auto;
import process.control.AutoSearch;

import java.sql.SQLException;
import java.util.List;

public class MainViewVertrieb extends VerticalLayout implements View {

    public void setUp(){

            this.setSizeFull();


            VerticalLayout content = new VerticalLayout();


            List<Auto> liste = AutoSearch.getInstance().getAutoAll();


            Grid<Auto> grid = new Grid<>("Liste aller Autos");
            grid.addColumn(Auto::getId).setCaption("ID");
            grid.addColumn(Auto::getMarke).setCaption("Marke");
            grid.addColumn(Auto::getPs).setCaption("PS");
            grid.addColumn(Auto::getBaujahr).setCaption("Baujahr");
            grid.addColumn(Auto::getDescription).setCaption("Description");

            grid.setItems(liste);
            grid.setSizeFull();

            // new feature goind into vaadin: column reordering
            grid.setColumnReorderingAllowed(true);

            grid.getDataCommunicator().fetchItemsWithRange(0, grid.getDataCommunicator().getDataProviderSize());





            content.addComponent(grid);
            content.setMargin(true);


            Button add = new Button("Add");
            add.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    Notification.show("Ein neues Auto Objekt wurde Hinzugefügt!");
                }
            });

            content.setSizeFull();

            content.addComponent(add);
            content.setComponentAlignment(add, Alignment.MIDDLE_CENTER);





    }



    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event){


        this.setUp();
    }
}

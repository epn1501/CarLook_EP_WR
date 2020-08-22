package gui.windows;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.widget.grid.DetailsGenerator;
import com.vaadin.client.widget.grid.RowReference;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.event.*;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.ItemClickListener;
import gui.views.MainView;
import model.objects.dto.Auto;
import model.objects.dto.NeueListeAuto;
import org.junit.jupiter.api.Order;
import process.control.AutoSearch;
import process.control.NeueListeAutoSearch;

import java.lang.annotation.Inherited;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class ReservierungsWindow extends Window {


    public ReservierungsWindow(Auto auto) {
        super("Reservierungsliste");
        center();


        NeueListeAuto liste = new NeueListeAuto();
        liste.setId(99);
        //liste.setId();
        liste.setMarke("Honda");
        liste.setPs(120);
        liste.setBaujahr(1990);
        liste.setDescription("Guter Zustand");

        /*
        NeueListeAuto liste2 = new NeueListeAuto();
        liste.setId(100);
        //liste.setId();
        liste.setMarke("Jaguar");
        liste.setPs(320);
        liste.setBaujahr(2015);
        liste.setDescription("Sportliche Ausstattung");
        */

        VerticalLayout content = new VerticalLayout();
        //content.addComponent(new Label("Reservierung"));

        Grid<NeueListeAuto> grid = new Grid<>("Auswahl");
        //List<NeueListeAuto> liste = AutoSearch.getInstance().getAutoByMarke(marke);
        //List<NeueListeAuto> liste = NeueListeAutoSearch.getInstance().getAutoById(marke);
        grid.setItems(liste);

        grid.addColumn(NeueListeAuto::getId).setCaption("ID");
        grid.addColumn(NeueListeAuto::getMarke).setCaption("Marke");
        grid.addColumn(NeueListeAuto::getPs).setCaption("PS");
        grid.addColumn(NeueListeAuto::getBaujahr).setCaption("Baujahr");
        grid.addColumn(NeueListeAuto::getDescription).setCaption("Description");

        // new feature goind into vaadin: column reordering
        grid.setColumnReorderingAllowed(true);


        //Muilit-Selection1

        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addSelectionListener(event -> {
            Set<NeueListeAuto> selected = event.getAllSelectedItems();
            Notification.show("Auto mit der ID: " + liste.getId() + " ausgewählt!");
        });
        MultiSelect<NeueListeAuto> selection = grid.asMultiSelect();

        content.addComponent(grid);
        content.setMargin(true);
        setContent(content);
        setClosable(true);



        Button delete = new Button("Delete");
        delete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                if(selection.isEmpty()){
                    Notification.show("Keine Auswahl getätigt!");
                    close();
                }
                else {
                    ListDataProvider<NeueListeAuto> dataProvider = (ListDataProvider<NeueListeAuto>) grid.getDataProvider();
                    dataProvider.getItems().remove(liste);
                    dataProvider.refreshAll();
                    Notification.show("Selektion wurde gelöscht");
                    //close();
                }
            }
        });

         Button reserverierungsButton = new Button("Reservieren");
        //reserverierungsButton.addClickListener(newevent -> close());
        reserverierungsButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                //ReservierungRequest request = new ReservierungRequest();
                //request.setAuto(auto);
                //ReservierungProcess.getInstance().createReservierung(request, ReservierungsWindow.this);

                if(selection.isEmpty()){
                    Notification.show("Keine Auswahl getätigt!");
                    close();
                }
                else {
                    Notification.show("Reservierung wurde versendet!");
                    close();
                }
            }
        });




        content.addComponents(delete, reserverierungsButton);
        content.setComponentAlignment(delete, Alignment.MIDDLE_LEFT);
        content.setComponentAlignment(reserverierungsButton, Alignment.MIDDLE_RIGHT);




    }
}

package gui.windows;


import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.*;
import model.objects.dto.NeueListe;
import process.control.NeueListeSearch;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;


public class ReservierungsWindow extends Window {


    public ReservierungsWindow(NeueListe neueListe) throws SQLException {
        super("Reservierungsliste");
        center();

        int random =(int) (Math.random()*100);
        VerticalLayout content = new VerticalLayout();


        NeueListe liste = new NeueListe();
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

        //List<NeueListe> liste = NeueListeSearch.getInstance().getNeueListeAll();
        Grid<NeueListe> grid = new Grid<>("Auswahl");
        grid.addColumn(NeueListe::getId).setCaption("ID").setMaximumWidth(70);
        grid.addColumn(NeueListe::getMarke).setCaption("Marke");
        grid.addColumn(NeueListe::getPs).setCaption("PS");
        grid.addColumn(NeueListe::getBaujahr).setCaption("Baujahr");
        grid.addColumn(NeueListe::getDescription).setCaption("Description");

        // new feature goind into vaadin: column reordering
        grid.setColumnReorderingAllowed(true);

        grid.setItems(liste);
        grid.setSizeFull();

        content.addComponent(grid);
        content.setMargin(true);
        setContent(content);
        setClosable(true);

        Button auswahl = new Button("Reservieren");

        //Muilit-Selection1
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addSelectionListener(event -> {
            Set<NeueListe> selected = event.getAllSelectedItems();
            // Notification.show("Auto mit der ID: " + liste+ " ausgewählt!");
            auswahl.setCaption("Delete!");
            auswahl.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    Notification.show("Selektion wurde gelöscht");
                }
            });
            auswahl.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    auswahl.setCaption("Reservieren!");
                    //Notification.show("Die Reservierung wurde versendet!");
                }
            });
        });

        MultiSelect<NeueListe> selection = grid.asMultiSelect();

        auswahl.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                if(selection.isEmpty()){
                    //test.setCaption("Reservieren!");
                    Notification.show("Die Reservierung wurde mit der Nummer: " + random +" versendet!");
                    close();
                }
                else {
                    ListDataProvider<NeueListe> dataProvider = (ListDataProvider<NeueListe>) grid.getDataProvider();
                    dataProvider.getItems().remove(liste);
                    dataProvider.refreshAll();
                    Notification.show("Selektion wurde gelöscht");
                    //close();
                }
            }
        });

        content.setSizeFull();
        content.addComponent(auswahl);
        content.setComponentAlignment(auswahl, Alignment.MIDDLE_CENTER);


    }
}

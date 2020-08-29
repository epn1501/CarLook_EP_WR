package gui.windows;


import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.*;
import model.dao.NeueListeDAO;
import model.objects.dto.NeueListe;
import org.atmosphere.interceptor.AtmosphereResourceStateRecovery;
import process.control.NeueListeSearch;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import services.util.Views;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;


public class ReservierungsWindow extends Window {


    public ReservierungsWindow(NeueListe neueListe) throws SQLException {
        super("Reservierungsliste");
        center();



        int random =(int) (Math.random()*100);
        VerticalLayout content = new VerticalLayout();


        /*
        NeueListe liste = new NeueListe();
        liste.setId(99);
        //liste.setId();
        liste.setMarke("Honda");
        liste.setPs(120);
        liste.setBaujahr(1990);
        liste.setDescription("Guter Zustand");
        */
        /*
        NeueListeAuto liste2 = new NeueListeAuto();
        liste.setId(100);
        //liste.setId();
        liste.setMarke("Jaguar");
        liste.setPs(320);
        liste.setBaujahr(2015);
        liste.setDescription("Sportliche Ausstattung");
        */

        List<NeueListe> liste = NeueListeSearch.getInstance().getNeueListeAll();
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

        SingleSelect<NeueListe> selection = grid.asSingleSelect();


        HorizontalLayout horizontalLayout = new HorizontalLayout();

        //Button für die Reservierung der Selektion in der Reservierungsliste
        Button reservieren = new Button("Reservieren");
        reservieren.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                if(selection.isEmpty()) {
                    Notification.show("Die Reservierung wurde versendet! Ihre Reservierungsnr.: " + random);
                    System.out.println("Die Reservierung wurde versendet! Ihre Reservierungsnr.: " + random);
                    close();
                }
                else{
                    //Notification.show("Das Auto mit der ID: " + selection.getValue().getId() + " wurde mit der Reservierungsnr: " + random +" reserviert!");
                    System.out.println("Das Auto mit der ID: " + selection.getValue().getId() + " wurde ausgewählt!");
                }
            }
        });


        //Button für die Löschung der Selektion in der Reservierungsliste
        Button delete = new Button("Delete");
        delete.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                if(!selection.isEmpty()){

                    ListDataProvider<NeueListe> dataProvider = (ListDataProvider<NeueListe>) grid.getDataProvider();
                    dataProvider.getItems().remove(liste);
                    dataProvider.refreshAll();
                    grid.getDataProvider().refreshAll();

                    NeueListeDAO.getInstance().deleteNeueListe(selection.getValue().getId());

                    Notification.show("Das Auto mit der ID: " + selection.getValue().getId() + " wurde gelöscht!");
                    System.out.println("Das Auto mit der ID: " + selection.getValue().getId() + " wurde gelöscht!");
                    close();


                }
                else {
                    System.out.println("Der Deletebutton wurde betätigt!");
                }
            }
        });



        horizontalLayout.addComponents(delete, reservieren);
        content.setSizeFull();
        content.addComponent(horizontalLayout);
        content.setComponentAlignment(horizontalLayout, Alignment.MIDDLE_CENTER);


    }
}

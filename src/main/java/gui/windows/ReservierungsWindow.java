package gui.windows;


import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.*;
import model.dao.NeueListeDAO;
import model.dao.ReservierungDAO;
import model.objects.dto.NeueListe;
import model.objects.dto.User;
import process.control.NeueListeSearch;
import com.vaadin.ui.Button;
import com.vaadin.ui.Window;
import services.util.Roles;
import java.sql.SQLException;
import java.util.List;

public class ReservierungsWindow extends Window {

    User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);


    public ReservierungsWindow(NeueListe neueListe) throws SQLException {
        super("Reservierungsliste");
        center();

        //int random =(int) (Math.random()*100);
        VerticalLayout content = new VerticalLayout();

        List<NeueListe> liste = NeueListeSearch.getInstance().getNeueListeAll();
        Grid<NeueListe> grid = new Grid<>("Auswahl");
        grid.addColumn(NeueListe::getId).setCaption("ID").setMaximumWidth(70);
        grid.addColumn(NeueListe::getMarke).setCaption("Marke");
        grid.addColumn(NeueListe::getPs).setCaption("PS");
        grid.addColumn(NeueListe::getBaujahr).setCaption("Baujahr");
        grid.addColumn(NeueListe::getDescription).setCaption("Description");
        // new feature going into vaadin: column reordering
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
                    //Notification.show("Die Reservierung wurde versendet! Ihre Reservierungsnr.: " + random);
                    //System.out.println("Die Reservierung wurde versendet! Ihre Reservierungsnr.: " + random);
                    Notification.show("Die Reservierung wurde versendet!");
                    System.out.println("Die Reservierung wurde versendet!" );

                    //Ausgabe der ReservierungsID
                    //ReservierungDAO.getInstance().getReservierungsId(user);

                    close();
                    // Die ReservierungsNr -> Reservierungs DB + user Login
                    ReservierungDAO.getInstance().createReservierung(user);
                    //DB Löschen
                    NeueListeDAO.getInstance().deleteTableNeueListe();
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
                } else {
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
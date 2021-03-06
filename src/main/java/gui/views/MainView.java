package gui.views;

import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.ItemClickListener;
import gui.components.TopPanel;
import model.dao.NeueListeDAO;
import model.objects.dto.Auto;
import model.objects.dto.NeueListe;
import model.objects.dto.User;
import process.control.AutoSearch;
import java.util.List;
import java.util.Locale;
import services.util.Roles;
import services.util.Views;

public class MainView extends VerticalLayout implements View {

    private int anzahlSuche = 0;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event){

        //Wenn User nicht vorhanden -> auf LoginView senden, wenn User vorhanden dann zur Main weiter leiten
        User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
        if(user == null){
            UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
        }else{
            this.setUp();
        }
    }

    public void setUp(){

        this.addComponent( new TopPanel());

        setMargin(true);

        final HorizontalLayout horizontalLayout = new HorizontalLayout();
        Button sucheButton = new Button("Suche", VaadinIcons.SEARCH);
        sucheButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        final TextField textField = new TextField();

        User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);

        //Vorsichtsabfrage!
        String vorname = null;
          if(user != null){
            vorname = user.getVorname();
         }
        Label labelText = new Label(vorname + ", gebe eine Marke ein: ");

        horizontalLayout.addComponent(labelText);
        horizontalLayout.setComponentAlignment(labelText, Alignment.MIDDLE_CENTER);
        horizontalLayout.addComponent(textField);
        horizontalLayout.addComponent(sucheButton);
        addComponent(horizontalLayout);
        setComponentAlignment(horizontalLayout, Alignment.BOTTOM_CENTER);

        Grid<Auto> grid = new Grid<>("Treffer");
        grid.addColumn(Auto::getId).setCaption("ID").setMaximumWidth(70);
        grid.addColumn(Auto::getMarke).setCaption("Marke");
        grid.addColumn(Auto::getPs).setCaption("PS");
        grid.addColumn(Auto::getBaujahr).setCaption("Baujahr");
        grid.addColumn(Auto::getDescription).setCaption("Description");
        grid.setSizeFull();

        SingleSelect<Auto> selection = grid.asSingleSelect();

        Button reserviereButton = new Button("Zur Reservierung hinzufügen", (Button.ClickListener) clickEvent -> {
           if(selection.isEmpty()){
               Notification.show("Bitte wählen Sie ein Auto aus!", Notification.Type.WARNING_MESSAGE);
            }
           else {
               Notification.show("Das Auto mit der ID: " + selection.getValue().getId()+ " wurde reserviert!");

               NeueListe neueListe = new NeueListe();
               neueListe.setId(selection.getValue().getId());
               neueListe.setMarke(selection.getValue().getMarke());
               neueListe.setPs(selection.getValue().getPs());
               neueListe.setBaujahr(selection.getValue().getBaujahr());
               neueListe.setDescription(selection.getValue().getDescription());

               System.out.println("Das Auto mit der ID: " + selection.getValue().getId() + " wurde ausgegeben!");
               System.out.println(selection.getValue().getMarke());
               System.out.println(selection.getValue().getDescription());
               System.out.println(selection.getValue().getBaujahr());

               NeueListeDAO.getInstance().createNeueListe(neueListe);
           }
        });

        grid.addItemClickListener((ItemClickListener<Auto>)
                itemClick -> System.out.println("Zeile selektiert: " + itemClick.getItem().getId()));

        //MainView -> SucheButton
        sucheButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                String marke = textField.getValue();

                if (marke.equals("")) {
                    Notification.show(null, "Bitte Marke eingeben!", Notification.Type.WARNING_MESSAGE);
                } else {
                    addComponent(grid);
                    setComponentAlignment(grid, Alignment.MIDDLE_CENTER);
                    List<Auto> liste = AutoSearch.getInstance().getAutoByMarke(marke);
                    grid.setItems(liste);
                    grid.setLocale(Locale.GERMANY);
                    addComponent(reserviereButton);
                    setComponentAlignment(reserviereButton, Alignment.MIDDLE_CENTER);
                    MainView.this.anzahlSuche++;
                    grid.setCaption("Treffer für " + marke + " (Anzahl der Suche: " + MainView.this.anzahlSuche + ")");
                }
            }
        });
    }
}

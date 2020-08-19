package gui.views;

import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.ItemClickListener;


import gui.components.TopPanel;
import  model.objects.dto.Auto;
import model.objects.dto.User;
import  process.control.AutoSearch;


import java.util.List;
import java.util.Locale;
import com.vaadin.ui.components.grid.*;
import services.util.Roles;
import services.util.Views;

import javax.management.relation.Role;

public class MainView extends VerticalLayout implements View {

    private int anzahlSuche = 0;
    //private Auto autoSelektiert = null;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event){

        //Wenn User nicht vorhanden -> auf LoginView senden, wenn User vorhanden dann zur Main weiter leiten
        User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);
        if(user == null){
            UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
        }else{
            this.setUp();
        }

        //this.setUp();
    }

    public void setUp(){

        this.addComponent( new TopPanel());
        this.addComponent(new Label ("<hr />", ContentMode.HTML)); //Linie


        setMargin(true);

        final HorizontalLayout horizontalLayout = new HorizontalLayout();
        Button button = new Button("Suche", FontAwesome.SEARCH);
        button.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        final TextField textField = new TextField();

        User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
                              //User user = ((MyUI) UI.getCurrent()).getUser();

        //Vorsichtsabfrage!
        String vorname = null;
          if(user != null){
            vorname = user.getVorname();
         }
        Label labelText = new Label(vorname + ", gebe eine Marke ein: ");


        horizontalLayout.addComponent(labelText);
        horizontalLayout.setComponentAlignment(labelText, Alignment.MIDDLE_CENTER);
        horizontalLayout.addComponent(textField);
        horizontalLayout.addComponent(button);
        addComponent(horizontalLayout);
        setComponentAlignment(horizontalLayout, Alignment.BOTTOM_CENTER);

        Grid<Auto> grid = new Grid<>("Treffer");
        grid.addColumn(Auto::getId).setCaption("ID");
        grid.addColumn(Auto::getMarke).setCaption("Marke");
        grid.addColumn(Auto::getPs).setCaption("PS");
        grid.addColumn(Auto::getBaujahr).setCaption("Baujahr");
        grid.addColumn(Auto::getDescription).setCaption("Description");
        grid.setSizeFull();

        SingleSelect<Auto> selection = grid.asSingleSelect();

        Button reserviereButton = new Button("Reservieren", (Button.ClickListener) clickEvent -> {

           if(selection.isEmpty()){
               Notification.show("Bitte wählen Sie ein Auto aus!", Notification.Type.WARNING_MESSAGE);
               //return;
            }
           else {
                //Notification.show("Auto reserviert!");
                Notification.show("Das Auto mit der ID: " + selection.getValue().getId()+ " wurde reserviert!");
                //System.out.println("Auto selektiert: " + MainView.this.autoSelektiert.getMarke());
           }
        });

        grid.addItemClickListener((ItemClickListener<Auto>)
                //itemClick -> System.out.println("Zeile selektiert: " + itemClick.getItem().toString()));
                itemClick -> System.out.println("Zeile selektiert: " + itemClick.getItem().getId()));



        button.addClickListener(new Button.ClickListener() {
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


                    // grid.getColumn("ID").setMaximumWidth(70);

                    addComponent(reserviereButton);
                    setComponentAlignment(reserviereButton, Alignment.MIDDLE_CENTER);

                    MainView.this.anzahlSuche++;
                    grid.setCaption("Treffer für " + marke + " (Anzahl der Suche: " + MainView.this.anzahlSuche + ")");

                }

            }
        });


    }




}

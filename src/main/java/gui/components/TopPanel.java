package gui.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import gui.windows.ReservierungsWindow;
import model.objects.dto.Auto;
import model.objects.dto.User;
import process.control.LoginControl;
import services.util.Roles;

import javax.management.relation.Role;
import java.awt.*;

public class TopPanel extends HorizontalLayout {
    public static final String CLASSNAME = "TOPPANEL";
    private Auto autoSelection = null;

    public TopPanel(){

        this.setSizeFull();

        Label headLabel = new Label("Carlook ltd. - <i>das Auto-Reservierungssystem</i>", ContentMode.HTML);
        headLabel.setSizeUndefined();
        headLabel.addStyleName("mytitel");

        this.addComponent(headLabel);
        this.setComponentAlignment(headLabel, Alignment.TOP_LEFT);

        HorizontalLayout horizontLayout = new HorizontalLayout();

       User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
        String vorname = null;
       if(user != null){
           vorname = user.getVorname();
        }



        Label loggedLabel = new Label ("Welcome: " + vorname + "!");
        loggedLabel.setSizeUndefined();
        loggedLabel.addStyleName("loggedLabel");

        horizontLayout.addComponent(loggedLabel);
        horizontLayout.setComponentAlignment(loggedLabel, Alignment.MIDDLE_CENTER);

        MenuBar bar = new MenuBar();
        MenuBar.MenuItem item1 = bar.addItem("Menu", null);
        //Logout des Users
        if(user.hasRole (Roles.ADMIN ) || user.hasRole(Roles.CURRENT_USER) || user.hasRole(Roles.ENDKUNDE_USER) || user.hasRole(Roles.VERTRIEBLER_USER)) {
            item1.addItem("Logout", FontAwesome.SIGN_OUT, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem selectedItem) {

                    LoginControl.logoutUser();
                }
            });
        }

        //Liste der Reservierungen -> Cancle
        if(user.hasRole (Roles.ADMIN )  || user.hasRole(Roles.ENDKUNDE_USER) ) {
            item1.addItem("Reservierungen", FontAwesome.LIST, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem selectedItem) {

                    ReservierungsWindow window = new ReservierungsWindow(TopPanel.this.autoSelection);
                    UI.getCurrent().addWindow(window);

                }
            });
        }



        // Stornierung von Reservierungen
        if(user.hasRole (Roles.ADMIN) || user.hasRole(Roles.ENDKUNDE_USER)) {

            item1.addItem("Cancel", FontAwesome.UNLINK, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem selectedItem) {
                    // Todo: ein Window wird geöffnet... Buchung wird dann angezeigt und ggf. storniert
                }
            });
        }
        // Hinzufügen von neuen Autos
        if(user.hasRole (Roles.ADMIN) || user.hasRole(Roles.VERTRIEBLER_USER)) {

            item1.addItem("Hinzufügen", FontAwesome.PLUS_SQUARE_O, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem selectedItem) {
                    // Todo: ein Window wird geöffnet... Buchung wird dann angezeigt und ggf. storniert
                }
            });
        }

        horizontLayout.addComponent(bar);
        this.addComponent(horizontLayout);
        this.setComponentAlignment(horizontLayout, Alignment.TOP_RIGHT);




    }
}

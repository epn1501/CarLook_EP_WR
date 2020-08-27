package gui.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import gui.windows.HinzufuegenWindow;
import gui.windows.NewsWindow;
import gui.windows.ReservierungsWindow;
import model.objects.dto.Auto;
import model.objects.dto.NeueListe;
import model.objects.dto.User;
import process.control.LoginControl;
import services.util.Roles;

import java.sql.SQLException;

public class TopPanel extends HorizontalLayout {
    public static final String CLASSNAME = "TOPPANEL";
    private Auto autoSelection = null;
    private NeueListe neueListeSelection = null;

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

                    ReservierungsWindow window = null;
                    try {
                        window = new ReservierungsWindow(TopPanel.this.neueListeSelection);
                        window.setWidth("70%");
                        window.setHeight("70%");
                        window.center();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    UI.getCurrent().addWindow(window);

                }
            });
        }

        // News window
        if(user.hasRole (Roles.ADMIN) || user.hasRole(Roles.ENDKUNDE_USER) || user.hasRole(Roles.VERTRIEBLER_USER) ) {

            item1.addItem("Nachrichten", FontAwesome.NEWSPAPER_O, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem selectedItem) {
                    // Todo: ein Window wird geöffnet um aktuelle Nachrichten/ Mitteilungen zu zeigen
                    NewsWindow window = new NewsWindow();
                    UI.getCurrent().addWindow(window);

                    //UI.getCurrent().getNavigator().navigateTo(Views.MAINvertrieb);
                }
            });
        }


        // Hinzufügen von neuen Autos
        if(user.hasRole (Roles.ADMIN) || user.hasRole(Roles.VERTRIEBLER_USER)) {

            item1.addItem("Hinzufügen", FontAwesome.PLUS_SQUARE_O, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem selectedItem) {
                    // Todo: ein Window wird geöffnet... Hinzufügen von neuen Autos
                    HinzufuegenWindow window = null;




                    try {
                        window = new HinzufuegenWindow(TopPanel.this.autoSelection);
                        window.setWidth("70%");
                        window.setHeight("70%");
                        window.center();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    UI.getCurrent().addWindow(window);
                }
            });
        }

        horizontLayout.addComponent(bar);
        this.addComponent(horizontLayout);
        this.setComponentAlignment(horizontLayout, Alignment.TOP_RIGHT);




    }
}

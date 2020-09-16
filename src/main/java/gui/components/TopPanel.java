package gui.components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import gui.windows.HinzufuegenWindow;
import gui.windows.ReservierungsWindow;
import model.objects.dto.Auto;
import model.objects.dto.NeueListe;
import model.objects.dto.User;
import process.control.LoginControl;
import services.util.Roles;
import services.util.Views;
import java.sql.SQLException;

public class TopPanel extends HorizontalLayout {
    public static final String CLASSNAME = "TOPPANEL";
    private Auto autoSelection = null;
    private NeueListe neueListeSelection = null;

    public TopPanel(){

        this.setSizeFull();

        Label headLabel = new Label("CarLook Ltd. <br><i>Das Auto-Reservierungssystem</i></br>", ContentMode.HTML);
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

        //Liste der Reservierungen -> Cancel
        if(user.hasRole (Roles.ADMIN )  || user.hasRole(Roles.ENDKUNDE_USER) ) {
            item1.addItem("Reservierungen", VaadinIcons.CHECK_CIRCLE_O, new MenuBar.Command() {
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
        if(user.hasRole (Roles.ADMIN)  || user.hasRole(Roles.VERTRIEBLER_USER) ) {
            item1.addItem("Liste", VaadinIcons.LIST, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem selectedItem) {

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

        // Hinzufügen von neuen Autos
        if(user.hasRole (Roles.ADMIN) || user.hasRole(Roles.VERTRIEBLER_USER)) {
            item1.addItem("Hinzufügen", VaadinIcons.PLUS_SQUARE_LEFT_O, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem selectedItem) {

                    UI.getCurrent().getNavigator().navigateTo(Views.HINZUFÜGEN);
                }
            });
        }

        //Logout des Users
        if(user.hasRole (Roles.ADMIN ) || user.hasRole(Roles.CURRENT_USER) || user.hasRole(Roles.ENDKUNDE_USER) || user.hasRole(Roles.VERTRIEBLER_USER)) {
            item1.addItem("Logout", VaadinIcons.SIGN_OUT, new MenuBar.Command() {
                @Override
                public void menuSelected(MenuBar.MenuItem selectedItem) {
                    LoginControl.logoutUser();
                }
            });
        }

        horizontLayout.addComponent(bar);
        this.addComponent(horizontLayout);
        this.setComponentAlignment(horizontLayout, Alignment.TOP_RIGHT);
    }
}

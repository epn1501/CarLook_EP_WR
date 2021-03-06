package gui.views;

import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import model.objects.dto.User;
import process.control.LoginControl;
import process.control.exceptions.DatabaseException;
import process.control.exceptions.NoSuchUserOrPassword;
import services.util.Roles;
import services.util.Views;

public class LoginView extends VerticalLayout implements View {
    public static final String CLASSNAME = "LOGINSEITE";

    public void setUp(){

        this.setSizeFull();

        Label headLabel = new Label("CarLook Ltd. <br><i>Das Auto-Reservierungssystem</i></br>", ContentMode.HTML);
        headLabel.setSizeUndefined();
        headLabel.addStyleName("mytitel");

        this.addComponent(headLabel);
        this.setComponentAlignment(headLabel, Alignment.TOP_LEFT);

        setMargin(true);

        final TextField userLogin = new TextField();
        userLogin.setCaption("E-Mail Adresse: ");
        userLogin.setPlaceholder("E-Mail");

        final PasswordField passwordField = new PasswordField();
        passwordField.setCaption("Passwort: ");
        passwordField.setPlaceholder("Passwort");

        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(userLogin);
        layout.addComponent(passwordField);

        Label label = new Label( "&nbsp;", ContentMode.HTML); //Platzhalter

        layout.addComponent(label);

        Button buttonLogin = new Button("Login", VaadinIcons.CHECK);
        buttonLogin.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        // buttonLogin.setPrimaryStyleName(CLASSNAME + "-login");
        layout.addComponent(buttonLogin);
        layout.setComponentAlignment(buttonLogin, Alignment.MIDDLE_CENTER);

        Panel panel = new Panel("Bitte Login-Daten angeben: ");
        panel.addStyleName("login");

        this.addComponent(panel);
        this.setComponentAlignment(panel, Alignment.TOP_CENTER);

        Button buttonRegister = new Button("Noch nicht registriert?");
        buttonRegister.addStyleName(ValoTheme.BUTTON_LINK);
        buttonRegister.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.REGISTER));
        layout.addComponent(buttonRegister);
        layout.setComponentAlignment(buttonRegister, Alignment.BOTTOM_CENTER);

        panel.setContent(layout);
        panel.setSizeUndefined();

        buttonLogin.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                String login = userLogin.getValue();
                String password = passwordField.getValue();
                try {
                    LoginControl.checkAuthentication(login, password);
                } catch (NoSuchUserOrPassword ex) {
                    Notification.show("Benutzer-Fehler!", "Login oder Passwort falsch", Notification.Type.ERROR_MESSAGE);
                    userLogin.setValue("");
                    passwordField.setValue("");
                }
                catch (DatabaseException ex) {

                    Notification.show("DB-Fehler!", ex.getReason(), Notification.Type.ERROR_MESSAGE);
                    userLogin.setValue("");
                    passwordField.setValue("");
                }
            }
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event){

        User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);

        if(user != null){
            UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
        }
        else{
            this.setUp();
        }
    }
}
package gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import model.dao.ReservierungDAO;
import model.dao.UserDAO;
import process.control.RegisterControl;
import process.control.exceptions.DatabaseException;
import services.db.JDBCConnection;
import services.util.Views;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterView extends VerticalLayout implements View {
    public static final String CLASSNAME ="REGISTERSEITE";

    public void setUp()  {

        this.setSizeFull();

        final TextField userVorname = new TextField();
        userVorname.setCaption("Vorname: ");
        userVorname.setWidth("475px");

        final TextField userNachname = new TextField();
        userNachname.setCaption("Nachname: ");
        userNachname.setWidth("475px");

        final TextField userRegister = new TextField();
        userRegister.setCaption("*E-Mail: ");
        userRegister.setWidth("475px");

        final PasswordField passwordField = new PasswordField();
        passwordField.setCaption("*Passwort: ");
        passwordField.setWidth("475px");

        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(userVorname);
        layout.addComponent(userNachname);
        layout.addComponent(userRegister);
        layout.addComponent(passwordField);

        Label label = new Label( "&nbsp;", ContentMode.HTML); //Platzhalter

        Button buttonRegister = new Button("Weiter", FontAwesome.ARROW_RIGHT);

        buttonRegister.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                String login = userRegister.getValue();
                String password = passwordField.getValue();
                String vorname = userVorname.getValue();
                String nachname = userNachname.getValue();


                if(userRegister.isEmpty() && passwordField.isEmpty()){
                    Notification.show("Ihre Registrierung war nicht erfolgreich. ", Notification.Type.ERROR_MESSAGE);
                }
                else if (userRegister.isEmpty()){
                    Notification.show("Die eingegebene E-Mail-Adresse ist ungültig!", Notification.Type.ERROR_MESSAGE);
                }
                else if(passwordField.isEmpty()){
                    Notification.show("Das eingegebene Passwort entspricht leider nicht den Anforderungen!", Notification.Type.ERROR_MESSAGE);
                }



                //ToDo-Bedingung für MAIN, FELDER müssen korrekt ausgefüllt sein
                //erst dann zu Main weiterleiten
                else {

                    try{
                        RegisterControl.registerUser(login, password, vorname, nachname);
                    }catch (DatabaseException | SQLException ex){
                        Notification.show("Fehler!", "Login oder Passwort falsch", Notification.Type.ERROR_MESSAGE);
                        userRegister.setValue("");
                        passwordField.setValue("");
                    }


                    //UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
                }
            }
        });

        //layout.addComponent(buttonRegister);
        //layout.setComponentAlignment(buttonRegister, Alignment.BOTTOM_RIGHT);

        // buttonRegister.setPrimaryStyleName(CLASSNAME + "-register");
        // layout.addComponent(buttonRegister);
        Button buttonZurueck = new Button("Zurück", FontAwesome.ARROW_LEFT);
        buttonZurueck.addClickListener(event -> UI.getCurrent().getNavigator().navigateTo(Views.LOGIN));
        layout.addComponents(buttonZurueck, buttonRegister);
        // layout.setComponentAlignment(buttonZurueck, Alignment.BOTTOM_LEFT);
        // layout.setComponentAlignment(buttonRegister, Alignment.BOTTOM_RIGHT);
        //layout.addComponents(buttonZurueck, buttonRegister);
        layout.setComponentAlignment(buttonZurueck, Alignment.BOTTOM_LEFT);

        Panel panel = new Panel("Jetzt registrieren - gratis in nur 2 Minuten!");
        panel.addStyleName("register");

        this.addComponent(panel);
        this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        panel.setContent(layout);
        //panel.setSizeUndefined();

        panel.setWidth("500px");
        panel.setHeight("500px");



    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event){

        this.setUp();

    }


}

package gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import gui.windows.ConfirmationWindow;
import model.dao.UserDAO;
import model.objects.dto.User;
import process.control.exceptions.DatabaseException;
import services.util.Views;

import java.sql.SQLException;

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

        Button buttonRegister = new Button("Registrieren", FontAwesome.ARROW_RIGHT);

        buttonRegister.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {

                String login = userRegister.getValue();
                String password = passwordField.getValue();
                String vorname = userVorname.getValue();
                String nachname = userNachname.getValue();


                if(userRegister.isEmpty() && passwordField.isEmpty()){
                    Notification.show("Ihre Registrierung war nicht erfolgreich! Bitte erneut versuchen!", Notification.Type.ERROR_MESSAGE);
                }
                else if (userRegister.isEmpty()){
                    Notification.show("Die eingegebene E-Mail-Adresse ist ungültig!", Notification.Type.ERROR_MESSAGE);
                }
                else if(passwordField.isEmpty()){
                    Notification.show("Das eingegebene Passwort entspricht leider nicht den Anforderungen!", Notification.Type.ERROR_MESSAGE);
                }

                //ToDo-Bedingung für MAIN, FELDER müssen korrekt ausgefüllt sein
                else {

                    User addUser = new User();
                    addUser.setLogin(login);
                    addUser.setPasswort(password);
                    addUser.setVorname(vorname);
                    addUser.setNachname(nachname);

                    try {
                        UserDAO.getInstance().addUser(addUser);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }


                    //System.out.println("Login1: " +login + " Passwort1: " + password + " " + addUser.getNachname() + "" + addUser.getVorname());

                    ConfirmationWindow window = new ConfirmationWindow("User wurde erstellt. Sie können sich jetzt einloggen!");
                    UI.getCurrent().addWindow(window);
                    UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);
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

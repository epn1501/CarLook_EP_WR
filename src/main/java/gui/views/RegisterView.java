package gui.views;

import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

public class RegisterView extends VerticalLayout implements View {
    public static final String CLASSNAME ="REGISTERSEITE";


    public void setUp(){

        this.setSizeFull();

        final TextField userVorname = new TextField();
        userVorname.setCaption("Vorname: ");

        final TextField userNachname = new TextField();
        userNachname.setCaption("Nachname: ");

        final TextField userRegister = new TextField();
        userRegister.setCaption("E-Mail: ");

        final PasswordField passwordField = new PasswordField();
        passwordField.setCaption("Passwort: ");

        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(userVorname);
        layout.addComponent(userNachname);
        layout.addComponent(userRegister);
        layout.addComponent(passwordField);

        Label label = new Label( "&nbsp;", ContentMode.HTML); //Platzhalter

        Button buttonRegister = new Button("Weiter", FontAwesome.ARROW_RIGHT);
        // buttonRegister.setPrimaryStyleName(CLASSNAME + "-register");
        layout.addComponent(buttonRegister);
        layout.setComponentAlignment(buttonRegister, Alignment.MIDDLE_RIGHT);

        Button buttonZurueck = new Button("Zurück", FontAwesome.ARROW_LEFT);
        layout.addComponent(buttonZurueck);
        layout.setComponentAlignment(buttonZurueck, Alignment.MIDDLE_LEFT);


        Panel panel = new Panel("Jetzt registrieren - gratis in nur 2 Minuten!");
        panel.addStyleName("register");

        this.addComponent(panel);
        this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);

        panel.setContent(layout);
        panel.setSizeUndefined();


    }


}

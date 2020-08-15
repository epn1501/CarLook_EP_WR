package gui.ui;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.*;
import gui.views.LoginView;
import gui.views.MainView;
import gui.views.RegisterView;
import services.util.Views;


/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Title("CarLook")
public class MyUI extends UI {


    @Override
    protected void init(VaadinRequest vaadinRequest) {

        System.out.println("LOG: neues UI-Objekt erzeugt. Session-ID: " + VaadinSession.getCurrent().toString());

        Navigator navi = new Navigator(this, this);
        navi.addView (Views.MAIN, MainView.class);
        navi.addView (Views.LOGIN, LoginView.class);
        navi.addView (Views.REGISTER, RegisterView.class);

        UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);

    }


    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }


}

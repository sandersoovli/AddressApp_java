module ch.makery.address {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.prefs;
    requires jakarta.xml.bind;
    //requires ch.makery.address;
    requires javafx.base;


    opens ch.makery.address to javafx.fxml;
    opens ch.makery.address.model to jakarta.xml.bind;
    opens ch.makery.address.view to javafx.fxml;

    exports ch.makery.address;
    exports ch.makery.address.view;


}
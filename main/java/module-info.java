module ch.makery.address {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.prefs;
    requires jakarta.xml.bind;


    opens ch.makery.address to javafx.fxml;
    opens ch.makery.address.model to jakarta.xml.bind;
    exports ch.makery.address;
    exports ch.makery.address.view;
    opens ch.makery.address.view to javafx.fxml;
}
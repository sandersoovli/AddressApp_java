package ch.makery.address.repository;

import ch.makery.address.model.Person;
import ch.makery.address.model.PersonListWrapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.io.File;

public class PersonRepository {


    private final ObservableList<Person> persons = FXCollections.observableArrayList();

    /**
     * Constructor
     */
    public PersonRepository() {
        // Add some sample data
        persons.add(new Person("Hans", "Muster"));
        persons.add(new Person("Ruth", "Mueller"));
        persons.add(new Person("Heinz", "Kurz"));
        persons.add(new Person("Cornelia", "Meier"));
        persons.add(new Person("Werner", "Meyer"));
        persons.add(new Person("Lydia", "Kunz"));
        persons.add(new Person("Anna", "Best"));
        persons.add(new Person("Stefan", "Meier"));
        persons.add(new Person("Martin", "Mueller"));
    }

    public ObservableList<Person> getPersons() {
        return persons;
    }

    public void loadFromFile(File file) throws Exception {
        JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
        Unmarshaller um = context.createUnmarshaller();

        PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);
        persons.clear();
        persons.addAll(wrapper.getPersons());
    }


    public void saveToFile(File file) throws Exception {
        JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        PersonListWrapper wrapper = new PersonListWrapper();
        wrapper.setPersons(persons);

        m.marshal(wrapper, file);
    }


}

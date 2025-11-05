package ch.makery.address.view;

import ch.makery.address.model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.text.DateFormatSymbols;
import java.util.*;

public class BirthdayStatisticsController {

    // --- Birthday Chart ---
    @FXML private BarChart<String, Integer> barChart;
    @FXML private CategoryAxis xAxis;
    @FXML private NumberAxis birthdayYAxis;

    // --- City Chart ---
    @FXML private BarChart<String, Integer> cityChart;
    @FXML private CategoryAxis cityXAxis;
    @FXML private NumberAxis cityYAxis;

    // --- Phone Chart ---
    @FXML private BarChart<String, Integer> phoneChart;
    @FXML private CategoryAxis phoneXAxis;
    @FXML private NumberAxis phoneYAxis;

    private ObservableList<String> monthNames = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Kuud x-teljele (Birthday)
        String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        monthNames.addAll(Arrays.asList(months));
        xAxis.setCategories(monthNames);
    }

    public void setPersonData(List<Person> persons) {
        // -----------------------------
        // Birthday Statistics
        // -----------------------------
        int[] monthCounter = new int[12];
        for (Person p : persons) {
            int month = p.getBirthday().getMonthValue() - 1;
            monthCounter[month]++;
        }

        XYChart.Series<String, Integer> birthdaySeries = new XYChart.Series<>();
        birthdaySeries.setName("Birthdays per Month");
        for (int i = 0; i < monthCounter.length; i++) {
            birthdaySeries.getData().add(new XYChart.Data<>(monthNames.get(i), monthCounter[i]));
        }
        barChart.getData().add(birthdaySeries);

        birthdayYAxis.setAutoRanging(false);
        birthdayYAxis.setLowerBound(0);
        birthdayYAxis.setUpperBound(Arrays.stream(monthCounter).max().orElse(1) + 1);
        birthdayYAxis.setTickUnit(1);

        // -----------------------------
        // City Statistics
        // -----------------------------
        Map<String, Integer> cityCounter = new HashMap<>();
        for (Person p : persons) {
            String city = p.getCity();
            cityCounter.put(city, cityCounter.getOrDefault(city, 0) + 1);
        }

        XYChart.Series<String, Integer> citySeries = new XYChart.Series<>();
        citySeries.setName("People per City");
        for (Map.Entry<String, Integer> entry : cityCounter.entrySet()) {
            citySeries.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }
        cityChart.getData().add(citySeries);

        cityXAxis.setCategories(FXCollections.observableArrayList(cityCounter.keySet()));
        cityYAxis.setAutoRanging(false);
        cityYAxis.setLowerBound(0);
        cityYAxis.setUpperBound(cityCounter.values().stream().max(Integer::compareTo).orElse(1) + 1);
        cityYAxis.setTickUnit(1);

        // -----------------------------
        // Phone Statistics
        // -----------------------------
        int withPhone = 0;
        int noPhone = 0;
        for (Person p : persons) {
            if (p.getPhone() != null && !p.getPhone().isEmpty()) withPhone++;
            else noPhone++;
        }

        XYChart.Series<String, Integer> phoneSeries = new XYChart.Series<>();
        phoneSeries.setName("Phone Availability");
        phoneSeries.getData().add(new XYChart.Data<>("With Phone", withPhone));
        phoneSeries.getData().add(new XYChart.Data<>("No Phone", noPhone));

        phoneChart.getData().add(phoneSeries);

        phoneXAxis.setCategories(FXCollections.observableArrayList("With Phone", "No Phone"));
        phoneYAxis.setAutoRanging(false);
        phoneYAxis.setLowerBound(0);
        phoneYAxis.setUpperBound(Math.max(withPhone, noPhone) + 1);
        phoneYAxis.setTickUnit(1);
    }
}

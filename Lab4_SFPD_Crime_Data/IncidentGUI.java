import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IncidentGUI extends Application {

    private ComboBox<String> neighborhoodBox;
    private ComboBox<String> yearBox;
    private ComboBox<String> dayOfWeekBox;
    private TextArea resultArea;
    private Button showTotalButton;
    private Button resetButton;

    private List<Incident> incidentList;

    public void start(Stage primaryStage) {
        incidentList = fillIncidentList();

        Label titleLabel = new Label("Homicides in San Francisco (2020-2023)");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        VBox.setMargin(titleLabel, new Insets(3, 0, 0, 0));

        Label neighborhoodLabel = new Label("Neighborhood:");
        Label yearLabel = new Label("Year:");

        neighborhoodBox = new ComboBox<>(FXCollections.observableArrayList(getUniqueNeighborhoods()));
        yearBox = new ComboBox<>(FXCollections.observableArrayList(getUniqueYearsAsStrings()));
        resultArea = new TextArea();
        showTotalButton = new Button("Show All Incidents");
        resetButton = new Button("Reset");

        GridPane inputGrid = new GridPane();
        inputGrid.setAlignment(Pos.CENTER);
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(5, 10, 10, 10));

        inputGrid.add(neighborhoodLabel, 0, 0);
        inputGrid.add(neighborhoodBox, 0, 1);
        inputGrid.add(yearLabel, 1, 0);
        inputGrid.add(yearBox, 1, 1);

        dayOfWeekBox = new ComboBox<>(FXCollections.observableArrayList(Incident.DayOfWeekEnum.getDisplayNames()));
        inputGrid.add(new Label("Day of Week:"), 2, 0);
        inputGrid.add(dayOfWeekBox, 2, 1);
        dayOfWeekBox.setOnAction(event -> showIncidentsByFilters());

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(showTotalButton, resetButton);

        VBox mainBox = new VBox(10);
        mainBox.getChildren().addAll(titleLabel, inputGrid, resultArea, buttonBox);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setPadding(new Insets(0, 0, 10, 0));
        mainBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(mainBox, 425, 300);

        primaryStage.setTitle("SFPD Incident Reports");
        primaryStage.setScene(scene);
        primaryStage.show();

        neighborhoodBox.setOnAction(event -> showIncidentsByFilters());
        yearBox.setOnAction(event -> showIncidentsByFilters());

        showTotalButton.setOnAction(event -> showTotalCount());
        resetButton.setOnAction(event -> resetFilters());
    }

    private List<Incident> fillIncidentList() {
        List<Incident> incidents = new ArrayList<>();
        try (Scanner fileScan = new Scanner(new FileReader(new File("SFPD.csv")))) {
            fileScan.nextLine();
            while (fileScan.hasNext()) {
                String line = fileScan.nextLine();
                String[] parts = line.split(",");
                String dateTime = parts[0].trim();
                String dayOfWeek = parts[2].trim();
                String neighborhood = parts[3].trim();
                Incident incident = new Incident(dateTime, dayOfWeek, neighborhood);
                incidents.add(incident);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return incidents;
    }

    private void showIncidentsByFilters() {
        String selectedNeighborhood = neighborhoodBox.getValue();
        String selectedYear = yearBox.getValue();
        String selectedDayOfWeek = dayOfWeekBox.getValue();
        resultArea.clear();

        int totalCount = filterAndDisplayIncidents(selectedNeighborhood, selectedYear, selectedDayOfWeek);

        resultArea.appendText("Total Count: " + totalCount + "\n\n");
    }

    private int filterAndDisplayIncidents(String selectedNeighborhood, String selectedYear, String selectedDayOfWeek) {
        int totalCount = 0;

        if (selectedNeighborhood != null || selectedYear != null || selectedDayOfWeek != null) {
            for (Incident incident : incidentList) {
                boolean neighborhoodMatch = selectedNeighborhood == null || incident.getNeighborhood().equalsIgnoreCase(selectedNeighborhood);
                boolean yearMatch = selectedYear == null || incident.getYear().equals(selectedYear);
                boolean dayOfWeekMatch = selectedDayOfWeek == null || incident.getDayOfWeek().getDisplayName().equalsIgnoreCase(selectedDayOfWeek);

                if (neighborhoodMatch && yearMatch && dayOfWeekMatch) {
                    totalCount++;
                    displayIncident(incident);
                }
            }
        }
        return totalCount;
    }

    
    
    private void displayIncident(Incident incident) {
        resultArea.appendText(incident.toString() + "\n");
    }

    private void showTotalCount() {
        int totalCount = incidentList.size();
        resultArea.clear();
        resultArea.appendText("Total Count of Homicides: " + totalCount + "\n\n");

        for (Incident incident : incidentList) {
            displayIncident(incident);
        }
    }

    private void resetFilters() {
        neighborhoodBox.setValue(null);
        yearBox.setValue(null);
        dayOfWeekBox.setValue(null);
        resultArea.clear();
    }

    private List<String> getUniqueYearsAsStrings() {
        List<String> years = new ArrayList<>();
        for (Incident incident : incidentList) {
            String year = incident.getYear();
            if (!years.contains(year)) {
                years.add(year);
            }
        }
        return years;
    }

    private List<String> getUniqueNeighborhoods() {
        List<String> neighborhoods = new ArrayList<>();
        for (Incident incident : incidentList) {
            if (!neighborhoods.contains(incident.getNeighborhood())) {
                neighborhoods.add(incident.getNeighborhood());
            }
        }
        return neighborhoods;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
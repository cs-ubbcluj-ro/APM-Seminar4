package gui;

import domain.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import service.Service;

import java.util.ArrayList;

public class DoctorsController {
    private Service service;

    public DoctorsController(Service service) {
        this.service = service;
    }

    @FXML
    private ListView<Doctor> doctorsListView;

    @FXML
    private TextField searchTextField;

    void populateList()
    {
        ObservableList<Doctor> doctorsList = FXCollections.observableArrayList(service.getAll());
        doctorsListView.setItems(doctorsList);
    }

    public void initialize()
    {
        populateList();
    }

    @FXML
    void searchOnKeyTyped(KeyEvent event) {
        String searchText = searchTextField.getText();
        if (searchText.equals(""))
            populateList();
        else {
            ObservableList<Doctor> filteredDoctors = FXCollections.observableArrayList(service.filterBySpecialty(searchText));
            doctorsListView.setItems(filteredDoctors);
        }

    }
}

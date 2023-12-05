package gui;

import domain.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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

    @FXML
    private TextField gradeTextField;

    @FXML
    private TextField idTextField;

    @FXML
    private TextField locationTextField;

    @FXML
    private TextField nameTextField;

    @FXML
    private Button removeButton;
    @FXML
    private Button addButton;

    @FXML
    private TextField specialtyTextField;

    void populateList()
    {
        ObservableList<Doctor> doctorsList = FXCollections.observableArrayList(service.getAll());
        doctorsListView.setItems(doctorsList);
    }

    public void initialize()
    {
        populateList();
        SelectionMode mode = doctorsListView.getSelectionModel().getSelectionMode();
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

    @FXML
    void onClickList(MouseEvent event) {
        int idx = doctorsListView.getSelectionModel().getSelectedIndex();
        ArrayList<Doctor> doctors = service.getAll();
        if (idx < 0 || idx >= doctors.size())
        {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setContentText("Invalid selection!");
            dialog.show();
        }
        else
        {
            Doctor d = doctors.get(idx);
            idTextField.setText(d.getId().toString());
            nameTextField.setText(d.getName());
            specialtyTextField.setText(d.getSpecialty());
            locationTextField.setText(d.getLocation());
            gradeTextField.setText(String.valueOf(d.getGrade()));
        }
    }

    @FXML
    void onAddButtonClicked(MouseEvent event) {
        // !!! check whether all fields have values

        try {
            Integer id = Integer.parseInt(idTextField.getText());
            double grade = Double.parseDouble(gradeTextField.getText());

            service.addDoctor(id, nameTextField.getText(), specialtyTextField.getText(),
                    locationTextField.getText(), grade);
            populateList();
        }
        catch (NumberFormatException e)
        {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setContentText("Id and grade must be numbers!");
            dialog.show();
        }
    }
}

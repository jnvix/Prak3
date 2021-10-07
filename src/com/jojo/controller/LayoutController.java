/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jojo.controller;

import com.jojo.MainApp;
import com.jojo.entity.Item;
import com.jojo.entity.Supplier;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Jonathan Pratama
 */
public class LayoutController implements Initializable {


    @FXML
    private Button btnSave;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    public ComboBox<Supplier> comboSupp;
    @FXML
    private TableView<Item> tableData;
    private SupplierLayoutController secondForm;
    private ObservableList<Item> items;
    @FXML
    private TableColumn<Item, String> colId;
    @FXML
    private TableColumn<Item, String> colNama;
    @FXML
    private TableColumn<Item, String> colAlamat;
    @FXML
    private BorderPane borderPane;
    private ObservableList<Supplier> a;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboSupp.setItems(SupplierLayoutController.getSuppliers());
    
    }
    public ObservableList<Item> getItems() {
        if (items == null){
            items= FXCollections.observableArrayList();
            //items.add(new Item());
        }
        return items;
    }

    @FXML
    private void btnSaveOnClicked(ActionEvent event) {
        if (txtName.getText().trim().isEmpty()) {
            Alert alertInfo = new Alert(Alert.AlertType.ERROR);
            alertInfo.setContentText(" Please fill supplier name");
            alertInfo.showAndWait();
        }
        else{
            Item i= new Item();
            i.setName(txtName.getText());
            //i.setPrice(txtPrice.getText());
            i.setSupplier(comboSupp.getValue());
            getItems().add(i);
            tableData.setItems(getItems());


            colNama.setCellValueFactory((data) -> {
                Item item = data.getValue();
                return new SimpleStringProperty(String.valueOf(item.getName()));
            });

            btnUpdate.setDisable(false);

        }
        }

    @FXML
    private void btnResetOnClicked(ActionEvent event) {
        txtId.clear();
        txtName.clear();
    }

    @FXML
    private void btnUpdateOnClicked(ActionEvent event) {
        items.get(items.size()-1).setName(txtName.getText());
        items.get(items.size()-1).setSupplier(comboSupp.getValue());
        tableData.refresh();
    }

    @FXML
    private void mnShowManagementSupplier(ActionEvent event) {
        try {
            Stage secondStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/SupplierLayout.fxml"));
            BorderPane root = loader.load();
            Scene scene = new Scene(root);
            secondStage.setScene(scene);
            secondStage.setTitle("Supplier Management");
            secondStage.initOwner(borderPane.getScene().getWindow());
            secondStage.initModality(Modality.WINDOW_MODAL);
            secondStage.show();      
        } catch (IOException ex) {
            Logger.getLogger(LayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(SupplierLayoutController.suppliers);
    }

    @FXML
    private void mnClose(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    private void mnAbout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Created by Jonathan Pratama");
        alert.setTitle(Alert.AlertType.INFORMATION.toString());
        alert.showAndWait();
    }
    
}

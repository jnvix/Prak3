/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jojo.controller;

import com.jojo.entity.Supplier;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Jonathan Pratama
 */
public class SupplierLayoutController implements Initializable {


    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtAlamat;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnReset;
    @FXML
    private Button btnUpdate;
    @FXML
    private TableView<Supplier> tableSupp;
    
    public static ObservableList<Supplier> suppliers;
    @FXML
    private TableColumn<Supplier, String> colId;
    @FXML
    private TableColumn<Supplier, String> colNama;
    @FXML
    private TableColumn<Supplier, String> colAlamat;

    public static ObservableList<Supplier> getSuppliers() {
        if(suppliers == null){
            suppliers = FXCollections.observableArrayList();
        }
        return suppliers;
    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tableSupp.setItems(getSuppliers());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
    }    

    @FXML
    private void btnSaveOnClicked(ActionEvent event) {
        getSuppliers().add(new Supplier(txtId.getText(), txtNama.getText(),txtAlamat.getText()));
       tableSupp.refresh();
    }
    @FXML
    private void btnResetOnClicked(ActionEvent event) {
        txtId.clear();
        txtNama.clear();
        txtAlamat.clear();
    }
    @FXML
    private void btnUpdateOnClicked(ActionEvent event) {
    }
    
}

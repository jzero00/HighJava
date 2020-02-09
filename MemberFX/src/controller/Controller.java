package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class Controller implements Initializable {

    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> name;

    @FXML
    private TableColumn<?, ?> tel;

    @FXML
    private TableColumn<?, ?> addr;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDel;

    @FXML
    void Del(ActionEvent event) {

    }

    @FXML
    void Edit(ActionEvent event) {

    }

    @FXML
    void Search(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

}


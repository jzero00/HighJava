package prodInfo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;

public class ProdInfoController implements Initializable{

    @FXML ComboBox box1;
    @FXML ComboBox box2;
    @FXML TableColumn id;
    @FXML TableColumn name;
    @FXML TableColumn lgu;
    @FXML TableColumn cost;
    @FXML TableColumn price;
    @FXML TableColumn sale;
    @FXML TableColumn outline;
    @FXML TableColumn detail;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
	
	ObservableList list = FXCollections.observableArrayList();
	box1.setItems(list);
	
    }

}

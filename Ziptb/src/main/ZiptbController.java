package main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import service.ZiptbService;
import service.ZiptbServiceImpl;
import vo.ZiptbVO;

public class ZiptbController implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<ZiptbVO, String> gugun;

    @FXML
    private TableColumn<ZiptbVO, String> postNo;

    @FXML
    private TableColumn<ZiptbVO, String> number;

    @FXML
    private ComboBox<String> comBox;

    @FXML
    private TableColumn<ZiptbVO, String> sido;

    @FXML
    private TextField searchBar;

    @FXML
    private TableColumn<ZiptbVO, String> dong;

    @FXML
    private Button btn;

    @FXML
    private TableView<ZiptbVO> table;

    @FXML

    ZiptbService zipService = ZiptbServiceImpl.getInstance();


    @FXML
    void search(ActionEvent event) {
	System.out.println("00");
    }

    List<ZiptbVO> getDongList = zipService.getDongList();

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
	
	comBox.getItems().addAll("동이름", "우편번호");
	
	gugun.setCellValueFactory(new PropertyValueFactory<>("gugun"));
	postNo.setCellValueFactory(new PropertyValueFactory<>("postNo"));
	number.setCellValueFactory(new PropertyValueFactory<>("number"));
	sido.setCellValueFactory(new PropertyValueFactory<>("sido"));
	dong.setCellValueFactory(new PropertyValueFactory<>("dong"));

	
    }


    @FXML public void setDong() {
	
	
	
    }
}

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
    private ComboBox<String> comboBox;
    @FXML
    private TextField searchBar;
    @FXML
    private Button btn;
    @FXML
    private TableView<ZiptbVO> table;
	@FXML
	private TableColumn<ZiptbVO, String> zipcode;
	@FXML
	private TableColumn<ZiptbVO, String> sido;
	@FXML
	private TableColumn<ZiptbVO, String> gugun;
	@FXML
	private TableColumn<ZiptbVO, String> dong;
	@FXML
	private TableColumn<ZiptbVO, String> bunji;
    
    ZiptbService zipService = ZiptbServiceImpl.getInstance();

    @FXML
    void search(ActionEvent event) {
    	

    	if(comboBox.getSelectionModel().getSelectedItem() == "동이름") {
	
    		String searchText = searchBar.getText();
   		
    		ZiptbVO vo = new ZiptbVO();
  		
    		vo.setDong(searchText);
 		
    		List<ZiptbVO> getDongList = zipService.searchDong(vo);
//    		vo.getZipCode()
    		ObservableList<ZiptbVO> list = FXCollections.observableList(getDongList);
    		
    		table.setItems(list);

    	} else if (comboBox.getSelectionModel().getSelectedItem() == "우편번호") {
    		
    		String searchText = searchBar.getText();
    		
    		ZiptbVO vo = new ZiptbVO();
    		
    		vo.setZipCode(searchText);
    		
    		List<ZiptbVO> getZipcodeList = zipService.searchBunji(vo);
    		
    		ObservableList<ZiptbVO> list = FXCollections.observableArrayList(getZipcodeList);
    		
    		table.setItems(list);
    	}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
	
	comboBox.getItems().addAll("동이름", "우편번호");
	
	zipcode.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
	gugun.setCellValueFactory(new PropertyValueFactory<>("gugun"));
	sido.setCellValueFactory(new PropertyValueFactory<>("sido"));
	dong.setCellValueFactory(new PropertyValueFactory<>("dong"));
	bunji.setCellValueFactory(new PropertyValueFactory<>("bunji"));

	
    }

}

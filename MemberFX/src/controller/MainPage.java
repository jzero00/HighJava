package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import service.MemberService;
import service.MemberServiceImpl;
import vo.MemberVO;

public class MainPage implements Initializable {

    @FXML
    private TableColumn<MemberVO, String> memTel;

    @FXML
    private TableColumn<MemberVO, String> memName;

    @FXML
    private Button btnAdd;

    @FXML
    private TableColumn<MemberVO, String> memAddr;

    @FXML
    protected TableView<MemberVO> tableView;

    @FXML
    private Button btnEdi;

    @FXML
    private Button btnDel;

    protected ObservableList<MemberVO> allTableData ,list;
    
    MemberService service = MemberServiceImpl.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

	allTableData = FXCollections.observableArrayList();
	
	memTel.setCellValueFactory(new PropertyValueFactory<>("mem_tel"));
	memName.setCellValueFactory(new PropertyValueFactory<>("mem_name"));
	memAddr.setCellValueFactory(new PropertyValueFactory<>("mem_addr"));
	
	allTableData = FXCollections.observableArrayList(service.getAllMemberList());
	tableView.setItems(allTableData);
	
    }

    @FXML public void addMem() throws IOException {

	AddPage add = new AddPage(tableView);
	add.addMem();

    }
    // 에러 메세지 창
    public void errMsg(String headerText, String msg) {
	Alert errAlert = new Alert(AlertType.ERROR);
	errAlert.setTitle("오류");
	errAlert.setHeaderText(headerText);
	errAlert.setContentText(msg);
	errAlert.showAndWait();
    }
    
    // 알림창
    protected void infoMsg(String headerText, String msg) {
	Alert infoAlert = new Alert(AlertType.INFORMATION);
	infoAlert.setTitle("정보 확인");
	infoAlert.setHeaderText(headerText);
	infoAlert.setContentText(msg);
	infoAlert.showAndWait();
    }

    @FXML public void ediMem() throws IOException {
	
	EditPage edit = new EditPage(tableView);
	edit.editMem();
	
    }
}


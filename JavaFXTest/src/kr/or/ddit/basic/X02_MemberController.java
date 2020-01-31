package kr.or.ddit.basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;

public class X02_MemberController implements Initializable {

    @FXML
    Button btnAdd;
    @FXML
    Button btnEdi;
    @FXML
    Button btnDel;

    @FXML
    TextField txtMemId;
    @FXML
    TextField txtMemNm;
    @FXML
    TextField txtMemTel;
    @FXML
    TextField txtMemAdd;

    @FXML
    TableColumn<TableRowDataModel, String> idColumn;
    @FXML
    TableColumn<TableRowDataModel, String> nameColumn;
    @FXML
    TableColumn<TableRowDataModel, String> telColumn;
    @FXML
    TableColumn<TableRowDataModel, String> addrColumn;

    @FXML
    TableView<TableRowDataModel> table;
    @FXML
    Button btnOk;
    @FXML
    Button btnCancel;

    // 테스트용 데이터
    ObservableList<TableRowDataModel> data = FXCollections.observableArrayList(
	    new TableRowDataModel(new SimpleStringProperty("gildong123"), new SimpleStringProperty("홍길동"),
		    new SimpleStringProperty("010-1234-5678"), new SimpleStringProperty("대전시 중구")));

    @Override
    public void initialize(URL location, ResourceBundle resources) {

	idColumn.setCellValueFactory(cellData -> cellData.getValue().getMemId());
	nameColumn.setCellValueFactory(cellData -> cellData.getValue().getMemNm());
	telColumn.setCellValueFactory(cellData -> cellData.getValue().getMemTel());
	addrColumn.setCellValueFactory(cellData -> cellData.getValue().getMemAdd());
	table.setItems(data);

    }

    @FXML
    public void btnAddClicked(ActionEvent event) {

	btnAdd.setOnAction(e -> {

	    checkMenu();

	    btnOk.setOnMouseClicked(confirm -> {

		if (txtMemId.getText().isEmpty() || txtMemNm.getText().isEmpty() || txtMemTel.getText().isEmpty()
			|| txtMemAdd.getText().isEmpty()) {
		    errMsg("작업오류", "빈 항목이 있습니다.");
		    return;
		}

		table.getItems().add(new TableRowDataModel(new SimpleStringProperty(txtMemId.getText()),
			new SimpleStringProperty(txtMemNm.getText()), new SimpleStringProperty(txtMemTel.getText()),
			new SimpleStringProperty(txtMemAdd.getText())));
		infoMsg("작업결과", txtMemNm.getText() + "님의 정보를 추가했습니다.");

		defaultMenu();

	    });

	    btnCancel.setOnMouseClicked(cancel -> {

		defaultMenu();

	    });

	});

	txtMemId.clear();
	txtMemNm.clear();
	txtMemTel.clear();
	txtMemAdd.clear();
    }

    @FXML
    public void btnEdiClicked(ActionEvent event) {

	btnEdi.setOnAction(e -> {

	    checkMenu();

	    btnOk.setOnMouseClicked(confirm -> {

		if (txtMemId.getText().isEmpty() || txtMemNm.getText().isEmpty() || txtMemTel.getText().isEmpty()
			|| txtMemAdd.getText().isEmpty()) {
		    errMsg("작업오류", "빈 항목이 있습니다.");
		    return;
		}

		table.getItems().set(table.getSelectionModel().getSelectedIndex(), new TableRowDataModel(
			new SimpleStringProperty(txtMemId.getText()), new SimpleStringProperty(txtMemNm.getText()),
			new SimpleStringProperty(txtMemTel.getText()), new SimpleStringProperty(txtMemAdd.getText())));

		infoMsg("작업결과", txtMemNm.getText() + "님의 정보를 수정했습니다.");

		defaultMenu();

	    });

	    btnCancel.setOnAction(cancel -> {

		defaultMenu();

	    });

	    txtMemId.clear();
	    txtMemNm.clear();
	    txtMemTel.clear();
	    txtMemAdd.clear();

	});
    }

    @FXML
    public void btnDelClicked() {

	btnDel.setOnAction(e -> {

	    checkMenu();

	    btnOk.setOnMouseClicked(confirm -> {

		if (table.getSelectionModel().isEmpty()) {
		    errMsg("작업오류", "삭제할 자료를 선택한 후 삭제하세요");
		    return;
		}
		data.remove(table.getSelectionModel().getSelectedIndex());
		infoMsg("작업 결과", txtMemNm.getText() + "님의 정보를 삭제했습니다.");

		defaultMenu();
	    });

	    btnCancel.setOnMouseClicked(cancel -> {

		defaultMenu();

	    });

	    txtMemId.clear();
	    txtMemNm.clear();
	    txtMemTel.clear();
	    txtMemAdd.clear();
	});
    }

    private void checkMenu() {

	btnAdd.setDisable(true);
	btnDel.setDisable(true);
	btnEdi.setDisable(true);
	btnOk.setDisable(false);
	btnCancel.setDisable(false);

    }

    private void defaultMenu() {

	btnAdd.setDisable(false);
	btnDel.setDisable(false);
	btnEdi.setDisable(false);
	btnOk.setDisable(true);
	btnCancel.setDisable(true);

    }

    public void errMsg(String headerText, String msg) {
	Alert errAlert = new Alert(AlertType.ERROR);
	errAlert.setTitle("오류");
	errAlert.setHeaderText(headerText);
	errAlert.setContentText(msg);
	errAlert.showAndWait();
    }

    public void infoMsg(String headerText, String msg) {
	Alert infoAlert = new Alert(AlertType.INFORMATION);
	infoAlert.setTitle("정보 확인");
	infoAlert.setHeaderText(headerText);
	infoAlert.setContentText(msg);
	infoAlert.showAndWait();
    }

    public class TableRowDataModel {

	private StringProperty memId;
	private StringProperty memNm;
	private StringProperty memTel;
	private StringProperty memAdd;

	public TableRowDataModel(StringProperty memId, StringProperty memNm, StringProperty memTel,
		StringProperty memAdd) {
	    this.memId = memId;
	    this.memNm = memNm;
	    this.memTel = memTel;
	    this.memAdd = memAdd;
	}

	public StringProperty getMemId() {
	    return memId;
	}

	public void setMemId(StringProperty memId) {
	    this.memId = memId;
	}

	public StringProperty getMemNm() {
	    return memNm;
	}

	public void setMemNm(StringProperty memNm) {
	    this.memNm = memNm;
	}

	public StringProperty getMemTel() {
	    return memTel;
	}

	public void setMemTel(StringProperty memTel) {
	    this.memTel = memTel;
	}

	public StringProperty getMemAdd() {
	    return memAdd;
	}

	public void setMemAdd(StringProperty memAdd) {
	    this.memAdd = memAdd;
	}

    }

}

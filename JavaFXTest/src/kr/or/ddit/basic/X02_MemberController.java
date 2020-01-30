package kr.or.ddit.basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class X02_MemberController implements Initializable {

	@FXML Button btnAdd;
	@FXML TextField txtMemId;
	@FXML TextField txtMemNm;
	@FXML TextField txtMemTel;
	@FXML TextField txtMemAdd;

	ObservableList<Member> data = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@FXML public void btnAddClicked(ActionEvent event) {
		
		btnAdd.setOnAction(e -> {
			data.add(new Member(txtMemId.getText(), txtMemNm.getText(),txtMemTel.getText(), txtMemAdd.getText()));
			infoMsg("작업결과", txtMemNm.getText() + "님의 정보를 추가했습니다.");
		});
		
		
		
		
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

	public class Member{
		
		private String memId;
		private String memNm;
		private String memTel;
		private String memAdd;
		
		public Member(String memId, String memNm, String memTel, String memAdd) {
			super();
			this.memId = memId;
			this.memNm = memNm;
			this.memTel = memTel;
			this.memAdd = memAdd;
		}

		public String getMemId() {
			return memId;
		}

		public void setMemId(String memId) {
			this.memId = memId;
		}

		public String getMemNm() {
			return memNm;
		}

		public void setMemNm(String memNm) {
			this.memNm = memNm;
		}

		public String getMemTel() {
			return memTel;
		}

		public void setMemTel(String memTel) {
			this.memTel = memTel;
		}

		public String getMemAdd() {
			return memAdd;
		}

		public void setMemAdd(String memAdd) {
			this.memAdd = memAdd;
		}

	}
}

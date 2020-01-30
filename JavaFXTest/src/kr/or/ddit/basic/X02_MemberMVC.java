package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class X02_MemberMVC extends Application{

	@FXML TableView table;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("MemberMVCMain.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setTitle("javaFX 회원관리");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
	
		launch(args);
		
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

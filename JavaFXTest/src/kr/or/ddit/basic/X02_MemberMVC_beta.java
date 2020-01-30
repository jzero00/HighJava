package kr.or.ddit.basic;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class X02_MemberMVC_beta extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		GridPane grid = new GridPane();
		grid.setPrefSize(300, 200);
		grid.setPadding(new Insets(10));
		
		Label label1 = new Label("회원 ID");
		Label label2 = new Label("회원이름");
		Label label3 = new Label("회원전화");
		Label label4 = new Label("회원주소");
		
		TextField txtField1 = new TextField();
		TextField txtField2 = new TextField();
		TextField txtField3 = new TextField();
		TextField txtField4 = new TextField();
		
		grid.add(label1, 1, 1);
		grid.add(label2, 1, 2);
		grid.add(label3, 1, 3);
		grid.add(label4, 1, 4);
		grid.add(txtField1, 3, 1, 2, 1);
		grid.add(txtField2, 3, 2, 2, 1);
		grid.add(txtField3, 3, 3, 2, 1);
		grid.add(txtField4, 3, 4, 2, 1);
		
		
		VBox vb1 = new VBox(10);
		vb1.setPadding(new Insets(10));
		
		Scene scene = new Scene(grid);
		primaryStage.setTitle("javaFX 회원관리");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
	
		launch(args);
		
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

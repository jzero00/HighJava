package kr.or.ddit.basic.ref;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.awt.Checkbox;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class PrecticeController implements Initializable {

	@FXML
	private TextField text1;
	@FXML
	private CheckBox trip;
	@FXML
	private CheckBox climbing;
	@FXML
	private CheckBox reading;
	@FXML
	private CheckBox baduk;
	@FXML
	private CheckBox janggi;
	@FXML
	private CheckBox game;
	@FXML
	private CheckBox tennis;
	@FXML
	private CheckBox badminton;
	@FXML
	private Button button1;
	@FXML
	private ToggleGroup group1;
	@FXML
	private TextArea textar;
	private String gender = "";
	private String go = "";
	CheckBox[] checkbox;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		group1.selectedToggleProperty()
				.addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
					if (newValue != null) {
						gender = newValue.getUserData().toString();
					}
				});
		checkbox = new CheckBox[] { trip, climbing, reading, baduk, janggi, game, tennis, badminton };

	}

	@FXML
	public void btnAction(ActionEvent event) {
		if (checkbox[0].isSelected()) {
			go = checkbox[0].textProperty().getValue();
		}
		if (checkbox[1].isSelected()) {
			go += checkbox[1].textProperty().getValue();
		}
		if (checkbox[2].isSelected()) {
			go += checkbox[2].textProperty().getValue();
		}
		if (checkbox[3].isSelected()) {
			go += checkbox[3].textProperty().getValue();
		}
		if (checkbox[4].isSelected()) {
			go += checkbox[4].textProperty().getValue();
		}
		if (checkbox[5].isSelected()) {
			go += checkbox[5].textProperty().getValue();
		}
		if (checkbox[6].isSelected()) {
			go += checkbox[6].textProperty().getValue();
		}
		if (checkbox[7].isSelected()) {
			go += checkbox[7].textProperty().getValue();
		}

		String str = text1.getText();
		str = "이름 : " + str + "\n" + "성별 : " + gender + "\n";
		str += "취미 : " + go + " ";
		textar.setText(str);

	}

}

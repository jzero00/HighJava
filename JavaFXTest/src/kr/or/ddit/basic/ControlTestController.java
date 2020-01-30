package kr.or.ddit.basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.RadioButton;

public class ControlTestController implements Initializable {

	@FXML
	ToggleGroup group1;
	@FXML
	CheckBox travel;
	@FXML
	CheckBox climb;
	@FXML
	CheckBox reading;
	@FXML
	CheckBox badook;
	@FXML
	CheckBox chess;
	@FXML
	CheckBox game;
	@FXML
	CheckBox tennis;
	@FXML
	CheckBox badminton;
	@FXML
	TextArea txtResult;
	@FXML
	RadioButton female;
	@FXML
	RadioButton male;

	boolean settxt = false;
	@FXML
	TextArea name;
	private String gender="";
	CheckBox[] checkbox;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		System.out.println("FXML Load Complete");

		group1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if (group1.getSelectedToggle().getUserData() != null) {
					gender = newValue.getUserData().toString();
				}
			}
			
		});
		checkbox = new CheckBox[]{travel,climb,reading,badook,chess,game,tennis,badminton};
	}

	@FXML
	public void btnClicked(ActionEvent event) {
		String sel = "";
		
		for(int i = 0; i < checkbox.length; i++) {
			if(checkbox[i].isSelected()) {
				sel += checkbox[i].textProperty().getValue();
			}
		}
		
		String result = "이름 : " + name.getText();
		result += "\n성별 : " + gender;
		result += "\n취미 : " + sel;
		txtResult.setText(result);
	}

}

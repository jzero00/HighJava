package kr.or.ddit.basic;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.RadioButton;

public class ControlTestController implements Initializable {

	@FXML
	ToggleGroup gender;
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
	@FXML TextArea name;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		System.out.println("FXML Load Complete");

	}

	public void genderSel() {
		if (male.isSelected()) {
			txtResult.setText(male.getText());
		}
		if (female.isSelected()) {
			txtResult.setText(female.getText());
		}
	}

	// 체크박스 아래의 버튼
	private ArrayList<String> list = new ArrayList<String>();
	String res = "";
	public void hobbySel() {
		if(travel.isSelected()) {
			list.add(travel.getText());
		}
		if(climb.isSelected()) {
			list.add(climb.getText());
		}
		if(reading.isSelected()) {
			list.add(reading.getText());
		}
		if(badook.isSelected()) {
			list.add(badook.getText());
		}
		if(chess.isSelected()) {
			list.add(chess.getText());
		}
		if(game.isSelected()) {
			list.add(game.getText());
		}
		if(tennis.isSelected()) {
			list.add(tennis.getText());
		}
		if(badminton.isSelected()) {
			list.add(badminton.getText());
		}
		for(Object selected : list) {
			res = list.toString();
		}
	}
	
	@FXML public void btnClicked(ActionEvent event) {
			txtResult.setText(res);
		}

}

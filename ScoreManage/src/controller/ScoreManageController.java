package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vo.Score;

public class ScoreManageController implements Initializable{

    @FXML
    private Button add;

    @FXML
    private TableColumn<Score, Integer> mat;

    @FXML
    private Button viewChart;

    @FXML
    private TableColumn<Score, String> name;

    @FXML
    private TableView<Score> tableView;

    @FXML
    private TableColumn<Score, Integer> kor;

    @FXML
    private TableColumn<Score, Integer> eng;

    
    ObservableList<Score> data = FXCollections.observableArrayList(
		//디폴트 데이터
		new Score("고하늘", 100, 80, 70),
		new Score("박성순", 90, 85, 80),
		new Score("배명수", 60, 80, 95),
		new Score("도연우", 95, 95, 75)		
		);
    
    @FXML
    void addStudent(ActionEvent event) throws Exception {

	add.setOnAction(e -> {
	    // 새창 띄우기

	    // 1. Stage객체 생성
	    Stage dialog = new Stage(StageStyle.UTILITY);

	    // 2. 모달창 여부 설정
	    // 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
	    dialog.initModality(Modality.APPLICATION_MODAL);

	    // 3. 부모창 지정
//	    dialog.initOwner(primaryStage);

	    dialog.setTitle("추가");
	    // 4. 자식창이 나타날 컨테이너 객체 생성
	    Parent parent = null;
	    try {
		parent = FXMLLoader.load(getClass().getResource("AddController.fxml"));
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	    
//	  부모창에서 FXML로 만든 자식창의 컨트롤객체 얻기
	    TextField name = (TextField) parent.lookup("#name");
	    TextField kor = (TextField) parent.lookup("#kor");
	    TextField eng = (TextField) parent.lookup("#eng");
	    TextField mat = (TextField) parent.lookup("#mat");
	    
	    Button save = (Button) parent.lookup("#save");
	    save.setOnAction(e2 -> {
		
		if(name.getText().isEmpty() || kor.getText().isEmpty() || eng.getText().isEmpty() || mat.getText().isEmpty()) {
			errMsg("작업오류", "빈 항목이 있습니다.");
			return;
		    }
		    //공백 체크하는 부분
		    if (!Pattern.matches("^[0-9]+$", kor.getText())) {
			errMsg("데이터 오류", "국어 점수는 정수형으로 입력하세요");
			return;
		} else if (!Pattern.matches("^[0-9]+$", eng.getText())) {
		    errMsg("데이터 오류", "영어 점수는 정수형으로 입력하세요");
			return;
		} else if (!Pattern.matches("^[0-9]+$", mat.getText())) {
		    errMsg("데이터 오류", "수학 점수는 정수형으로 입력하세요");
			return;
		}
		data.add(new Score(name.getText(), Integer.parseInt(kor.getText()), Integer.parseInt(eng.getText()), Integer.parseInt(mat.getText())));
		tableView.setItems(data);
		infoMsg("작업결과", name.getText() + "님의 정보를 추가했습니다.");
		dialog.close();
	    });

	    Button cancel = (Button) parent.lookup("#cancel");
	    cancel.setOnAction(e3 -> {
		dialog.close();
	    });
	    
	    // 5. Scene객체 생성해서 컨테이너 객체 추가
	    Scene scene = new Scene(parent);
	    
	    // 6. Stage객체에 Scene객체 추가
	    dialog.setScene(scene);
	    dialog.setResizable(false);// 크기고정
	    dialog.show();
	});
    }


    /**
     * 성적을 볼 사람을 체크했는지 여부를 확인해서 사람이 선택되어 있으면 그사람의
     * 성적을 보여주는 파이차트를 보여주고,
     * 체크하지 않았을 경우에는 사람 전체의 바차트를 보여준다
     */
    @FXML
    void showChart(ActionEvent event) {
	// 1. Stage객체 생성
	    Stage dialog = new Stage(StageStyle.UTILITY);

	    // 2. 모달창 여부 설정
	    // 모달창은 자식창이 나타나면 부모창을 사용할 수 없다.
	    dialog.initModality(Modality.APPLICATION_MODAL);

	    // 3. 부모창 지정
//	    dialog.initOwner(primaryStage);

	    dialog.setTitle("추가");
	    
	if(tableView.getSelectionModel().getSelectedItem() == null) {
	    
	 // 축의 값이 주로 문자열일 때 사용하는 객체
		CategoryAxis xAxis = new CategoryAxis();

		// 축의 값이 숫자일 때 사용하는 객체
		NumberAxis yAxis = new NumberAxis();

		// 위에서 만든 축 정보를 이용한 BarChart객체 생성
		BarChart<String, Number> bc = new BarChart<>(xAxis, yAxis);

		bc.setTitle("막대 그래프");
		// BarChart에 나타날 데이터 구성하기
		XYChart.Series<String, Number> ser1 = new XYChart.Series<>();
		XYChart.Series<String, Number> ser2 = new XYChart.Series<>();
		XYChart.Series<String, Number> ser3 = new XYChart.Series<>();
		for(int i = 0; i < data.size(); i++) {
		    ser1.getData().add(new XYChart.Data<String, Number>(data.get(i).getName(),data.get(i).getKor()));		    
		    ser2.getData().add(new XYChart.Data<String, Number>(data.get(i).getName(),data.get(i).getEng()));
		    ser3.getData().add(new XYChart.Data<String, Number>(data.get(i).getName(),data.get(i).getMat()));		    
		}
		ser1.setName("국어");
		ser2.setName("영어");
		ser3.setName("수학");
		bc.getData().addAll(ser1, ser2, ser3);

		Scene scene = new Scene(bc, 800, 600);

		dialog.setTitle("막대그래프");
		dialog.setScene(scene);
		dialog.show();
	} else {
	    PieChart pieChart = new PieChart();

		// 차트에 나타날 데이터 구성하기
	    	Score score = tableView.getSelectionModel().getSelectedItem();
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
			new PieChart.Data("국어", score.getKor()),
			new PieChart.Data("영어", score.getEng()),
			new PieChart.Data("수학", score.getMat()));

		pieChart.setTitle("파이 그래프");
		pieChart.setLabelLineLength(50);
		pieChart.setLegendSide(Side.RIGHT); // 범례가 나타날 위치
		pieChart.setData(pieChartData); // 데이터 설정

		Scene scene = new Scene(pieChart, 500, 500);
		dialog.setTitle("파이그래프");
		dialog.setScene(scene);
		dialog.show();
	}
    }

    private void infoMsg(String headerText, String msg) {
	Alert infoAlert = new Alert(AlertType.INFORMATION);
	infoAlert.setTitle("정보 확인");
	infoAlert.setHeaderText(headerText);
	infoAlert.setContentText(msg);
	infoAlert.showAndWait();
    }

    public void errMsg(String headerText, String msg) {
	Alert errAlert = new Alert(AlertType.ERROR);
	errAlert.setTitle("오류");
	errAlert.setHeaderText(headerText);
	errAlert.setContentText(msg);
	errAlert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
	ObservableList<Score> data = FXCollections.observableArrayList(
		//디폴트 데이터
		new Score("고하늘", 100, 80, 70),
		new Score("박성순", 90, 85, 80),
		new Score("배명수", 60, 80, 95),
		new Score("도연우", 95, 95, 75)		
		);
	tableView.setItems(data);
	name.setCellValueFactory(new PropertyValueFactory<>("name"));
	kor.setCellValueFactory(new PropertyValueFactory<>("kor"));
	eng.setCellValueFactory(new PropertyValueFactory<>("eng"));
	mat.setCellValueFactory(new PropertyValueFactory<>("mat"));

    }

}


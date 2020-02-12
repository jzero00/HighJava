package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vo.MemberVO;

public class AddPage extends MainPage implements Initializable {
    
    TableView<MemberVO> tableView;

    public AddPage(TableView<MemberVO> tableView) {
	this.tableView = tableView;
    }

    public void addMem() throws IOException {

	// 1. Stage 객체 생성
	Stage dialog = new Stage(StageStyle.UTILITY);

	// 2. 모달창 여부 설정
	dialog.initModality(Modality.APPLICATION_MODAL);

	// 3. 부모창 지정
	dialog.setTitle("회원 추가 페이지");

	// 4. 자식창이 나타날 컨테이너 객체 생성
	Parent parent = FXMLLoader.load(getClass().getResource("addPage.fxml"));

	// 부모창에서 FXML로 만든 자식창의 컨트롤 객체 얻기
	TextField id = (TextField) parent.lookup("#id");
	TextField name = (TextField) parent.lookup("#name");
	TextField tel = (TextField) parent.lookup("#tel");
	TextField addr = (TextField) parent.lookup("#addr");

	Button btnBack = (Button) parent.lookup("#btnCancel");
	Button btnOk = (Button) parent.lookup("#btnOk");

	// 멤버 추가 버튼 눌렀을 때 이벤트 처리
	btnOk.setOnMouseClicked(e -> {

	    if (id.getText().isEmpty() || name.getText().isEmpty() || tel.getText().isEmpty()
		    || addr.getText().isEmpty()) {
		errMsg("작업오류", "빈 항목이 있습니다.");
		return;
	    }

	    MemberVO vo = new MemberVO();

	    vo.setMem_addr(addr.getText());
	    vo.setMem_id(id.getText());
	    vo.setMem_name(name.getText());
	    vo.setMem_tel(tel.getText());

	    if (service.insertMember(vo) == 1) {
		infoMsg("작업 완료", "회원추가 성공.");
		tableView.getItems().add(vo);
		dialog.close();
	    }
	});

	// 뒤로가기 버튼 눌렀을 때 이벤트 처리
	btnBack.setOnAction(e -> {
	    dialog.close();
	});

	// 5. Scene객체 생성해서 컨테이너 객체 추가
	Scene scene = new Scene(parent);

	// 6. Stage객체에 Scene객체 추가
	dialog.setScene(scene);
	dialog.setResizable(false);// 크기고정
	dialog.show();

    }
}

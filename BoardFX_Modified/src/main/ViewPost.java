package main;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vo.BoardVO;

public class ViewPost extends BoardController implements Initializable{

    public void viewContent(TableView<BoardVO> tableView, ObservableList<BoardVO> allTableData) throws IOException {

	BoardVO data = tableView.getSelectionModel().getSelectedItem();
	// 1. Stage 객체 생성
	Stage dialog = new Stage(StageStyle.UTILITY);

	// 2. 모달창 여부 설정
	dialog.initModality(Modality.APPLICATION_MODAL);

	// 3. 부모창 지정
	dialog.setTitle("게시글 창");

	// 4. 자식창이 나타날 컨테이너 객체 생성
	Parent parent = null;
	parent = FXMLLoader.load(getClass().getResource("view_page.fxml"));

	TextField wrtier1 = (TextField) parent.lookup("#board_wrtier");
	TextField title1 = (TextField) parent.lookup("#board_title");
	TextArea content1 = (TextArea) parent.lookup("#board_content");

	Button btnBack = (Button) parent.lookup("#btnBack");
	Button btnEdit = (Button) parent.lookup("#btnEdit");
	Button btnDel = (Button) parent.lookup("#btnDel");

	wrtier1.setText(data.getBoard_writer());
	title1.setText(data.getBoard_title());
	content1.setText(data.getBoard_content());

	// 보여주기만 할거라... 수정은 안되게 막아놓음
	wrtier1.setEditable(false);
	title1.setEditable(false);
	content1.setEditable(false);

	btnBack.setOnAction(e -> {
	    dialog.close();
	});

	// 삭제버튼 눌렀을때 이벤트 처리
	btnDel.setOnAction(e3 -> {

	    Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
	    alertConfirm.setTitle("삭제 확인");
	    alertConfirm.setContentText("선택한 게시글을 삭제하시겠습니까?");

	    // Alert창을 보여주고 사용자가 누른 버튼 값 읽어오기
	    ButtonType confirmResult = alertConfirm.showAndWait().get();

	    if (confirmResult == ButtonType.OK) {

		BoardVO vo2 = new BoardVO();
		vo2.setBoard_no(tableView.getSelectionModel().getSelectedItem().getBoard_no());

		boardService.deletePost(vo2.getBoard_no());

		Alert alertInformation = new Alert(AlertType.INFORMATION);
		alertInformation.setTitle("INFORMATION");
		alertInformation.setContentText("삭제를 완료했습니다.");
		allTableData.addAll(boardService.getAllPostList());
		tableView.setItems(allTableData);
		alertInformation.showAndWait(); // Alert창 보이기

	    } else if (confirmResult == ButtonType.CANCEL) {

		Alert alertInformation = new Alert(AlertType.INFORMATION);
		alertInformation.setTitle("INFORMATION");
		alertInformation.setContentText("삭제를 취소하였습니다.");
		alertInformation.showAndWait(); // Alert창 보이기
		return;
	    }
	    // 새로고침하는 부분

	    paging(allTableData);

	    infoMsg("삭제 완료", "게시글을 삭제하였습니다.");
	    dialog.close();

	});

	// 선택한 수정하는 창 띄우기
	btnEdit.setOnAction(e -> {

	    // 1. Stage 객체 생성
	    Stage dialog2 = new Stage(StageStyle.UTILITY);

	    // 2. 모달창 여부 설정
	    dialog2.initModality(Modality.APPLICATION_MODAL);

	    // 3. 부모창 지정
	    dialog2.setTitle("게시글 수정 창");

	    // 4. 자식창이 나타날 컨테이너 객체 생성
	    Parent parent2 = null;
	    try {
		parent2 = FXMLLoader.load(getClass().getResource("edit_page.fxml"));
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }

	    // 부모창에서 FXML로 만든 자식창의 컨트롤 객체 얻기
	    TextField writer2 = (TextField) parent2.lookup("#board_writer");
	    TextField title2 = (TextField) parent2.lookup("#board_title");
	    TextArea content2 = (TextArea) parent2.lookup("#board_content");

	    Button btnBack2 = (Button) parent2.lookup("#btnBack");
	    Button btnEdit2 = (Button) parent2.lookup("#btnEdit");

	    title2.setEditable(true);
	    content2.setEditable(true);

	    // 목록으로 돌아가기 버튼눌렀을때
	    btnBack2.setOnAction(e1 -> {
		dialog2.close();
	    });
	    // 수정버튼 눌렀을 때 이벤트 처리
	    btnEdit2.setOnAction(e2 -> {

		if (title2.getText().isEmpty() || content2.getText().isEmpty()) {
		    errMsg("작업오류", "빈 항목이 있습니다.");
		    return;
		}

		BoardVO vo2 = new BoardVO();

		vo2.setBoard_title(title2.getText());
		vo2.setBoard_content(content2.getText());
		vo2.setBoard_no(tableView.getSelectionModel().getSelectedItem().getBoard_no());

		boardService.updatePost(vo2);
		if (boardService.updatePost(vo2) == 1) {
		    infoMsg("작업 완료!", "게시글 수정이 완료되었습니다.");
		}
		;
		// 새로고침하는 부분

		paging(allTableData);

		dialog2.close();
		dialog.close();
	    });

	    // 5. Scene객체 생성해서 컨테이너 객체 추가
	    Scene scene = new Scene(parent2);

	    // 6. Stage객체에 Scene객체 추가
	    dialog2.setScene(scene);
	    dialog2.setResizable(false);// 크기고정
	    dialog2.show();

	});

	// 5. Scene객체 생성해서 컨테이너 객체 추가
	Scene scene = new Scene(parent);

	// 6. Stage객체에 Scene객체 추가
	dialog.setScene(scene);
	dialog.setResizable(false);// 크기고정
	dialog.show();
	
    }

}

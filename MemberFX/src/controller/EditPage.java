package controller;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vo.MemberVO;

public class EditPage extends MainPage implements Initializable {

	TableView<MemberVO> tableView;

	public EditPage(TableView<MemberVO> tableView) {
		this.tableView = tableView;
	}

	public void editMem()throws IOException {

		// 1. Stage 객체 생성
		Stage dialog = new Stage(StageStyle.UTILITY);

		// 2. 모달창 여부 설정
		dialog.initModality(Modality.APPLICATION_MODAL);

		// 3. 부모창 지정
		dialog.setTitle("회원 추가 페이지");

		// 4. 자식창이 나타날 컨테이너 객체 생성
		Parent parent = FXMLLoader.load(getClass().getResource("editPage.fxml"));

		// 부모창에서 FXML로 만든 자식창의 컨트롤 객체 얻기
		TextField id = (TextField) parent.lookup("#id");
		TextField name = (TextField) parent.lookup("#name");
		TextField tel = (TextField) parent.lookup("#tel");
		TextField addr = (TextField) parent.lookup("#addr");

		Button btnEdit = (Button) parent.lookup("#btnEdit");
		Button btnDel = (Button) parent.lookup("#btnDel");
		Button btnCancel = (Button) parent.lookup("#btnCancel");

		//멤버 수정 버튼 눌렀을 때 이벤트 처리

		if(tableView.getSelectionModel().getSelectedItem() != null) {

			btnEdit.setOnMouseClicked(e -> {

				id.setText(tableView.getSelectionModel().getSelectedItem().getMem_id());
				name.setText(tableView.getSelectionModel().getSelectedItem().getMem_name());
				tel.setText(tableView.getSelectionModel().getSelectedItem().getMem_tel());
				addr.setText(tableView.getSelectionModel().getSelectedItem().getMem_addr());
				
				if (id.getText().isEmpty() || name.getText().isEmpty() || tel.getText().isEmpty() || addr.getText().isEmpty()) {
					errMsg("작업오류", "빈 항목이 있습니다.");
					return;
				}

				MemberVO input = new MemberVO();

				input.setMem_addr(addr.getText());
				input.setMem_id(id.getText());
				input.setMem_name(name.getText());
				input.setMem_tel(tel.getText());

				service.updateMember(input);

				int i = service.updateMember(input);

				if(i == 1) {
					service.updateMember(input);
//					tableView.setItems((ObservableList<MemberVO>) service.getAllMemberList());
					infoMsg("작업 완료", "회원정보 수정 성공.");
					dialog.close();
				}
			});

			//삭제버튼 눌렀을 때 이벤트 처리
			btnDel.setOnAction(e -> {
				
				service.deleteMember(tableView.getSelectionModel().getSelectedItem().getMem_id());
			});

			//뒤로가기 버튼 눌렀을 때 이벤트 처리
			btnCancel.setOnAction(e -> {
				dialog.close();
			});
		}


		// 5. Scene객체 생성해서 컨테이너 객체 추가
		Scene scene = new Scene(parent);

		// 6. Stage객체에 Scene객체 추가
		dialog.setScene(scene);
		dialog.setResizable(false);// 크기고정
		dialog.show();

	}

}


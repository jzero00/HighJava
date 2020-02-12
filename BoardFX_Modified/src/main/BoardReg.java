package main;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import vo.BoardVO;

public class BoardReg extends BoardController implements Initializable{

    public void registry(Button btn_reg, ObservableList<BoardVO> allTableData) {
	
	btn_reg.setOnAction(e -> {
	    // 게시글 등록창 띄우기
	    
	    // 1. Stage 객체 생성
	    Stage dialog = new Stage(StageStyle.UTILITY);
	    
	    // 2. 모달창 여부 설정
	    dialog.initModality(Modality.APPLICATION_MODAL);
	    
	    // 3. 부모창 지정
	    dialog.setTitle("게시글 등록 창");
	    
	    // 4. 자식창이 나타날 컨테이너 객체 생성
	    Parent parent = null;
	    try {
		parent = FXMLLoader.load(getClass().getResource("reg_page.fxml"));
	    } catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	    }
	    
	    // 부모창에서 FXML로 만든 자식창의 컨트롤 객체 얻기
	    TextField wrtier = (TextField) parent.lookup("#board_wrtier");
	    TextField title = (TextField) parent.lookup("#board_title");
	    TextArea content = (TextArea) parent.lookup("#board_content");
	    
	    Button btnBack = (Button) parent.lookup("#btnBack");
	    Button btnReg = (Button) parent.lookup("#btnReg");
	    
	    btnBack.setOnAction(e2 -> {
		dialog.close();
	    });
	    
	    // 등록버튼 눌렀을때 이벤트 처리
	    btnReg.setOnAction(e3 -> {
		
		// 공백체크하는 부분
		if (wrtier.getText().isEmpty() || title.getText().isEmpty() || content.getText().isEmpty()) {
		    errMsg("작업오류", "빈 항목이 있습니다.");
		    return;
		}
		
		BoardVO vo = new BoardVO();
		vo.setBoard_writer(wrtier.getText());
		vo.setBoard_title(title.getText());
		vo.setBoard_content(content.getText());
		
		int chk = boardService.regPost(vo);
		
		if (chk == 1) {
		    
		    infoMsg("작업 완료", "게시글이 등록되었습니다.");
		    
		    // 새로고침하는 부분
		    paging(allTableData);
		    
		    dialog.close();
		    wrtier.clear();
		    title.clear();
		    content.clear();
		}
		
	    });
	    
	    // 5. Scene객체 생성해서 컨테이너 객체 추가
	    Scene scene = new Scene(parent);
	    
	    // 6. Stage객체에 Scene객체 추가
	    dialog.setScene(scene);
	    dialog.setResizable(false);// 크기고정
	    dialog.show();
	});
	
    }



}

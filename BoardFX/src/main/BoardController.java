package main;

import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import service.BoardService;
import service.BoardServiceImpl;
import vo.BoardVO;
import javafx.scene.input.MouseEvent;

public class BoardController implements Initializable{

    BoardService boardService = BoardServiceImpl.getInstance();

    @FXML
    private Pagination pagination;

    @FXML
    private TableColumn<BoardVO, String> board_title;

    @FXML
    private TableColumn<BoardVO, ?> board_date;

    @FXML
    private TableColumn<BoardVO, Integer> board_no;

    @FXML
    private TableView<BoardVO> tableView;

    @FXML
    private Button btn_reg;

    private int from, to, itemsForPage;

    private ObservableList<BoardVO> allTableData, currentPageData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

	allTableData = FXCollections.observableArrayList();

	board_no.setCellValueFactory(new PropertyValueFactory<>("board_no"));
	board_title.setCellValueFactory(new PropertyValueFactory<>("board_title"));
	board_date.setCellValueFactory(new PropertyValueFactory<>("board_date"));

	//메인 테이블 데이터 설정
	allTableData.addAll(boardService.getAllPostList());

	itemsForPage = 10; // 한페이지에 보여줄 항목 수 설정
	int totPageCount = allTableData.size() % itemsForPage == 0 ? allTableData.size() / itemsForPage
		: allTableData.size() / itemsForPage + 1;
	pagination.setPageCount(totPageCount); // 전체페이지 수 설정

	pagination.setPageFactory(new Callback<Integer, Node>() {

	    @Override
	    public Node call(Integer pageIndex) {
		from = pageIndex * itemsForPage;
		to = from + itemsForPage - 1;
		tableView.setItems(getTableViewData(from, to));

		return tableView;
	    }

	    private ObservableList<BoardVO> getTableViewData(int from, int to) {
		// 현재 페이지의 데이터 초기화
		currentPageData = FXCollections.observableArrayList();

		int totSize = allTableData.size();
		for (int i = from; i <= to && i < totSize; i++) {
		    currentPageData.add(allTableData.get(i));
		}

		return currentPageData;
	    }
	});

	//	tableView.




    }

    @FXML
    void btn_regPushed(ActionEvent event) {

	btn_reg.setOnAction(e -> {
	    //게시글 등록창 띄우기

	    //1. Stage 객체 생성
	    Stage dialog = new Stage(StageStyle.UTILITY);

	    //2. 모달창 여부 설정
	    dialog.initModality(Modality.APPLICATION_MODAL);

	    //3. 부모창 지정
	    dialog.setTitle("게시글 등록 창");

	    //4. 자식창이 나타날 컨테이너 객체 생성
	    Parent parent = null;
	    try {
		parent = FXMLLoader.load(getClass().getResource("reg_page.fxml"));
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }

	    //부모창에서 FXML로 만든 자식창의 컨트롤 객체 얻기
	    TextField wrtier = (TextField) parent.lookup("#board_wrtier");
	    TextField title = (TextField) parent.lookup("#board_title");
	    TextArea content = (TextArea) parent.lookup("#board_content");

	    Button btnBack = (Button) parent.lookup("#btnBack");
	    Button btnReg = (Button) parent.lookup("#btnReg");

	    btnBack.setOnAction(e2 ->{
		//		Alert alertConfirm = new Alert(AlertType.CONFIRMATION);
		//		alertConfirm.setTitle("CONFIRMATION");

		//		ButtonType confirmResult = alertConfirm.showAndWait().get();
		dialog.close();
	    });

	    btnReg.setOnAction(e3 -> {
		//공백체크하는 부분
		if(wrtier.getText().isEmpty() || title.getText().isEmpty() || content.getText().isEmpty()) {
		    errMsg("작업오류", "빈 항목이 있습니다.");
		    return;
		}
		BoardVO vo = new BoardVO();
		vo.setBoard_writer(wrtier.getText());
		vo.setBoard_title(title.getText());
		vo.setBoard_content(content.getText());
		boardService.regPost(vo);
		ObservableList<BoardVO> list = FXCollections.observableArrayList(boardService.getAllPostList());

		tableView.setItems(list);
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


    public void errMsg(String headerText, String msg) {
	Alert errAlert = new Alert(AlertType.ERROR);
	errAlert.setTitle("오류");
	errAlert.setHeaderText(headerText);
	errAlert.setContentText(msg);
	errAlert.showAndWait();
    }

    private void infoMsg(String headerText, String msg) {
	Alert infoAlert = new Alert(AlertType.INFORMATION);
	infoAlert.setTitle("정보 확인");
	infoAlert.setHeaderText(headerText);
	infoAlert.setContentText(msg);
	infoAlert.showAndWait();
    }

    //게시글 보는 기능
    @FXML public void onclick(MouseEvent event){

	BoardVO data = tableView.getSelectionModel().getSelectedItem();
	//1. Stage 객체 생성
	Stage dialog = new Stage(StageStyle.UTILITY);

	//2. 모달창 여부 설정
	dialog.initModality(Modality.APPLICATION_MODAL);

	//3. 부모창 지정
	dialog.setTitle("게시글 창");

	//4. 자식창이 나타날 컨테이너 객체 생성
	Parent parent = null;
	try {
	    parent = FXMLLoader.load(getClass().getResource("view_page.fxml"));
	} catch (IOException ex) {
	    ex.printStackTrace();
	}

	TextField wrtier1 = (TextField) parent.lookup("#board_wrtier");
	TextField title1 = (TextField) parent.lookup("#board_title");
	TextArea content1 = (TextArea) parent.lookup("#board_content");

	Button btnBack = (Button) parent.lookup("#btnBack");
	Button btnEdit = (Button) parent.lookup("#btnEdit");

	
	wrtier1.setText(data.getBoard_writer());
	title1.setText(data.getBoard_title());
	content1.setText(data.getBoard_content());
	
	
	//보여주기만 할거라... 수정은 안되게 막아놓음
	wrtier1.setEditable(false);
	title1.setEditable(false);
	content1.setEditable(false);


	btnBack.setOnAction(e ->{
	    dialog.close();
	});

	//선택한 수정하는 창 띄우기
	btnEdit.setOnAction(e -> {
	    
	  //1. Stage 객체 생성
	    Stage dialog2 = new Stage(StageStyle.UTILITY);
	    
	  //2. 모달창 여부 설정
	    dialog2.initModality(Modality.APPLICATION_MODAL);
	    
	  //3. 부모창 지정
	    dialog2.setTitle("게시글 수정 창");
	    
	  //4. 자식창이 나타날 컨테이너 객체 생성
	    Parent parent2 = null;
	    try {parent2 = FXMLLoader.load(getClass().getResource("edit_page.fxml"));
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	    
	  //부모창에서 FXML로 만든 자식창의 컨트롤 객체 얻기
	    TextField wrtier = (TextField) parent2.lookup("#board_wrtier");
	    TextField title = (TextField) parent2.lookup("#board_title");
	    TextArea content = (TextArea) parent2.lookup("#board_content");

	    Button btnBack2 = (Button) parent2.lookup("#btnBack");
	    Button btnEdit2 = (Button) parent2.lookup("#btnEdit");
	    
	    //목록으로 돌아가기 버튼눌렀을때
	    btnBack2.setOnAction(e1 -> {
		dialog.close();
	    });
	    //수정버튼 눌렀을 떄 이벤트 처리
	    btnEdit2.setOnAction(e2 ->{
		
	    if(wrtier1.getText().isEmpty() || title1.getText().isEmpty() || content1.getText().isEmpty()) {
		    errMsg("작업오류", "빈 항목이 있습니다.");
		    return;
	    }
	    BoardVO vo2 = new BoardVO();
	    BoardVO vo3 = tableView.getSelectionModel().getSelectedItem();
	    vo2.setBoard_title(title1.getText());
	    vo2.setBoard_content(content1.getText());
	    vo2.setBoard_no();
	    
	    boardService.updatePost(vo2);
	    if (boardService.updatePost(vo2) == 1) {
		infoMsg("작업 완료!", "게시글 수정이 완료되었습니다.");
	    };
		
	    });
   
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

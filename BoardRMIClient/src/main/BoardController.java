package main;

import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
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
import vo.BoardVO;
import javafx.scene.input.MouseEvent;

public class BoardController implements Initializable {

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
    BoardService boardService;
    
    Registry reg;
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

	try {
	reg = LocateRegistry.getRegistry("localhost", 4869);
	BoardService boardService = (BoardService) reg.lookup("boardService");
	} catch (RemoteException | NotBoundException e) {
	    e.printStackTrace();
	}
	
	allTableData = FXCollections.observableArrayList();

	board_no.setCellValueFactory(new PropertyValueFactory<>("board_no"));
	board_title.setCellValueFactory(new PropertyValueFactory<>("board_title"));
	board_date.setCellValueFactory(new PropertyValueFactory<>("board_date"));

	paging(allTableData);

	allTableData.addAll(boardService.getAllPostList());
	tableView.setItems(allTableData);

    }

    @FXML
    public void btn_regPushed(ActionEvent event) {
	
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

    // 게시글 보는 기능
    @FXML
    public void onclick(MouseEvent event) throws IOException {

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

    protected void paging(ObservableList<BoardVO> allTableData) {

	List<BoardVO> cellRefresh = new ArrayList<>();
	cellRefresh = boardService.getAllPostList();
	ObservableList<BoardVO> allTableData1 = FXCollections.observableArrayList(cellRefresh);
	tableView.setItems(allTableData1);

	itemsForPage = 10; // 한페이지에 보여줄 항목 수 설정

	int totPageCount = allTableData1.size() % itemsForPage == 0 ? allTableData1.size() / itemsForPage

		: allTableData1.size() / itemsForPage + 1;

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

		int totSize = allTableData1.size();
		for (int i = from; i <= to && i < totSize; i++) {
		    currentPageData.add(allTableData1.get(i));
		}

		return currentPageData;
	    }
	});

    }

// 에러 메세지 창
    public void errMsg(String headerText, String msg) {
	Alert errAlert = new Alert(AlertType.ERROR);
	errAlert.setTitle("오류");
	errAlert.setHeaderText(headerText);
	errAlert.setContentText(msg);
	errAlert.showAndWait();
    }
    
// 알림창
    public void infoMsg(String headerText, String msg) {
	Alert infoAlert = new Alert(AlertType.INFORMATION);
	infoAlert.setTitle("정보 확인");
	infoAlert.setHeaderText(headerText);
	infoAlert.setContentText(msg);
	infoAlert.showAndWait();
    }
}

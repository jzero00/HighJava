package main;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import service.BoardService;
import service.BoardServiceImpl;
import vo.BoardVO;
import javafx.scene.input.MouseEvent;

public class BoardController implements Initializable {

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

	paging(allTableData);

	allTableData.addAll(boardService.getAllPostList());
	tableView.setItems(allTableData);

    }

    @FXML
    public void btn_regPushed(ActionEvent event) {

	BoardReg reg = new BoardReg();
	reg.registry(btn_reg, allTableData);
	
    }

    /**
     * 테이블 뷰에 있는 내용을 클릭했을 때 팝업창을 띄워 내용을 확인하고
     * 내용 수정과 삭제를 할 수 있음
     * @param event
     * @throws IOException
     */
    @FXML
    public void onclick(MouseEvent event) throws IOException {

	ViewPost view = new ViewPost();
	view.viewContent(tableView, allTableData);
	
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

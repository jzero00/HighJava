package kr.or.ddit.basic;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.scene.control.TableColumn;
import javafx.scene.Node;
import javafx.scene.control.Pagination;

public class PagingControllermy implements Initializable {

    @FXML
    TableView<MemberVO> tableView;
    @FXML
    TableColumn<MemberVO, String> id;
    @FXML
    TableColumn<MemberVO, String> name;
    @FXML
    TableColumn<MemberVO, String> address;
    @FXML
    Pagination pagination;

    private int from, to, itemsForPage;

    ObservableList<MemberVO> allTableData, currentPageData;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

	id.setCellValueFactory(new PropertyValueFactory<>("id"));
	name.setCellValueFactory(new PropertyValueFactory<>("name"));
	address.setCellValueFactory(new PropertyValueFactory<>("address"));

	// 전체 테이블 데이터 설정
	allTableData = FXCollections.observableArrayList();

	allTableData.add(new MemberVO("1", "홍길동1", "대전시 중구 대흥동 대덕인재개발원"));
	allTableData.add(new MemberVO("2", "홍길동2", "대전시 중구 대흥동 대덕인재개발원"));
	allTableData.add(new MemberVO("3", "홍길동3", "대전시 중구 대흥동 대덕인재개발원"));
	allTableData.add(new MemberVO("4", "홍길동4", "대전시 중구 대흥동 대덕인재개발원"));
	allTableData.add(new MemberVO("5", "홍길동5", "대전시 중구 대흥동 대덕인재개발원"));
	allTableData.add(new MemberVO("6", "홍길동6", "대전시 중구 대흥동 대덕인재개발원"));
	allTableData.add(new MemberVO("7", "홍길동7", "대전시 중구 대흥동 대덕인재개발원"));
	allTableData.add(new MemberVO("8", "홍길동8", "대전시 중구 대흥동 대덕인재개발원"));
	allTableData.add(new MemberVO("9", "홍길동9", "대전시 중구 대흥동 대덕인재개발원"));
	allTableData.add(new MemberVO("10", "홍길동10", "대전시 중구 대흥동 대덕인재개발원"));
	allTableData.add(new MemberVO("11", "홍길동11", "대전시 중구 대흥동 대덕인재개발원"));
	allTableData.add(new MemberVO("12", "홍길동12", "대전시 중구 대흥동 대덕인재개발원"));
	allTableData.add(new MemberVO("13", "홍길동13", "대전시 중구 대흥동 대덕인재개발원"));
	allTableData.add(new MemberVO("14", "홍길동14", "대전시 중구 대흥동 대덕인재개발원"));

	itemsForPage = 5; // 한페이지에 보여줄 항목 수 설정
	int toPageCount = allTableData.size() % itemsForPage == 0 ?
		allTableData.size() / itemsForPage
		: allTableData.size() / itemsForPage + 1;
	pagination.setPageCount(toPageCount); // 전체 페이지 수 설정

	pagination.setPageFactory(new Callback<Integer, Node>() {

	    @Override
	    public Node call(Integer pageIndex) {
		from = pageIndex * itemsForPage;
		to = from + itemsForPage - 1;
		tableView.setItems(getTableViewData(from, to));
		return tableView;
	    }
	});

    }

    /**
     * TableView에 재워줄 데이터를 가져오는 메소드
     * 
     * @param from2
     * @param to2
     * @return currentPageData
     */
    
    protected ObservableList<MemberVO> getTableViewData(int from2, int to2) {

	// 현재 페이지의 데이터 초기화
	currentPageData = FXCollections.observableArrayList();

	int totSize = allTableData.size();
	for (int i = 0; i <= to && i < totSize; i++) {
	    currentPageData.add(allTableData.get(i));
	}
	return currentPageData;
    }
}


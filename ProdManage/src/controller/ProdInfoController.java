package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import service.LprodService;
import service.LprodServiceImpl;
import vo.LprodVO;

public class ProdInfoController implements Initializable {

    LprodService lprodService = LprodServiceImpl.getInstance();

    @FXML
    private TableColumn<LprodVO, Integer> prod_sale;

    @FXML
    private ComboBox<LprodVO> namebox;

    @FXML
    private TableColumn<LprodVO, String> prod_lgu;

    @FXML
    private TableColumn<LprodVO, String> p_buyer;

    @FXML
    private TableColumn<LprodVO, String> prod_detail;

    @FXML
    private TableColumn<LprodVO, Integer> prod_price;

    @FXML
    private TableColumn<LprodVO, String> prod_outline;

    @FXML
    private ComboBox<LprodVO> nmbox;

    @FXML
    private TableColumn<LprodVO, Integer> prod_cost;

    @FXML
    private TableColumn<LprodVO, String> prod_id;

    @FXML
    private TableColumn<LprodVO, String> prod_name;

    @FXML
    void lpSelect(ActionEvent event) {
	LprodVO vo = new LprodVO();
	String lprod_nm = nmbox.getSelectionModel().getSelectedItem().getLprod_nm();
	vo.setLprod_nm(lprod_nm);
	List <LprodVO> list = lprodService.SelectProdName(vo);
	ObservableList<LprodVO> prodNmList = FXCollections.observableArrayList(list);
	namebox.setItems(prodNmList);
	
	namebox.setCellFactory(new Callback<ListView<LprodVO>, ListCell<LprodVO>>() {

	    @Override
	    public ListCell<LprodVO> call(ListView<LprodVO> param) {
		return new ListCell<LprodVO>() {
		    protected void updateItem(LprodVO item, boolean empty) {
			super.updateItem(item, empty);
			if (item == null || empty) {
			    setText(null);
			} else {
			    setText(item.getLprod_nm()); // 이름으로 셋팅 
			}
		    }
		};
	    }
	});
	namebox.setButtonCell(new ListCell<LprodVO>() {
	    protected void updateItem(LprodVO item, boolean empty) {
		super.updateItem(item, empty);
		if (empty) {
		    setText(null);
		} else {
		    setText(item.getProd_name());
		}
	    }
	});
	
    }

    @FXML
    void pSelect(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
	List<LprodVO> lprodList = lprodService.SelectLprodName();

	ObservableList<LprodVO> nameList = FXCollections.observableArrayList(lprodList);

	nmbox.setItems(nameList);

	nmbox.setCellFactory(new Callback<ListView<LprodVO>, ListCell<LprodVO>>() {

	    @Override
	    public ListCell<LprodVO> call(ListView<LprodVO> param) {
		return new ListCell<LprodVO>() {
		    protected void updateItem(LprodVO lprod_nm, boolean empty) {
			super.updateItem(lprod_nm, empty);
			if (lprod_nm == null || empty) {
			    setText(null);
			} else {
			    setText(lprod_nm.getLprod_nm());
			}
		    }
		};
	    }
	});

	nmbox.setButtonCell(new ListCell<LprodVO>() {
	    protected void updateItem(LprodVO lprod_nm, boolean empty) {
		super.updateItem(lprod_nm, empty);
		if (lprod_nm == null || empty) {
		    setText(null);
		} else {
		    setText(lprod_nm.getLprod_nm());
		}
	    }
	});
    }
}

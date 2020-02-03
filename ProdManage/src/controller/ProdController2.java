package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import service.LprodService;
import service.LprodServiceImpl;
import vo.LprodVO;

public class ProdController2 implements Initializable {

    @FXML
    private TableColumn<?, ?> prod_sale;
    @FXML
    private TableView<LprodVO> view;
    @FXML
    private TableColumn<?, ?> prod_lgu;
    @FXML
    private TableColumn<?, ?> prod_detail;
    @FXML
    private ComboBox<LprodVO> lprod_name_box;
    @FXML
    private ComboBox<LprodVO> prod_name_box;
    @FXML
    private TableColumn<LprodVO, ?> prod_price;
    @FXML
    private TableColumn<LprodVO, ?> prod_outline;
    @FXML
    private TableColumn<?, ?> prod_cost;
    @FXML
    private TableColumn<?, ?> prod_id;
    @FXML
    private TableColumn<LprodVO, String> prod_name;
    @FXML
    private TableColumn<?, ?> prod_buyer;

    LprodService lprodService = LprodServiceImpl.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

	List<LprodVO> lprodList = lprodService.SelectLprodName();

	ObservableList<LprodVO> nameList = FXCollections.observableArrayList(lprodList);

	lprod_name_box.setItems(nameList);

	lprod_name_box.setCellFactory(new Callback<ListView<LprodVO>, ListCell<LprodVO>>() {

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
	lprod_name_box.setButtonCell(new ListCell<LprodVO>() {
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

    @FXML
    void lpSelect(ActionEvent event) {
	
    }
    
    @FXML
    void pSelect(ActionEvent event) {
//	lprod_name_box.setOnAction(new EventHandler<ActionEvent>() {
//	    
//	    @Override
//	    public void handle(ActionEvent event) {
//		
//		LprodVO vo = new LprodVO();
//		
//		String lprod_nm = lprod_name_box.getSelectionModel().getSelectedItem().getLprod_nm();
//		
//		vo.setLprod_nm(lprod_nm);
//		
//		List<LprodVO> list = lprodService.SelectProdName(vo);
//		
//		ObservableList<LprodVO> prodNmList = FXCollections.observableArrayList(list);
//		
//		prod_name_box.setItems(prodNmList);
//		
//		prod_name_box.setCellFactory(new Callback<ListView<LprodVO>, ListCell<LprodVO>>() {
//		    
//		    @Override
//		    public ListCell<LprodVO> call(ListView<LprodVO> param) {
//			return new ListCell<LprodVO>() {
//			    protected void updateItem(LprodVO prod_name, boolean empty) {
//				super.updateItem(prod_name, empty);
//				if (prod_name == null || empty) {
//				    setText(null);
//				} else {
//				    setText(prod_name.getProd_name());
//				}
//			    }
//			};
//		    }
//		});
		lprod_name_box.setButtonCell(new ListCell<LprodVO>() {
		    protected void updateItem(LprodVO prod_name, boolean empty) {
			super.updateItem(prod_name, empty);
			if (prod_name == null || empty) {
			    setText(null);
			} else {
			    setText(prod_name.getProd_name());
			}
		    }
		});
//		
//	    }
//	});
    }
}

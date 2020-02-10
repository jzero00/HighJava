package controller;

import java.net.URL;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import service.LprodService;
import service.LprodServiceImpl;
import vo.LprodVO;
import javafx.scene.control.TableView;

public class ProdInfoController implements Initializable {

    LprodService lprodService = LprodServiceImpl.getInstance();

    @FXML TableView<LprodVO> table;
    @FXML private ComboBox<LprodVO> com1;
    @FXML private ComboBox<LprodVO> com2;
    @FXML private TableColumn<LprodVO, String> prod_id;
    @FXML private TableColumn<LprodVO, String> prod_name;
    @FXML private TableColumn<LprodVO, String> prod_lgu;
    @FXML private TableColumn<LprodVO, String> prod_buyer;
    @FXML private TableColumn<LprodVO, Integer> prod_cost;
    @FXML private TableColumn<LprodVO, Integer> prod_price;
    @FXML private TableColumn<LprodVO, Integer> prod_sale;
    @FXML private TableColumn<LprodVO, String> prod_outline;
    @FXML private TableColumn<LprodVO, String> prod_detail;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
	prod_id.setCellValueFactory(new PropertyValueFactory<>("prod_id"));
	prod_name.setCellValueFactory(new PropertyValueFactory<>("prod_name"));
	prod_lgu.setCellValueFactory(new PropertyValueFactory<>("prod_lgu"));
	prod_buyer.setCellValueFactory(new PropertyValueFactory<>("prod_buyer"));
	prod_cost.setCellValueFactory(new PropertyValueFactory<>("prod_cost"));
	prod_price.setCellValueFactory(new PropertyValueFactory<>("prod_price"));
	prod_sale.setCellValueFactory(new PropertyValueFactory<>("prod_sale"));
	prod_outline.setCellValueFactory(new PropertyValueFactory<>("prod_outline"));
	prod_detail.setCellValueFactory(new PropertyValueFactory<>("prod_detail"));

	ObservableList<LprodVO> com1Data = FXCollections.observableArrayList(lprodService.SelectLprodName());
	com1.setItems(com1Data);

	com1.setCellFactory(new Callback<ListView<LprodVO>, ListCell<LprodVO>>() {

	    @Override
	    public ListCell<LprodVO> call(ListView<LprodVO> param) {

		return new ListCell<LprodVO>() {
		    protected void updateItem(LprodVO item, boolean empty) {
			super.updateItem(item, empty);
			if(!empty) {
			    setText(item.getLprod_nm());
			}
		    }
		};
	    }
	});
	com1.setButtonCell(new ListCell<LprodVO>() {
	    protected void updateItem(LprodVO item, boolean empty) {
		super.updateItem(item, empty);
		if(!empty) {
		    setText(item.getLprod_nm());
		}
	    }
	});

    }

    @FXML public void com1Selected(ActionEvent event) {
	LprodVO vo = com1.getValue();

	ObservableList<LprodVO> com2Data = FXCollections.observableArrayList(lprodService.SelectProdName(vo.getLprod_nm()));
	com2.setItems(com2Data);

	com2.setCellFactory(new Callback<ListView<LprodVO>, ListCell<LprodVO>>() {

	    @Override
	    public ListCell<LprodVO> call(ListView<LprodVO> param) {
		return new ListCell<LprodVO>(){
		    protected void updateItem(LprodVO item, boolean empty) {
			super.updateItem(item, empty);
			if(!empty) {
			    setText(item.getProd_name());
			}
		    }
		};
	    }

	});
	com2.setButtonCell(new ListCell<LprodVO>(){
	    protected void updateItem(LprodVO item, boolean empty) {
		super.updateItem(item, empty);
		if(!empty) {
		    setText(item.getProd_name());
		}
	    }
	});
    }

    @FXML public void com2Selected(ActionEvent event) {

	LprodVO vo = com2.getValue();
	ObservableList<LprodVO> listView = FXCollections.observableArrayList(vo);
	table.setItems(listView);

    }




}

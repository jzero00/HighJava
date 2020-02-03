package prodInfo;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ProdInfoController implements Initializable {

    @FXML ComboBox nmbox;
    @FXML ComboBox namebox;

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

    @FXML public void lpSelect() {}

    @FXML public void pSelect() {}

}

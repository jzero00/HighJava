package prodInfo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProdMain extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
	
	FXMLLoader loader = new FXMLLoader(getClass().getResource("lprod.fxml"));
	Parent root = loader.load();
	
	Scene scene = new Scene(root);
	
	primaryStage.setTitle("상품관리");
	primaryStage.setScene(scene);
	primaryStage.show();
	
    }

    public static void main(String[] args) {
	
	launch(args);
	
    }
}

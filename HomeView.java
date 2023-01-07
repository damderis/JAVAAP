

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;


public class HomeView {

    private Parent view;

    public HomeView() throws IOException {
        
       
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        this.view = root;
    }

    public Parent getView(){
        return this.view;
    }
}

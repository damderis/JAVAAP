import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class CartView {

    private Parent view;

    public CartView()throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("cart.fxml"));
        this.view = root;
    }

    public Parent getView(){
        return this.view;
    }
    
}

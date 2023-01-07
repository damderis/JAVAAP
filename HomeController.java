

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class HomeController {

    @FXML
    private VBox frame;

    @FXML
    private GridPane productGridPane;

    @FXML
    private Label item;

    @FXML
    public void initialize()throws FileNotFoundException { 
        
        productGridPane.getChildren().clear();

        VBox productView1 = productView(Product.MAGGI);
        productGridPane.add(productView1,0,0);

        VBox productView2 = productView(Product.HUPSENG);
        productGridPane.add(productView2,1,0);

        VBox productView3 = productView(Product.OREO);
        productGridPane.add(productView3,2,0);

        VBox productView4 = productView(Product.PEPSI);
        productGridPane.add(productView4,0,1);

        VBox productView5 = productView(Product.EVIAN);
        productGridPane.add(productView5,1,1);
        
        VBox productView6 = productView(Product.CABLE);
        productGridPane.add(productView6,2,1);

        VBox productView7 = productView(Product.CHIP);
        productGridPane.add(productView7,0,2);

        VBox productView8 = productView(Product.BISCUIT);
        productGridPane.add(productView8,1,2);

    }

private VBox productView(Product product) throws FileNotFoundException { 
VBox layout = new VBox();
layout.setAlignment (Pos.CENTER);
FileInputStream input = new FileInputStream("src/"+product.getImageFile());
Image image = new Image (input);
ImageView imageView = new ImageView(image); 

imageView.setFitWidth (100);
imageView.setFitHeight (100);

Label productName = new Label(product.name());
productName.setStyle("-fx-font-size:12pt; -fx-padding:5px; -fx-font-family: impact"); 
BigDecimal value1 = new BigDecimal(String.valueOf(product.getPrice()));
BigDecimal rounded1 = value1.setScale(2, RoundingMode.HALF_UP);
Label price = new Label("RM "+rounded1);
price.setStyle("-fx-font-size:12pt; -fx-padding:5px; -fx-font-family: impact");
Button addButton = new Button("Add to Cart");
addButton.setUserData(product.name());
addButton.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent actionEvent){
        Node sourceComponent = (Node)actionEvent.getSource();
        String productName = (String)sourceComponent.getUserData();
        ShoppingCart shoppingCart = ShoppingCart.getInstance();
        shoppingCart.addProduct(productName);
    }
});

layout.getChildren().addAll(imageView,productName,price,addButton);

return layout;


}
}
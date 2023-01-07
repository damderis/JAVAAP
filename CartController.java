import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CartController {

    static int ind;
    
    @FXML
    private VBox cartPane;

    @FXML
    private Label empty;

    @FXML
    private Label shopCart;

    @FXML
    private Label totalLabel;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private Label thanks;

    @FXML
    public void initialize() throws FileNotFoundException{
        //populate the view
        List<CartEntry> entries = ShoppingCart.getInstance().getEntries();
        cartPane.getChildren().clear();

        if(entries.isEmpty()){
            cartPane.getChildren().add(empty);
            
        }else{
            
            cartPane.getChildren().add(shopCart);
            
            for(CartEntry cartEntry:entries){
                HBox productview = cartEntryView(cartEntry);
                ind++;
                cartPane.getChildren().add(productview);
            }

            Separator separator = new Separator();
            separator.setOrientation(Orientation.HORIZONTAL);
            cartPane.getChildren().add(separator);
            
            HBox totalview =  totalView(ShoppingCart.getInstance().calculateTotal());
            cartPane.getChildren().add(totalview);


        }
    }

    private HBox totalView(BigDecimal totalPrice){
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);

        String total ="RM "+String.valueOf(totalPrice);
        totalPriceLabel.setText(total);

        
        
        Button payButton = new Button("Pay");
        payButton.setStyle("-fx-padding:5px");
        payButton.setOnAction(e->{
            cartPane.getChildren().clear();
            cartPane.getChildren().addAll(empty, thanks);
            ShoppingCart.nullInstance();
        });

        layout.getChildren().addAll(totalLabel, this.totalPriceLabel,payButton);
        return layout;
    }

    private HBox cartEntryView(CartEntry cartEntry) throws FileNotFoundException{
        HBox layout = new HBox();
        layout.setAlignment(Pos.CENTER);
        

        FileInputStream input = new FileInputStream("src/"+cartEntry.getProduct().getImageFile());

        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        Label productName = new Label(cartEntry.getProduct().name());
        productName.setPrefWidth(100);
        
        productName.setStyle("-fx-font-size:15pt; -fx-padding:5px; -fx-font-family: impact");

        Label quantity = new Label(String.valueOf(cartEntry.getQuantity()));
        quantity.setStyle("-fx-padding:5px; -fx-font-family: impact");
        BigDecimal value1 = new BigDecimal(String.valueOf(cartEntry.getProduct().getPrice()));
        BigDecimal rounded1 = value1.setScale(2, RoundingMode.HALF_UP);
        Label price = new Label(String.valueOf("RM "+rounded1));
        price.setStyle("-fx-padding:5px; -fx-font-family: impact");

        Button plusButton = new Button("+");
        plusButton.setStyle("-fx-padding:5px");
        plusButton.setUserData(cartEntry.getProduct().name());
        plusButton.setOnAction(e ->{
            ind++;
            String name = (String)((Node) e.getSource()).getUserData();
            ShoppingCart.getInstance().addProduct(name);
            quantity.setText(String.valueOf(ShoppingCart.getInstance().getQuantity(name)));
            this.totalPriceLabel.setText("RM "+String.valueOf(ShoppingCart.getInstance().calculateTotal()));
        });

        Button minusButton = new Button("-");
        minusButton.setStyle("-fx-padding:5px");
        minusButton.setUserData(cartEntry.getProduct().name());
        minusButton.setOnAction(e ->{
            ind--;
            String name = (String)((Node) e.getSource()).getUserData();
            if(ShoppingCart.getInstance().getQuantity(name)==1){
                layout.getChildren().clear();
                if(ind==0){
                    cartPane.getChildren().clear();
                    cartPane.getChildren().add(empty);
                    ShoppingCart.nullInstance();
                }
            }
            
            ShoppingCart.getInstance().removeProduct(name);
            
            quantity.setText(String.valueOf(ShoppingCart.getInstance().getQuantity(name)));
            this.totalPriceLabel.setText("RM "+String.valueOf(ShoppingCart.getInstance().calculateTotal()));
            
        });

        
        
        
        layout.getChildren().addAll(imageView, productName, plusButton,quantity,minusButton,price);

        return layout;
    }

}

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class ProfileController {
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtMatric;
    @FXML
    private TextField txtPhone;

    @FXML
    private Label lblName;
    @FXML
    private Label lblMatric;
    @FXML
    private Label lblPhone;

    @FXML
    private Button btEdit;
    @FXML
    private Button btUpdate;

    @FXML
    private VBox profilePane;

    @FXML
    void EditValue(MouseEvent event) {
        btEdit.setOnMouseClicked(e ->{
            btEdit.setVisible(false);
            btUpdate.setVisible(true);

            lblName.setVisible(false);
            lblMatric.setVisible(false);
            lblPhone.setVisible(false);

            txtName.setVisible(true);
            txtMatric.setVisible(true);
            txtPhone.setVisible(true);
        });
        
    }

    @FXML
    void UpdateValue(MouseEvent event) {
        btUpdate.setOnMouseClicked(e->{
            btUpdate.setVisible(false);
            btEdit.setVisible(true);
            
            lblName.setText(txtName.getText());
            lblMatric.setText(txtMatric.getText());
            lblPhone.setText(txtPhone.getText());

            txtName.setVisible(false);
            txtMatric.setVisible(false);
            txtPhone.setVisible(false);

            lblName.setVisible(true);
            lblMatric.setVisible(true);
            lblPhone.setVisible(true);
        });
    }

}


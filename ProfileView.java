import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class ProfileView {

    private Parent view;

    public ProfileView()throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("profile.fxml"));
        this.view = root;
    }

    public Parent getView(){
        return this.view;
    }
    
}
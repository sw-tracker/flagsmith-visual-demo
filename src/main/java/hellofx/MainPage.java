package hellofx;

import java.time.LocalDateTime;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainPage {

  // We need a private variable so we can change the text on a click on the button.
//  private static Label clicked;

  public static VBox getHolder(String email, String colour, String country) {
    // Traits
    Label emailLabel = new Label("email: " + email);
    emailLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
    Label countryLabel = new Label("country: " + country);
    countryLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

    // Holder to align the items vertically
    VBox holder = new VBox(emailLabel, countryLabel);
    holder.setSpacing(20);
    holder.setPadding(new Insets(0, 20, 10, 20));
    holder.setAlignment(Pos.CENTER);
    holder.setStyle("-fx-background-color: " + colour);

    return holder;
  }

//  public static VBox getHolder() {
//    // Requesting the Java versions used by the application
////    final String versionJava = System.getProperty("java.version");
////    final String versionJavaFX = System.getProperty("javafx.version");
//
//    // Show Java versions in a label
//    Label versions = new Label("Email: " + EMAIL);
//    versions.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");
//
//    // Text label in which a text will be displayed when you click on the button
//    clicked = new Label();
//    clicked.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
//
//    // Button to have some interaction
//    Button button = new Button("Click me");
//    button.setStyle("-fx-text-fill: black; -fx-font-size: 14px;");
//    button.setOnAction(e -> clicked.setText("You clicked me on " + LocalDateTime.now().toString()));
//
//    // Button to exit the application
////    Button exitButton = new Button("Exit");
////    exitButton.setStyle("-fx-text-fill: black; -fx-font-size: 14px;");
////    exitButton.setOnAction(e -> ((Stage) exitButton.getScene().getWindow()).close());
//
//    // Holder to align the items vertically
////    VBox holder = new VBox(versions, button, clicked, exitButton);
//    VBox holder = new VBox(versions, button, clicked);
//    holder.setSpacing(20);
//    holder.setPadding(new Insets(0, 20, 10, 20));
//    holder.setAlignment(Pos.CENTER);
//    holder.setStyle("-fx-background-color: darkblue");
//
//    return holder;
//  }
}
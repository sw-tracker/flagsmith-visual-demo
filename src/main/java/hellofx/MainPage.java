package hellofx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

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

  public static GridPane getGrid(String email, String colour, String country) {
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(25, 25, 25, 25));
    grid.setStyle("-fx-background-color: " + colour);

    Text scenetitle = new Text("Welcome!");
    scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
    scenetitle.setStyle("-fx-text-fill: white");
    grid.add(scenetitle, 0, 0, 2, 1);

    grid.add(createLabel("Email:"), 0, 1);
    TextField userTextField = new TextField(email);
    grid.add(userTextField, 1, 1);

    grid.add(createLabel("Country:"), 0, 2);
    TextField countryField = new TextField(country);
    grid.add(countryField, 1, 2);

    return grid;
  }

  private static Label createLabel(String content) {
    final Label lbl = new Label(content);
    lbl.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
    return lbl;
  }
}
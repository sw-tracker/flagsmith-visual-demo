package hellofx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class MainPage {

  private static GridPane grid;

  public static GridPane getGrid(String email, String colour, String country) {
    grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(5, 10, 5, 10));
    grid.setStyle("-fx-background-color: " + colour);

    grid.add(createLabel("Email:"), 0, 1);
    grid.add(createLabel(email), 1, 1);

    grid.add(createLabel("Country:"), 0, 2);
    TextField countryField = new TextField(country);
    grid.add(countryField, 1, 2);

    grid.add(createLabel("Money:"), 0, 3);
    TextField moneyField = new TextField("1000");
    grid.add(moneyField, 1, 3);

    // Button to have some interaction
    Button button = new Button("Update");
    button.setStyle("-fx-text-fill: black; -fx-font-size: 14px;");
    EventHandler<ActionEvent> onButtonClicked = e -> {
      System.out.println("Button clicked");
      grid.setStyle("-fx-background-color: black");
    };
    button.setOnAction(onButtonClicked);
    grid.add(button, 1, 4);

    return grid;
  }

  private static Label createLabel(String content) {
    final Label lbl = new Label(content);
    lbl.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-min-width: 60px");
    return lbl;
  }
}
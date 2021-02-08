package hellofx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import org.jetbrains.annotations.NotNull;

public class MainPage {

  private static GridPane grid;
  private static Button button;

  public static GridPane getGrid(String email, String colour, String country, boolean updateButtonEnabled) {
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
    button = new Button("Update Traits");
    button.setStyle("-fx-text-fill: black; -fx-font-size: 14px;");
    setUpdateTraitsButtonVisible(updateButtonEnabled);
    button.setOnAction(getActionButtonClickedEventEventHandler());
    grid.add(button, 1, 4);

    return grid;
  }

  @NotNull
  private static EventHandler<ActionEvent> getActionButtonClickedEventEventHandler() {
    return e -> {
      System.out.println("Button clicked" + e);
    };
  }

  private static Label createLabel(String content) {
    final Label lbl = new Label(content);
    lbl.setStyle("-fx-text-fill: white; -fx-font-size: 14px; -fx-min-width: 60px");
    return lbl;
  }

  public static void setGridColor(String color) {
    grid.setStyle("-fx-background-color: " + color);
  }

  public static void setUpdateTraitsButtonVisible(boolean enabled) {
    button.setVisible(enabled);
  }
}
package hellofx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HelloFX extends Application {

  private FlagsmithConfiguration flagsmithConfiguration;

  @Override
  public void start(Stage stage) {
    this.flagsmithConfiguration = new FlagsmithConfiguration();

    // The scene which is the "root" of our application
    Scene scene = new Scene(MainPage.getGrid(
        this.flagsmithConfiguration.getEmail(),
        this.flagsmithConfiguration.getColour(),
        this.flagsmithConfiguration.getCountry()
    ), 200, 150);

    // The top level JavaJX container
    stage.setTitle("Demo");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }

}
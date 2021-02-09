package hellofx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

import static hellofx.InitialData.WINDOW_HEIGHT;
import static hellofx.InitialData.WINDOW_WIDTH;
import static hellofx.InitialData.getInitialX;
import static hellofx.InitialData.getInitialY;

public class HelloFX extends Application {

  private FlagsmithConfiguration flagsmithConfiguration;

  @Override
  public void start(Stage stage) throws Exception {
    String rawId = getParameters().getNamed().get("id");
    int id = 1;
    if (rawId != null) {
      id = Integer.parseInt(rawId);
    }

    this.flagsmithConfiguration = new FlagsmithConfiguration(id);

    // The scene which is the "root" of our application
    Scene scene = new Scene(MainPage.getGrid(
        this.flagsmithConfiguration.getEmail(),
        this.flagsmithConfiguration.getColour(),
        this.flagsmithConfiguration.getCountry(),
        this.flagsmithConfiguration.getUpdateButtonEnabled(),
        this.flagsmithConfiguration.getMoneySpentEnabled(),
        this.flagsmithConfiguration.getGeolocationEnabled()
    ), WINDOW_WIDTH, WINDOW_HEIGHT);

    // The top level JavaJX container
    stage.setTitle("Demo");
    stage.setScene(scene);
    stage.setX(getInitialX(id));
    stage.setY(getInitialY(id));
    stage.show();
    refreshFlags(id);
  }

  private void refreshFlags(int id) {
    final int DELAY_MS = 1000;
    final int PERIOD_MS = 1000;
    new Timer().scheduleAtFixedRate(new TimerTask() {
      public void run() {
        flagsmithConfiguration.getUserFlagsAndTraits();
        MainPage.setGridColor(flagsmithConfiguration.getColour());
        MainPage.setUpdateTraitsButtonVisible(flagsmithConfiguration.getUpdateButtonEnabled());
        MainPage.setMoneyVisible(flagsmithConfiguration.getMoneySpentEnabled());
        MainPage.setCountryVisible(flagsmithConfiguration.getGeolocationEnabled());
      }
    }, DELAY_MS + (id * 50), PERIOD_MS);
  }

  public static void main(String[] args) {
    launch(args);
  }

}
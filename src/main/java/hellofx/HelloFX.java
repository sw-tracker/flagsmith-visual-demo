package hellofx;

import hellofx.flagsmith.FlagsmithConfiguration;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

import static hellofx.InitialData.TOTAL_USERS;
import static hellofx.InitialData.WINDOW_HEIGHT;
import static hellofx.InitialData.WINDOW_WIDTH;
import static hellofx.InitialData.getInitialX;
import static hellofx.InitialData.getInitialY;

public class HelloFX extends Application {

  private FeatureFlagsProxy ffsConfiguration;

  @Override
  public void start(Stage stage) {
    String rawId = getParameters().getNamed().get("id");
    int id = 1;
    if (rawId != null) {
      id = Integer.parseInt(rawId);
    }

    this.ffsConfiguration = new FeatureFlagSystemAdapter().getFfs(id);

    MainPage.setButtonClickConsumer(new TraitsConsumer() {
      @Override
      public void update(String country, String money) {
        ffsConfiguration.update(country, money);
      }
    });

    // The scene which is the "root" of our application
    Scene scene = new Scene(MainPage.getGrid(
        this.ffsConfiguration.getEmail(),
        this.ffsConfiguration.getColour(),
        this.ffsConfiguration.getCountry(),
        this.ffsConfiguration.getUpdateButtonEnabled(),
        this.ffsConfiguration.getMoneySpentEnabled(),
        this.ffsConfiguration.getGeolocationEnabled()
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
    final int MIN_MS_BETWEEN = 50;
    final int PERIOD_MS = TOTAL_USERS + MIN_MS_BETWEEN;
    new Timer().scheduleAtFixedRate(new TimerTask() {
      public void run() {
        ffsConfiguration.getUserFlagsAndTraits();
        MainPage.setGridColor(ffsConfiguration.getColour());
        MainPage.setUpdateTraitsButtonVisible(ffsConfiguration.getUpdateButtonEnabled());
        MainPage.setMoneyVisible(ffsConfiguration.getMoneySpentEnabled());
        MainPage.setCountryVisible(ffsConfiguration.getGeolocationEnabled());
      }
    }, DELAY_MS + (id * MIN_MS_BETWEEN), PERIOD_MS);
  }

  public static void main(String[] args) {
    launch(args);
  }

}
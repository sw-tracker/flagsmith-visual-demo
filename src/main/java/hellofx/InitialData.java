package hellofx;

public class InitialData {

  public static final int WINDOW_WIDTH = 200;
  public static final int WINDOW_HEIGHT = 150;

  public static String getEmail(int id) {
    return id + "@a.com";
  }

  public static int getInitialX(int id) {
    return ((id - 1) % 3) * WINDOW_WIDTH;
  }

  public static int getInitialY(int id) {
    int winHeigth = WINDOW_HEIGHT;
    if (id <= 6) {
      winHeigth += 50;
    } else {
      winHeigth += 33;
    }
    return ((id - 1) / 3 % 5) * winHeigth;
  }

  public static String getCountry(int id) throws Exception {
    switch (((id - 1) % 3)) {
      case 0:
        return "DE";
      case 1:
        return "CH";
      case 2:
        return "AT";
      default:
        throw new Exception("You did something wrong with the country calculation");
    }
  }
}

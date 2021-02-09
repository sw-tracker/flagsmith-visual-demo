package hellofx;

public class InitialData {

  public static final int WINDOW_WIDTH = 200;
  public static final int WINDOW_HEIGHT = 170;
  public static final int H_OFFSET = -3430;
  public static final int V_OFFSET = 70;
  public static final int COLUMNS = 3;
  public static final int ROWS = 7;
  public static final int HEADER_SIZE = 33;

  public static String getEmail(int id) {
    return id + "@a.com";
  }

  public static int getInitialX(int id) {
    return (((id - 1) % COLUMNS) * WINDOW_WIDTH) + H_OFFSET;
  }

  public static int getInitialY(int id) {
    int winHeigth = WINDOW_HEIGHT;
    if (id > COLUMNS) {
      winHeigth += HEADER_SIZE;
    }
    return (((id - 1) / COLUMNS % ROWS) * winHeigth) - V_OFFSET;
  }

  public static String getCountry(int id) throws Exception {
    switch (((id - 1) % COLUMNS)) {
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

package hellofx;

public class InitialData {

  // house curved monitor (normal resolution)
  public static final int WINDOW_WIDTH = 250;
  public static final int WINDOW_HEIGHT = 180;
  public static final int H_OFFSET = -3250;
  public static final int V_OFFSET = 440;

//  public static final int WINDOW_WIDTH = 200;
//  public static final int WINDOW_HEIGHT = 172;
//  public static final int H_OFFSET = 0;
//  public static final int V_OFFSET = -20;

  public static final int COLUMNS = 6;
  public static final int ROWS = 6;
  public static final int HEADER_SIZE = 33;
  public static final int TOTAL_USERS = COLUMNS * ROWS;

  private static final String[] COUNTRY_ISO_CODES = {"DE", "CH", "AT", "AU", "IT", "FR", "GB", "AF", "BS", "AD", "BG", "CA"};

  public static String getEmail(int id) {
    return id + "@demo.com";
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
    int index = (id - 1) % COLUMNS;
    return COUNTRY_ISO_CODES[index];
  }
}

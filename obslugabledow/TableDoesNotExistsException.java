package obslugabledow;

public class TableDoesNotExistsException extends BasicException {
    public TableDoesNotExistsException(String message) {
      super(message);
    }
    public TableDoesNotExistsException(String message, String what) {
      super(message + "\n Twoja nazwa: " + what);
    }
}

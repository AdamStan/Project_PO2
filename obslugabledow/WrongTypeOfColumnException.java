package obslugabledow;

public class WrongTypeOfColumnException extends BasicException {
    private String nameOfType;
    public WrongTypeOfColumnException(String message, String type){
      super(message);
      nameOfType = type;
    }
    public String getWrongType(){
        return nameOfType;
    }
    @Override
    public String getMessage(){
        String buff = super.getMessage();
        buff = buff + "\nTwoja nazwa to : " + nameOfType;
        return buff;
    }
}
package myownsql;
 import java.io.Serializable;

public class Field <T> implements Serializable {
    private T value;
    public Field(){
        value = null;
    }
    public Field(T v){
        value = v;
    }
    public T get(){
        return value;
    }
    public void set(T v){
        value = v;
    }
    @Override
    public String toString(){
        return String.format("%s", value);
    }
}

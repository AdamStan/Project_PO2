package myownsql;
 import java.util.ArrayList;
 import obslugabledow.WrongTypeOfColumnException;
 import java.io.Serializable;
public class Column implements Serializable{
    private ArrayList column;
    private String nameOfType;
    public Column(String name) throws Exception{
        if(name.equalsIgnoreCase("INT"))
            column = new ArrayList<Field<Integer>>();
        else if (name.equalsIgnoreCase("STRING"))
            column = new ArrayList<Field<String>>();
        else if(name.equalsIgnoreCase("DOUBLE"))
            column = new ArrayList<Field<Double>>();
        else throw new WrongTypeOfColumnException("W kolumnie typy się nie zgadzają", name);
        nameOfType = name.toUpperCase();
    }
    public void add(String nowe) throws Exception{
        try {
            Integer a; Double b;
            Field buff;
            if( nowe == null && (nameOfType.equalsIgnoreCase("INT") ||
                    nameOfType.equalsIgnoreCase("DOUBLE")) ){
                buff = new Field<Integer>();
            }
            else if(nameOfType.equalsIgnoreCase("INT")){
                a = Integer.parseInt(nowe);
                buff = new Field<Integer>(a);
            }
            else if(nameOfType.equalsIgnoreCase("DOUBLE")){
                b = Double.valueOf(nowe);
                buff = new Field<Double>(b);
            }
            else
                buff = new Field<String>(nowe);
            column.add(buff);
        } catch(java.lang.NumberFormatException e){
            e.getMessage();
            column.add(new Field<Integer>());
            throw new WrongTypeOfColumnException("Zły typ wartości, którą chcesz wstawić", nowe);
        }
    }
    public void addNull() throws Exception{
        Field buff = new Field();
        column.add(buff);
    }
    public ArrayList getColumn(){
        return column;
    }
    public String getType(){
        return nameOfType;
    }
    public void set(Integer i, String newValue){
        Integer a;
        Field buff;
        if(nameOfType.equalsIgnoreCase("INT")){
            a = Integer.parseInt(newValue);
            buff = new Field<Integer>(a);
        }
        else 
            buff = new Field<String>(newValue);
        column.set(i,buff);
    }
    @Override
    public String toString(){
        return String.format("%s", column);
    }
}

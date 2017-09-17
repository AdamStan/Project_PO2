package myownsql;
 import java.util.ArrayList;
 import java.io.IOException;
 import obslugabledow.*;
 import java.io.Serializable;
public class Table implements Serializable{
    private ArrayList<String> nameOfColumns = new ArrayList<>();
    private ArrayList<String> typeOfColumns = new ArrayList<>();
    private ArrayList<Column> columns = new ArrayList<>();
    private String nameTable;
    public Table(){ //on jest potrzebny

    }
    private Table(ArrayList<String> new_c, 
                  ArrayList<String> new_t, 
                  String new_d) throws Exception{
        nameOfColumns = new_c;
        typeOfColumns = new_t;
        nameTable = new_d;
        for(int i = 0; i < new_t.size(); i++){
            Column buff = new Column(new_t.get(i));
            columns.add(buff);
        }
    }
    public static Table Create(ArrayList<String> name_c, 
                               ArrayList<String> name_t, 
                               String name_tab) throws Exception {
        if(name_c.size() > name_t.size())
            throw new WrongAmountColumnsException("Za dużo kolumn,"
                    + " a za mało typów \nNazwa_kolumny typ, ...");
        else if(name_c.isEmpty())
            throw new SyntaxErrorException("Nie podałeś parametrów");
        Table buff = new Table(name_c, name_t, name_tab);
        return buff;
    }
    public void Insert(ArrayList<String> whichColumn, ArrayList<String> values) throws Exception {
        try{
            if( whichColumn.size() > nameOfColumns.size() )
                throw new WrongAmountColumnsException("Zła liczba kolumn");
            else if ( !whichColumn.get(0).equals("*") && whichColumn.size() != values.size() )
                throw new WrongAmountArgumentsException("Za mało wartości");
            else {
                int j = 0;
                for(int i = 0; i < nameOfColumns.size(); i++){
                    if (whichColumn.get(0).equals("*")){
                        columns.get(i).add(values.get(i));
                    }
                    else if( whichColumn.get(j).equals(nameOfColumns.get(i)) ) {
                        columns.get(i).add(values.get(j));
                        if(j < whichColumn.size()-1 ) j++;
                    }
                    else columns.get(i).add(null);
                }
            }
            //zapis wierszy do pliku
        } finally {
        //bedziemy zamykac plik jesli zostanie otworzony, genialne :-)
        }
    }
    public Table Delete() throws Exception {
        ArrayList<Column> buff = new ArrayList();
        for(int i = 0; i < typeOfColumns.size(); i++){
            Column buffer = new Column(typeOfColumns.get(i));
            buff.add(buffer);
        }
        columns = buff;
        return this;
    }
    public Table Update(String whichColumn, String newValue) {
        for(int j = 0; j <  columns.get(0).getColumn().size(); j++){ //wsztstkie muszą być tej samej długości
            for(int k = 0; k < columns.size(); k++){
                if(nameOfColumns.get(k).equals(whichColumn)){
                    columns.get(k).getColumn().set(j, newValue);
                }
            }
        }
        return this;
    }
    public void AlterAddColumn(String name, String type) throws Exception{
        Column new_column = new Column(type);
        for(int i = 0; i < columns.get(0).getColumn().size(); i++){
            new_column.addNull();
        }
        columns.add(new_column);
    }
    public String Select(ArrayList<String> whichColumn) throws IOException {
        StringBuilder buff = new StringBuilder();
        if(whichColumn.get(0).equals("*")){
            for(int i = 0; i<nameOfColumns.size(); i++){
                buff.append(nameOfColumns.get(i) + "\t");
            }
            buff.append("\n");

            for(int j = 0; j <  columns.get(0).getColumn().size(); j++){ //wsztstkie muszą być tej samej długości
                for(int k = 0; k < columns.size(); k++)
                    buff.append(columns.get(k).getColumn().get(j) + "\t");
                buff.append("\n");
            }
          
        }
        else {
          ArrayList<Integer> help = new ArrayList<Integer>();
          for (int i = 0; i < whichColumn.size(); i++){
            for(int j = 0; j < nameOfColumns.size(); j++){
              if(whichColumn.get(i).equals(nameOfColumns.get(j))){
                help.add(j);
                break;
              }
            }
          }
          for(int i = 0; i<help.size();i++){
            buff.append(nameOfColumns.get(help.get(i)) + "\t");
          }
          buff.append("\n");
          
              for(int k = 0; k < columns.get(0).getColumn().size(); k++){ //wsztstkie muszą być tej samej długości
                  for(int i = 0; i<help.size(); i++){ 
                    buff.append(columns.get(help.get(i)).getColumn().get(k) + "\t");
                  }
                  buff.append("\n");
              }
          
        }
      return buff.toString();
    }
    public void opis(){
        System.out.println("Nazwa tabeli: " + nameTable);
        System.out.println("Nazwy kolumn: " + nameOfColumns);
        System.out.println("Typy kolumn: " + typeOfColumns);
        System.out.println("Dane w tabeli: " + columns);
    }
    public void setColumn(ArrayList<Column> values){
        columns = values;
    }
    public ArrayList<Column> getColumn(){
        return columns;
    }
    public void setTypeOfColumns(String old_type, String type){
      Integer i = typeOfColumns.indexOf(old_type);
      typeOfColumns.set(i, type);
    }
    public void setNameOfColumns(String old_name, String name){
      Integer i = nameOfColumns.indexOf(old_name);
      nameOfColumns.set(i, name);
    }
    public ArrayList<String> getNameOfColumns(){
      return nameOfColumns;
    }
    public String getName(){
      return nameTable;
    }
    @Override
    public String toString(){
      return String.format("%s", nameTable);
    }
}

package myownsql;
 import java.util.ArrayList;
 import java.io.Serializable;
 //import input.and.output.*; //to będzie

public class Database implements Serializable{
    ArrayList<Table> Tables = new ArrayList<>();
    String fileName;
    public Database(String file_name){
            fileName = file_name;
    }
    /**Obsługa tabel*/
    public void addToDatabase(Table newTab) {
        Tables.add(newTab);
    }
    public ArrayList<Table> getTables(){
        return Tables;
    }
    public void setTables(ArrayList<Table> help){
      Tables = help;
    }
    public Integer getPositionTableByName(String nameOfTable){
        Integer i = 0;
        while(i<Tables.size()){
            if(Tables.get(i).getName().equals(nameOfTable)) return i;
            else i++;
        }
        return -1;
    }
    /**Koniec obsługi tabel*/
    public void opis(){
      System.out.println("Nazwa bazy danych: " + fileName);
      for(int i = 0; i<Tables.size(); i++){
        Tables.get(i).opis();
      }
    }
    public String getFileName(){
        return fileName;
    }
    @Override
    public String toString(){
        return String.format("%s", fileName);
    }
}

package commands;

 import GUI.SelectBox;
 import java.util.ArrayList;
 import java.util.Scanner;
 import myownsql.Database;
 import myownsql.Table;
 import obslugabledow.EmptyDatabaseException;
 import obslugabledow.TableDoesNotExistsException;

public class CommandSelect extends Command{
    private ArrayList<String> whichColumns = new ArrayList<>();
    private String whichTable = null;
    public CommandSelect(Command c){
        super(c);
        whichTable = c.getText().get(findTableName(c.getText()));
        // trzeba wyciągnąć które kolumny
        int j = 0;
        while(!this.getText().get(j).equalsIgnoreCase("from")){
            whichColumns.add(this.getText().get(j));
            j++;
        }
    }
    public static Integer findTableName(ArrayList<String> command){
        Integer i = 0;
        for(; i < command.size(); i++){
            if(command.get(i).equalsIgnoreCase("FROM")) break;
        }
        return i+1;
    }
    @Override public Database make(Database Data) throws Exception{
            if(Data.getPositionTableByName(whichTable).equals(-1))
                throw new TableDoesNotExistsException("Tej tabeli nie ma w bazie "
                    + "z której korzystasz...");
            Table tab = Data.getTables().get(Data.getPositionTableByName(whichTable));
            String select = tab.Select(whichColumns);
            
            // mamy naszego selecta
            // a jak mamy limita to ucinamy to co wybraliśmy
            if(this.getText().get(this.getText().size()-2).equalsIgnoreCase("LIMIT")){
            Scanner buff = new Scanner(select);
            try {
                Integer howmany = Integer.valueOf(this.getText().get(this.getText().size()-1));
                String new_select = buff.nextLine(); // pierwsza linia to nazwy kolumn!
                new_select += "\n"; //nextLine() bierze i nie konczy znakiem nowej linii, trzeba dopisywać samemu
                for(int l = 0; l < howmany; l++){
                    new_select += buff.nextLine();
                    new_select += "\n";
                }
                select = new_select;
            }
                catch(java.util.NoSuchElementException e){ // w selectcie jest mniej wierszy niż chcemy limitować

                }
                catch(java.lang.NumberFormatException e){
                    //tu nie można tego ignorować
                    //ale na potrzeby zaliczenia to można...
                    //normalnie jednak w tym miejscu wyrzucasz własny wyjatek
                    //z komunikatem: "co za g*wno mi tutaj podałeś?" np
                }
            }
            System.out.println(select);
            SelectBox.display(select);
            return Data;
    }
}

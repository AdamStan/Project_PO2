package commands;

import java.util.ArrayList;
import myownsql.Database;
import myownsql.Table;
import obslugabledow.SyntaxErrorException;
import obslugabledow.TableDoesNotExistsException;
import obslugabledow.WrongColumnException;

public class CommandUpdate extends Command {
    private String whichTable, whichColumn, newValue;
    public CommandUpdate(Command c){
        super(c);
        //dla czytelnosci 
        ArrayList<String> buff = this.getText();
        whichTable = buff.get(0);
        whichColumn = buff.get(2);
        newValue = buff.get(4);
        
    }
    public Database make(Database Data) throws Exception {
        System.out.print(this.getText());
        if(this.getText().get(3).equals(" =")) //nie wiem dlaczego tak trzeba
            throw new SyntaxErrorException("Błąd składniowy");
        Integer whichT = Data.getPositionTableByName(whichTable);
        if(whichT.equals(-1))
            throw new TableDoesNotExistsException("W bazie nie ma tablicy o podanej nazwie");
        Integer indexWhichColumn;
        Table buff = Data.getTables().get(whichT);
        indexWhichColumn 
                = Data.getTables().get(whichT).getNameOfColumns().indexOf(whichColumn);
        if(indexWhichColumn.equals(-1))
            throw new WrongColumnException("Nie ma takiej kolumny");
        buff.Update(whichColumn,newValue);
        Data.getTables().set(whichT, buff);
        System.out.print("W tabeli: " + whichTable + " \n");
        System.out.println("Zmieniono wartości w columnie "
                + whichColumn + " na " + newValue);
        return Data;
    }
}

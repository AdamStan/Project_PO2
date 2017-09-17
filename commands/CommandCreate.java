/** 
 * This is class extends by Command
 * It's a quite difficult
 * But I've just started learn java!
 */
package commands;

import inout.IOclass;
 import java.util.Scanner;
 import java.util.ArrayList;
 import myownsql.Database;
 import myownsql.Table;
 import myownsql.Types;
 import obslugabledow.WrongTypeOfColumnException;

public class CommandCreate extends Command {
    /** additional paramiters: */
    private ArrayList<String> nameOfColumns = new ArrayList<String>(), 
            typesOfColumns = new ArrayList<String>();
    /** constructors: */
    public CommandCreate(Scanner list) throws Exception{
        super(list);
        ArrayList<String> command = this.getText();
        for(int i = 2; i < command.size(); i+=2){
            nameOfColumns.add(command.get(i));
        }
        for(int i = 3; i< command.size(); i+=2){
            if (Types.checkName(command.get(i))) typesOfColumns.add(command.get(i));
            else throw new WrongTypeOfColumnException("Zły typ kolumny",command.get(i));
        }
    }
    public CommandCreate(Command com) throws Exception{
        super(com);
        ArrayList<String> command = this.getText();
        for(int i = 2; i < command.size(); i+=2){
            nameOfColumns.add(command.get(i));
        }
        for(int i = 3; i< command.size(); i+=2){
            if (Types.checkName(command.get(i))) typesOfColumns.add(command.get(i));
            else throw new WrongTypeOfColumnException("Zły typ kolumny",command.get(i));
        }
    }
    /** Methods: */
    @Override public Database make(Database Data)
    throws Exception{
        //Trzeba sprawdzać czy istnieje tabela o podanej nazwie, jak tak to wyjątek
        if(this.getText().get(0).equalsIgnoreCase("TABLE")){
            Table TABLE = Table.Create(this.nameOfColumns, 
                    this.typesOfColumns, this.getText().get(1));
            System.out.println("Nazwa bazy do której wstawiamy: " + Data.toString());
            Data.addToDatabase(TABLE);
        }
        else if(this.getText().get(0).equalsIgnoreCase("DATABASE")){
            IOclass.writeObjects(Data);
            Data = new Database(this.getText().get(1));
            System.out.println("Stworzono baze: " + this.getText().get(1));
        }
        else {
            System.out.println("No to co ty piszesz?");
        }
        return Data;
    }
    public ArrayList<String> getNames(){
        return this.nameOfColumns;
    }
    public ArrayList<String> getTypes(){
        return this.typesOfColumns;
    }
}

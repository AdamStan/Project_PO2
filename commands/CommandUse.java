package commands;

import inout.IOclass;
import myownsql.Database;

public class CommandUse extends Command {
    public CommandUse(Command c){
        super(c);
    }
    @Override public Database make(Database Data)
    throws Exception {
        try {
            Database buff = IOclass.readObjects(this.getText().get(0));
            IOclass.writeObjects(Data);
            Data = buff;
            System.out.println("Aktualnie używasz bazy: " + Data);
        } catch(java.io.FileNotFoundException e) {
            System.out.println("Nie znaleziono tej bazy danych");
        } finally {
            return Data;
        }
    }
}

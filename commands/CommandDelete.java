package commands;

import myownsql.Database;

public class CommandDelete extends Command {
    private String whichTable;
    public CommandDelete(Command c){
        super(c);
        whichTable = this.getText().get(1);
    }
    @Override public Database make(Database Data) throws Exception{
        int i = Data.getPositionTableByName(whichTable);
        Data.getTables().get(i).Delete();
        return Data;
    }
}
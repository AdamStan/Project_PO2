package commands;
 import inout.IOclass;
 import myownsql.Database;

public class Choose {
    public static Database Processing(Command c, Database Data) throws Exception{
        Order ch = c.getName();
        Command cn = null;
        switch(ch){
            case CREATE:
                cn = new CommandCreate(c);
                break;
            case INSERT:
                cn = new CommandInsert(c);
                break;
            case SELECT:
                cn = new CommandSelect(c);
                break;
            case UPDATE:
                System.out.println("Polecenie UPDATE");
                cn = new CommandUpdate(c);
                break;
            case DELETE:
                System.out.println("Polecenie DELETE");
                cn = new CommandDelete(c);
                break;
            case USE:
                System.out.println("Polecenie USE");
                cn = new CommandUse(c);
                break;
            case EXIT:
                System.out.println("Polecenie EXIT");
                IOclass.writeObjects(Data);
                System.exit(0);
                break;
            default:
                System.out.println("Nieznane polecenie");
        }
        if(cn != null) Data = cn.make(Data);
        Data.opis();
        return Data;
    }
}

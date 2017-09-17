package commands;
 import java.util.ArrayList;
 import myownsql.Database;
 import obslugabledow.*;

public class CommandInsert extends Command{
    private ArrayList<String> columns = new ArrayList<String>(),
            arguments = new ArrayList<String>();
    public CommandInsert(Command c) throws Exception{
        super(c);
        int i = 2;
        for(; i < this.getText().size(); i++){
            if(this.getText().get(i).equalsIgnoreCase("VALUES")){
                this.getText().set(i, this.getText().get(i).toUpperCase());
                break;
            } else {
                columns.add(this.getText().get(i));
            }
        }
        if(columns.isEmpty()) columns.add("*");
        int j = 2;
        if(!this.getText().get(j).equalsIgnoreCase("VALUES")) 
            throw new SyntaxErrorException("Nie znaleziono Values");
        j+=1;
        for(; j < this.getText().size(); j++){
            arguments.add(this.getText().get(j));  
        }
    }
    /**Obsluga INSERTA*/
    @Override public Database make(Database ref) throws Exception{
        String nameOfTable = this.getText().get(1);
        // ref.getTables() - tablice z bazy
        if(ref.getTables().isEmpty())
            throw new EmptyDatabaseException("W bazie nie ma żadnej tabeli");
        int i = 0;
        while(i<ref.getTables().size()){
            if(ref.getTables().get(i).getName().equals(nameOfTable)){
                ref.getTables().get(i).Insert(columns,arguments);
                break;
            }
            i++;
        }
        if(i == ref.getTables().size())
            throw new TableDoesNotExistsException("W bazie nie ma tablicy o podanej nazwie", 
                    this.getText().get(2));
        System.out.print("Do tabeli: " + nameOfTable + " \n");
        System.out.println("Dodano nastepujace argumenty: " + arguments);
        return ref;
    }
}

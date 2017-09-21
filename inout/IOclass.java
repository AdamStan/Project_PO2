package inout;
 import java.io.*;
 import java.util.Scanner;
 import myownsql.Database;
public class IOclass {
  //zapisz do pliku
  public static void write(String whatYouWant, String name){
    try{
      File file = new File(name + ".txt");
      PrintWriter output = new PrintWriter(file);
      output.println(whatYouWant);
      output.close();
    } catch (IOException e){
      System.out.println("ERROR: " + e.getMessage());
    }
  }
  //wczytaj z pliku
  public static void read(String name){
    try{
      File file = new File(name + ".txt");
      Scanner input = new Scanner(file);
      String line = input.nextLine();
      System.out.println(line);
    } catch (FileNotFoundException e){
      System.out.println("ERROR: " + e.getMessage());
    }
  }
  //nasz Object musi implementować klasę Serializable!
    public static void writeObjects(Database data){
        try{
            File file = new File(data.getFileName()+".txt");
            FileOutputStream fo = new FileOutputStream(file);
            ObjectOutputStream output = new ObjectOutputStream(fo);
            output.writeObject(data);
            output.close();
            fo.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static Database readObjects(String name)
    throws Exception
    {
        File file = new File(name+".txt");
        FileInputStream fi = new FileInputStream(file);
        ObjectInputStream input = new ObjectInputStream(fi);
        Database buff = null;
        try {
            while(true){
                buff = (Database)input.readObject();
            }
        } catch (EOFException e){

        }
        input.close();
        fi.close();
        return buff;
    }
}

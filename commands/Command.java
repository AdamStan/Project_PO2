package commands;

import java.util.ArrayList;
import java.util.Scanner;
import myownsql.Database;

public class Command {
    private Order name;
    private ArrayList<String> text = new ArrayList();
    public Command(Scanner lineWithCom){
        String nowe = lineWithCom.nextLine();
        nowe = Command.replaceSigns(nowe);
        String[] words = nowe.split(" ");
        this.name = Order.valueOf(words[0].toUpperCase());
        for(int i = 1; i < words.length ;i++){
            if(words[i].equals("")){
                continue;
            }
            text.add(words[i]);
        }
    }
    public Command(String nowe){
        nowe = Command.replaceSigns(nowe);
        String[] words = nowe.split(" ");
        this.name = Order.valueOf(words[0].toUpperCase());
        System.out.println(words[0]);
        for(int i = 1; i < words.length ;i++){
            if(words[i].equals("")){
                continue;
            }
            text.add(words[i]);
        }
    }
    public Command(Command other){
        this.name = other.name;
        this.text = other.text;
    }
    public Order getName(){
        return name;
    }
    public ArrayList<String> getText(){
        ArrayList<String> buff = new ArrayList<>(text);
        return buff;
    }
    public String get(Integer i){
        String buff = text.get(i);
        return buff;
    }
    public void setName(Order buff){
        name = buff;
    }
    public static String replaceSigns(String word){
        word = word.replace(',',' ');
        word = word.replace(';',' ');
        word = word.replace('(',' ');
        word = word.replace(')',' ');
        word = word.replace('\'',' ');
        word = word.replace('\"',' ');
        return word;
    }
    public Database make(Database data)
    throws Exception{
        return data;
    }
}
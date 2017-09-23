package test;

import myownsql.*;
import commands.*;
import java.util.Scanner;

public class MainWithoutJAVAFX {
    public static void sign(){
        System.out.print("myOWNsql<< ");
    }
    public static void main(String[] args){
        Database myown_database = new Database("tymczasowa");
        System.out.println("Hello World");
        while(true){
            try {
                sign();
                Scanner readline = new Scanner(System.in); //wpisujesz komendę
                Command first = new Command(readline);
                myown_database = Choose.Processing(first, myown_database); // wybiera co robić
            } catch(Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e);
            }
        }
    }
}

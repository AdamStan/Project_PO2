package myownsql;
 import java.util.ArrayList;
 import java.util.Arrays;
public class Types {
    static final ArrayList<String> KINDOFTYPES 
        = new ArrayList<String>(Arrays.asList("INT","INTEGER","DOUBLE","STRING"));
    public static boolean checkName(String candidate){
        for(String s : KINDOFTYPES){
            if(candidate.equalsIgnoreCase(s))
                return true;
        }
        return false;
    }
    /*public static boolean checkValue(String value){
        return true;
    }*/
}
//makefile: mainman ewentualnie ant

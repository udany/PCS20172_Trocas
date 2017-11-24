/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author labccet
 */
public class ConsoleUtil {
    
    public static String Read(){
                
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        try{
            s = br.readLine();
        }catch(Exception e){
            
        }
        
        return s;
    }    
    public static int ReadInt(){
        String s = Read();
        
        try{
            int i = Integer.parseInt(s);
            return i;
        }catch(NumberFormatException nfe){
            return 0;
        }
    }    
}

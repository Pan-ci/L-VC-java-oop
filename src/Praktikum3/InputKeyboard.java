/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Praktikum3;
import java.io.*;
/**
 *
 * @author 3cc
 */
public class InputKeyboard {
    public static void main (String [] args) {
        
        BufferedReader input = new BufferedReader
                (new InputStreamReader (System.in));
        
        String nim = "";
        String nama = "";
        
        //input
        try {
            System.out.println("    INPUT DATA  ");
            System.out.println("==========================");
            
            System.out.print("Input NIM: ");
                nim = input.readLine();
                
            System.out.println("Input nama: ");
                nama = input.readLine();
            System.out.println("==========================");
            
        } catch (IOException e){
            System.out.println("Ada yang salah");
        }
        
        //output
        System.out.println("    BIODATA ");
        System.out.println("==========================");
        System.out.println("NIM anda adalah: " + nim);
        System.out.println("nama anda adalah: " + nama);
        System.out.println("==========================");
        System.out.println();
    }
}

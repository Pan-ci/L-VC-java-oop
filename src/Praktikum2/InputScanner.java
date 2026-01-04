/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Praktikum2;
import java.util.*;
/**
 *
 * @author 3cc
 */
public class InputScanner {
    public static void main(String [] args) {
        //Scanner untuk input
        Scanner ipul = new Scanner(System.in);
        
        System.out.println("%% Program Java Menghitung Gaji %%");
        System.out.println("--------------------------------------");
        System.out.println();
        
        String ism;
        char gol;
        int jamKerja, gajiPerJam = 0, totalGaji;
        
        System.out.print("Nama karyawan: ");
        ism = ipul.nextLine();
        
        System.out.print("Golongan: ");
        gol = ipul.next().charAt(0);

        System.out.print("Jumlah jam kerja: ");
        jamKerja = ipul.nextInt();
        
        System.out.println();
        
        //upah per jam berdasarkan gol
        switch(gol) {
            case 'I' -> //case 'II': salah, karena deklarasi char(sebuah karakter)
            //, bukan String
                gajiPerJam = 6500;
            case 'J' -> gajiPerJam = 6000;
            case 'K', 'L' -> gajiPerJam = 5000;
            default -> System.out.println("Kesalahan Input");
        }
        
        totalGaji = jamKerja * gajiPerJam;
        
        // gaji tuk kerja diatas 48 jam
        if ((jamKerja - 48) > 0) {
            totalGaji = totalGaji + ((jamKerja - 48) * 5000);
        }
        
        // output
        System.out.println(ism + " menerima upah Rp." + totalGaji
                            + " per minggu");
    }
}

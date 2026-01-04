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
public class InputKeyboard1 {
    public static void main (String [] args) {
        
        BufferedReader input = new BufferedReader
                (new InputStreamReader (System.in));

        String jumData = "";
        String periode = "";
        
        try {
            System.out.println("    Program Entri Data Gaji ");
            System.out.println("=============");
            System.out.println("Periode: ");
                periode = input.readLine();
            System.out.println("Masukkan jumlah data: ");
                jumData = input.readLine();
            System.out.println();
        } catch (IOException e){
            System.out.println("Terjadi kesalahan");
        }
        
        int jd = Integer.parseInt(jumData);
        String nama[] = new String [jd];
        String jabatan[] = new String [jd];
        String gajiPokok[] = new String [jd];
        String upahLembur[] = new String [jd];
        String jumlahLembur[] = new String [jd];
        double gajiLembur[] = new double [jd];
        double jumlahGaji[] = new double [jd];
        
        double total = 0;
        
        for (int i = 0; i < jd; i++) {
            
            try {
                System.out.println("    Input Data  ");
                System.out.println("=============");
                System.out.println("Nama karyawan: ");
                    nama[i] = input.readLine();
                System.out.println("Jabatan: ");
                    jabatan[i] = input.readLine();
                System.out.println("Gaji pokok: ");
                    gajiPokok[i] = input.readLine();
                System.out.println("Jumlah lembur: ");
                    jumlahLembur[i] = input.readLine();
                System.out.println("Upah lembur/jam: ");
                    upahLembur[i] = input.readLine();

                //konversi nilai string ke nilai angka (double)
                double gP = Double.parseDouble(gajiPokok[i]);
                double jL = Double.parseDouble(jumlahLembur[i]);
                double uL = Double.parseDouble(upahLembur[i]);
                
                gajiLembur[i] = jL * uL;
                jumlahGaji[i] = gajiLembur[i] + gP;
                total += jumlahGaji[i];
                
            } catch (IOException | NumberFormatException e){
                System.out.println("Terjadi kesalahan");
            }
            
            System.out.println("    Laporan Data Gaji Karyawan  ");
            System.out.println("=============");
            System.out.println("Periode: " + periode);
            System.out.println("=============");
            System.out.println("Nama    Jabatan Gaji Pokok  Jumlah Gaji ");
            System.out.println("============");
            
            for(int j = 0; j <= i; j++) {
                System.out.println(nama[j] + "\t" + jabatan[j] + "\t"
                    + gajiPokok[j] + "\t" + gajiLembur[j] + "\t"
                    + jumlahGaji[j]);
            }
            
            System.out.println("=============");
            System.out.println("Total: " + total);
            System.out.println("=============");
        }
    }
}

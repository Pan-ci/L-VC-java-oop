/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Praktikum1;

/**
 *
 * @author 3cc
 */
public class VariabelNTipeData {
    public static void main (String [] args) {
        String namaa;
        int umur;
        char golDarah;
        //float desimal
        float tinggi;
        //double desimal dengan presisi lebih tinggi
        double berat;
        //boolean tipe data hanya 2 nilai: true atau false
        boolean punyaPenyakitDalam;
        
        //memberi nilai pada data yang telah dideklarasi
        //tipe data String pakai 2 petik ""
        namaa = "Fulan";
        umur = 23;
        //char pake 2 petik ''
        //char bisa BESAR atau kecil
        golDarah = 'O';
        //float pake tambahan f, pake titik bukan koma
        tinggi = 171.12f;
        //double juga pake titik bukan koma
        berat = 60.87;
        punyaPenyakitDalam = false;
         
        System.out.println(" == == == == == Biodata == == == == ==");
        System.out.println("Nama: " + namaa);
        System.out.println("Umur: " + umur);
        System.out.println("Golongan darah: " + golDarah);
        System.out.println("Tinggi: " + tinggi);
        System.out.println("Berat badan: " + berat);
        System.out.println("Punya penyakit dalam: " + punyaPenyakitDalam);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Praktikum2;

/**
 *
 * @author 3cc
 */
public class Perulangan {
    public static void main (String [] args) {
        //perulangan for
        int o;
        for(o = 2; o < 7; o ++) {
            System.out.println(o);
        }
        //perulangan while
        int p = 9;
        while (p > 1) {
            System.out.print(p);
            p--;
        }
        //perulangan do while
        int k = 1;
        do {
            System.out.println(k);
            k++;
        } while (k < 9);
    }
}

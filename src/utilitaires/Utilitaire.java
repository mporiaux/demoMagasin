package utilitaires;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Utilitaire {
    private static Scanner sc = new Scanner(System.in);
    public static int choixListe(List l){
       affListe(l);
       return choixElt(l);
    }

    public static void affListe(List l){
        int i =1;
        for(Object o :l) {
            System.out.println((i++)+"."+o);
        }
    }

    public static int choixElt(List l){
        int choix;
        do {
            System.out.println("choix :");
            choix = sc.nextInt();
            sc.skip("\n");
        } while(choix <1 || choix > l.size());
        return choix;
    }

    public static LocalDate lecDate(){
        String[] jma = sc.nextLine().split(" ");
        int j = Integer.parseInt(jma[0]);
        int m = Integer.parseInt(jma[1]);
        int a = Integer.parseInt(jma[2]);
        return LocalDate.of(a,m,j);
    }

    public static LocalTime lecTime(){
        String[] hms = sc.nextLine().split(" ");
        int h = Integer.parseInt(hms[0]);
        int m = Integer.parseInt(hms[1]);
        int s = Integer.parseInt(hms[2]);
        return LocalTime.of(h,m,s);
    }

    public static String getDateFrench(LocalDate d){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d MM yyyy");
        return dtf.format(d);
    }

    public static String modifyIfNotBlank(String label,String oldValue){
        System.out.println(label+" : "+oldValue);
        System.out.print("nouvelle valeur (enter si pas de changement) : ");
        String newValue= sc.nextLine();
        if(newValue.isBlank()) return oldValue;
        return newValue;
    }
}

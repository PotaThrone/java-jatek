package Futtatas;
import Karakter.*;
import Palya.*;
import java.awt.*;
import java.util.Scanner;
/**
 * Ez a rész végzi a program futását.
 * Ha nem megfelelő értéket adsz neki, akkor egy kivételt dob.
 * Két ága van, attól függöen melyiket adod meg: van egy előre legenerált pálya, és egy egyéni pálya amit neked kell megadnod.
 * Az egyéni pályávol kapcsolatban a kötprog mappában található readme.txt-ben talász további infókat.
 */

public class Main {

    /**
     * Miután eldönti melyik ágon megy tovább, létrehozza a keretet, utána a pályát, papirt, játékost és a slendermant.
     * A sorrend fontos hiszen használják egymást.
     *
     * @throws NumberFormatException rossz formátumú szám esetén hibát dob
     * @param args a program parancssori argumentumai, erre nem lesz szükség
     */
    public static void main(String[] args) {

        System.out.println("Valassz a 2 opció kozul!(1 vagy 2)");
        System.out.println("1. Elore legeneralt palya betoltese");
        System.out.println("2. Egyeni palya betoltese");
        Scanner sc = new Scanner(System.in);
        int indito = 0;
        while(indito != 1 && indito != 2 ){
            System.out.println("Irj be egy szamot! (1 vagy 2)");
            try {
                indito = Integer.parseInt(sc.nextLine());
            }catch(NumberFormatException e){
                System.err.println("Nem megfelelo formatumu szamot adtal meg!");
            }catch(Exception e){
                System.err.println("Valami nem jo..");
            }
        }
        sc.close();
        System.out.println("Jatek inditasa... (elvileg kinyilt egy uj ablak)");
        Keret frame = new Keret();
        if(indito == 1){
            Palya palya = new Palya();
            palya.palyaGeneralas();
            Papir papir = new Papir(palya);
            papir.papirSzetosztas(palya);
            Jatekos player = new Jatekos(palya, papir, frame);
            Slenderman slenderman = new Slenderman(palya, player, papir, frame);
            player.mozgatas(slenderman);
            papir.meglevoPapir(frame);
            frame.add(palya, BorderLayout.CENTER);

        }else{
            EgyeniPalya palya = new EgyeniPalya();
            palya.palyaGeneralas();
            Papir papir = new Papir(palya);
            papir.papirSzetosztasEgyeni(palya);
            Jatekos player = new Jatekos(palya, papir, frame);
            Slenderman slenderman = new Slenderman(palya, player, papir, frame);
            player.mozgatas(slenderman);
            papir.meglevoPapir(frame);
            frame.add(palya, BorderLayout.CENTER);
        }
        frame.pack();
    }
}


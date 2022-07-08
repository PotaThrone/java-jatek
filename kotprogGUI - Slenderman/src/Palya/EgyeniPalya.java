package Palya;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Ez a rész végzi az egyéni pálya létrehozását.
 * A Pálya osztályból öröklődik
 * Müködése hasonlít a Palya osztályhoz
 *
 * @see Palya
 */


public class EgyeniPalya extends Palya{
    private final int[][] palya = new int[15][15];

    /**
     * Itt inicializálodnak az adattagok
     * A targyak száma alapértelmezetten 0
     * A fájl beolvassa a palya tömbbe az értékeket és annak megfelelően hozza létre az objektumokat
     *
     * @throws NumberFormatException ha a fájlban nem megfelelő érteket adsz meg azt lekezeli, további részlet a readme.txt-ben
     */


    public EgyeniPalya(){
        negyzet=  new JLabel[15][15];
        gbc = new GridBagConstraints();
        targyakDb = 0;
        setLayout(new GridBagLayout());
        try(Scanner sc = new Scanner(new File("palya.txt"))){
            while(sc.hasNextLine()){
                for (int i=0; i<palya.length; i++) {
                    String[] line = sc.nextLine().split(" ");
                    for (int j=0; j<line.length; j++) {
                        try{
                            palya[i][j] = Integer.parseInt(line[j]);
                        }catch(NumberFormatException e){
                            System.err.println("Nem megfelelo formatumu szamot adtal meg a txt fajlban!");
                        }
                    }
                }
            }
        }catch(FileNotFoundException e){
            System.err.println("A fajl nem talalhato!");
        }

    }
    /**
     * Miután beolvasta a fájlból az értékeket akkor itt hozza létre a tárgyakat
     * A tárgyak létrehozását a Palya osztályban valositottam meg
     * Ha az érték amit vizsgál megegyezik 3-mal akkor ház van ott...
     */


    public void palyaGeneralas(){
        for (int i = 0; i<15; i++){
            gbc.gridy = i;
            for(int j = 0; j < 15; j++){
                if(palya[i][j] == 3){
                    haz(i,j);
                }
                if(palya[i][j] == 6){
                    szikla(i,j);
                }
                if(palya[i][j] == 5){
                    teherauto(i,j);
                }
                if(palya[i][j] == 4){
                    auto1(i,j);
                }
                if(palya[i][j] == 7){
                    hordo(i,j);
                }
                if(palya[i][j] == 2){
                    nagyfa(i,j);
                }
                if(palya[i][j] == 1){
                    kisfa(i,j);
                }
                if(palya[i][j] == 0 || palya[i][j] == 8 || negyzet[i][j] == null){
                    mezo(i,j);
                }
            }
        }
    }

    public int[][] getPalya() {
        return palya;
    }
}

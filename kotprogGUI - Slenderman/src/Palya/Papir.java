package Palya;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Random;
/**
 * Egy papirt hoz létre ami a pálya részét képviseli ezért a palya packageban található meg
 * A papirok tömbbe számokat fog sorsolni ami eldönti melyik tárgy kapjon papirt
 */

public class Papir {
    private final int[] papirok;
    private int papirDb;

    /**
     * Mindig 8 elemű a tömb mivel 8 papir lehet
     * Randommal addig sorsol egy számot még nem lesz egyedi, és minden tárgynak van egyedi száma igy egyenlő valószinüséggel oszlik el
     *
     * @param p megkapja a pályát és azon helyezi el a papirokat
     */

    public Papir(Palya p){
        papirok = new int[8];
        Random r = new Random();
        for(int i = 0; i < papirok.length; i++){
            papirok[i] = r.nextInt(p.getTargyakDb())+1;
        }

        Arrays.sort(papirok);

        for(int i = 0; i < papirok.length-1; i++){
            while(papirok[i] == papirok[i+1]){
                papirok[i+1] = r.nextInt(p.getTargyakDb())+1;
                Arrays.sort(papirok);
                i = 0;
            }
        }

        papirDb = 0;

    }

    /**
     * A kereten egy indikátort jelenit meg felül, hogy a játékos mindig tudja mennyi papir van nála
     *
     * @param keret megkapja a keretet amin a változtatást kell végrehajtani
     */


    public void meglevoPapir(JFrame keret){
        JPanel mutato = new JPanel();
        mutato.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel papirokSzama = new JLabel("Osszegyujtott papirok szama:  "+papirDb);
        papirokSzama.setFont(new Font("Verdana",Font.PLAIN,30));
        mutato.add(papirokSzama);
        keret.add(mutato, BorderLayout.NORTH);
    }
    /**
     * Itt történik a papirok szétosztása miután mindegyik objektum megkapja az azonosítóját
     * A négyzet neve papir lesz innen tudja majd a játékos hogy papirt talált
     *
     * @param palya a pályát kapja meg ahol szét kell osztani a papirokat
     */

    public void papirSzetosztas(Palya palya){
        for (int j : papirok) {
            switch (j) {
                case 1 -> palya.getNegyzet()[2][4].setName("papir");
                case 2 -> palya.getNegyzet()[12][0].setName("papir");
                case 3 -> palya.getNegyzet()[11][14].setName("papir");
                case 4 -> palya.getNegyzet()[11][7].setName("papir");
                case 5 -> palya.getNegyzet()[6][11].setName("papir");
                case 6 -> palya.getNegyzet()[0][6].setName("papir");
                case 7 -> palya.getNegyzet()[7][0].setName("papir");
                case 8 -> palya.getNegyzet()[12][5].setName("papir");
                case 9 -> palya.getNegyzet()[8][2].setName("papir");
                case 10 -> palya.getNegyzet()[3][1].setName("papir");
                case 11 -> palya.getNegyzet()[14][9].setName("papir");
                case 12 -> palya.getNegyzet()[2][14].setName("papir");
            }
        }
    }

    /**
     * Hasonló mint az előző metódus, annyi a különbség hogy itt egy egyéni pályát kap és azon osztja szét a papirokat
     * Itt minden papir az adott azonositójú objektum bal felső sarkától balra eső mezőjébe teszi a papirokat, további részletek readme.txt
     *
     * @param p az egyéni pályát kapja meg ahol szét kell osztani a papirokat
     */

    public void papirSzetosztasEgyeni(EgyeniPalya p){
        int id = 0;
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if(p.getPalya()[i][j] == 2 || p.getPalya()[i][j] == 3 || p.getPalya()[i][j] == 4 || p.getPalya()[i][j] == 5 || p.getPalya()[i][j] == 6 || p.getPalya()[i][j] == 7){
                    id++;
                    for(int k = 0; k < 8; k++){
                        if(papirok[k] == id){
                            p.getNegyzet()[i][j-1].setName("papir");
                        }
                    }
                }
            }
        }
    }

    public int getPapirDb() {
        return papirDb;
    }

    public void setPapirDb(int papirDb) {
        this.papirDb = papirDb;
    }
}



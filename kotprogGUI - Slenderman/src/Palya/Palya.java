package Palya;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * Ez a rész végzi a pálya létrehozását
 * A JPanel osztályból öröklődik és ez fog hozzáadódni a Kerethez
 * A negyzet a mezőket képviseli, amik mint JLabel elemek
 * A gbc arra szolgál hogy a megfelelő mezőkbe helyezze el a tárgyakat
 */

public class Palya extends JPanel{
        protected JLabel[][] negyzet;
        protected GridBagConstraints gbc;
        protected int targyakDb;

    /**
     * Miután meghivtuk ezt inicializálja a mezőket, és a tárgyak számát 0-ra rakja
     */

        public Palya(){
            negyzet =  new JLabel[15][15];
            gbc = new GridBagConstraints();
            targyakDb = 0;
            setLayout(new GridBagLayout());
        }

    /**
     * Itt a poziciokat előre beállitottam és annak megfelelően hozza létre a tárgyakat a pályán
     * Minden mást esetben ahol nincs tárgy ott egyszerű mező van
     */

        public void palyaGeneralas(){
            for (int i = 0; i<15; i++){
                gbc.gridy = i;
                for(int j = 0; j < 15; j++){
                    if(i == 2 && j == 8){
                        haz(i,j);
                    }
                    if((i == 0 && j == 0) || i == 12 && j == 10){
                        szikla(i, j);
                    }
                    if(i == 5 && j == 3){
                        teherauto(i,j);
                    }
                    if((i == 0 && j == 3) || (i == 12 && j == 2)){
                        auto1(i,j);
                    }
                    if(i == 4 && j == 0){
                        auto2(i,j);
                    }
                    if(i == 0 && j == 11){
                        hordo(i,j);
                    }
                    if((i == 10 && j == 0) || (i == 11 && j == 8) || (i == 9 && j == 13) || (i == 2 && j == 5)){
                        nagyfa(i,j);
                    }
                    if( (i == 3 && j == 0) || (i == 7 && j == 1) || (i == 0 && j == 8) || (i == 14 && j == 7) || (i == 6 && j == 14) ||
                            (i == 5 && j == 7) || (i == 10 && j == 5) || (i == 14 && j == 0) ) {
                        kisfa(i, j);
                    }
                    if(negyzet[i][j] == null){
                        mezo(i,j);
                    }
                }
            }

        }

        /**
         * Létrehozza az adott négyzetben a JLabel elemeket és azokat formázza a megfelelő módon
         * Mindegyik mérete 45 x 45
         *
         * @param i melyik mezőben hozza létre az y tengelyen
         * @param j melyik mezőben hozza létre az x tengelyen
         */

        protected void letrehoz(int i, int j){
            negyzet[i][j] = new JLabel();
            negyzet[i][j].setBorder(new LineBorder(Color.BLACK));
            negyzet[i][j].setBackground(new Color(34, 146, 34));
            negyzet[i][j].setPreferredSize(new Dimension(45,45));
            negyzet[i][j].setOpaque(true);
            negyzet[i][j].setHorizontalAlignment(JLabel.CENTER);
        }

    /**
     * Egy kisfát hoz létre, a képet beállitja a neki megfelelővel
     * A gbc pozicionálja oda ahol lennie kell és végül hozzáadja a pályához
     * Kisfanak nevezi el a negyzet nevét, ez annyit tesz hogy keresztül lehet rajta menni
     *
     * @param i a kisfa poziciója y tengelyen
     * @param j a kisfa poziciója x tengelyen
     */


        protected void kisfa(int i, int j){
            letrehoz(i,j);
            negyzet[i][j].setName("kisfa");
            ImageIcon image = new ImageIcon("img/kisfa.png");
            negyzet[i][j].setIcon(image);
            gbc.gridx = j;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.BOTH;

            add(negyzet[i][j],gbc);
        }
    /**
     * Egy nagyfát hoz létre, a képet beállitja a neki megfelelővel
     * A gbc pozicionálja oda ahol lennie kell és végül hozzáadja a pályához
     * Növeli a targyakDb értékét eggyel, a papirokhoz fog ez majd kelleni
     * Nagyfanak nevezi el a negyzet nevét, ez annyit tesz hogy keresztül lehet rajta menni
     *
     * @param i a nagyfa bal felső sarkának a poziciója y tengelyen
     * @param j a nagyfa bal felső sarkának a poziciója x tengelyen
     */

        protected void nagyfa(int i, int j){
            targyakDb++;
            for(int y = i; y < i+2; y++){
                for(int x = j; x < j +2; x++){
                    letrehoz(y,x);
                    negyzet[y][x].setName("mezo");
                }
            }
            negyzet[i][j].setName("nagyfa");
            ImageIcon image = new ImageIcon("img/nagyfa.png");
            negyzet[i][j].setIcon(image);
            gbc.gridx = j;
            gbc.gridwidth = 2;
            gbc.gridheight = 2;
            gbc.fill = GridBagConstraints.BOTH;

            add(negyzet[i][j],gbc);
        }

    /**
     * Egy hordót hoz létre, a képet beállitja a neki megfelelővel
     * A gbc pozicionálja oda ahol lennie kell és végül hozzáadja a pályához
     * Növeli a targyakDb értékét eggyel, a papirokhoz fog ez majd kelleni
     * A foglalt részek az objektumok határát jelenti
     *
     * @param i a hordó bal felső sarkának a poziciója y tengelyen
     * @param j a hordó bal felső sarkának a poziciója x tengelyen
     */

        protected void hordo(int i, int j){
            targyakDb++;
            for(int y = i; y < i+2; y++){
                for(int x = j; x < j +4; x++){
                    letrehoz(y,x);
                    negyzet[y][x].setName("foglalt");
                }
            }
            ImageIcon image = new ImageIcon("img/hordo.png");
            negyzet[i][j].setIcon(image);
            gbc.gridx = j;
            gbc.gridwidth = 4;
            gbc.gridheight = 2;
            gbc.fill = GridBagConstraints.BOTH;

            add(negyzet[i][j],gbc);
        }
    /**
     * Egy vertikális autót hoz létre, a képet beállitja a neki megfelelővel
     * A gbc pozicionálja oda ahol lennie kell és végül hozzáadja a pályához
     * Növeli a targyakDb értékét eggyel, a papirokhoz fog ez majd kelleni
     * A foglalt részek az objektumok határát jelenti
     *
     * @param i az autó bal felső sarkának a poziciója y tengelyen
     * @param j az autó bal felső sarkának a poziciója x tengelyen
     */

        protected void auto2(int i, int j){
            targyakDb++;
            for(int y = i; y < i+3; y++){
                for(int x = j; x < j +2; x++){
                    letrehoz(y,x);
                    negyzet[y][x].setName("foglalt");
                }
            }
            ImageIcon image = new ImageIcon("img/auto2.png");
            negyzet[i][j].setIcon(image);
            gbc.gridx = j;
            gbc.gridwidth = 2;
            gbc.gridheight = 3;
            gbc.fill = GridBagConstraints.BOTH;

            add(negyzet[i][j],gbc);
        }
    /**
     * Egy horizontális autót hoz létre, a képet beállitja a neki megfelelővel
     * A gbc pozicionálja oda ahol lennie kell és végül hozzáadja a pályához
     * Növeli a targyakDb értékét eggyel, a papirokhoz fog ez majd kelleni
     * A foglalt részek az objektumok határát jelenti
     *
     * @param i az autó bal felső sarkának a poziciója y tengelyen
     * @param j az autó bal felső sarkának a poziciója x tengelyen
     */

        protected void auto1(int i, int j){
            targyakDb++;
            for(int y = i; y < i+2; y++){
                for(int x = j; x < j +3; x++){
                    letrehoz(y,x);
                    negyzet[y][x].setName("foglalt");
                }
            }
            ImageIcon image = new ImageIcon("img/auto.png");
            negyzet[i][j].setIcon(image);
            gbc.gridx = j;
            gbc.gridwidth = 3;
            gbc.gridheight = 2;
            gbc.fill = GridBagConstraints.BOTH;

            add(negyzet[i][j],gbc);
        }
    /**
     * Egy teherautót hoz létre, a képet beállitja a neki megfelelővel
     * A gbc pozicionálja oda ahol lennie kell és végül hozzáadja a pályához
     * Növeli a targyakDb értékét eggyel, a papirokhoz fog ez majd kelleni
     * A foglalt részek az objektumok határát jelenti
     *
     * @param i a teherautó bal felső sarkának a poziciója y tengelyen
     * @param j a teherautó bal felső sarkának a poziciója x tengelyen
     */

        protected void teherauto(int i, int j){
            targyakDb++;
            for(int y = i; y < i+5; y++){
                for(int x = j; x < j +3; x++){
                    letrehoz(y,x);
                    negyzet[y][x].setName("foglalt");
                }
            }
            ImageIcon image = new ImageIcon("img/teherauto.png");
            negyzet[i][j].setIcon(image);
            gbc.gridx = j;
            gbc.gridwidth = 3;
            gbc.gridheight = 5;
            gbc.fill = GridBagConstraints.BOTH;

            add(negyzet[i][j],gbc);
        }

    /**
     * Egy alapértelmezett mezőt hoz létre
     * A gbc pozicionálja oda ahol lennie kell és végül hozzáadja a pályához
     *
     * @param i a mező poziciója y tengelyen
     * @param j a mező poziciója x tengelyen
     */

        protected void mezo(int i, int j){
            negyzet[i][j] = new JLabel();
            negyzet[i][j].setName("mezo");
            negyzet[i][j].setBorder(new LineBorder(Color.BLACK));
            negyzet[i][j].setBackground(new Color(34, 146, 34));
            negyzet[i][j].setPreferredSize(new Dimension(45,45));
            negyzet[i][j].setOpaque(true);
            gbc.gridx = j;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;

            add(negyzet[i][j],gbc);
        }

    /**
     * Egy házat hoz létre, a képet beállitja a neki megfelelővel
     * A gbc pozicionálja oda ahol lennie kell és végül hozzáadja a pályához
     * Növeli a targyakDb értékét eggyel, a papirokhoz fog ez majd kelleni
     * Létrehozza a ház falakat, illetve az ajtókat, ahol foglalt van jelenti a falakat
     * 4 ajtó van a házban
     *
     * @param i a ház bal felső sarkának a poziciója y tengelyen
     * @param j a ház felső sarkának a poziciója x tengelyen
     */

        protected void haz(int i, int j){
            targyakDb++;
            for(int y = i; y < i+7; y++){
                letrehoz(y,j+5);
                negyzet[y][j+5].setName("foglalt");
                if(y != i+5) {
                    letrehoz(y, j);
                    negyzet[y][j].setName("foglalt");
                }
                if(y != i+1){
                    letrehoz(y,j+2);
                    negyzet[y][j+2].setName("foglalt");
                }
                for(int x = j; x < j +5; x++){
                    letrehoz(i,x);
                    negyzet[i][x].setName("foglalt");
                    if(x != j+4) {
                        letrehoz(i+6, x);
                        negyzet[i+6][x].setName("foglalt");
                    }
                }
            }
            letrehoz(i+3,j+3);
            negyzet[i+3][j+3].setName("foglalt");
            ImageIcon image = new ImageIcon("img/haz.png");
            negyzet[i][j].setIcon(image);

            gbc.gridx = j;
            gbc.gridwidth = 6;
            gbc.gridheight = 7;
            gbc.fill = GridBagConstraints.BOTH;

            add(negyzet[i][j],gbc);
        }
    /**
     * Egy sziklát hoz létre, a képet beállitja a neki megfelelővel
     * A gbc pozicionálja oda ahol lennie kell és végül hozzáadja a pályához
     * Növeli a targyakDb értékét eggyel, a papirokhoz fog ez majd kelleni
     * A foglalt részek az objektumok határát jelenti
     *
     * @param i a szikla bal felső sarkának a poziciója y tengelyen
     * @param j a szikla  bal felső sarkának a poziciója x tengelyen
     */

        protected void szikla(int i, int j){
            targyakDb++;
            for(int y = i; y < i+3; y++){
                for(int x = j; x < j +3; x++){
                    letrehoz(y,x);
                    negyzet[y][x].setName("foglalt");
                }
            }
            ImageIcon image = new ImageIcon("img/szikla.png");
            negyzet[i][j].setIcon(image);
            gbc.gridx = j;
            gbc.gridwidth = 3;
            gbc.gridheight = 3;
            gbc.fill = GridBagConstraints.BOTH;

            add(negyzet[i][j],gbc);
        }

    public JLabel[][] getNegyzet() {
        return negyzet;
    }

    public GridBagConstraints getGbc() {
        return gbc;
    }

    public int getTargyakDb() {
        return targyakDb;
    }
}


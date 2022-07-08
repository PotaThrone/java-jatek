package Karakter;

import Palya.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * A játékost hozza létre a Karakter osztályból öröklődik és az AcitonListener implementálja
 * Alapértelmezetten a vadászás false, hiszen a Slenderman nem támad egyből
 */

public class Jatekos extends Karakter implements ActionListener {
    private JButton buttonFel;
    private JButton buttonLe;
    private JButton buttonJobb;
    private JButton buttonBal;
    private Slenderman slenderman;
    private boolean vadaszas = false;

    /**
     * Miután megkapja a paramétereket inicializálja az ősosztályban létrehozott változókat
     * Az alapértelmezett pozicioja jobb alsó sarok lesz
     *
     * @param p melyik pályán legyen a játékos
     * @param p2 a papirokat ismernie kell hogy össze tudja őket szedni
     * @param f ismernie kell a keretet, ha összeszedi az összes papirt akkor tudomást kapjon róla
     */


    public Jatekos(Palya p, Papir p2, JFrame f){
        palya = p;
        papir = p2;
        frame = f;
        image = new ImageIcon("img/player.png");
        x = 14;
        y = 14;
        palya.getNegyzet()[y][x].setIcon(image);
    }
    /**
     * Miután megkapja a paramétereket inicializálja az ősosztályban létrehozott változókat
     * Az alapértelmezett pozicioja attól függ hogy hol adtad meg a 8-as értéket a txt fájlban
     *
     * @param p egyéni pályán legyen a játékos
     * @param p2 a papirokat ismernie kell hogy össze tudja őket szedni
     * @param f ismernie kell a keretet, ha összeszedi az összes papirt akkor tudomást kapjon róla
     */


    public Jatekos(EgyeniPalya p, Papir p2, JFrame f){
        palya = p;
        papir = p2;
        frame = f;
        image = new ImageIcon("img/player.png");
        if( p.getPalya()[14][14] == 8){
            x = 14;
            y = 14;
        }else{
            x = 0;
            y = 0;
        }
        palya.getNegyzet()[y][x].setIcon(image);
    }

    /**
     * A játékos mozgásáért felelős
     * Inicializálja a gombokat, annak érdekében hogy lehessen irányitani
     * Az ActionListenert használja
     *
     * @param s miután lép akkor a slenderman is teleportáljon valahova
     */


    public void mozgatas(Slenderman s){
        slenderman = s;
        buttonFel = new JButton();
        buttonFel.setBounds(0,0,90,45);
        buttonFel.addActionListener(this);
        buttonFel.setText("Fel");
        palya.getGbc().gridx = 17;
        palya.getGbc().gridy = 7;
        formazas();
        palya.add(buttonFel,palya.getGbc());

        buttonLe = new JButton();
        buttonLe.setBounds(0, 0,90,45);
        buttonLe.addActionListener(this);
        buttonLe.setText("Le");
        palya.getGbc().gridx = 17;
        palya.getGbc().gridy = 8;
        formazas();
        palya.add(buttonLe,palya.getGbc());

        buttonJobb = new JButton();
        buttonJobb.setBounds(0,0,90,45);
        buttonJobb.addActionListener(this);
        buttonJobb.setText("Jobb");
        palya.getGbc().gridx = 19;
        palya.getGbc().gridy = 8;
        formazas();
        palya.add(buttonJobb,palya.getGbc());

        buttonBal = new JButton();
        buttonBal.setBounds(0,0,90,45);
        buttonBal.addActionListener(this);
        buttonBal.setText("Bal");
        palya.getGbc().gridx = 15;
        palya.getGbc().gridy = 8;
        formazas();
        palya.add(buttonBal,palya.getGbc());

    }

    /**
     * A gombok formázása
     */

    private void formazas(){
        palya.getGbc().gridheight = 1;
        palya.getGbc().gridwidth = 2;
        palya.getGbc().insets = new Insets(5,5,5,5);
    }

    /**
     * Miután a gomb lenyomódik ezek a események következnek be, attól függöen melyik irányba szeretne mozogni
     *
     * @param e ha a gomb lenyómodik ennek a paraméternek az értékét figyeli
     */

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==buttonFel){
            buttonfel();
        }

        if(e.getSource()==buttonLe){
            buttonle();
        }
        if(e.getSource()==buttonJobb){
            buttonjobb();
        }
        if(e.getSource()==buttonBal){
            buttonbal();
        }
    }

    /**
     * Ha talál egy papirt akkor azt felveszi, ha az első papir volt az akkor a Slenderman megkezdi vadászását
     * Ha a játékos a 8 papirt szedi fel akkor a játékos nyert
     * A papirok száma mindig változzon az indikátoron
     */


    public void papirtFelvesz(){
        if(papir.getPapirDb() == 0){
            slenderman.mozgatas();
            vadaszas = true;
        }
        papir.setPapirDb(papir.getPapirDb() + 1);
        if(papir.getPapirDb() == 8){
            gyozelem();
        }
        papir.meglevoPapir(frame);
    }

    /**
     * Akkor hivodik meg ha a papirok száma 8
     * Kicseréli a Kereten a pályát egy szövegre
     */

    private void gyozelem() {
        frame.remove(palya);
        JLabel gyozelem = new JLabel("Gratulalok! Sikeresen osszegyujtotted a 8 papirt!");
        gyozelem.setFont(new Font("Verdana",Font.PLAIN ,30) );
        frame.add(gyozelem, BorderLayout.CENTER);
        frame.revalidate();
    }

    /**
     * Akkor hivodik meg amikor a Fel gombot megnyomod
     * A játékos helyzete y tengelyen 1-el csökken, ha a pálya tetején van akkor nem lép sehova
     * Vigyázz mert ha olyan helyre akarsz lépni ahova nem birsz az is lépésnek számit, a te poziciod nem változik viszont a Slenderman pozicioja igen!!!
     * Ha olyan mezőre lépsz ahol papir van akkor felveszed
     * Ha olyan mezőre lépsz ahol a Slenderman van akkor vesztettél
     * Ha olyan mezőre lépsz ahol nagyfa illetve kisfa van akkor bebújsz a fa alá, igy nem látszódsz
     */


    public void buttonfel(){
        if(y != 0){
            if(palya.getNegyzet()[y-1][x].getName().equals("slenderman")){
                slenderman.elkap();
            }
            if(palya.getNegyzet()[y-1][x].getName().equals("nagyfa") || palya.getNegyzet()[y-1][x].getName().equals("kisfa") ){
                palya.getNegyzet()[y][x].setIcon(null);
                y--;
            }
            else {
                if((palya.getNegyzet()[y][x].getName().equals("nagyfa") && !palya.getNegyzet()[y-1][x].getName().equals("foglalt") )|| (palya.getNegyzet()[y][x].getName().equals("kisfa") &&  !palya.getNegyzet()[y-1][x].getName().equals("foglalt")) ){
                    y--;
                    palya.getNegyzet()[y][x].setIcon(image);
                }
                else{
                    if(palya.getNegyzet()[y-1][x].getName().equals("mezo")|| palya.getNegyzet()[y-1][x].getName().equals("papir")){
                        palya.getNegyzet()[y][x].setIcon(null);
                        y--;
                        palya.getNegyzet()[y][x].setIcon(image);
                    }
                }
            }
        }
        if(palya.getNegyzet()[y][x].getName().equals("papir")){
            palya.getNegyzet()[y][x].setName("mezo");
            papirtFelvesz();
        }
    }
    /**
     * Akkor hivodik meg amikor a Le gombot megnyomod
     * A játékos helyzete y tengelyen 1-el nő, ha a pálya alján van akkor nem lép sehova
     * Vigyázz mert ha olyan helyre akarsz lépni ahova nem birsz az is lépésnek számit, a te poziciod nem változik viszont a Slenderman pozicioja igen!!!
     * Ha olyan mezőre lépsz ahol papir van akkor felveszed
     * Ha olyan mezőre lépsz ahol a Slenderman van akkor vesztettél
     * Ha olyan mezőre lépsz ahol nagyfa illetve kisfa van akkor bebújsz a fa alá, igy nem látszódsz
     */

    public void buttonle(){
        if(y != 14){
            if(palya.getNegyzet()[y+1][x].getName().equals("slenderman")){
                slenderman.elkap();
            }
            if(palya.getNegyzet()[y+1][x].getName().equals("nagyfa") || palya.getNegyzet()[y+1][x].getName().equals("kisfa") ){
                palya.getNegyzet()[y][x].setIcon(null);
                y++;
            }
            else {
                if((palya.getNegyzet()[y][x].getName().equals("nagyfa")  && !palya.getNegyzet()[y+1][x].getName().equals("nagyfa") )|| (palya.getNegyzet()[y][x].getName().equals("kisfa") &&  !palya.getNegyzet()[y+1][x].getName().equals("foglalt"))){
                    y++;
                    palya.getNegyzet()[y][x].setIcon(image);

                }
                else{
                    if(palya.getNegyzet()[y+1][x].getName().equals("mezo") || palya.getNegyzet()[y+1][x].getName().equals("papir")){
                        palya.getNegyzet()[y][x].setIcon(null);
                        y++;
                        palya.getNegyzet()[y][x].setIcon(image);
                    }
                }

            }
        }
        if(palya.getNegyzet()[y][x].getName().equals("papir")){
            palya.getNegyzet()[y][x].setName("mezo");
            papirtFelvesz();
        }
    }
    /**
     * Akkor hivodik meg amikor a Jobb gombot megnyomjuk
     * A játékos helyzete x tengelyen 1-el nő, ha a pálya jobb szélén van akkor nem lép sehova
     * Vigyázz mert ha olyan helyre akarsz lépni ahova nem birsz az is lépésnek számit, a te poziciod nem változik viszont a Slenderman pozicioja igen!!!
     * Ha olyan mezőre lépsz ahol papir van akkor felveszed
     * Ha olyan mezőre lépsz ahol a Slenderman van akkor vesztettél
     * Ha olyan mezőre lépsz ahol nagyfa illetve kisfa van akkor bebújsz a fa alá, igy nem látszódsz
     */

    public void buttonjobb(){
        if(x != 14){
            if(palya.getNegyzet()[y][x+1].getName().equals("slenderman")){
                slenderman.elkap();
            }
            if(palya.getNegyzet()[y][x+1].getName().equals("nagyfa") || palya.getNegyzet()[y][x+1].getName().equals("kisfa")  ){
                palya.getNegyzet()[y][x].setIcon(null);
                x++;
            }
            else {
                if((palya.getNegyzet()[y][x].getName().equals("nagyfa") && !palya.getNegyzet()[y][x+1].getName().equals("nagyfa")  ) || (palya.getNegyzet()[y][x].getName().equals("kisfa") &&  !palya.getNegyzet()[y][x+1].getName().equals("foglalt"))){
                    x++;
                    palya.getNegyzet()[y][x].setIcon(image);
                }else{
                    if(palya.getNegyzet()[y][x+1].getName().equals("mezo") || palya.getNegyzet()[y][x+1].getName().equals("papir")){
                        palya.getNegyzet()[y][x].setIcon(null);
                        x++;
                        palya.getNegyzet()[y][x].setIcon(image);
                    }
                }
            }
        }
        if(palya.getNegyzet()[y][x].getName().equals("papir")){
            palya.getNegyzet()[y][x].setName("mezo");
            papirtFelvesz();
        }
    }
    /**
     * Akkor hivodik meg amikor a Bal gombot megnyomjuk
     * A játékos helyzete x tengelyen 1-el csökken, ha a pálya bal szélén van akkor nem lép sehova
     * Vigyázz mert ha olyan helyre akarsz lépni ahova nem birsz az is lépésnek számit, a te poziciod nem változik viszont a Slenderman pozicioja igen!!!
     * Ha olyan mezőre lépsz ahol papir van akkor felveszed
     * Ha olyan mezőre lépsz ahol a Slenderman van akkor vesztettél
     * Ha olyan mezőre lépsz ahol nagyfa illetve kisfa van akkor bebújsz a fa alá, igy nem látszódsz
     */

    public void buttonbal(){
        if(x != 0){
            if(palya.getNegyzet()[y][x-1].getName().equals("slenderman")){
                slenderman.elkap();
            }
            if(palya.getNegyzet()[y][x-1].getName().equals("nagyfa") || palya.getNegyzet()[y][x-1].getName().equals("kisfa")){
                palya.getNegyzet()[y][x].setIcon(null);
                x--;
            }
            else {
                if((palya.getNegyzet()[y][x].getName().equals("nagyfa") && !palya.getNegyzet()[y][x-1].getName().equals("foglalt")) || (palya.getNegyzet()[y][x].getName().equals("kisfa") &&  !palya.getNegyzet()[y][x-1].getName().equals("foglalt"))){
                    x--;
                    palya.getNegyzet()[y][x].setIcon(image);
                }else{
                    if(palya.getNegyzet()[y][x-1].getName().equals("mezo")|| palya.getNegyzet()[y][x-1].getName().equals("papir")){
                        palya.getNegyzet()[y][x].setIcon(null);
                        x--;
                        palya.getNegyzet()[y][x].setIcon(image);
                    }
                }
            }
        }
        if(palya.getNegyzet()[y][x].getName().equals("papir")){
            palya.getNegyzet()[y][x].setName("mezo");
            papirtFelvesz();
        }
    }

    public JButton getButtonFel() {
        return buttonFel;
    }

    public JButton getButtonLe() {
        return buttonLe;
    }

    public JButton getButtonJobb() {
        return buttonJobb;
    }

    public JButton getButtonBal() {
        return buttonBal;
    }

    public boolean isVadaszas() {
        return vadaszas;
    }
}

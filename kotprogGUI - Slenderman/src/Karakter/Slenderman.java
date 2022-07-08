package Karakter;

import Palya.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
/**
 * Ez a resz vegzi a Slenderman letrehozasat.
 * A Karakter osztalybol oroklodik es implementalja az ActionListener
 * ActionListener lenyege, hogy a gomb megnyomasa utan mit csinaljon a Slenderman
 */

public class Slenderman extends Karakter implements ActionListener {
    private final Jatekos player;
    private int lepes = 4;
    private int tavolsag = 0;
    private int kozel = 0;

    /**
     * Az értékeket itt inicializáljuk a paraméterek segitségével. Igy tudja a Slenderman melyik osztályokat kell használnia
     *
     * @param p A pálya, erre azért van szükség, hogy tudjon a pályán teleportálni
     * @param j A játékos, ismernie kell a játékost mivel ő tőle függ a mozgása
     * @param p2 A papír, szinten függ a papirok számától a mozgása
     * @param f A keret, megváltoztatja a keret kinézetet, ha interakcióba kerül a játkossal
     */

    public Slenderman(Palya p, Jatekos j, Papir p2, JFrame f) {
        image = new ImageIcon("img/slenderman.png");
        palya = p;
        player = j;
        papir = p2;
        frame = f;
        x=0;
        y=0;

    }
    /**
     * A gombok müködéséhez szükséges, az ActionListenert használja
     */

    public void mozgatas(){
        player.getButtonFel().addActionListener(this);
        player.getButtonLe().addActionListener(this);
        player.getButtonBal().addActionListener(this);
        player.getButtonJobb().addActionListener(this);
    }

    /**
     * A kód ismétlés elkerülése céljából valósitottam meg, lényege ha a Slenderman már elment az adott mezőröl, akkor visszavált alapértelmezett mezőre
     */

    private void atvalt(){
        if(palya.getNegyzet()[y][x].getName().equals("slenderman")){
            palya.getNegyzet()[y][x].setName("mezo");
            palya.getNegyzet()[y][x].setIcon(null);
            palya.getNegyzet()[y][x].setBackground(new Color(34, 146, 34));
        }
    }

    /**
     * Ha a Slenderman maximum 3 távolságra van akkor, a játékos látja őt és ha megegyezik a játékos és a szörny pozicioja, akkor a játékos vesztett
     * Van hogy a Slenderman a játékos előző poziciójára teleportál ilyenkor a Slenderman képe nem jelenik meg csak a háttér szine lesz piros
     * Ezt a hibát nem tudtam lekezelni, légyszi ettől tekints el :D
     * Lényeg, ha piros a melletted lévő mező, de nincs rajta semmi akkor képzeld oda, hogy mégis ott van a Slenderman, hiszen ha rámész attól ugyanúgy elkap
     */

    private void latszodjon(){
        if(palya.getNegyzet()[y][x].getName().equals("mezo")){
            palya.getNegyzet()[y][x].setName("slenderman");
        }
        if(tavolsag <= 3 && palya.getNegyzet()[y][x].getName().equals("slenderman")){
            palya.getNegyzet()[y][x].setBackground(Color.red);
            palya.getNegyzet()[y][x].setIcon(image);
        }else{
            if(palya.getNegyzet()[y][x].getName().equals("slenderman") && tavolsag > 3 )
                palya.getNegyzet()[y][x].setIcon(null);
            palya.getNegyzet()[y][x].setBackground(new Color(34, 146, 34));
        }
        if(tavolsag == 0 && palya.getNegyzet()[player.y][player.x].getName().equals("slenderman")){
            elkap();
        }
    }
    /**
     * Ha a játékosnak sikerült összeszednie 6 papirt, akkor ez a metódus hivodik meg.
     * Generál egy poziciot addig míg nem lesz kisebb vagy egyenlő mint 3 a távolság
     * Szintén ha megegyezik a pozicio, akkor elkap
     * Itt nincs szükség a latszodjon metódusra hiszen mindig látnia kellene
     *
     * @param a A játékos milyen irányba lépett az y tengelyen
     * @param b A játékos milyen irányba lépett az x tengelyen
     */

    private void teleportal4(int a, int b){
        atvalt();
        Random r = new Random();
        do{
            x = r.nextInt(15);
            y = r.nextInt(15);
            tavolsag = Math.abs(player.y - y+a) + Math.abs(player.x - x+b);
        }
        while(tavolsag  > 3);
        if(palya.getNegyzet()[y][x].getName().equals("mezo")){
            palya.getNegyzet()[y][x].setName("slenderman");
        }
        if(palya.getNegyzet()[y][x].getName().equals("slenderman")){
            palya.getNegyzet()[y][x].setBackground(Color.red);
            palya.getNegyzet()[y][x].setIcon(image);
        }else{
            if(palya.getNegyzet()[y][x].getName().equals("slenderman") && tavolsag > 3 )
                palya.getNegyzet()[y][x].setIcon(null);
            palya.getNegyzet()[y][x].setBackground(new Color(34, 146, 34));
        }
        if(tavolsag == 0 && palya.getNegyzet()[player.y][player.x].getName().equals("slenderman")){
            elkap();
        }
    }
    /**
     * Ha a játékosnak sikerült összeszednie 4 papirt, akkor ez a metódus hivodik meg.
     * Generál egy poziciot addig míg nem lesz kisebb vagy egyenlő mint 4 a távolság
     * Szintén ha megegyezik a pozicio, akkor elkap
     * Meghivja a latszodjon metódust amiben benne van az elkap is
     *
     * @param a A játékos milyen irányba lépett az y tengelyen
     * @param b A játékos milyen irányba lépett az x tengelyen
     */

    private void teleportal3(int a, int b){
        atvalt();
        Random r = new Random();
        do{
            x = r.nextInt(15);
            y = r.nextInt(15);
            tavolsag = Math.abs(player.y - y+a) + Math.abs(player.x - x+b);
        }
        while(tavolsag  > 4);
        latszodjon();
    }
    /**
     * Ha a játékosnak sikerült összeszednie 2 papirt, akkor ez a metódus hivodik meg.
     * Generál egy poziciot addig míg nem lesz kisebb vagy egyenlő mint 5 a távolság
     * Szintén ha megegyezik a pozicio, akkor elkap
     * Meghivja a latszodjon metódust amiben benne van az elkap is
     *
     * @param a A játékos milyen irányba lépett az y tengelyen
     * @param b A játékos milyen irányba lépett az x tengelyen
     */

    private void teleportal2(int a, int b){
        atvalt();
        Random r = new Random();
        do{
            x = r.nextInt(15);
            y = r.nextInt(15);
            tavolsag = Math.abs(player.y - y+a) + Math.abs(player.x - x+b);
        }
        while(tavolsag  > 5);
        latszodjon();
    }
    /**
     * Ha a játékosnak sikerült összeszednie 1 papirt, akkor ez a metódus hivodik meg.
     * Generál egy poziciot addig míg nem lesz nagyobb vagy egyenlő mint 5 a távolság
     * Itt elvileg nem egyezhet meg a pozicio igy itt nem kaphat el illetve nem is latszodhat
     */


    private void teleportal1(){
        atvalt();
        Random r = new Random();
        do{
                x = r.nextInt(15);
                y = r.nextInt(15);
                tavolsag = Math.abs(player.y - y) + Math.abs(player.x - x);
        }
        while(tavolsag < 5);
        if(palya.getNegyzet()[y][x].getName().equals("mezo")){
            palya.getNegyzet()[y][x].setName("slenderman");
        }
    }
    /**
     * Ez dönti el, hogy a Slenderman hogyan teleportáljon az adott körülmények között
     * Ha az 5. lépés akkor pedig random teleportál
     * Ha a teleportálás után a távolság 1 három egymás követő alkalom után akkor az adott valószinüséggel a Slenderman elkap, itt másik kiiratást használtam (elkap2)
     * Erre egy random osztály használtam és ellenőriztem mikor milyen értéked ad
     *
     * @param i A játékos milyen irányba lépett az y tengelyen
     * @param j A játékos milyen irányba lépett az x tengelyen
     */


    public void hogyanTeleportaljon(int i, int j){
        Random r = new Random();
        tavolsag = Math.abs(player.y - y+i) + Math.abs(player.x - x+j);
        if (player.isVadaszas()) {
            lepes++;
            if(lepes == 5){
                randomTeleport(i,j);
            }else{
                if (papir.getPapirDb() == 1) {
                    teleportal1();
                } else {
                    if (papir.getPapirDb() >= 2 && papir.getPapirDb() < 4) {
                            teleportal2(i,j);
                        if(tavolsag == 1){
                            kozel++;
                        }else{
                            kozel = 0;
                        }
                        if(kozel == 3 && r.nextInt(3) == 0){
                            elkap2();
                        }
                    }else{
                        if(papir.getPapirDb() >= 4 && papir.getPapirDb() < 6){
                            teleportal3(i,j);
                            if(tavolsag == 1){
                                kozel++;
                            }else{
                                kozel = 0;
                            }
                            if(kozel == 3 && r.nextInt(2) == 0){
                                elkap2();
                            }
                        }else{
                            if(papir.getPapirDb() >= 6){
                                teleportal4(i,j);
                                if(tavolsag == 1){
                                    kozel++;
                                }else{
                                    kozel = 0;
                                }
                                int elkape = r.nextInt(3);
                                if(kozel == 3 && (elkape == 0 || elkape == 1)){
                                    elkap2();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    /**
     * Ha sikerült elkapnia akkor ez a függvény hivodik meg
     * Kitörli a pályáról az objektumokat és kicseréli egy feliratra
     */

    public void elkap(){
        frame.remove(palya);
        JLabel vereseg = new JLabel("Vesztettel, a Slenderman elkapott!");
        vereseg.setFont(new Font("Verdana",Font.PLAIN  ,30) );
        frame.add(vereseg, BorderLayout.CENTER);
        frame.revalidate();
    }
    /**
     * Lényege ugyanaz mint az elkap-nak csak más a kiiratás*
     * Akkor hivodik meg amikor a kozel értéke eléri a 3-at, tehát 3x 1 távolságra teleportál hozzád egymás után
     */

    private void elkap2() {
        frame.remove(palya);
        JLabel vereseg = new JLabel("Vesztettel, a Slenderman 3x melled teleportalt!");
        vereseg.setFont(new Font("Verdana",Font.PLAIN ,30) );
        frame.add(vereseg, BorderLayout.CENTER);
        frame.revalidate();
    }

    /**
     * Minden 5. lépést követően hivodik meg, bárhova teleportálhat a pályán
     * Arra is van esély hogy elkapjon, illetve hogy a közeledbe teleportáljon, ezért meg van hivva a látszódjon metódus
     *
     * @param a A játékos milyen irányba lépett az y tengelyen
     * @param b A játékos milyen irányba lépett az x tengelyen
     */

    private void randomTeleport(int a, int b) {
        atvalt();
        Random r = new Random();
        x = r.nextInt(15);
        y= r.nextInt(15);
        tavolsag = Math.abs(player.y - y+a) + Math.abs(player.x - x+b);
        latszodjon();
        lepes = 0;
    }

    /**
     * A gombok müködéséért felelős, amikor a gomb lenyomódik itt fogja érzékelni a függvény
     * Ez az ActionListener beépített függvénye, enélkül nem is engedi használni
     * Hiba le van kezelve, ha a pálya szélén vagy akkor nem kezeli az adott irányba a mezőket
     * Itt hivodik meg a hogyanTeleportáljon és adja át hogy a játekos melyik irányba mozdult el
     *
     * @param e ha a gomb lenyómodik ennek a paraméternek az értékét figyeli
     */


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==player.getButtonFel()) {
            if(player.y != 0){
                if(palya.getNegyzet()[player.y-1][player.x].getName().equals("slenderman")){
                    elkap();
                }
            }
           hogyanTeleportaljon(-1,0);
        }
        if(e.getSource()==player.getButtonLe()){
            if(player.y != 14){
                if(palya.getNegyzet()[player.y+1][player.x].getName().equals("slenderman")){
                    elkap();
                }
            }
            hogyanTeleportaljon(1,0);
        }
        if(e.getSource()==player.getButtonBal()){
            if(player.x != 0){
                if(palya.getNegyzet()[player.y][player.x-1].getName().equals("slenderman")){
                    elkap();
                }
            }
            hogyanTeleportaljon(0,-1);
        }
        if(e.getSource()==player.getButtonJobb()){
            if(player.x != 14){
                if(palya.getNegyzet()[player.y][player.x+1].getName().equals("slenderman")){
                    elkap();
                }
            }
            hogyanTeleportaljon(0,1);
        }
    }
}


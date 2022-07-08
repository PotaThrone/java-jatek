package Karakter;

import Palya.*;
import javax.swing.*;
/**
 * Itt hozzuk létre az értékeket amiket majd a Jatekos és a Slenderman használ
 * Célja a hibák kiiratása illetve a könnyebb kódolás
 */

public class Karakter {
    /**
     * A játékos képe image változóban
     */
    protected ImageIcon image;
    protected Palya palya;
    protected Papir papir;
    protected JFrame frame;
    protected int x;
    protected int y;


    @Override
    public String toString() {
        return "A karakter poziciója: "+y + " "+x;
    }
}

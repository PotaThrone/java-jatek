package Futtatas;

import javax.swing.*;
import java.awt.*;

/**
 * Keretet hozza létre, ami vizualizálja a játékot.
 * A JFrame osztályból öröklődik
 */

public class Keret extends JFrame{
    /**
     * A BorderLayout lényege, hogy szépen rendezni lehet adott irányba az objektumokat.
     * Nem módosítható a mérete
     */

        public Keret(){
            setSize(1920,1080);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            setTitle("Slenderman");
            setLayout(new BorderLayout(10,0));
            setVisible(true);
        }
}

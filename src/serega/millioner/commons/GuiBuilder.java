package serega.millioner.commons;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GuiBuilder {

    public static void setComponentSize(JComponent component, Dimension dimension){
        component.setMaximumSize(dimension);
        component.setMinimumSize(dimension);
        component.setPreferredSize(dimension);
        component.setSize(dimension);
    }

    public static ImageIcon getScaledImageIcon(ImageIcon imageIcon, int w, int h) {
        Image image = imageIcon.getImage();
        Image newImg = image.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImg);
        return imageIcon;
    }

    public static ArrayList<Long> progressSimvols(){

        ArrayList<Long> list = new ArrayList<Long>();

        long money = 500;

        for (int i = 0; i < 11; i++) {
            money*=2;
            list.add(money);
        }

        return list;
    }


}

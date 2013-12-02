package author.mapCreation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import constants.Constants;


@SuppressWarnings("serial")
public class MapCreationView extends JPanel {

    private Image myBackground;
    private BufferedImage myImage;
    private Graphics myGraphics;

    private int horizontalTilesShowing;
    private int verticalTilesShowing;

    private int middleHorizontalTile;
    private int middleVerticalTile;

    private double tileWidth;
    private double tileHeight;

    public MapCreationView () {
        setFocusable(true);
        myImage = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, BufferedImage.TYPE_INT_RGB);
        myGraphics = myImage.getGraphics();
        myBackground = new ImageIcon("images/background/shortGrass.png").getImage();
        configureTiles(Constants.NUM_TILES_HORIZONTAL, Constants.NUM_TILES_VERTICAL); // 15, 9
        // this.setBackground(Color.BLUE);
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked (MouseEvent arg0) {
                System.out.println("Mouse clicked at: " + arg0.getX() + ", " + arg0.getY());
            }

            @Override
            public void mouseEntered (MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited (MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed (MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseReleased (MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

        });
    }

    private void configureTiles (int horizontal, int vertical) {
        horizontalTilesShowing = horizontal;
        verticalTilesShowing = vertical;
        middleHorizontalTile = horizontalTilesShowing / 2;
        middleVerticalTile = verticalTilesShowing / 2;
        tileWidth = (double) Constants.WIDTH / horizontalTilesShowing;
        tileHeight = (double) Constants.HEIGHT / verticalTilesShowing;
    }

    public void paintComponent (Graphics g) {
        g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }

    public Image getBackgroundImage () {
        return myBackground;
    }

    public Graphics getGraphics () {
        return myGraphics;
    }

}

package author.mapCreation;

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
    private CanvasTileManager myTileManager;

    public MapCreationView () {
        setFocusable(true);
        myImage = new BufferedImage(Constants.WIDTH, Constants.HEIGHT, BufferedImage.TYPE_INT_RGB);
        myGraphics = myImage.getGraphics();
        myBackground = new ImageIcon("images/background/shortGrass.png").getImage();
        myTileManager = new CanvasTileManager(); // 15, 9
        // this.setBackground(Color.BLUE);
        createMouseListener();
        createZoomListener();
    }
    
    private void createZoomListener(){
    	
    }

	private void createMouseListener() {
		this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked (MouseEvent arg0) {
                System.out.println("Mouse clicked at: " + arg0.getX() + ", " + arg0.getY());
            }

            @Override
            public void mouseEntered (MouseEvent arg0) { /* Intentionally left blank */ }

            @Override
            public void mouseExited (MouseEvent arg0) { /* Intentionally left blank */ }

            @Override
            public void mousePressed (MouseEvent arg0) { /* Intentionally left blank */ }

            @Override
            public void mouseReleased (MouseEvent arg0) { /* Intentionally left blank */ }

        });
	}
    
    public CanvasTileManager getTileManager(){
    	return myTileManager;
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

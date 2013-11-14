package game.model;

import java.awt.Image;
import javax.swing.ImageIcon;
import org.json.simple.JSONObject;

public class Obstacle extends AbstractViewableObject {
    private Image myImage;
    public Obstacle (int x, int y, JSONObject definition) {
        super(x, y, definition);
        String imageURL = definition.get("image").toString();
        System.out.println(imageURL);
        myImage = new ImageIcon(imageURL).getImage();
        //TODO: KeyItems
    }
    @Override
    public Image getImage () {
        return myImage;
    }
}

   

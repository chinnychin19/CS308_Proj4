package author.model;

import java.awt.image.BufferedImage;
import constants.Constants;

public class EmptyTileWrapper implements TileWrapper {
    
    public EmptyTileWrapper() {}

    public EmptyTileWrapper (String name, String type, BufferedImage image) {}

    public EmptyTileWrapper (String name, String type, String imagePath) {}
    
    @Override
    public BufferedImage getImage () {
        return new BufferedImage(0, 0, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public String getType () {
        return Constants.EMPTY_STRING;
    }

    @Override
    public String getName () {
        return Constants.EMPTY_STRING;
    }

    @Override
    public String getMyAdditionalInformation () {
        return Constants.EMPTY_STRING;
    }

    @Override
    public void setMyAdditionalInformation (String myAdditionalInformation) {
        System.out.println(Constants.EMPTY_TILE_ADD_INFO_WARNING);
    }
    
    public String toString(){
        return Constants.EMPTY_TILE_STRING;
    }

}

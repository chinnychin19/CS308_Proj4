package game.model;

import game.model.attack.Attack;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.ImageIcon;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;


/**
 * Work in progress
 * 
 * @author rtoussaint
 * 
 */

public abstract class Monster extends AbstractModelObject {

    private int myMaxHP;
    private double myCatchRate;
    private List<Attack> myAttacks;
    private List<Monster> myEvolution;
    private Image myImage;

    public Monster (GameModel model, SmartJsonObject definition) {
        super(model, definition);
        try {

            String imageURL = definition.getString(Constants.JSON_IMAGE);
            myImage = new ImageIcon(imageURL).getImage();
            myMaxHP = definition.getInt(Constants.JSON_MONSTER_MAX_HP);
            myCatchRate = definition.getDouble(Constants.JSON_MONSTER_CATCH_RATE);
            myAttacks = new ArrayList<Attack>();
            for (Object obj : definition.getJSONArray(Constants.JSON_MONSTER_ALL_ATTACKS)) {
                SmartJsonObject attackJson = new SmartJsonObject((JSONObject) obj);
                String name = attackJson.getString("name");

            }
        }
        catch (SmartJsonException e) {
            e.printStackTrace();
        }
        // TODO: Implement myEvolution

    }
}

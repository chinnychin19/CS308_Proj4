package game.model;

import game.model.attack.AbstractAttack;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.ImageIcon;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.jsonwrapper.SmartJsonObject;
import constants.Constants;

/**
 * Work in progress
 * @author rtoussaint
 *
 */

public abstract class Monster extends AbstractViewableObject {

	private int myMaxHP;
	private double myCatchRate;
//	private List<Attack> myAttacks;
	private List<Monster> myEvolution;
	private Image myImage;
	
    public Monster (World world, SmartJsonObject definition, SmartJsonObject objInWord) {
        super(world, definition, objInWord);
        
//        String imageURL = definition.get(Constants.JSON_IMAGE).toString();
//        myImage = new ImageIcon(imageURL).getImage();
//        myMaxHP = Integer.parseInt(definition.get(Constants.JSON_MONSTER_MAX_HP).toString());
//        myCatchRate = Double.parseDouble(definition.get(Constants.JSON_MONSTER_CATCH_RATE).toString());
//        myAttacks = new ArrayList<Attack>();
//        for(Object obj : (JSONArray) definition.get(Constants.JSON_MONSTER_ATTACK)){
//        	myAttacks.add(obj);
//        }
        //TODO: Implement myEvolution
        
        
        
        
    }
}

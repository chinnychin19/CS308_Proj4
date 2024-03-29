package game.model;

import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import constants.Constants;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.NoJSONObjectJsonException;
import util.jsonwrapper.jsonexceptions.SmartJsonException;


// TODO: Write Tests
/**
 * Represents how well a certain type performs against another Type in battle
 * Information is read in throught the definition.json for the game
 * Uses a Map to store the data internally
 * @author tylernisonoff
 *
 */
public class TypeMatrix extends AbstractModelObject {
    private Map<Type, Map<Type, Double>> myTypeMap;

    public TypeMatrix (GameModel model, SmartJsonObject definition) {
        super(model, definition);
    }
    
    /**
     * Calculates the damage multiplier for a given type attacking another type
     * @param from - The type performing the attack
     * @param to  - The type recieving the attack
     * @return the mulitpling factor for the damage
     */
    public double getDamageMultiplier (Type from, Type to) {
        return myTypeMap.get(from).get(to);
    }
    
    @Override
    protected void readDefinition(SmartJsonObject definition) throws SmartJsonException{
        myTypeMap = new HashMap<Type, Map<Type, Double>>();
        SmartJsonObject matrix = definition.getSmartJsonObject(Constants.TYPE_MATRIX_KEY);
        for (Object key : matrix.keySet()) {
            String s = (String) key;
            Type t = new Type(s);
            myTypeMap.put(t, new HashMap<Type, Double>());
            SmartJsonObject damages = matrix.getSmartJsonObject(s);
            for (Object other : damages.keySet()) {
                String versus = other.toString();
                Type type = new Type(versus);
                double multiplier = damages.getDouble(versus);
                Map<Type, Double> damageMap = myTypeMap.get(t);
                damageMap.put(type, multiplier);
            }
        }
    }
}

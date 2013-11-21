package game.model;

import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

//TODO: Write Tests
public class TypeMatrix extends AbstractModelObject {
    private static final String MATRIX_KEY = "matrix";
    private Map<Type,Map<Type,Double>> myTypeMap;
    public TypeMatrix(JSONObject definition){
        super(definition);
        myTypeMap = new HashMap<Type, Map<Type,Double>>(); 
        JSONObject matrix = (JSONObject) definition.get(MATRIX_KEY);
        for(Object key : matrix.keySet()){
            String s = (String) key;
            Type t = new Type(s);
            myTypeMap.put(t, new HashMap<Type, Double>());
            JSONObject damages = (JSONObject)matrix.get(s);
            for(Object other : damages.keySet()){
                String versus = other.toString();
                Type type = new Type(versus);
                String damageMultiplier = damages.get(versus).toString();
                double multiplier = Double.parseDouble(damageMultiplier);
                Map<Type,Double> damageMap = myTypeMap.get(t);
                damageMap.put(type, multiplier);
            }
        }
    }
    
    public double getDamageMultiplier(Type from, Type to){
        return myTypeMap.get(from).get(to);
    }
}

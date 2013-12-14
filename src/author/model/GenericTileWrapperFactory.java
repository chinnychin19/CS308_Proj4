package author.model;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import util.FilepathReformatter;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.NoStringValueJsonException;
import util.jsonwrapper.jsonexceptions.SmartJsonException;
import constants.Constants;
import author.mapCreation.GenericTileWrapper;

public class GenericTileWrapperFactory {
    
    public GenericTileWrapperFactory(){
        
    }
    
    public Collection<TileWrapper> generateTiles(AuthoringCache ac){
        Collection<TileWrapper> results = new HashSet<TileWrapper>();
        for (String category : Constants.VIEWABLE_CATEGORIES){
            Collection<SmartJsonObject> wholeCat = new HashSet<SmartJsonObject>();
            try {
                wholeCat.addAll(ac.getAllCategoryInstances(category));
            }
            catch (SmartJsonException e) {
                System.out.println("No objects found for category: " + category);
                //e.printStackTrace();
            }
            for (SmartJsonObject smartObj : wholeCat){
                TileWrapper gtw = this.convertToTile(smartObj, category);
                results.add(gtw);
            }
            
        }
        
        return results;
    }

    public TileWrapper convertToTile (SmartJsonObject smartObj, String category) {
        String name = "";
        String imgPath = "";
        try {
            name = smartObj.getString(Constants.NAME_LOWERCASE);
          
            Set<Object> JSONKeySet = smartObj.keySet();
            String key = "";
            for (Object o : JSONKeySet) {
                    if (((String) o).contains(Constants.IMAGE.toLowerCase())) {
                        key = (String) o;
                        break;                            
                    }
            }
            String unixStyleTruncatedFilepath = smartObj.getString(key);
            imgPath = convertToFullFunctionalFilepath(unixStyleTruncatedFilepath);
        }
        catch (NoStringValueJsonException e) {
            System.out.println(Constants.TILE_FACTORY_WARNING_PRE +
            		smartObj.toString() + Constants.TILE_FACTORY_WARNING_POST);
            //e.printStackTrace();
            return new EmptyTileWrapper();
            //return new GenericTileWrapper("default-blank", "obstacle", convertToFullFunctionalFilepath("images/test.gif"));
        }
        
        return new GenericTileWrapper(name, category, imgPath);
    }
    
    private static String convertToFullFunctionalFilepath (String unixStyleTruncatedFilepath) {
        FilepathReformatter fr = FilepathReformatter.getInstance();
        String correctedSeparators = fr.formatForCurrentSystem(unixStyleTruncatedFilepath);
        return System.getProperty(Constants.USER_DIR) + File.separator + correctedSeparators;
    }
}

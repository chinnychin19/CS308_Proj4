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
    
    public Collection<GenericTileWrapper> generateTiles(AuthoringCache ac){
        Collection<GenericTileWrapper> results = new HashSet<GenericTileWrapper>();
        for (String category : Constants.VIEWABLE_CATEGORIES){
            try {
                Collection<SmartJsonObject> wholeCat = ac.getAllCategoryInstances(category);
                for (SmartJsonObject smartObj : wholeCat){
                    GenericTileWrapper gtw = this.convertToTile(smartObj, category);
                    results.add(gtw);
                }
            }
            catch (SmartJsonException e) {
                System.out.println("No objects found for category: " + category);
            }
        }
        
        return results;
    }

    public GenericTileWrapper convertToTile (SmartJsonObject smartObj, String category) {
        String name = "";
        String imgPath = "";
        try {
            name = smartObj.getString(Constants.NAME_LOWERCASE);
            
            Set<Object> JSONKeySet = smartObj.keySet();
            for (Object o : JSONKeySet) {
                    if (((String) o).contains(Constants.IMAGE.toLowerCase())) {
                            String unixStyleTruncatedFilepath = smartObj.getString(((String) o));
                            imgPath = convertToFullFunctionalFilepath(unixStyleTruncatedFilepath);
                    }
            }
        }
        catch (NoStringValueJsonException e) {
            System.out.println("JSON-to-tile conversion failure: " +
            		"name and/or image not found for object " +
            		smartObj.toString());
        }
        
        return new GenericTileWrapper(name, category, imgPath);
    }
    
    private static String convertToFullFunctionalFilepath (String unixStyleTruncatedFilepath) {
        FilepathReformatter fr = FilepathReformatter.getInstance();
        String correctedSeparators = fr.formatForCurrentSystem(unixStyleTruncatedFilepath);
        return System.getProperty(Constants.USER_DIR) + File.separator + correctedSeparators;
    }
}

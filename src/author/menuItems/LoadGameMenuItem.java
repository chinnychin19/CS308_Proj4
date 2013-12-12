package author.menuItems;

import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import util.FileChooserSingleton;
import util.OptionPaneSingleton;
import util.jsonwrapper.SmartJsonObject;
import util.jsonwrapper.jsonexceptions.NoJSONArrayJsonException;
import util.jsonwrapper.jsonexceptions.NoJSONObjectJsonException;
import jsoncache.JSONReader;
import author.AuthorView;
import author.model.AuthoringCache;

import constants.Constants;

/**
 * CreateMapMenuItem extends AbstractMenuItem and is added to the File
 * menu in our AuthorView.  When clicked, it allows the user to load
 * a previously created map, which can then be edited and re-saved.
 * 
 * @author weskpga
 *
 */

@SuppressWarnings("serial")
public class LoadGameMenuItem extends AbstractMenuItem {

    private AuthoringCache myAuthoringCache;
    
	public LoadGameMenuItem(AuthoringCache cache) {
		super(Constants.LOAD_EXISTING_GAME);
		myAuthoringCache = cache;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		handleLoadExistingGame();
	}

	private void handleLoadExistingGame(){
		System.out.println(Constants.CLICKED_LOAD_GAME);
		JFileChooser chooser = FileChooserSingleton.getInstance();
		chooser.setFileFilter(new FileNameExtensionFilter("JSON files", "json"));
		int returnVal = chooser.showOpenDialog(this);
		
		boolean userOk = 
	                OptionPaneSingleton.getInstance().getOk(Constants.RESET_AUTHOR_CACHE_WARNING);

	        if (returnVal == JFileChooser.APPROVE_OPTION && userOk) {
	            //System.out.println("Chose one");
	            AuthorView currentView = myAuthoringCache.getAuthorView();
	            myAuthoringCache.reset();
	            parseFileIntoCache(chooser.getSelectedFile().getAbsolutePath());
	            currentView.updateMenuAndSidebar();
	            //currentView.
	        }
	}

    private void parseFileIntoCache (String path) {
        @SuppressWarnings("static-access")
        JSONObject obj = new JSONReader().getJSON(path);
        try {
            SmartJsonObject smartObj = new SmartJsonObject(obj);
            for (Object s : smartObj.keySet()){
                JSONArray jArr = smartObj.getJSONArray((String) s);
                for (Object j : jArr){
                    myAuthoringCache.add((String) s, (JSONObject) j);                }
                
            }
        }
        catch (NoJSONObjectJsonException e) {
            e.printStackTrace();
        }
        catch (NoJSONArrayJsonException e) {
            e.printStackTrace();
        }        
    }
	
	
}

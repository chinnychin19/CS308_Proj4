package author.model;

import jsoncache.JSONCache;
import jsoncache.JSONException;
import org.json.simple.JSONObject;
import author.AuthorView;
import constants.Constants;

public class AuthoringCache extends JSONCache{
	
    private AuthorView myView;

    public AuthoringCache (AuthorView av) {
        super(Constants.CATEGORIES);
        myView = av;
    }
    
    public AuthoringCache () {
        super(Constants.CATEGORIES);
        myView = null;
    }


    public void add (String category, JSONObject data) {
        super.add(category, data);
        refreshAuthorView();
    }
    
    public void delete (String category, JSONObject data) {
        try {
            super.delete(category, (String) data.get(Constants.NAME));
        }
        catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        refreshAuthorView();
    }

    public void update (String category, JSONObject data) {
        try {
            super.update(category, data);
        }
        catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        refreshAuthorView();
    }
    
    public void reset() {
        myJSON = new JSONObject(); 
        initCategories(Constants.CATEGORIES);
        refreshAuthorView();
    }
    
    public AuthorView getAuthorView() {
        return myView;
    }
    
    private void refreshAuthorView () {
        if (myView != null) {
                myView.updateMenuAndSidebar();
        }
    }  
}

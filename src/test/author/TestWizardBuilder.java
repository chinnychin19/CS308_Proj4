package test.author;

import static org.junit.Assert.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;
import util.jsonwrapper.SmartJsonObject;
import constants.Constants;
import author.AuthorView;
import author.wizard.WizardBuilder;
import author.model.AuthoringCache;


public class TestWizardBuilder {
    
    private WizardBuilder myWizardBuilder;

    protected void setUp() throws Exception
    {
        this.myWizardBuilder = new WizardBuilder("FightingNPC", Constants.PLAYER_JSON, new AuthoringCache(new AuthorView()));
    }
    
    @Test
    public void testAddPanelsFromFile () {
        String filepath = System.getProperty("user.dir") + File.separator + "test-json"+ File.separator +"test_player.json";
        myWizardBuilder.addPanelsFromFile(filepath);
        //assertTrue();
    }

    
}

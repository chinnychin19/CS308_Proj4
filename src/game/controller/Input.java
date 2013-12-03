package game.controller;

import java.util.HashMap;

/**
 * Organizes the statuses of all the directions and key presses so that the model can stay updated.  Calls can be made to see if 
 * a key is pressed or to set a key to being pressed
 * @author rtoussaint
 *
 */

public class Input {
	private HashMap<Enum, Boolean> myInputs;
          
	public Input() {
		 myInputs = new HashMap<Enum, Boolean>();
		 for (InputIndex key : InputIndex.values()){
			 myInputs.put(key, false);
		 }
   }
	/**
	 * Checks to see if the up key is pressed
	 * @return position of key
	 */
	public boolean isKeyUpPressed(){
		return myInputs.get(InputIndex.UP);
	}
	/**
	 * Checks to see if the down key is pressed
	 * @return position of key
	 */
	public boolean isKeyDownPressed(){
		return myInputs.get(InputIndex.DOWN);
	}
	/**
	 * Checks to see if the left key is pressed
	 * @return position of key
	 */
	public boolean isKeyLeftPressed(){
		return myInputs.get(InputIndex.LEFT);
	}
	/**
	 * Checks to see if the right key is pressed
	 * @return position of key
	 */
	public boolean isKeyRightPressed(){
		return myInputs.get(InputIndex.RIGHT);
	}
	/**
	 * Checks to see if the interact key is pressed
	 * @return position of key
	 */
	public boolean isKeyInteractPressed(){
		return myInputs.get(InputIndex.INTERACT);
	}
	/**
	 * Checks to see if the menu key is pressed
	 * @return position of key
	 */
	public boolean isKeyMenuPressed(){
		return myInputs.get(InputIndex.MENU);
	}
	
	/**
         * Checks to see if the menu key is pressed
         * @return position of key
         */
        public boolean isKeyBackPressed(){
                return myInputs.get(InputIndex.BACK);
        }
	/**
	 * Checks to see if any of the direction keys are pressed
	 * @return true if a direction is pressed
	 */
	public boolean isDirectionPressed(){ 
		if(myInputs.get(InputIndex.UP)){
			return true;
		}
		else if(myInputs.get(InputIndex.DOWN)){
			return true;
		}
		else if(myInputs.get(InputIndex.LEFT)){
			return true;
		}
		else if(myInputs.get(InputIndex.RIGHT)){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * Set a specific key to the 'pressed state'
	 * @param val index of which key to set
	 */
	public void setKeyPressed(Enum key){
		if(myInputs.keySet().contains(key)){
			myInputs.put(key, true);
		}
	}
	/**
	 * Set a specific key to the 'unpressed state'
	 * @param val index of which key to set
	 */
	public void setKeyUnpressed(Enum key){
		if(myInputs.keySet().contains(key)){
			myInputs.put(key, false);
		}
	}
	/**
	 * Reset all the inputs to the original states
	 */
	public void resetAllInputs(){
		for (InputIndex key : InputIndex.values()){
			 myInputs.put(key, false);
		 }
	}
	
	public void setInput(InputIndex input, boolean value){
		myInputs.put(input, value);
	}
}

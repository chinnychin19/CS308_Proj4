package game.controller.state;

import game.controller.AbstractMode;

public class TextState extends AbstractState {
	private String myText;
	private AbstractMode myMode;
	public TextState(AbstractMode mode, int x, int y, int w, int h, String text) {
		super("", mode, x, y, w, h);
		myText = text;
		myMode = mode;
		System.out.println(myText);
	}

	@Override
	public void paint(){
		super.paint();
		int x = 15;
        int y = 30;
        myBuffer.drawString(myText, x, y);
	}
	@Override
	protected void onBack() {
		myMode.removeDynamicState(this);
	}

	@Override
	protected void onInteract() {

	}
}

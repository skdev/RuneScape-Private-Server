package net.scapeemulator.game.msg;

public class PlayerOptionMessage extends Message {
	private final String option;
	private final int top, icon, index;
	
	public PlayerOptionMessage(String option, int top, int icon, int index) {
		this.option = option;
		this.top = top;
		this.icon = icon;
		this.index = index;
	}
	
	public String getOption() {
		return option;
	}
	
	public int getTop() {
		return top;
	}
	
	public int getIcon() {
		return icon;
	}
	
	public int getIndex() {
		return index;
	}
}

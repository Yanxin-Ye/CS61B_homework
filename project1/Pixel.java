package project1;

public class Pixel {
	private short red;
	private short green;
	private short blue;
	
	public Pixel() {
		this.red=0;
		this.green=0;
		this.blue=0;
	}
	
	public short getRed() {
		return red;
	}
	
	public short getGreen() {
		return green;
	}
	
	public short getBlue() {
		return blue;
	}
	
	public void setRed(short red) {
		this.red=red;
	}
	
	public void setGreen(short green) {
		this.green=green;
	}

	public void setBlue(short blue) {
		this.blue=blue;
	}
}

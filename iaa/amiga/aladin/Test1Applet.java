package iaa.amiga.aladin;

import javax.swing.JApplet;

public class Test1Applet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long tope = 2000000;
		JApplet var = new JApplet();
		var.show(false);
		var.setVisible(false);
		var.stop();
		var.destroy();
		for(long i = 1; i<tope; i++){
			System.out.print(".");
		}
		
	}

}

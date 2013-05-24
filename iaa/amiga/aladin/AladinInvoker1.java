package iaa.amiga.aladin;

import cds.aladin.Aladin;

public class AladinInvoker1 extends Thread {

	private String script;
	
	private int option;
	
	//path to the place where are the files for testing
	private String pathToMyTestAladin = "/Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/";
	
	
	public AladinInvoker1(int opt ){
		option = opt;
	}
		
	public void runScript(String script){
		String[] args = new String[2];
		
		args[0]="-nogui";
		args[1] = "script="+script;
		Aladin.main(args);
		
	}
	
	public void runScriptURL(String url){
		String[] args = new String[2];
		
		args[0]="-nogui";
		args[1] = "-scriptfile="+url;
		Aladin.main(args);
	}
	
	
	@Override
	public void run(){
				
		if(option == 1){
			String example2 = "get aladin(J,FITS) m1 ;\n save " + pathToMyTestAladin + "resources/m1.jpg; quit";
			System.out.println("Starting option 1");
			runScript(example2);
			System.out.println("Ending option 1");
		}else if(option == 3){
			String scriptMacro ="macro "+ pathToMyTestAladin +"resources/macro_jer.ajs "+ pathToMyTestAladin +"resources/parameters_jer.dat";
			System.out.println("Starting option 3");
			runScript(scriptMacro);
			System.out.println("Ending option 3");
		}else if(option == 4){
			String example2 = "get aladin(J,FITS) m2 ;\n save " + pathToMyTestAladin + "resources/m2.jpg; quit";
			System.out.println("Starting option 4");
			runScript(example2);
			System.out.println("Ending option 4");
		}/*else if(option ==2){
			String scriptpath = "/Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/resources/examplescript.ajs";
			String scriptURL = "file:///Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/resources/examplescript.ajs";
			System.out.println("Starting option 2");
			runScriptURL(scriptpath);
			System.out.println("Ending option 2");
		}*/
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AladinInvoker1 invoker3 = new AladinInvoker1(3);
		AladinInvoker1 invoker4 = new AladinInvoker1(4);
		AladinInvoker1 invoker1 = new AladinInvoker1(1);
		
		//if you uncomment invoker3.start(), make sure that macro_jer.ajs contains a valid path
		//invoker3.start();
		invoker4.start();
		invoker1.start();
		
		System.out.println("The end");
	}

}

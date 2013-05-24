package iaa.amiga.aladin;

import cds.aladin.Aladin;

public class AladinInvoker0 {

	private String script;
	
	private int option;
	
	private String pathToMyTestAladin = "/Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/";
		
	public void runScript(String script){
		//java -jar Aladin.jar -nogui -scriptfile=/Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/resources/examplescript
		String[] args = new String[2];
		
		args[0]="-nogui";
		//args[1]="-scriptfile=file:/Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/resources/examplescript";
		args[1] = "script="+script;
		//args[1] = "script=get SDSSDR7(Title=filter g) NGC1055; contour 4 ; save /Users/julian/Documents/wf4ever/aladin/example & tests/ngc1055.jpg";
		//Aladin aladin = new Aladin();
		Aladin.main(args);
	}
	
	public void runScriptURL(String url){
		//java -jar Aladin.jar -nogui -scriptfile=/Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/resources/examplescript
		String[] args = new String[2];
		
		args[0]="-nogui";
		//args[1]="-scriptfile=file:/Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/resources/examplescript";
		args[1] = "-scriptfile="+url;
		//args[1] = "script=get SDSSDR7(Title=filter g) NGC1055; contour 4 ; save /Users/julian/Documents/wf4ever/aladin/example & tests/ngc1055.jpg";
		//Aladin aladin = new Aladin();
		Aladin.main(args);
	}
	
	

	public void run(int option){
				
		if(option == 1){
			String example2 = "get aladin(J,FITS) m1 ;\n save /Users/julian/Documents/wf4ever/aladin/example & tests/m1.jpg; quit";
			System.out.println("Starting option 1");
			runScript(example2);
			System.out.println("Ending option 1");
		}else if(option ==2){
			String scriptpath = "/Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/resources/examplescript.ajs";
			String scriptURL = "file:///Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/resources/examplescript.ajs";
			System.out.println("Starting option 2");
			runScriptURL(scriptpath);
			System.out.println("Ending option 2");
		}else if(option == 3){
			//String scriptMacro ="macro /Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/resources/Aladin_workflow_script.ajs /Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/resources/Aladin_workflow_params.txt";
			String scriptMacro ="macro "+ pathToMyTestAladin +"resources/macro_jer.ajs "+ pathToMyTestAladin +"resources/parameters_jer.dat";
			System.out.println("Starting option 3");
			runScript(scriptMacro);
			System.out.println("Ending option 3");
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AladinInvoker0 invoker = new AladinInvoker0();
		
		invoker.run(3);
		
		
		System.out.println("The end");
	}

}

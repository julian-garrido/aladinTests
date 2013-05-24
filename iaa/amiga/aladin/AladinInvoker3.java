package iaa.amiga.aladin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;


//import org.purl.wf4ever.astrotaverna.utils.NoExitSecurityManager_;
//import org.purl.wf4ever.astrotaverna.utils.StreamReaderAsync;

/**
 * It runs Aladin scripts and macros. By the time being, Aladin.jar has to be 
 * in an specific folder: This issue should be resolved. 
 * @author Julian Garrido
 * @date 10/04/2013
 *
 */
public class AladinInvoker3 {

	//private String script;
	private String std_out="";
	private String error_out="";
	private int option;
	
	public static final String GUI = "gui";
	public static final String NOGUI = "nogui";
	
	private String ALADINJAR = "/Applications/Aladin.app/Contents/Resources/Java/Aladin.jar";
	//private String Aladinjar = "/Users/julian/Documents/wf4ever/aladin/Aladin.jar";
	//private String ALADINJAR = "/home/julian/Documentos/wf4ever/aladin/Aladin.jar";
	
	//path to the place where are the files for testing
	private String pathToMyTestAladin = "/Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/";
	
	//private static Logger logger = Logger.getLogger(AladinInvoker.class);
	
	public AladinInvoker3(){
	
	}
	
	public AladinInvoker3(int opt ){
		option = opt;
	}
		
	public void runScript(String script, String gui) throws InterruptedException, IOException{
		ProcessBuilder builder;
		if(AladinInvoker3.GUI.compareTo(gui)!=0){
		    builder = new ProcessBuilder("java", "-jar", ALADINJAR, "-nogui", "script="+script);
		}else{
			builder = new ProcessBuilder("java", "-jar", ALADINJAR, "script="+script);
		}
		
		//Map<String, String> environ = builder.environment();

	    Process process;
	    //SecurityManager securityBackup = System.getSecurityManager();
		//System.setSecurityManager(new NoExitSecurityManager_());
		
		try{
		    
			process = builder.start();
		    InputStream is = process.getInputStream();		    
		    StreamReaderAsync outputReader = new StreamReaderAsync(is, "OUTPUT");
		    
		    InputStream eis = process.getErrorStream();
		    StreamReaderAsync errorReader = new StreamReaderAsync(eis, "ERROR");
		    
		    //start the threads
		    outputReader.start();
		    errorReader.start();
	    
	    
		    //System.out.println("Estoy antes del waitfor");
		    int exitValue = process.waitFor();
		    //System.out.println("Estoy despues del waitfor");
		    
		    //is.close();
		    //eis.close();
		    	        
		    this.error_out = errorReader.getResult();
		    this.std_out = outputReader.getResult();
		   
		    //System.out.println("exit value for the process: " + process.exitValue());
		    process.destroy();
		    
		}catch(SecurityException ex){
			System.out.println("Se ha ejecutado exit() en AladinInvoker");
			//logger.error("Se ha ejecutado exit() en AladinInvoker");
		}
		
		//System.setSecurityManager(securityBackup);
	    
		
		    
	    //System.out.println("ERROR: " + this.error_out);
	    //System.out.println("STD: " + this.std_out);
		
		
	}
	
	public String getStd_out() {
		return std_out;
	}

	public String getError_out() {
		return error_out;
	}

	public void runScriptURL(String url, String gui) throws InterruptedException, IOException{
		ProcessBuilder builder;
		if(AladinInvoker3.GUI.compareTo(gui)!=0){
		
			builder = new ProcessBuilder("java", "-jar", ALADINJAR, "-nogui", "-scriptfile="+url);
		}else{
			builder = new ProcessBuilder("java", "-jar", ALADINJAR, "-scriptfile="+url);
		}
		
		////Map<String, String> environ = builder.environment();

	    Process process;

		process = builder.start();
	
	    InputStream is = process.getInputStream();		    
	    StreamReaderAsync outputReader = new StreamReaderAsync(is, "OUTPUT");
	    
	    InputStream eis = process.getErrorStream();
	    StreamReaderAsync errorReader = new StreamReaderAsync(eis, "ERROR");
	    
	    //start the threads
	    outputReader.start();
	    errorReader.start();
	    
	    int exitValue = process.waitFor();
	    
	    //is.close();
	    //eis.close();
	    
	    this.error_out = errorReader.getResult();
	    this.std_out = outputReader.getResult();
	    
		    		
	}
	
	public void runMacro(String scriptURL, String parametersURL, String gui) throws InterruptedException, IOException{
		
		String macroScript = "macro "+ scriptURL + " " + parametersURL; 
		System.out.println("Calling Aladin script: "+ macroScript);
		runScript(macroScript, gui);
		
	}
	

	public void run() throws IOException{
		try {		
			/*
			if(option == 1){
				String example2 = "get aladin(J,FITS) m1 ;\n save /Users/julian/Documents/wf4ever/aladin/exampleTests/m1.jpg; quit";
				System.out.println("Starting option 1");
				runScript(example2, "gui");				
				System.out.println("Ending option 1");
			}else if(option ==2){
				String scriptpath = "/Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/resources/examplescript.ajs";
				String scriptURL = "file:///Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/resources/examplescript.ajs";
				System.out.println("Starting option 2");
				runScriptURL(scriptpath, "nogui");
				System.out.println("Ending option 2");
			}else if(option == 3){
				String scriptMacro ="macro /Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/resources/Aladin_workflow_script.ajs /Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/resources/Aladin_workflow_params.txt";
				System.out.println("Starting option 3");
				runScript(scriptMacro, "nogui");
				System.out.println("Ending option 3");
			}else if(option == 4){
				System.out.println("Starting option 4");
				runMacro("/Users/julian/src/astrotaverna/Image-activity/src/test/resources/Aladin_workflow_script.ajs", "/Users/julian/src/astrotaverna/Image-activity/src/test/resources/Aladin_workflow_params.txt", "nogui");
				System.out.println("Ending option 4");
			}else if(option == 5){
				String example2 = "get aladin(J,FITS) m1 ;\n save /home/julian/Documentos/wf4ever/aladin/m1.jpg; quit";
				System.out.println("Starting option 5");
				runScript(example2, "gui");				
				System.out.println("Ending option 5");
			}else if(option == 6){
				System.out.println("Starting option 6");
				runMacro("file:///home/julian/Documentos/wf4ever/aladin/Aladin_workflow_script.ajs", "file:///home/julian/Documentos/wf4ever/aladin/Aladin_workflow_params.txt", "nogui");
				System.out.println("Ending option 6");
			}
			*/
			if(option == 1){
				String example2 = "get aladin(J,FITS) m1 ;\n save " + pathToMyTestAladin + "resources/m1.jpg; quit";
				System.out.println("Starting option 1");
				runScript(example2,"nogui");
				System.out.println("Ending option 1");
			}else if(option == 3){
				String scriptMacro ="macro "+ pathToMyTestAladin +"resources/macro_jer.ajs "+ pathToMyTestAladin +"resources/parameters_jer.dat";
				System.out.println("Starting option 3");
				runScript(scriptMacro,"nogui");
				System.out.println("Ending option 3");
			}else if(option == 4){
				String example2 = "get aladin(J,FITS) m2 ;\n save " + pathToMyTestAladin + "resources/m2.jpg; quit";
				System.out.println("Starting option 4");
				runScript(example2,"nogui");
				System.out.println("Ending option 4");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		AladinInvoker3 invoker4 = new AladinInvoker3(4);
		AladinInvoker3 invoker3 = new AladinInvoker3(3);
		AladinInvoker3 invoker1 = new AladinInvoker3(1);
		//AladinInvoker3 invoker5 = new AladinInvoker3(5);
		//AladinInvoker3 invoker6 = new AladinInvoker3(6);
		
		//invoker1.run();
		//invoker2.run();
		//invoker3.run();
		invoker4.run();
		//invoker5.run();
		//invoker6.run();
		System.out.println("The end");
	}

}

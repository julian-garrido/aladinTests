package iaa.amiga.aladin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import cds.aladin.Aladin;

public class AladinInvoker2 extends Thread {

	private String script;
	
	private int option;
	
	public AladinInvoker2(int opt ){
		option = opt;
	}
		
	public void runScript(String script) throws InterruptedException{
		//java -jar Aladin.jar -nogui -scriptfile=/Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/resources/examplescript
		String[] args = new String[2];
		
		args[0]="-nogui";
		//args[1]="-scriptfile=file:/Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/resources/examplescript";
		args[1] = "script="+script;
		
		ProcessBuilder builder = new ProcessBuilder("java", "-jar", "/Users/julian/Documents/wf4ever/aladin/Aladin.jar", "-nogui", "script="+script); 
		
		Map<String, String> environ = builder.environment();

	    Process process;
		try {
			//process = builder.start();
		    //InputStream is = process.getInputStream();
		    //InputStreamReader isr = new InputStreamReader(is);
		    //BufferedReader br = new BufferedReader(isr);
		    //String line;
		    //while ((line = br.readLine()) != null) {
		    //  System.out.println("----- "+line);
		    //}
		    
			process = builder.start();
			
		    InputStream is = process.getInputStream();		    
		    //InputStreamReader isr = new InputStreamReader(is);
		    //BufferedReader br = new BufferedReader(isr);
		    StreamReaderAsync outputReader = new StreamReaderAsync(is, "OUTPUT");
		    
		    InputStream eis = process.getErrorStream();
		    //InputStreamReader eisr = new InputStreamReader(eis);
		    //BufferedReader ebr = new BufferedReader(eisr);
		    StreamReaderAsync errorReader = new StreamReaderAsync(eis, "ERROR");
		    
		    //start the threads
		    outputReader.start();
		    errorReader.start();
		    
		    int exitValue = process.waitFor();
		    
		    //String line;
		    //String eline;
		    
		    //while ((line = br.readLine()) != null || (eline = ebr.readLine()) != null) {
		    //  if(line != null)
		    //	System.out.println("----- "+line);
		    //  if(eline != null)
		    //	  System.out.println("----- "+eline);
		    //}
		    
		    System.out.println("OUTPUT: "+ outputReader.getResult());
		    System.out.println("ERROR: "+ errorReader.getResult());
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception!!!!!!");
			e.printStackTrace();
		} 
		
		
	}
	
	public void runScriptURL(String url) throws InterruptedException{
		//java -jar Aladin.jar -nogui -scriptfile=/Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/resources/examplescript
		String[] args = new String[2];
		
		args[0]="-nogui";
		//args[1]="-scriptfile=file:/Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/resources/examplescript";
		args[1] = "-scriptfile="+url;
		
		//ProcessBuilder builder = new ProcessBuilder("java", "-jar", "/Users/julian/Documents/wf4ever/aladin/Aladin.jar", "-nogui", "-scriptfile="+url); 
		ProcessBuilder builder = new ProcessBuilder("java", "-jar", "/Users/julian/Documents/wf4ever/aladin/Aladin.jar", "-scriptfile="+url);
		
		Map<String, String> environ = builder.environment();

	    Process process;
		try {
			process = builder.start();
		
		    InputStream is = process.getInputStream();		    
		    //InputStreamReader isr = new InputStreamReader(is);
		    //BufferedReader br = new BufferedReader(isr);
		    StreamReaderAsync outputReader = new StreamReaderAsync(is, "OUTPUT");
		    
		    InputStream eis = process.getErrorStream();
		    //InputStreamReader eisr = new InputStreamReader(eis);
		    //BufferedReader ebr = new BufferedReader(eisr);
		    StreamReaderAsync errorReader = new StreamReaderAsync(eis, "ERROR");
		    
		    //start the threads
		    outputReader.start();
		    errorReader.start();
		    
		    int exitValue = process.waitFor();
		    
		    //String line;
		    //String eline;
		    
		    //while ((line = br.readLine()) != null || (eline = ebr.readLine()) != null) {
		    //  if(line != null)
		    //	System.out.println("----- "+line);
		    //  if(eline != null)
		    //	  System.out.println("----- "+eline);
		    //}
		    
		    System.out.println("OUTPUT: "+ outputReader.getResult());
		    System.out.println("ERROR: "+ errorReader.getResult());
		    
			//process.waitFor();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception!!!!!!");
			e.printStackTrace();
		}// catch (InterruptedException e) {
		//	// TODO Auto-generated catch block
		///	e.printStackTrace();
		//}
	    System.out.println("Program terminated!");
		
	}
	
	
	@Override
	public void run(){
		try {		
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
				String scriptMacro ="macro /Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/resources/Aladin_workflow_script.ajs /Users/julian/workspaces/aladinTest_ws/myAladin/myTestSRC/iaa/amiga/aladin/resources/Aladin_workflow_params.txt";
				System.out.println("Starting option 3");
				runScript(scriptMacro);
				System.out.println("Ending option 3");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		//AladinInvoker2 invoker3 = new AladinInvoker2(3);
		AladinInvoker2 invoker2 = new AladinInvoker2(2);
		//AladinInvoker2 invoker1 = new AladinInvoker2(1);
		
		//invoker3.start();
		invoker2.start();
		//invoker1.start();
		
		System.out.println("The end");
	}

}

package iaa.amiga.aladin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamReaderAsync  extends Thread{

    InputStream is;
    String type;
    String result = "";
    
    public StreamReaderAsync(InputStream is, String type)
    {
        this.is = is;
        this.type = type;
    }
    
    public void run()
    {
        try
        {
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line=null;
            while ( (line = br.readLine()) != null)
            	if(line !=null)
            		result += line + "\n";    
        } catch (IOException ioe){
            ioe.printStackTrace();  
        }
    }
    
    String getResult(){
    	return result;
    }
}

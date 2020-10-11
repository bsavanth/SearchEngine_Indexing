import java.io.*;
import java.util.*;


public class Pass1 {
    

    public static void main(String[] args) throws Exception {

		//global hashtable(our dict file),  Actual size is (3 * 700000)
        HashTable globalhash = new HashTable(70000);

		// Create an output file if you dont have one
        File output = new File(args[1]);
        if (!output.exists()) {
             output.mkdir();
             }
        
       // Reading tempoutput files
        File input = new File(args[0]);
        // Storing files in a file list
        File[] List_Of_Files = input.listFiles();
        //iterate each file from the file list
        for (File file : List_Of_Files) {
        	// file exists and not null
            if (file.isFile()) {
				
				
				
                File output1 = null;
                //local hashtable (Actual size will be three times this size)
                HashTable localhash = new HashTable(3000);
                //local list - Arraylist
                ArrayList<String> tokenlist = new ArrayList<>();
                
				PrintWriter writer=null;
				//Always wrap file reader in buffered reader for thread safe operations
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    
                    //Creates output file in output directory with same name as it is in temmpout
                    output1 = new File(args[1] + "/"+ file.getName());
                    
                    writer = new PrintWriter(output1);
                    
                    //Read each line in the file (unsorted tokens)
                    String line;
                    while((line = br.readLine()) != null){
                        if(!line.equals("")){
                        	// Send tokens to Hashtable class for insertion(It will handle rest)
                            localhash.insert(line);
                        }

                        if(!tokenlist.contains(line)){
                        	//Calculate the vocabulary of the file]
                            tokenlist.add((line.toString()));
                        }

                    }

					//Sort vocabulary by term in ascending order, Crucial for pass2
                    Collections.sort(tokenlist);

                    //iterate each term in vocabulary list 
                    for(String str : tokenlist){
						
						//write in the file, term and term frequency
						//HashTable class already calculated term frequencies
                        writer.println(str + " "+ localhash.getName(str));
                    }
                    
                    //Dump local hashtable into global hash, grab the index of key of local hash table, put it in global hashtable on the same index

                    for(int i=0; i<localhash.getSize(); i++){
                        String str = localhash.getaddr(i);
                        if(!str.equals("")){
                            globalhash.insert(str);
                        }
                    }
                    
                     //flush localhash for next file iteration
        			localhash.clear();

                }
				//Flush and close to copy contents from buffer onto file
                finally {
                    if(writer != null){
                    	//dump content onto main memory which is file
                        writer.flush();
                        writer.close();

                    }

                }
            }
        }

       
	//call pass2, pass global hashtable and the output file we generated
         Pass2 two = new Pass2(globalhash, args[1]);

    }


}




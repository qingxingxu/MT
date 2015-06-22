package edu.buaa.nlp.xqx;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String,String> hashMap = new HashMap<String,String>();
		
		Map<String,String> trainMap = new HashMap<String,String>(); 
		Map<String,String> testMap = new HashMap<String,String>(); 
		
		File chiFile = new File("D:\\chi.txt");
		File korFile = new File("D:\\kor.txt");	
		readMap(hashMap, chiFile, korFile);
		
		System.out.println(hashMap.size());
		
		int i=0;
		for (Map.Entry<String, String> entry : hashMap.entrySet()) {
			if(i % 60 == 23 && i<64050 && i>4000 )
				testMap.put(entry.getKey().trim(), entry.getValue().trim());
			else {
				trainMap.put(entry.getKey().trim(), entry.getValue().trim());
			}
			System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
			i++;
		}
		
		
		chiFile = new File("D:\\chi_train.txt");
		korFile = new File("D:\\kor_train.txt");
		saveMap(trainMap, chiFile, korFile);
		
		chiFile = new File("D:\\chi_tune.txt");
		korFile = new File("D:\\kor_tune.txt");
		saveMap(testMap, chiFile, korFile);
		
		System.out.println("OK!");
	}

	public static void readMap(Map<String,String> hashMap,File chiFile,File korFile){
		try {
			BufferedReader bufferedReader_chi=new BufferedReader(new InputStreamReader(new FileInputStream(chiFile)));
			BufferedReader bufferedReader_kor=new BufferedReader(new InputStreamReader(new FileInputStream(korFile)));
			String temp_chi=null;
			String temp_kor=null;
			while( (temp_chi=bufferedReader_chi.readLine())!=null)
			{
				temp_kor=bufferedReader_kor.readLine();
				//if( !temp_kor.contains(" ") && temp_kor.length()>8 )
				//	continue;
				hashMap.put(temp_chi, temp_kor);
			}
			
		}catch(FileNotFoundException e) {	
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void saveMap(Map<String,String> hashMap,File chiFile,File korFile){	
		OutputStreamWriter outputStreamWriter_chi=null;
		OutputStreamWriter outputStreamWriter_kor=null;
		try {
			outputStreamWriter_chi=new OutputStreamWriter(new FileOutputStream(chiFile,true), "UTF-8");
			outputStreamWriter_kor=new OutputStreamWriter(new FileOutputStream(korFile,true), "UTF-8");
			BufferedWriter bufferedWriter_chi=new BufferedWriter(outputStreamWriter_chi);
			BufferedWriter bufferedWriter_kor=new BufferedWriter(outputStreamWriter_kor);
			
			for (Map.Entry<String, String> entry : hashMap.entrySet()) {
				bufferedWriter_chi.write(entry.getKey().trim()+"\n");
				bufferedWriter_kor.write(entry.getValue().trim()+"\n");
				System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
			}
			
		   /* for(int i=0;i<hashMap.size();i++){
		    	bufferedWriter1.write(outList.get(i).trim()+"\n");
			}*/
			//清空缓存
			bufferedWriter_chi.flush();
			bufferedWriter_kor.flush();
		
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

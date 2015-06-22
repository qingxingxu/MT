package edu.buaa.nlp.utils;

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
import java.util.List;

public class MyFile {
	
	public static void readTXT(File inFile ,List<String> chiList ){
		try {
			BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(inFile),"UTF-8"));
			String temp=null;
			while( (temp=bufferedReader.readLine())!=null){
				chiList.add(temp);
			}
		}catch(FileNotFoundException e) {	
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void saveTXT(File outFile,List<String> outList){	
		OutputStreamWriter outputStreamWriter1=null;
		try {
			outputStreamWriter1=new OutputStreamWriter(new FileOutputStream(outFile,true), "UTF-8");
			BufferedWriter bufferedWriter1=new BufferedWriter(outputStreamWriter1);
		    for(int i=0;i<outList.size();i++){
		    	bufferedWriter1.write(outList.get(i).trim()+"\n");
			}
			//清空缓存
			bufferedWriter1.flush();
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void saveTXT(File chiFile,List<String> chiList,File korFile,List<String> korList){	
		OutputStreamWriter outputStreamWriter1=null;
		OutputStreamWriter outputStreamWriter2=null;
		try {
			outputStreamWriter1=new OutputStreamWriter(new FileOutputStream(chiFile,true), "UTF-8");
			BufferedWriter bufferedWriter1=new BufferedWriter(outputStreamWriter1);
			outputStreamWriter2=new OutputStreamWriter(new FileOutputStream(korFile,true), "UTF-8");
			BufferedWriter bufferedWriter2=new BufferedWriter(outputStreamWriter2);
			
		    for(int i=0;i<chiList.size();i++){
		    	bufferedWriter1.write(chiList.get(i).trim()+"\n");
		    	bufferedWriter2.write(korList.get(i).trim()+"\n");
			}
			//清空缓存
			bufferedWriter1.flush();
			bufferedWriter2.flush();
		
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

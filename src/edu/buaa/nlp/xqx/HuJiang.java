package edu.buaa.nlp.xqx;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import edu.buaa.nlp.utils.ParaToSen;


public class HuJiang {
	
	public static void main(String[] args) {
		
		Map<String, String> strsMap = new HashMap<String, String>();
		
		File filePath=new File("D:\\hujiang20131216"); //"C:\\Users\\xingxing\\Desktop\\yy");
		File[] files=filePath.listFiles();
		for(int i=0;i<files.length;i++){
			List<String> chsList = new LinkedList<String>();
			List<String> krsList = new LinkedList<String>();
			XML.readXML(files[i].getAbsolutePath(),chsList,krsList);
			for(int j=0;j<chsList.size();j++){
				HuJiang.Judge(chsList.get(j),krsList.get(j),strsMap);
			}
		}
		System.out.println("完成了");
	}
	
	
	public static void Judge(String ch,String kr,Map<String, String> strsMap){
		List <String> chList = ParaToSen.GetSenChi(ch);
		List <String> krList = ParaToSen.GetSenKor(kr);
		if(chList.size() == krList.size()){
			saveTXT(new File("E:\\chi.txt"), chList, new File("E:\\kor.txt"), krList,strsMap);
		}
		else if(krList.size()==1) {
			saveTXT(new File("E:\\chi.txt"), chList, new File("E:\\kor.txt"), krList,strsMap);
		}
	}
	
	
	public static void saveTXT(File chiFile,List<String> chiList,File korFile,List<String> korList,Map<String, String> strsMap){	
		OutputStreamWriter outputStreamWriter1=null;
		OutputStreamWriter outputStreamWriter2=null;
		try {
			outputStreamWriter1=new OutputStreamWriter(new FileOutputStream(chiFile,true), "UTF-8");
			BufferedWriter bufferedWriter1=new BufferedWriter(outputStreamWriter1);
			outputStreamWriter2=new OutputStreamWriter(new FileOutputStream(korFile,true), "UTF-8");
			BufferedWriter bufferedWriter2=new BufferedWriter(outputStreamWriter2);
			
		    for(int i=0;i<korList.size();i++){
		    	
		    	if(korList.get(i).length()>=5 &&!strsMap.containsKey(korList.get(i))){
		    		strsMap.put(korList.get(i), i+"");
			    	bufferedWriter1.write(chiList.get(i).trim()+"\n");
			    	bufferedWriter2.write(korList.get(i).trim()+"\n");
		    	}
		    	
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

package edu.buaa.nlp.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Wiki {

	/**
	 * 从wiki中获取平行双语术语
	 * @param inputChiList
	 * @param inputKorList
	 * @param chiList
	 * @param korList
	 */
	public static void getWiki(List<String>inputChiList,List<String>inputKorList,List<String> chiList,List<String>korList){
		Map<String, String> chiMap = new HashMap<String, String>();
		Map<String, String> korMap = new HashMap<String, String>();
		
		// 处理zh串
		for(int i=0;i<inputChiList.size();i++){
			String chiString = inputChiList.get(i);
			String regEx = "\\((\\d+),'zh','(.*?)'\\)"; //汉字unicode编码范围  
			Pattern  pat = Pattern.compile(regEx);  
			Matcher mat = pat.matcher( chiString );  
	      	while( mat.find() ) {
	      		chiMap.put(mat.group(1), mat.group(2));
	      	}
	    }
		
		//处理zh-classical
		for(int i=0;i<inputChiList.size();i++){
			String chiString = inputChiList.get(i);
			String regEx = "\\((\\d+),'zh-classical','(.*?)'\\)"; //汉字unicode编码范围  
			Pattern  pat = Pattern.compile(regEx);  
			Matcher mat = pat.matcher( chiString );  
	      	while( mat.find() ) {
	      		chiMap.put(mat.group(1), mat.group(2));
	      	}
	    }
		
		//处理韩文
		for(int i=0;i<inputKorList.size();i++){
			String korString = inputKorList.get(i);
			String regEx = "\\((\\d+),\\d+,'(.*?)',"; //韩语unicode编码范围  
			Pattern  pat = Pattern.compile(regEx);  
			Matcher mat = pat.matcher( korString );  
	      	while( mat.find() ) {
	      		korMap.put(mat.group(1), mat.group(2));
	      	}
	    }
		
		
		for (Map.Entry<String, String> entry : chiMap.entrySet()) {
			   System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
			   
			   if(entry.getValue() == null || entry.getValue().equals("") || korMap.get(entry.getKey()) == null ||
					   korMap.get(entry.getKey()).equals("")){
				   continue;
			   }
			   
			   chiList.add(entry.getValue().replaceAll("Category:", "").replaceAll("Template:", ""));
			   korList.add(korMap.get(entry.getKey()).replaceAll("_", " "));
		}
		System.out.println("\t"+chiMap.size()+"\t"+korMap.size());
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

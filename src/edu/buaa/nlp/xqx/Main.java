package edu.buaa.nlp.xqx;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;
import taobe.tec.jcc.JChineseConvertor;
import edu.buaa.nlp.util.Utility2;
import edu.buaa.nlp.utils.Chinese;
import edu.buaa.nlp.utils.Korean;
import edu.buaa.nlp.utils.MyFile;
import edu.buaa.nlp.utils.ParaToSen;
import edu.buaa.nlp.utils.Wiki;


public class Main {

	public Main() {
		
	}

	public static void main(String[] args) throws Exception
	{
		
		String inputChi="D:\\wike\\kowiki-20140614-langlinks.sql\\kowiki-20140614-langlinks.sql";
		String inputKor="D:\\wike\\kor_source.txt";	
		String outputChi="D:\\chi_source.txt";
		String outputKor="D:\\wike\\kor_tok.txt";
		
		File inputChiFile = null;
		File inputKorFile = null;
		File chiFile = null;
		File korFile = null;
		
		List<String> inputChiList =new ArrayList<String>();
		List<String> inputKorList=new ArrayList<String>();
		List<String> chiList =new ArrayList<String>();
		List<String> korList=new ArrayList<String>();
		
		inputChiFile=new File(inputChi);
		inputKorFile=new File(inputKor);
		chiFile=new File(outputChi);
		korFile=new File(outputKor);
		
		try
		{
			Main main=new Main();
			
			// 读入文件信息
			MyFile.readTXT(inputChiFile,inputChiList);
			MyFile.readTXT(inputKorFile,inputKorList);

			Wiki.getWiki(inputChiList, inputKorList, chiList, korList);
			
			//存储文件
			MyFile.saveTXT(chiFile, chiList);
			MyFile.saveTXT(korFile, korList);
			//main.saveTXT(chiFile,chiList,korFile,korList);
		}catch (Exception ex){
			ex.printStackTrace();
		} 
	    System.out.println("程序执行完毕!!");
	    
	}
	
	
	

	
	//   去除　[]|
	public void SQL2seg(List<String > inputChiList,List<String> chiList){
		    JChineseConvertor jcc = null;
		    try {
				jcc = JChineseConvertor.getInstance();
			} catch (IOException e) {
				e.printStackTrace();
			}
			for(int i=0;i<inputChiList.size();i++){
				String clean = jcc.t2s(Utility2.convertVietHTMLCode( Utility2.convertHTMLCode(inputChiList.get(i).replaceAll("<br/>", " "))))
								.replaceAll("	", " ").replaceAll("/", " ").replaceAll("[' ']{2,}", " ").trim();
				if( !clean.equals("") && clean.length() >= 3 ){
					chiList.add(clean);
					System.out.println(clean);
				}
			}
	}
	

	/*
	 * 
	 */
	public void judgeChi_kor(List<String > inputChiList,List<String> inputKorList,List<String > chiList,List<String> korList){
	
		for(int i=0;i<inputChiList.size();i++){
			if(inputChiList.get(i).length() <= 150 &&inputKorList.get(i).length() <= 150 && Chinese.isChinese(inputChiList.get(i)) && Korean.isKoreanCharacter(inputKorList.get(i)) ){
				
				String regEx="[a-z@A-Z]"; // 
				Pattern pat=Pattern.compile(regEx);  
				Matcher mat=pat.matcher(inputChiList.get(i));  
				String clean = mat.replaceAll("");
				chiList.add(clean.trim());
				
				mat=pat.matcher(inputKorList.get(i));  
				clean = mat.replaceAll("");
				korList.add(clean.trim());
			}
		}
	}

}
 

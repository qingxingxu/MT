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
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhraseTable {

	//对翻译模型中添加手动短语对齐
	public static void addTerm(List<String > inputChiList,List<String > inputKr,List<String > inputCn,List<String> korList){		
				for(int i = 0;i < inputKr.size();i ++){		
					String align = inputChiList.get(i);
					String korString = inputKr.get(i);
					String chiString = inputCn.get(i);	
					korList.add(korString + " ||| "+ chiString+" ||| 1 1 1 1 ||| "+align+" ||| 1 1 1 |||");
				}
	}
	
	
	// clean rule-table
	public static void rule_clean(File inFile,File outFile){
			
			OutputStreamWriter outputStreamWriter1=null;
			BufferedWriter bufferedWriter1 = null;
			try {
				outputStreamWriter1=new OutputStreamWriter(new FileOutputStream(outFile,true), "UTF-8");
				bufferedWriter1=new BufferedWriter(outputStreamWriter1);
				BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(inFile)));
				String temp=null;
				while( (temp=bufferedReader.readLine())!=null)
				{
					String str = temp;
					String[] parts = str.split("\\|\\|\\|");
					System.out.println(str);
					
					String korString = parts[0].replaceAll("\\[X\\]", " ");
					String chnString = parts[1].replaceAll("\\[X\\]", " ");
					String regEx = "[\\[\\]]";  
					Pattern pat = Pattern.compile(regEx);  
					Matcher mat1 = pat.matcher(korString); 
					Matcher mat2 = pat.matcher(chnString); 
					boolean rs1 = mat1.find(); 
					boolean rs2 = mat2.find();
					System.out.println(korString+"\t"+chnString);
					if( rs1==false && rs2==false ){
						//chiList.add(str);
					    bufferedWriter1.write(str.trim()+"\n");	
					}
					
					
					/*String regEx = "[~,，\\.。;；\\？?!！“”\"‘’':：、<>《》（）()#]";  
					Pattern pat = Pattern.compile(regEx);  
					Matcher mat1 = pat.matcher(parts[0]); 
					Matcher mat2 = pat.matcher(parts[1]); 
					boolean rs1 = mat1.find(); 
					boolean rs2 = mat2.find();
					if( (rs1 == false && rs2==false) ||  (rs1 == true && rs2==true && parts[0].trim().length()==1 && parts[1].trim().length()==1) ){
						//chiList.add(str);
					    bufferedWriter1.write(str.trim()+"\n");	
					}
					 */
					//清空缓存
					bufferedWriter1.flush();
				}
				
			}catch(FileNotFoundException e) {	
				e.printStackTrace();
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	
	// clean PhraseTable 主要是出去带标点的短语
	public static void PhraseTable_clean(File inFile,File outFile){
		//for(int i=0;i<inputChiList.size();i++){
		OutputStreamWriter outputStreamWriter1=null;
		BufferedWriter bufferedWriter1 = null;
		try {
			outputStreamWriter1=new OutputStreamWriter(new FileOutputStream(outFile,true), "UTF-8");
			bufferedWriter1=new BufferedWriter(outputStreamWriter1);
			BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(new FileInputStream(inFile)));
			String temp=null;
			while( (temp=bufferedReader.readLine())!=null)
			{
				String str = temp;
				String[] parts = str.split("\\|\\|\\|");
				System.out.println(str);
				String regEx = "[~,，\\.。;；\\？?!！“”\"‘’':：、<>《》（）()#]";  
				Pattern pat = Pattern.compile(regEx);  
				Matcher mat1 = pat.matcher(parts[0]); 
				Matcher mat2 = pat.matcher(parts[1]); 
				boolean rs1 = mat1.find(); 
				boolean rs2 = mat2.find(); 
				if( (rs1 == false && rs2==false) ||  
						(rs1 == true && rs2==true && parts[0].trim().length()==1 && parts[1].trim().length()==1) ){
					//chiList.add(str);
				    bufferedWriter1.write(str.trim()+"\n");	
				}
				//清空缓存
				bufferedWriter1.flush();
			}	
		}catch(FileNotFoundException e) {	
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}		
		//}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

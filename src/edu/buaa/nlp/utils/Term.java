package edu.buaa.nlp.utils;

import java.util.LinkedList;
import java.util.List;

public class Term {

	
	   
	
	    //  
		public static void getTerm_5(List<String > inputChiList,List<String > inputKorList,List<String> chiList){
				for(int i=0; i<inputChiList.size() ;i ++){		
					//chiList.add(inputKorList.get(i)+"<>"+inputChiList.get(i));	
					chiList.add(inputChiList.get(i));
					System.out.println(inputChiList.get(i));
				}
		}

		//  
		public static void getTerm_4(List<String > inputChiList,List<String> chiList){
				List<String>  list = new LinkedList<String>();
				for(int i=0; i<inputChiList.size() ;i ++){
					if(inputChiList.get(i).trim().equals("")){
						for(int j=0;j<list.size()/2;j++){
							chiList.add(list.get(j)+"<>"+list.get(j+list.size()/2));
						}
						list.clear();
					}
					else {
						list.add(inputChiList.get(i));
					}
				}
		}
		
		 // 
		public static void getTerm_3(List<String > inputChiList,List<String> chiList){
				for(int i=0;i<inputChiList.size();i ++ ){
					String string = inputChiList.get(i).replaceAll(" {2,}", " ").trim();
					char[] chars = string.toCharArray();
					System.out.println(string);
					for(int j=1;j<chars.length && j+1<chars.length ;j++){
						System.out.println(chars[j-1]+"<>"+chars[j]+"<>"+chars[j+1]);
						if( (isChinese(chars[j-1]) ) && chars[j]==' ' 
							&& ( (chars[j+1]>='a'&&chars[j+1]<='z')|| (chars[j+1]>='A'&&chars[j+1]<='Z') ) ){
							String chiString = string.substring(0, j);
							String engString = null;
							if(chars[chars.length-1]=='.')
								engString = string.substring(j, chars.length-1);
							else {
								engString = string.substring(j, chars.length);
							}
							System.out.println("kor"+engString);
							chiList.add(engString.trim()+"<>"+chiString.replaceAll("\\s",""));
							break;
						}else if( (isChinese(chars[j]) )  
							&& ( (chars[j+1]>='a'&&chars[j+1]<='z')|| (chars[j+1]>='A'&&chars[j+1]<='Z') )  ){
							String chiString = string.substring(0, j);
							String engString = null;
							System.out.println("chi2"+chiString);
							if(chars[chars.length-1]=='.')
								engString = string.substring(j, chars.length-1);
							else {
								engString = string.substring(j, chars.length);
							}
							chiList.add(engString.trim()+"<>"+chiString.replaceAll("\\s",""));
							break;
						}
					}
				}
		}
		
		//  
		public static void getTerm_2(List<String > inputChiList,List<String> chiList){
				for(int i=0;i<inputChiList.size();i ++ ){
					String string = inputChiList.get(i).trim();
					char[] chars = string.toCharArray();
					for(int j=1;j<chars.length && j+1<chars.length ;j++){
						if( (Character.isLetter(chars[j-1])||chars[j-1]==')' ) && chars[j]==' ' 
							&& isChinese(chars[j+1]) ){
							String engString = string.substring(0, j);
							String chiString = null;
							if(chars[chars.length-1]=='.')
								chiString = string.substring(j, chars.length-1);
							else {
								chiString = string.substring(j, chars.length);
							}
							chiList.add(engString.trim()+"<>"+chiString.trim());
							break;
						}else if( (Character.isLetter(chars[j])||chars[j]==')' )  
							&& isChinese(chars[j+1]) ){
							String engString = string.substring(0, j);
							String chiString = null;
							if(chars[chars.length-1]=='.')
								chiString = string.substring(j, chars.length-1);
							else {
								chiString = string.substring(j, chars.length);
							}
							chiList.add(engString.trim()+"<>"+chiString.trim());
							break;
						}
					}
				}
		}
		
		
		private static boolean isChinese(char c) {
	        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
	        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
	                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
	                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
	                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
	            return true;
	        }
	        return false;
	    }
		  //   
		public static void getTerm_1(List<String > inputChiList,List<String> chiList){
				for(int i=0;i<inputChiList.size();i += 2){
					chiList.add(inputChiList.get(i)+"<>"+inputChiList.get(i+1));
				}
		}
		
	
		public static void main(String[] args) {
			// TODO Auto-generated method stub
	
		}

}

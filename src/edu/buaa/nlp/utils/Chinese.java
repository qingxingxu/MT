package edu.buaa.nlp.utils;

import java.util.List;

public class Chinese {

	
	// GENERAL_PUNCTUATION 判断中文的“号  
    // CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号  
    // HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号  
    private static final boolean isChinese(char c) {  
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
            return true;  
        }  
        return false;  
    }  
    
    public static final boolean isChinese(String strName) {  
        char[] ch = strName.toCharArray();  
        for (int i = 0; i < ch.length; i++) {  
            char c = ch[i];  
            if (isChinese(c)) {  
                return true;  
            }  
        }  
        return false;  
    }  
    
    
    
	
	//  汉语分句
	public static void toSen(List<String > inputChiList,List<String> chiList){
		for(int i=0;i<inputChiList.size();i++){
			List<String> sentList = ParaToSen.GetSenChi(inputChiList.get(i));
			for(int j=0;j<sentList.size();j++)
				chiList.add(sentList.get(j).trim());
		}
	}
	
	// 汉语分词后 处理为 按字分开
	public static void toWord(List<String > inputChiList,List<String> chiList){
		for(int i=0;i<inputChiList.size();i++){
			String inputString = inputChiList.get(i);
			inputString = inputString.replaceAll("\\s", "");
			inputString = inputString.replaceAll("", " ");
			chiList.add(inputString);
		}
	}
	
	
	/*
	 *   把词典分隔符变为<>
	 */
	public void dic(List<String > inputChiList,List<String> chiList){
		for(int i=0;i<inputChiList.size();i++){
			String inputString = inputChiList.get(i);
			int index = inputString.indexOf("\t");
			if(index==-1)
				continue;
			System.out.println(inputString);
			String chiString = inputString.substring(0, index);
			String korString = inputString.substring(index+1);
			chiList.add(korString+" <> "+chiString);
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

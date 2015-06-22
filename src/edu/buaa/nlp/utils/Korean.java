package edu.buaa.nlp.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Korean {

	
	public static void tokenition_kor(List<String> inputKorList,List<String> korList){
		int j=0;
		for(int i=0;i<inputKorList.size();i++){
			String korString=inputKorList.get(i);
		//	if(chiString.trim().equals("") || korString.trim().equals("")){
		//		continue; 
		//	}
			String clean  = korString.replaceAll(",", " , ").replaceAll("'", " ' ").replaceAll("]", " ] ").replaceAll("\\[", " [ ").replaceAll("\"", " \" ")
					.replaceAll("\\(", " ( ").replaceAll("\\)", " ) ").replaceAll(":", " : ").replaceAll(" ~ ", " ~ ").replaceAll("：", " ： ").replaceAll("…", " … ")
					.replaceAll("！", " ！ ").replaceAll("？", " ？ ").replaceAll("—", " — ").replaceAll(";", " ; ").replaceAll("#", " # ").replaceAll("\\*", " * ")
					.replaceAll("，", " ， ").replaceAll("。", " 。 ").replaceAll("/", " / ").replaceAll("~", " ~ ").replaceAll("→", " → ").replaceAll("＂", " ＂ ")
					.replaceAll("\\$", " \\$ ").replaceAll("%", " % ").replaceAll("\\^", " ^ ").replaceAll("=", " = ").replaceAll("\\|", " ")
					.trim();      
			
			//， 。/ ~ → · ＂  ^ $ %
			
			
			clean  = clean.replaceAll("‘", " ‘ ").replaceAll("’", " ’ ").replaceAll("》", " 》 ").replaceAll("《", " 《 ").replaceAll("、", " 、 ").replaceAll("◇", " ◇ ").replaceAll("▶", " ▶ ")
					.replaceAll("▲", " ▲ ").replaceAll("”", " ” ").replaceAll("“", " “ ").replaceAll("「", " 「 ").replaceAll("」", " 」 ").replaceAll("<", " < ").replaceAll(">", " > ")
					.replaceAll("-", " - ").replaceAll("【", " 【 ").replaceAll("】", " 】 ").replaceAll("（", " （ ").replaceAll("）", " ） ").trim();
			
			String regEx = "\\d+[\\.]?\\d*%?";  
			Pattern pat = Pattern.compile(regEx);  
			Matcher mat = pat.matcher(clean);  
			boolean rs = mat.find(); 
			while(rs){
				for(int k=0;rs==true && k<=mat.groupCount();k++){  
				     System.out.println(mat.group(k));  
				     clean=clean.replaceAll(mat.group(k), " "+mat.group(k)+" ");
				}  
				rs=mat.find();
			}
			
			regEx = "([0-9])^[0-9]";  
			pat = Pattern.compile(regEx);  
			mat = pat.matcher(clean);  
			rs = mat.find(); 
			while(rs){
				for(int k=1;rs==true && k<=mat.groupCount();k++){  
				     System.out.println(mat.group(k));  
				     clean=clean.replaceAll(mat.group(k), " "+mat.group(k)+" ");
				}  
				rs=mat.find();
			}
			clean=clean.replaceAll("[' ']{2,}", " ");
			korList.add(j, clean.trim());
			j++;
		}
	}
	

	
	/*
	 * 判断韩语字符
	 */
	public static final boolean isKoreanCharacter(String koreanStr) {  
        char[] charArray = koreanStr.toCharArray();  
        for (int i = 0; i < charArray.length; i++) {  
            if ((charArray[i] >= 0xAC00) && (charArray[i] <= 0xD7A3)) {  
                return true;  
            }  
        }  
        return false;  
    }  
	
	/*
	 * 从韩语proper nouns 提取术语方法
	 */
	public static void getTerms(List<String> inputChiList,List<String > korList,List<String> chiList){
		
		for(int i=0; i<inputChiList.size() && inputChiList.get(i)!=null ;i ++){		
			String[] phrases = inputChiList.get(i).split(", ");
			if( !phrases[2].equals("CHN") ){
				continue;
			}
			String[] temps = phrases[0].split(" ");
			String korString = temps[0];
			String regEx = "[\u4e00-\u9fa5]"; //汉字unicode编码范围  
			
			Pattern  pat = Pattern.compile(regEx);  
			Matcher mat = pat.matcher( phrases[3] );  
          	if( ! mat.find() ) {
          		 continue;
            }
          	
          	korList.add( korString.trim() );
			chiList.add( phrases[3].replaceAll("/", "").replaceAll("Z", "").trim() );
			
		}
	}
	

	//对韩语调序
	public static void reorderKor(List<String > inputKr,List<String > inputCn,List<String > inputChiList,List<String> chiList){
		
		for(int i=0;i<inputChiList.size();i++){
			
			String src = inputChiList.get(i);
			String[] aligns = src.split(" ");
			String[] senKr = inputKr.get(i).split(" ");
			String tempsStr = "";
			for(int j = 0;j < aligns.length ;j++){
				String[] temp = aligns[j].split("-");
				tempsStr += senKr[Integer.parseInt(temp[0])]+" ";
			}
			chiList.add(tempsStr.trim());
		}
	}
	

	//增加对齐长度信息
	public static void addAlength2(List<String > inputKr,List<String > inputCn,List<String > inputChiList,List<String> chiList){
		for(int i=0;i<inputChiList.size();i++){
			
			String src = inputChiList.get(i);
			String[] senKr = inputKr.get(i).split(" ");
			String[] senCn = inputCn.get(i).split(" ");
			chiList.add( senKr.length+"-"+senCn.length+" ||| "+src);
		}
	}


	//增加对齐长度信息
	public static void addAlength(List<String > inputChiList,List<String> chiList){
		for(int i=0;i<inputChiList.size();i++){
			int maxA = 0 ;
			int maxB = 0 ;
			String src = inputChiList.get(i);
			String[] sens = src.trim().split(" ");
			for(int j = 0; j < sens.length ;j++){
				String[] temp = sens[j].split("-");
				if( Integer.parseInt(temp[0]) > maxA){
					maxA = Integer.parseInt(temp[0]);
				}
				if( Integer.parseInt(temp[1]) > maxB){
					maxB = Integer.parseInt(temp[1]);
				}
			}
			
			chiList.add( (maxA+1)+"-"+(maxB+1)+" ||| "+src);
		}
	}


	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

package edu.buaa.nlp.xqx;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String korString = "(488837,'zh','1924年冬季奥林匹克运动会速度滑冰比赛 - 男子500米'),(491334,'zh','1924年冬季奥林匹克运动会速度滑冰比赛 - 男子全能')";
		String regEx = "\\((\\d+),'zh','(.*?)'\\)";
		//	String regEx = "\\((\\d+),\\d+,'([\\uac00-\\ud7ff_]*)',"; //汉字unicode编码范围  
		Pattern  pat = Pattern.compile(regEx);  
		Matcher mat = pat.matcher( korString );  
      	while( mat.find() ) {
      		System.out.println(mat.group(1)+"\t"+mat.group(2));
      	}
	}

}

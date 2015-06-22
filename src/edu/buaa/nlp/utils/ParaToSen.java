package edu.buaa.nlp.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParaToSen {
	
	
	 private static final int MIN_THRESHOLD = 100;  
	 private static final int MAX_THRESHOLD = 100;  

	 
	 public static List<String> GetSenChi(String para){
		 
		    StringBuffer sb = new StringBuffer(MAX_THRESHOLD);  
			 
		    para = para.replaceAll("\\.{2,}", ".").replaceAll("!{2,}", "!").replaceAll("\\?{2,}", "?"); //去除多个连续标点
		    
			 //String [] substrs = str.split("。|\\？|\\！|\\?|\\.|!");  
	        /*正则表达式：句子结束符*/  
		    String regEx="[。？！?!]";  
	        Pattern p =Pattern.compile(regEx);  
	        Matcher m = p.matcher(para);  

	        /*按照句子结束符分割句子*/  
	        String[] substrs = p.split(para);  
	        List<String> realStrs = new ArrayList<String>();

	        /*将句子结束符连接到相应的句子后*/  
	        if(substrs.length > 0)  
	        {  
	        	
	        	//句子结尾加上标点
	            int count = 0;  
	            while(count < substrs.length)  
	            {  
	                if(m.find())  
	                {  
	                    substrs[count] += m.group();  
	                }  
	                count++;  
	            }  
	            
	            String prev = substrs[0];
	            
	        	for(int i=1; i<substrs.length; i++)
	        	{
	        		boolean flag = false;
	        		Pattern pattern = Pattern.compile("[0-9A-Za-z_]*"); //匹配数字字符串正则表达式
	        		
	        		if( pattern.matcher(substrs[i].trim().substring(0,1)).matches()  &&  prev.matches(".*[0-9A-Za-z_]\\.$" ) ){
	        			prev += substrs[i].trim();
	        			//realStrs.add(prev);
	        			//prev = "";
	        		}
	        		else if( substrs[i].trim().substring(0,1).equals("\"")  )           //"
	        		{
	        			if(substrs[i].trim().length() == 1){ //本句子只有"
		        			prev += "\"";
		        			realStrs.add(prev);
		        			substrs[i] = substrs[i].substring(1);
		        			if(substrs[i].trim().isEmpty())
		        			{
		        				prev = "";
		        			}
		        			else {                         
		        				prev = substrs[i];
							}
	        			}else {                  //出了"还有其他字符
	        				prev += "\"";
		        			realStrs.add(prev);
	        				//substrs[i]= substrs[i].trim().substring(1);
	        				prev= substrs[i].trim().substring(1);
						}
	        		}
	        		else if( substrs[i].trim().substring(0,1).equals("’")  )     //’
	        		{
	        			if(substrs[i].trim().length() == 1){ //本句子只有"
		        			prev += "’";
		        			realStrs.add(prev);
		        			substrs[i] = substrs[i].substring(1);
		        			if(substrs[i].trim().isEmpty())
		        			{
		        				prev = "";
		        			}
		        			else {                         
		        				prev = substrs[i];
							}
	        			}else {                  //出了"还有其他字符
	        				prev += "’";
		        			realStrs.add(prev);
	        				//substrs[i]= substrs[i].trim().substring(1);
		        			prev= substrs[i].trim().substring(1);
						}
	        		}
	        		else if( substrs[i].trim().substring(0,1).equals("》")  )     // 》
	        		{
	        			if(substrs[i].trim().length() == 1){ //本句子只有"
		        			prev += "》";
		        			realStrs.add(prev);
		        			substrs[i] = substrs[i].substring(1);
		        			if(substrs[i].trim().isEmpty())
		        			{
		        				prev = "";
		        			}
		        			else {                         
		        				prev = substrs[i];
							}
	        			}else {                  //出了"还有其他字符
	        				prev += "》";
		        			realStrs.add(prev);
	        				//substrs[i]= substrs[i].trim().substring(1);
		        			prev= substrs[i].trim().substring(1);
						}
	        		}
	        		else{
	        			realStrs.add(prev);
	        			prev = substrs[i];
					}
	        	}
	        
	        	if(prev.trim().isEmpty() == false)
	        	{
	        		realStrs.add(prev);
	        	}
	        }  
	        
	        //限制句子长度
	      /*  List<String> senStrs = new ArrayList<String>();
	        for(int i=0;i<realStrs.size();i++){
	        	String string=realStrs.get(i);
	        	if(string.length()<=80){
	        		senStrs.add(string);
	        	}
	        	else {
					while(string.length()>80){
						senStrs.add(string.substring(0, 80));
						string=string.substring(80);
					}
					if(string.length()>0);
						senStrs.add(string);
				}
	        }
	        return senStrs;*/
	       
	        return realStrs;
		}
		


	 public static List<String> GetSenKor(String para){
		 
		    StringBuffer sb = new StringBuffer(MAX_THRESHOLD);  
			 
		    para = para.replaceAll("\\.{2,}", ".").replaceAll("!{2,}", "!").replaceAll("\\?{2,}", "?"); //去除多个连续标点
		    
			 //String [] substrs = str.split("。|\\？|\\！|\\?|\\.|!");  
	        /*正则表达式：句子结束符*/  
		    String regEx="[。？！?.!]";  
	        Pattern p =Pattern.compile(regEx);  
	        Matcher m = p.matcher(para);  

	        /*按照句子结束符分割句子*/  
	        String[] substrs = p.split(para);  
	        List<String> realStrs = new ArrayList<String>();

	        /*将句子结束符连接到相应的句子后*/  
	        if(substrs.length > 0)  
	        {  
	        	
	        	//句子结尾加上标点
	            int count = 0;  
	            while(count < substrs.length)  
	            {  
	                if(m.find())  
	                {  
	                    substrs[count] += m.group();  
	                }  
	                count++;  
	            }  
	            
	            String prev = substrs[0];
	            
	        	for(int i=1; i<substrs.length; i++)
	        	{
	        		boolean flag = false;
	        		Pattern pattern = Pattern.compile("[0-9A-Za-z_]*"); //匹配数字字符串正则表达式
	        		
	        		if( pattern.matcher(substrs[i].trim().substring(0,1)).matches()  &&  prev.matches(".*[0-9A-Za-z_]\\.$" ) ){
	        			prev += substrs[i].trim();
	        			//realStrs.add(prev);
	        			//prev = "";
	        		}
	        		else if( substrs[i].trim().substring(0,1).equals("\"")  )           //"
	        		{
	        			if(substrs[i].trim().length() == 1){ //本句子只有"
		        			prev += "\"";
		        			realStrs.add(prev);
		        			substrs[i] = substrs[i].substring(1);
		        			if(substrs[i].trim().isEmpty())
		        			{
		        				prev = "";
		        			}
		        			else {                         
		        				prev = substrs[i];
							}
	        			}else {                  //出了"还有其他字符
	        				prev += "\"";
		        			realStrs.add(prev);
	        				//substrs[i]= substrs[i].trim().substring(1);
	        				prev= substrs[i].trim().substring(1);
						}
	        		}
	        		else if( substrs[i].trim().substring(0,1).equals("’")  )     //’
	        		{
	        			if(substrs[i].trim().length() == 1){ //本句子只有"
		        			prev += "’";
		        			realStrs.add(prev);
		        			substrs[i] = substrs[i].substring(1);
		        			if(substrs[i].trim().isEmpty())
		        			{
		        				prev = "";
		        			}
		        			else {                         
		        				prev = substrs[i];
							}
	        			}else {                  //出了"还有其他字符
	        				prev += "’";
		        			realStrs.add(prev);
	        				//substrs[i]= substrs[i].trim().substring(1);
		        			prev= substrs[i].trim().substring(1);
						}
	        		}
	        		else if( substrs[i].trim().substring(0,1).equals("》")  )     // 》
	        		{
	        			if(substrs[i].trim().length() == 1){ //本句子只有"
		        			prev += "》";
		        			realStrs.add(prev);
		        			substrs[i] = substrs[i].substring(1);
		        			if(substrs[i].trim().isEmpty())
		        			{
		        				prev = "";
		        			}
		        			else {                         
		        				prev = substrs[i];
							}
	        			}else {                  //出了"还有其他字符
	        				prev += "》";
		        			realStrs.add(prev);
	        				//substrs[i]= substrs[i].trim().substring(1);
		        			prev= substrs[i].trim().substring(1);
						}
	        		}
	        		else{
	        			realStrs.add(prev);
	        			prev = substrs[i];
					}
	        	}
	        
	        	if(prev.trim().isEmpty() == false)
	        	{
	        		realStrs.add(prev);
	        	}
	        }  
	        
	        //限制句子长度
	      /*  List<String> senStrs = new ArrayList<String>();
	        for(int i=0;i<realStrs.size();i++){
	        	String string=realStrs.get(i);
	        	if(string.length()<=80){
	        		senStrs.add(string);
	        	}
	        	else {
					while(string.length()>80){
						senStrs.add(string.substring(0, 80));
						string=string.substring(80);
					}
					if(string.length()>0);
						senStrs.add(string);
				}
	        }
	        return senStrs;*/
	       
	        return realStrs;
		}
		

	 
	 public static List<String> GetSen(String para){
		 
	    StringBuffer sb = new StringBuffer(MAX_THRESHOLD);  
		 
		 //String [] substrs = str.split("。|\\？|\\！|\\?|\\.|!");  
        /*正则表达式：句子结束符*/  
	    String regEx="[。？]"; //String regEx="[。？！?.!]";  
        Pattern p =Pattern.compile(regEx);  
        Matcher m = p.matcher(para);  

        /*按照句子结束符分割句子*/  
        String[] substrs = p.split(para);  

        /*将句子结束符连接到相应的句子后*/  
        if(substrs.length > 0)  
        {  
            int count = 0;  
            while(count < substrs.length)  
            {  
                if(m.find())  
                {  
                    substrs[count] += m.group();  
                }  
                count++;  
            }  
            
            
        }  
        
 /*       
//      String [] substrs = str.split("[。？！?.!]");  
        for (int i=0;i<substrs.length;i++) {  

            if (substrs[i].length()<MIN_THRESHOLD) { //语句小于要求的分割粒度  
                sb.append(substrs[i]);  
                //sb.append("||");  
                if (sb.length()>MIN_THRESHOLD) {  
                    //System.out.println("A New TU: " + sb.toString());  
                    list.add(sb.toString());  
                    sb.delete(0, sb.length());  
                }  
            }  
            else {  //语句满足要求的分割粒度  
                    if(sb.length()!=0)  //此时如果缓存有内容则应该先将缓存存入再存substrs[i]的内容  以保证原文顺序  
                    {  
                        list.add(sb.toString());  
                        //System.out.println("A New Tu:"+sb.toString());  
                        sb.delete(0, sb.length());  
                    }  
                        list.add(substrs[i]);  
                        //System.out.println("A New Tu:"+substrs[i]);  
            }  
        }  */
        
        List<String> list = java.util.Arrays.asList(substrs);
        return list;
	}
	
	
	 public static void main(String [] argv ){
		
	 //	List <String> sens =  ParaToSen.GetSenKor("이처럼 평균 경쟁률이 높아지면서 조사대상 기업 중 경쟁률이 100대1이 넘는 기업이 전체의 46.9%인 30개사에 달했으며 200대 1 이상의 경쟁률을 기록한 기업도 8곳(12.5%)이나 됐다.");
	
	 //	List <String> sens =  ParaToSen.GetSenKor(" \"中 위안화 2% 절상… 달러 고정 환율제 폐지.\" ");
		
		List <String> sens =  ParaToSen.GetSenKor("\"환율제 폐지.\"환율제 폐.");
		
		
	 //	List <String> sens =  ParaToSen.GetSenKor("《6월 1일 일본 교토(京都). 독일 루프트한자에 올 3월 합병된 스위스항공은 교토에서 열린 세계 최대 항공 동맹체인 ‘스타 얼라이언스(Star Alliance)’ 사장단 회의에서 만장일치로 회원사 가입 승인을 받았다. 이로부터 8일이 지난 6월 9일. 또 다른 항공 동맹체인 ‘스카이팀(SkyTeam)’은 네덜란드 암스테르담에서 회의를 갖고 에어유로파(스페인), 타롬(루마니아), 케냐항공(케냐), 코파항공(파나마) 등 4개 항공사를 준회원으로 영입했다. 세계 항공업계가 동맹체를 강화하고 있다. 현재 항공분야 동맹체는 스타 얼라이언스, 스카이팀, 그리고 ‘원월드(Oneworld)’ 등 세 가지. ‘뭉쳐야 산다’는 것이 이들의 구호다.》");

		for(int i=0;i<sens.size();i++)
			  System.out.println( sens.get(i));
		/*String string="abcdf";
		System.out.println(string.substring(0,4));
		System.out.println(string.substring(4));*/
		
		
		/*List <String> sens_cn =  ParaToSen.GetSenChi("就综合房地产税增加率的限度，安炳烨称：“至于废除限制措施还是上调的问题，将选择明年立即大幅上调税率或分期逐渐上调的方案中选择其一。多数人的意见是废除限制措施。”");

		for(int i=0;i<sens_cn.size();i++)
			  System.out.println( sens_cn.get(i));*/
		
		//韩国开放的我们党房地产政策企划团团长安炳烨21日接受本报电话采访时说：“2住宅拥有者应该寻求躲避重课税的方法。关于重课税措施延期1年实行的方案，将在党政协议会上进行讨论。” 
		//System.out.println("h\n".trim().length());
	}
	
}

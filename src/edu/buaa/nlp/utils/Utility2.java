package edu.buaa.nlp.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.mozilla.universalchardet.UniversalDetector;


/*
 * 
 * Utility��һЩ���ߺ���ļ��ϣ�
 */
public class Utility2 {

	static String regEx = "[\\u4e00-\\u9fa5]";   
    static Pattern patChi = Pattern.compile(regEx);
    static String regExAll = "[a-zA-Z\\u4e00-\\u9fa5]";   
    static Pattern patWord= Pattern.compile(regExAll);  
	

	public static String detectCharSet(String fileName) {
		byte[] buf = new byte[4096];
		FileInputStream fis = null;
		UniversalDetector detector = null;
		try {
			fis = new FileInputStream(fileName);
			detector = new UniversalDetector(null);
			int nread;
			while ((nread = fis.read(buf)) > 0 && !detector.isDone()) {
				detector.handleData(buf, 0, nread);
			}
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		detector.dataEnd();
		String encoding = detector.getDetectedCharset();
		detector.reset();
		if (encoding == null) {
			encoding = "GB2312";
		}
		return encoding;
	}
	
    public static boolean deleteFile(String fileName){    
    	try
    	{
	        File file = new File(fileName);    
	        if(file.isFile() && file.exists()){    
	            if(file.delete()==true)
	            {
		            //System.out.println("ɾ����ļ�"+fileName+"�ɹ���");    
		            return true;
	            }
	            else
	            {
	                System.out.println("ɾ����ļ�"+fileName+"ʧ�ܣ�");    
	                return false; 	
	            }
	        }else{    
	            System.out.println("ɾ����ļ�"+fileName+"ʧ�ܣ��ļ������ڣ�");    
	            return false;    
	        }    
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    		System.out.println("ɾ����ļ�"+fileName+"ʧ�ܣ�-exception");    
    		return false;
    	}
    }    

	public static void main(String args[]) throws IOException {

	}
	
	
	public static String RemoveStyleCode(String content) {

		try {
			
			// Pattern p3 = Pattern.compile("(?s)<!--\\s*.*?>(.*?)-->");
			Pattern p3 = Pattern.compile("(?s)<!--.*?-->");
			Matcher m3 = p3.matcher(content);
			content = m3.replaceAll("");
			
			Pattern p1 = Pattern.compile("(?s)<script\\s*.*?>(.*?)</script>",
					Pattern.CASE_INSENSITIVE);
			Matcher m1 = p1.matcher(content);
			content = m1.replaceAll("");

			Pattern p2 = Pattern.compile("(?s)<style\\s*.*?>(.*?)</style>",
					Pattern.CASE_INSENSITIVE);
			Matcher m2 = p2.matcher(content);
			content = m2.replaceAll("");

			Pattern p11 = Pattern.compile("(?s)<script\\s*.*?/>",
					Pattern.CASE_INSENSITIVE);
			Matcher m11 = p11.matcher(content);
			content = m11.replaceAll("");

			Pattern p21 = Pattern.compile("(?s)<style\\s*.*?/>",
					Pattern.CASE_INSENSITIVE);
			Matcher m21 = p21.matcher(content);
			content = m21.replaceAll("");

			Pattern p22 = Pattern.compile("(?s)<img\\s*.*?/>",
					Pattern.CASE_INSENSITIVE);
			Matcher m22 = p22.matcher(content);
			content = m22.replaceAll("");

			// ȥ��ע��

		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;

	}
}
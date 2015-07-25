package Algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;

import compare.compare;



public class MWA {

	Hashtable<String, String> m_dataTable = new Hashtable<String, String>();
	
	public MWA(){
		Init();
	}
	
	
	public List<String> readOrg(){
		List<String> ls = new ArrayList<String>();

		try {
			//读取存放地址的文件
			File f = new File("Data/data38z.txt");			
			if (f.isFile() && f.exists()) {
				//按行读取文件中的地址
				InputStreamReader isr = new InputStreamReader(
						new FileInputStream(f), "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				String lineStr = br.readLine();
				while (lineStr != null) { 
					ls.add(lineStr);
					lineStr = br.readLine();
				}
				br.close();
				isr.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}
	
	//比较算法
	public void test(List<String> ls){
		List<String> rsls = new ArrayList<String>();
		List<String> output=new ArrayList<String>();
		String s = "";
		String response = "";
		
		try {
			File f = new File("Data/MWAresult.txt");
			if (!f.exists()) {
				f.createNewFile();
			}
			OutputStreamWriter result = new OutputStreamWriter(new FileOutputStream(f),"UTF-8");
			BufferedWriter write = new BufferedWriter(result);
			
			for(int i=0;i<ls.size();i++){
				//System.out.println(ls.get(i));
				int len=ls.get(i).length();
				int start=0;
				int end=len;
				//System.out.print(ls.get(i)+" :: ");
				write.write(ls.get(i)+" :: ");
				
				do {
					if (start < end) {
						// 取子串
						String queryStr = ls.get(i).substring(start, end);
						// 子串匹配成功
						if (isExist(queryStr)) {
	//						//判断子串加后面三个字符是否可以匹配
	//						if((end<len-2) && (isExist(ls.get(i).substring(start, end+3))))
	//						{
	//							System.out.print(ls.get(i).substring(start, end+3) + "|");
	//						    start = end+3;
	//						    end = start + 1;
	//						}else if((end<len-1) && (isExist(ls.get(i).substring(start, end+2)))){
	//							//判断子串加后面两个字符是否可以匹配
	//							System.out.print(ls.get(i).substring(start, end+2) + "|");
	//						    start = end+2;
	//						    end = start + 1;
	//						}else if ((end<len) && (isExist(ls.get(i).substring(start, end+1)))){
	//							//判断子串加后面一个字符是否可以匹配
	//							System.out.print(ls.get(i).substring(start, end+1) + "|");
	//						    start = end+1;
	//							end = start + 1;
	//							}
	//						else{
	//							System.out.print(queryStr + "|");
	//							start = end;
	//							end = start + 1;
	//						}
	//						output.add(ls.get(i)+" :: "+queryStr + "|");*/
							write.write(queryStr + "|");
							start = end;
							end=len;
						} else { // 子串匹配失败
							end--;
						}
					} else {
						//System.out.print(ls.get(i).substring(start, start + 1) + "|");
						start = start + 1;
						end = len;
					}
				} while (start < len);
				write.write("\n");
	
			}
			write.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public boolean isExist(String value){
		if(m_dataTable.containsKey(value))
			return true;
		else
			return false;
	}

	
	private void Init() {
		try {
			File dictionary = new File("Data/district.txt");
			if (dictionary.isFile() && dictionary.exists()) {
				// 按行读取文件中的地址
				InputStreamReader isr = new InputStreamReader(new FileInputStream(dictionary), "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				String str = br.readLine();
				while (str != null) {
					if(!m_dataTable.containsKey(str))
						m_dataTable.put(str, str);
					str = br.readLine();
				}				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		MWA seg = new MWA();
		List<String> ls = seg.readOrg();
		seg.test(ls);

		long endTime = System.currentTimeMillis();
		System.out.println("分词时间： " + (endTime - startTime) + "ms");
		
	} 
}

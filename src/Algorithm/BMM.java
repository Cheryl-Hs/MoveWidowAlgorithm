package Algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;

public class BMM {

	Hashtable<String, String> m_dataTable = new Hashtable<String, String>();
	
	public BMM(){
		Init();
	}
	
	public List<String> readOrg(){
		List<String> ls = new ArrayList<String>();
		String response = "";  
        String s = ""; 
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
		String s = "";
		String response = "";
		int maxlen=5;
		int k=0;int j=0;int slen=maxlen;
		
		try {
			File f = new File("Data/BMMresult.txt");
			if (!f.exists()){
				f.createNewFile();
			}
			OutputStreamWriter result = new OutputStreamWriter(new FileOutputStream(f),"UTF-8");
			BufferedWriter write =  new BufferedWriter(result);
			
			for(int i=0;i<ls.size();i++){
				//System.out.print(ls.get(i)+" :: ");
				write.write(ls.get(i)+" :: ");
				if (slen>=ls.get(i).length()){
					slen=ls.get(i).length();
					j=slen;
				}else{
					j = maxlen;
				}
				k=ls.get(i).length();
				while (j>0) {
					if(k <= 0){
						break;
					}
		            if ((k-j)<=0) {
		            	s += ls.get(i).substring(0,k);
		            } else {
					s += ls.get(i).substring(k-j,k);  
		            }
		            //System.out.println("now S = " + s);
		            if(isExist(s) ) { 
		                response = (s + "|") + response; 
		                s = ""; 
		                k=k-j;
		                j=maxlen;
		            }else if (s.length()==1){  
		            	//response = (s + "|") + response;  
		                s = "";
		                k--;
		                j=maxlen;
		            }else{
		            	s = "";
		            	j--;
		            }
		            
				}
				write.write(response + "\n");
	            rsls.add(response);
	          
	            response="";
			}
			write.close();
		} catch (Exception e){
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
		BMM seg = new BMM();
		List<String> ls = seg.readOrg();
		seg.test(ls);

		long endTime = System.currentTimeMillis();
		System.out.println("分词时间： " + (endTime - startTime) + "ms");
		
	} 
}

package compare;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import sun.applet.Main;

public class compare {

	/**
	 * @param args
	 * @throws IOException 
	 * @author witailab
	 */
/*	public static void main(String[] args) throws IOException {
		List<String> org = new ArrayList<String>();
		List<String> exp = new ArrayList<String>();
		String str1="";
		String str2="";
		int equalNumber=0;
		int line_n = 1;
		
		try {
			//读取存放地址的文件
			File f = new File("Data/bz.txt");			
			if (f.isFile() && f.exists()) {
				//按行读取文件中的地址
				InputStreamReader isr = new InputStreamReader(
						new FileInputStream(f), "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				String lineStr = br.readLine();
				while (lineStr != null) { 
					org.add(lineStr);
					lineStr = br.readLine();
				}
				br.close();
				isr.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			//读取存放地址的文件
			File f = new File("Data/FMMresult.txt");			
			if (f.isFile() && f.exists()) {
				//按行读取文件中的地址
				InputStreamReader isr = new InputStreamReader(
						new FileInputStream(f), "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				String lineStr = br.readLine();
				while (lineStr != null) { 
					exp.add(lineStr);
					lineStr = br.readLine();
				}
				br.close();
				isr.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(org.size());
		System.out.println(org.size());

		


		long size=0;
		if (org.size()>=exp.size()) {
			size=org.size();
		}else{
			size=exp.size();
		}
		for(int i=0;i<org.size();i++){
			str1=org.get(i);
			//System.out.println(i + " " + org.get((int) i));
			str2=exp.get(i);
			int len1=str1.length();
			int len2=str2.length();
			
			if (len1>=len2) {
				len2=len1;
			} else{
				len1=len2;
			}
			
			if (str1.equals(str2)){
				str1="";
				str2="";
				equalNumber++;
			}else{
				System.out.println(i + "  " + str1 + "  !==  " + str2);
			}
			
		}
		System.out.println("equalNumber= " + equalNumber );

	}
*/

	public int equalNumber=0;
	public int accuracyRate=0;
	public compare(String fileURL_1, String fileURL_2,String fileURL_3){
		
		int line_n = 1;
		InputStreamReader isr_bz = null;
		BufferedReader brr_bz = null;
		
		InputStreamReader isr_result = null;
		BufferedReader br_result = null;
		
		File compare_Result = new File(fileURL_3);
		OutputStreamWriter out_Result = null;
		BufferedWriter write_Result = null;
		
		try {
			//读取存放地址的文件
			File f_bz = new File(fileURL_1);
			File f_result = new File(fileURL_2);
			if ( !(f_bz.isFile() && f_bz.exists()) || !(f_result.isFile() && f_result.exists())) 
			{
				System.out.println("file Data/bz.txt or Data/result.txt is not a file or doesn't exist");
				assert(false);
			}
				//按行读取文件中的地址
				isr_bz = new InputStreamReader( new FileInputStream(f_bz), "UTF-8");
				brr_bz = new BufferedReader(isr_bz);
				
				isr_result = new InputStreamReader( new FileInputStream(f_result), "UTF-8");
				br_result = new BufferedReader(isr_result);
				
				out_Result = new OutputStreamWriter( new FileOutputStream(compare_Result), "UTF-8");
				write_Result = new BufferedWriter(out_Result);
				
				while (true){
					
					String lineStrr_bz = brr_bz.readLine();
					String lineStr_result = br_result.readLine();
					if(lineStrr_bz == null || lineStr_result == null)
						break;
				
					if (lineStrr_bz.equals(lineStr_result)){
						equalNumber++;
					}else{
						
						write_Result.write(line_n + "  " + lineStrr_bz + "  !==  " + lineStr_result+"\n");
//						System.out.println(line_n + "  " + lineStrr_bz + "  !==  " + lineStr_result);
					}
					line_n++;

				}
				write_Result.write("\nEqual line NUM == "+equalNumber);
				write_Result.write("\nAccuracy rate is  == "+(float)equalNumber*100/384075 + "%");

		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				brr_bz.close();
				br_result.close();
				isr_bz.close();
				isr_result.close();
				write_Result.close();
				out_Result.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	public static void main(String[] args){
		compare myResult = new compare("Data/MWAresult.txt", "Data/BMMresult.txt","Data/compareResult.txt");
		System.out.println(" OK =======================");
		System.out.println(" Equal line NUM == "+myResult.equalNumber);
		System.out.println("Accuracy rate is  == "+(float)myResult.equalNumber*100/384075 + "%");
	}

}

package Algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
//import java.util.Vector;
import java.util.Hashtable;

public class YDM {
	//private static Vector vc = new Vector();
	private static String lineStr ;
	
    static Hashtable<String, String> m_dataTable = new Hashtable<String, String>();
	
	public YDM(){
		Init();
	}
	
	private void Init() {
		try {
			File dictionary = new File("Data/data.txt");
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
	
	public static boolean isExist(String value){
		if(m_dataTable.containsKey(value))
			return true;
		else
			return false;
	}
	
	
	public static void main(String[] args) {

		long startTime = System.currentTimeMillis();

		try {
			// 读取存放地址的文件
			File f = new File("Data/address.txt");
			File fo = new File("Data/result.txt");
			if (!fo.exists()) {
				f.createNewFile();
			}
			OutputStreamWriter osw = new OutputStreamWriter(
					new FileOutputStream(fo), "utf-8");
			BufferedWriter bw = new BufferedWriter(osw);
			
			//vc = ReadFileToVector.ReadFile("Data/data.txt");
			if (f.isFile() && f.exists()) {
				// 按行读取文件中的地址
				InputStreamReader isr = new InputStreamReader(
						new FileInputStream(f), "utf-8");
				BufferedReader br = new BufferedReader(isr);
				String lineStr = br.readLine();
				while (lineStr != null) {
					// System.out.println(lineStr);
					int len = lineStr.length();
					//System.out.println(len);
					int start = 0;
					int end = len;
					// System.out.println(lineStr.substring(start, end));
					// System.out.println("lineStr:" + lineStr);
					// bw.write("as");

					do {
						if (end <= len) {

							// System.out.println(start + "," + end);
							// 取子串
							String queryStr = lineStr.substring(start, len);

							// System.out.println("queryStr" + queryStr);

							// 子串匹配成功
							
							if (isExist(queryStr)) {
//								//判断子串加后面三个字符是否可以匹配
//								if((end<len-2) && (isExist(lineStr.substring(start, end+3)))) {
//									bw.write(lineStr.substring(start, end+3) + "|");
//								    start = end+3;
//								    end = start + 1;
//								}else if((end<len-1) && (isExist(lineStr.substring(start, end+2)))){
//									//判断子串加后面两个字符是否可以匹配
//									bw.write(lineStr.substring(start, end+2) + "|");
//								    start = end+2;
//								    end = start + 1;
//								}else if ((end<len) && (isExist(lineStr.substring(start, end+1)))){
//									//判断子串加后面一个字符是否可以匹配
//									bw.write(lineStr.substring(start, end+1) + "|");
//								    start = end+1;
//									end = start + 1;
//									}
//								else{
//									bw.write(queryStr + "|");
//									start = end;
//									end = start + 1;
//								}
								bw.write(queryStr + "|");
								queryStr="";
								start=end;
								end=len;
							} else { // 子串匹配失败
								end--;
								// System.out.println(start + "," + end);
							}
						} else {
							// if(end <= len)
							//bw.write(lineStr.substring(start, start + 1) + "|");
							// bw.write(lineStr.substring(start, start));
							
							start = start + 1;
							end = len;
						}
					} while (start < len);

					bw.newLine();
					lineStr = br.readLine();
				}
				bw.close();
				br.close();
				isr.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		long endTime = System.currentTimeMillis();
		System.out.println("分词时间： " + (endTime - startTime) + "ms");

	}
//private static boolean myJudge(int start, int end)
//{
//	boolean flag = false;
//	if( end > lineStr.length())
//		return false;
//		
//	if (vc.contains(lineStr.substring(start, end)))
//	{
//		flag = true;
//	}
//	return flag;
//}

}

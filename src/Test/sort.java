package Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class sort {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		String response = "";  
        String s = ""; 
		try {
			//读取存放地址的文件
			File f = new File("Data/data38w.txt");
			File fo = new File("Data/data38z.txt");
			if (!fo.exists()){
				fo.createNewFile();
			}
			OutputStreamWriter result =new OutputStreamWriter(new FileOutputStream(fo),"UTF-8");
			BufferedWriter write = new BufferedWriter(result);
			
			if (f.isFile() && f.exists()) {
				//按行读取文件中的地址
				InputStreamReader isr = new InputStreamReader(
						new FileInputStream(f), "UTF-8");
				BufferedReader br = new BufferedReader(isr);
				String lineStr = br.readLine();
				while (lineStr != null) { 
					list.add(lineStr);
					lineStr = br.readLine();
				}
				br.close();
				isr.close();
			}
			Collections.sort(list,Collator.getInstance(java.util.Locale.CHINA));
			for(int i=0;i<list.size();i++)
			{
			    write.write(list.get(i)+"\n");
			}
			write.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		Collections.sort(list,Collator.getInstance(java.util.Locale.CHINA));
//		for(int i=0;i<list.size();i++)
//		{
//		    write.(list.get(i));
//		}

	}
	

}

/*
 * 日期：2015.3.9
 * 功能：在dataAddress中每一行最后加“|”，输出到dataAddress1中
 * 作者：黄爽
 */
package Test;

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class jiazifu {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// 读取存放地址的文件
			File f = new File("Data/dataAddress.txt");
			File fo = new File("Data/dataAddress1.txt");
			if (!fo.exists()) {
				f.createNewFile();
			}
			OutputStreamWriter osw = new OutputStreamWriter(
					new FileOutputStream(fo), "utf-8");
			BufferedWriter bw = new BufferedWriter(osw);
			
			if (f.isFile() && f.exists()) {
				// 按行读取文件中的地址
				InputStreamReader isr = new InputStreamReader(
						new FileInputStream(f), "utf-8");
				BufferedReader br = new BufferedReader(isr);
				String lineStr = br.readLine();
				while (lineStr != null) {
					int len = lineStr.length();
					//System.out.println(len);
					int start = 0;
					int end = len;
					String queryStr = lineStr.substring(start, end);
					bw.write(queryStr + "|");
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

	}
}

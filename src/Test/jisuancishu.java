/*
 * 日期：2015.3.9
 * 功能：计算dataAddresse中的词数
 * 作者：黄爽
 */

package Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class jisuancishu {

	/**
	 * @param args
	 * @throws IOException 
	 */
	    public static void main(String[] args)
	    {
	        /** 要读取的文件路径，可以自己修改成自己的路径 */
	        String url = "Data/gaoshuai.txt";
	        /**
	         * 读取文件数据
	         */
	        File file = new File(url);
	        if (!file.exists() || file.isDirectory())
	        {
	            System.out.println("文件不存在！");
	            return;
	        }
	        StringBuffer sb = null;
	        BufferedReader br;
	        try
	        {
	            br = new BufferedReader(new FileReader(file));
	            String temp = null;
	            sb = new StringBuffer();
	            temp = br.readLine();
	            while (temp != null)
	            {
	                sb.append(temp + "\r");
	                temp = br.readLine();
	            }
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        /** 读取的文件内容 */
	        String info = sb.toString();
	        int num = 0;
	        for (int i = 0; i < info.length(); i++)
	        {
	            /** 挨个字符判断是否是|，如果是A就将总数加1 */
	            if ("|".equals(info.charAt(i) + ""))
	            {
	                num++;
	            }
	        }
	        System.out.println("MWAresult.txt文件中的词数：" + num);
	    }
	}
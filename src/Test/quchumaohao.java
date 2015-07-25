package Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class quchumaohao {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//int i=0;
		Scanner input = new Scanner(new File("Data/MWAresult.txt"));//filepath写你的txt路径
		PrintWriter output = new PrintWriter(new File("Data/output.txt"));
		//int ii = 10;
		while(input.hasNext() )
		{
		    String[] temp = input.nextLine().split("::");//这里必须保证每行都存在冒号，否则需要加一个判断条件
		    output.write(temp[1]);
		    output.write("\r\n");
		    //System.out.println(temp[1]);
//			String mi = input.next();
//			String lastName = 
//			ii--;
		}
		input.close();
		output.close();

	}

}

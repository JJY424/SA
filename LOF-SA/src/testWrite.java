

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.RandomAccessFile;



public class testWrite 
{
	final static int CHAR_LENTH = 256;
	public static void main(String[] args) throws IOException 
	{
		String path = "c:\\mp4\\01.txt";
		File file = new File(path);
		WriteSA writeSA = new WriteSA();
		ConstructLOFSA constructLOFSA = new ConstructLOFSA();
		FileInputStream fis = new FileInputStream(file);
		RandomAccessFile raf = new RandomAccessFile(file, "r");
		int len = (int) raf.length();
		byte input[] = new byte[len+1];
		LOFNode list[] = null;
		System.out.println("len = "+len);
		raf.read(input);
		input[len] = Util.CharToByte('#');
		
		
		int c[] = Util.byteArrToIntArr(input, len+1);
		/*for(int i = 0;i<len+1;i++)
		{
			if(i%10==0)
				System.out.println();
			
			System.out.print(c[i]+" ");
		}
		System.out.println("----------------------------------");*/
		list = constructLOFSA.construct_LOFSA(c, CHAR_LENTH, len+1);
		
		
		for(int i = 0;i<list.length;i++)
	    {
	    	System.out.print("[ "+list[i].L+", "+list[i].O+", "+list[i].c1+""+list[i].c2+"]");
	    }
	    System.out.println();
	}
}

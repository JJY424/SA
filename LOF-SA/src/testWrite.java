

import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.io.RandomAccessFile;



public class testWrite 
{
	final static int CHAR_LENTH = 19999;
	public static void main(String[] args) throws IOException 
	{
		String inputPath = "c:\\mp4\\01.txt";
		File inputFile = new File(inputPath);
		String outputPath = "c:\\mp4\\test.txt";
		File outputFile = new File(outputPath);
		RandomAccessFile read = new RandomAccessFile(inputFile,"r");
		RandomAccessFile write = new RandomAccessFile(outputFile, "rw");
		
		ConstructLOFSA constructLOFSA = new ConstructLOFSA();
		WriteSA writeSA = new WriteSA();
		
		int len = (int) read.length();
		byte input[] = new byte[len+1];
		LOFNode list[] = null;
		
		System.out.println("len = "+len);
		read.read(input);
		input[len] = 0;
		
		int c[] = Util.byteArrToIntArr(input, len+1);
		list = constructLOFSA.construct_LOFSA(c, CHAR_LENTH, len+1);
		writeSA.WriteLOFSA(list);
		write.close();
		read.close();
		/*for(int i = 0;i<list.length;i++)
	    {
			if(i%10==0)
				System.out.println();
	    	System.out.print("[ "+list[i].L+", "+list[i].O+", "+list[i].c1+""+list[i].c2+"]");
	    }
	    System.out.println();*/
	}
}

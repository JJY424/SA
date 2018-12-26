import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class WriteSA 
{
	public void write(int SA[])
	{
		int len = SA.length;
		String path = "c:\\mp4\\test.txt";
		File file = new File(path);
		
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			for(int i = 0;i<len;i++)
			{
				byte []saByte = Util.IntToByte(SA[i]);
				randomAccessFile.write(saByte[3]);
				randomAccessFile.write(saByte[2]);
				randomAccessFile.write(saByte[1]);
				randomAccessFile.write(saByte[0]);
			}
			randomAccessFile.close();
			
		}catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void WriteLOFSA(LOFNode list[]) 
	{
		String path = "c:\\mp4\\test.txt";
		File file = new File(path);
		int len = list.length;
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			for(int i = 0;i<len;i++)
			{
				LOFNode node = list[i];
				byte []LByte = Util.IntToByte(node.L);
				randomAccessFile.write(LByte[3]);
				randomAccessFile.write(LByte[2]);
				randomAccessFile.write(LByte[1]);
				randomAccessFile.write(LByte[0]);
				
				byte []saByte = Util.IntToByte(node.O);
				randomAccessFile.write(saByte[3]);
				randomAccessFile.write(saByte[2]);
				randomAccessFile.write(saByte[1]);
				randomAccessFile.write(saByte[0]);
				
				byte c1 = Util.CharToByte(node.c1);
				byte c2 = Util.CharToByte(node.c2);
				randomAccessFile.write(c1);
				randomAccessFile.write(c2);
				
			}
			randomAccessFile.close();
			
		}catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class WriteSA 
{
	private void write(int SA[])
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
}

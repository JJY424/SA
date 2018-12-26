
public class Util 
{
	public static byte[] IntToByte(int n)
	{
		byte sa[] = new byte[4];
		
		sa[3] = (byte)(n>>24);
		sa[2] = (byte)(n>>16);
		sa[1] = (byte)(n>>8);
		sa[0] = (byte)(n);
		
		return sa;
	}
	
	public static byte CharToByte(char c)
	{
		byte b = (byte)c;
		return b;
	}
	
	public static int[] byteArrToIntArr(byte b[],int len)
	{
		int c[] = new int[len];
		for(int i = 0;i<len;i++)
		{
			c[i] = b[i]&0xff;
		}
		return c;
	}
}

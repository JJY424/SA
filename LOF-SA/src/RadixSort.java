import java.util.ArrayList;

public class RadixSort 
{
	final static int CHAR_NUM = 256;
	
	public static void radixSort(String [] arr, int maxLen)
    {

        ArrayList<String> [] wordsByLength = new ArrayList[maxLen + 1];
        ArrayList<String> [] buckets = new ArrayList[CHAR_NUM];
 
        for (int i = 0; i < wordsByLength.length; i++)
            wordsByLength[i] = new ArrayList<>();
        for (int i = 0; i < CHAR_NUM; i++)
            buckets[i] = new ArrayList<>();
        for (String s : arr)
            wordsByLength[s.length()].add(s);
 
        int idx = 0;
        for (ArrayList<String> wordList : wordsByLength)
            for (String s : wordList)
                arr[idx++] = s;
 
        int startIndex = arr.length;
        for (int pos = maxLen - 1; pos >= 0; pos--)
        {
            startIndex = startIndex - wordsByLength[pos + 1].size();
 
            for (int i = startIndex; i < arr.length; i++)
                buckets[arr[i].charAt(pos)].add(arr[i]);
 
            idx = startIndex;
            for (ArrayList<String> thisBucket : buckets)
            {
                for (String s : thisBucket)
                    arr[idx++] = s;
                thisBucket.clear();
            }
        }
    }

	
	
	public static void countSort(int []position,int len,int s[])
	{
		int SA1[] = new int[len+1];
		int bucket[] = new int[CHAR_NUM];
		for(int i = 0;i<len;i++)
		{
			
		}
	}
	
	public static void main(String[] args) 
	{
		String str[] = {"064", "008", "000", "001", "343", "010","0022","2323","0001"};
		RadixSort.radixSort(str, 9);
		for(String s:str)
		{
			System.out.println(s);
		}
		System.out.println();
	}
}

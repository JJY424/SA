

import java.util.Arrays;

import javax.print.attribute.standard.NumberUpSupported;

public class ConstructLOFSA 
{
	private static int L_TYPE = 0;
	private static int S_TYPE = 1;
	
	public boolean is_lms_char(int type[],int index)
	{
		if(index>0 && type[index-1]==L_TYPE && type[index]==S_TYPE)
			return true;
		return false;
	}
	
	public boolean is_equals_substring(int s[],int type[],int x,int y)
	{
		int i = x;
		int j = y;
		do 
		{
			if(s[i]!=s[j])
				return false;
			i++;
			j++;
		}while(!is_lms_char(type, i) && !is_lms_char(type, j));
		return true;
	}
	
	public void indeced_sort(int []s,int []type,int []sa,int []lbucket,int []sbucket,int []bucket,int charNum)
	{
		
		for(int i = 0;i<sa.length;i++)
		{
			if(sa[i]>0 && type[sa[i]-1]==L_TYPE)
				sa[lbucket[s[sa[i]-1]]++] = sa[i]-1; 
		}
		for (int i = 1; i <= charNum; i++)  // Reset S-type bucket
	        sbucket[i] = bucket[i] - 1;
		for(int i = sa.length-1;i>=0;i--)
		{
			//System.out.println("i = "+i);
			if(sa[i]>0 && type[sa[i]-1]==S_TYPE)
				sa[sbucket[s[sa[i]-1]]--] = sa[i]-1; 
		}
	}
	
	public int[] construct_SA(int []s,int charNum,int len)
	{
		int type[] = new int[len];
		int position[] = new int[len];
		int name[] = new int[len];
		int bucket[] = new int[charNum+1];
		int lbucket[] = new int[charNum+1];
		int sbucket[] = new int[charNum+1];
		int SA[] = new int[len];
		Arrays.fill(SA, -1);
	
		for(int i = 0;i<len;i++)
		{
			bucket[s[i]]++;
		}
		for(int i = 1;i<=charNum;i++)
		{
			bucket[i]+=bucket[i-1];
			lbucket[i] = bucket[i-1];
			sbucket[i] = bucket[i]-1;
		}
		//count array of type
		type[len-1] = S_TYPE;
		for(int i = len-2;i>=0;i--)
		{
			if(s[i]>s[i+1])
				type[i] = L_TYPE;
			else if(s[i]<s[i+1])
				type[i] = S_TYPE;
			else
				type[i] = type[i+1];
		}
		//count LMS substring
		int index = 0;
		for(int i = 1;i<len;i++)
		{
			if(type[i-1]==L_TYPE && type[i]==S_TYPE)
				position[index++] = i;
		}

		for(int i = 0;i<index;i++)
		{
			SA[sbucket[s[position[i]]]--] = position[i];
		}
	
		//System.out.println("len = "+len+" first");
		indeced_sort(s, type, SA, lbucket, sbucket,bucket,charNum);
		//judge if are same LMS substring
		Arrays.fill(name, -1);
		int latex = -1;
		int namecnt = 1;
		boolean flag = false;
		for(int i = 1;i<len;i++)
		{
			int x = SA[i];
			if(is_lms_char(type, x))
			{
				if(latex>=0 && !is_equals_substring(s, type, latex, x))
				{
					namecnt++;
				}
					
				if(latex>=0 && namecnt==name[latex]);
					flag = true;
				
				name[x] = namecnt;
				latex = x;
			}	
		}
		name[len-1] = 0;
		
		int s1[] = new int[index];
		int sa1_index = 0;
		for(int i = 0;i<len;i++)
		{
			if(name[i]!=-1)
				s1[sa1_index++] = name[i];
		}
		
		int SA1[];
		if(!flag)
		{
			SA1 = new int[index];
			for(int i = 0;i<index;i++)
			{
				SA1[s1[i]] = i;
			}
		}
		else 
		{
			SA1 = construct_SA(s1, charNum,index);
		}
			
		sa1_index = 0;
		lbucket[0] = sbucket[0] = 0;
		for(int i = 1;i<charNum;i++)
		{
			lbucket[i] = bucket[i-1];
			sbucket[i] = bucket[i]-1;
		}
		
		Arrays.fill(SA, -1);
		
		for (int i = index - 1; i >= 0; i--) 
		{
			SA[sbucket[s[position[SA1[i]]]]--] = position[SA1[i]];
		}
		System.out.println();
	        
		//System.out.println("len = "+len+" second");
		indeced_sort(s, type, SA, lbucket, sbucket,bucket,charNum);
		
		return SA;
	}
	
	public int get_same_char_num(int []s,int x,int y)
	{
		int res = 0;
		int len = s.length;
		while(x<len && y<len && s[x]==s[y])
		{
			res++;
			x++;
			y++;
		}
		return res;
	}
	
	public char[] getLOFChar(int []s,int x)
	{
		char c[] = new char[2];
		int len = s.length;
		if(x<len)
			c[0] = (char) s[x];
		if(x+1<len)
			c[1] = (char) s[x+1];
		//if(x+1<len)
		//System.out.println(s[x]+"  "+s[x+1]);
		return c;
	}
	
	public LOFNode ConstructLOFNode(int l,int o,char[]f)
	{
		if(f==null)
			return new LOFNode(l, o, ' ', ' ');
		return new LOFNode(l, o, f[0], f[1]);
	}
	
	public LOFNode[] ConstructLOF(int s[],int sa[])
	{
		int len = sa.length;
		int latex = 0;
		LOFNode list[] = new LOFNode[sa.length];
		int index = 0;
		list[index++] = ConstructLOFNode(0, sa[0], getLOFChar(sa, sa[0]));
		for(int i = 1;i<len;i++)
		{
			int x = sa[i];
			int l = get_same_char_num(s, latex, x);
			char c[] = getLOFChar(s, x+l);
			list[index++] = ConstructLOFNode(l, x, c);
			latex = x;;
		}
		return list;
	}
	
	public LOFNode[] construct_LOFSA(int[] s, int i, int j) 
	{
		int sa[] = construct_SA(s,i,j);
		//printList(sa);
		return ConstructLOF(s,sa);
	}
	
	public void printList(int num[]) 
	{
		int count = 0;
		for(int i = 0;i<num.length;i++)
		{
			if(count%10==0)
				System.out.println();
			System.out.print(num[i]+" ");
			count++;
			
		}
			
		System.out.println();
	}
	public static void main(String[] args) 
	{
		int s[] = {'m','m','i','i','s','s','i','i','s','s','i','i','p','p','i','i','#'};

	    
	    for(int i = 0;i<s.length;i++)
	        System.out.print(s[i]+" ");
	    System.out.println();
	    
	    ConstructLOFSA constructLOFSA = new ConstructLOFSA();
	    LOFNode list[] = constructLOFSA.construct_LOFSA(s, 256,17);
	    
	    for(int i = 0;i<list.length;i++)
	    {
	    	System.out.print("[ "+list[i].L+", "+list[i].O+", "+list[i].c1+""+list[i].c2+"]");
	    }
	    System.out.println();
	}

	
}

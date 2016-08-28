public class Card implements Comparable<Card>
{
 
	public int suit;
	public int rank;
	
	@Override
	public int compareTo(Card o) 
	{
	     if (this.rank == (o.rank))
	            return 0;
	     else if ((this.rank) > (o.rank))
	            return 1;
	     else
	           return -1;
	}
	

}

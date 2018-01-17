import java.util.ArrayList;
import java.util.Iterator;

public class GoodPeople {
	private HuluBoy []Boy=new HuluBoy[7];
	private GradPa Gradpa;//爷爷
	public void initial() { //初始化，随机站队
		   Gradpa=new GradPa();
		   ArrayList<Integer> set=new ArrayList<Integer>();

		   while(set.size()<7) {

			   int num = (int) (Math.random() *(7-0));

			   if(set.contains(num)) continue;

			   set.add(num);

		   }

		   int i=0;

		   Iterator<Integer> iterator = set.iterator();  

	       while (iterator.hasNext()) {  

	          Boy[i]=new HuluBoy(iterator.next());

	          Boy[i].setPosition(i);

	          i++;

	       }

		}

		public void SortByColor() {

		        for (int i = 1; i < Boy.length; i++) {

		        	HuluBoy temp = Boy[i];

		            int right = i - 1;

		            int left = 0;

		            int mid;

		            while (left <= right) {

		                mid = (left + right) / 2;

		                if (Boy[mid].colorCompare(temp)>0) {

		                    right = mid - 1;

		                } else if (Boy[mid].colorCompare(temp)<0) {

		                    left = mid + 1;

		                }

		            }

		            for (int j = i; j > left; j--) {

		                Boy[j] = Boy[j - 1];

		            	//System.out.print(Boy[j].getColor()+": "+(j-1)+"->"+j+"  ,");

		            }

		            Boy[left] = temp;

		           // System.out.print(temp.getColor()+": "+i+"->"+left+"  ,");

		        }

		        System.out.println();

		}

		public void SortByRank() {

			for(int i=1;i<7;i++) {

				for(int j=0;j<7-i;j++)

				{

					if(Boy[j].RankCompare(Boy[j+1])<0){

						Boy[j].ChangeBoy(Boy[j+1]);

					}

				}

			}

			//System.out.println();

		}

		public void BaoShu() {

			for(HuluBoy b:Boy) {

				System.out.print(b.getName()+"  ");

			}

			System.out.println();

		}

		public void BaoColor() {

			for(HuluBoy b:Boy) {

				System.out.print(b.getColor()+"  ");

			}

			System.out.println();

		}
		public GradPa GetGrandPa() {
			return Gradpa;
		}
		public HuluBoy[] GetHuluBoy() {
			return Boy;
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GoodPeople A=new GoodPeople();

		A.initial();

		System.out.println("随机站队");

		A.BaoShu();

		System.out.println("按排行排序");

		A.SortByRank();

		System.out.println("排序结果");

		A.BaoShu();

		System.out.println("随机站队");

		A.initial();

		A.BaoColor();

		System.out.println("按颜色排序");

		A.SortByColor();

		System.out.println("排序结果");

		A.BaoColor();
	}

}


public class Demo {
	
	public static void main(String[] args)
	{
		HuluBrothers s = new HuluBrothers();
		
		s.randomSort();
		System.out.print("排序前: "); s.reportName();
		s.bubbleSortByName();
		System.out.print("排序后: "); s.reportName();
		System.out.println();
	
		s.randomSort();
		System.out.print("排序前: "); s.reportColor();
		s.binarySortByColor();
		System.out.print("排序后: "); s.reportColor();		
	}
}

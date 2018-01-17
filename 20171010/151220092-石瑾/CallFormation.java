
public class CallFormation extends Formation{
	String [][]content;
	public CallFormation(String matter) {
		len=wid=1;
		content=new String[len][wid];
		content[0][0]=matter;
	}
	String get_content(int i,int j) {
		return content[i][j];
	}
}

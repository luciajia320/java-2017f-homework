
public class GrusFormation extends Formation{
	String [][]content;
	public GrusFormation(String matter) {
		len=7;
		wid=4;
		content=new String[wid][len];
		for(int i=0;i<wid;i++)
			for(int j=0;j<len;j++)
				content[i][j]="⬜";
		for(int i=0;i<wid;i++) {
			content[i][i]=matter;
			content[i][len-1-i]=matter;
		}
	}
	String get_content(int i,int j) {
		return content[i][j];
	}
}


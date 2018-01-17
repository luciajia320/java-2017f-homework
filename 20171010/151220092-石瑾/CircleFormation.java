
public class CircleFormation extends Formation{
	String [][]content;
	public CircleFormation(String matter) {
        len=5;
        wid=5;
        content=new String[wid][len];
        for(int i=0;i<wid;i++)
			for(int j=0;j<len;j++)
				content[i][j]="⬜";
        int a=0,b=4;
        for (int i = 2; i >=0 ; i--) {
            content[i][a] = matter;
            content[i][b] = matter;
            a++;
            b--;
        }
        a=0;
        b=4;
		for (int i = 2; i < len; i++) {
            content[i][a] = matter;
            content[i][b] = matter;
            a++;
            b--;
        }
    }
	String get_content(int i,int j) {
		return content[i][j];
	}

}

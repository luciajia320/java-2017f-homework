
public class SnakeFormation extends Formation{
	String [][]content;
	public SnakeFormation(String matter) {
        len=7;
        wid=1;
        content=new String[wid][len];
		for (int i = 0; i < 7; i++) {
            content[0][i] = matter;
        }
    }
	String get_content(int i,int j) {
		return content[i][j];
	}
}

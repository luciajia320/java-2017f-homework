根据老师所讲，创建一个Formation作为“母阵型”，这个阵型只是一块区域，上面什么都没有
但是注明了阵型的空间长度和宽度
public abstract class Formation {
    protected int len,wid;
    abstract String get_content(int i,int j);
}

然后，根据Formantion来创建其他阵型：蛇形阵、鹤翼阵、方圆阵、老爷爷与蛇精的打call阵
以鹤翼阵为例
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
首先，明确鹤翼阵所占的具体区域面积，即注明空间长度与宽度
然后，将这个区域上面的杂草去除，以方便排兵布阵
最后，士兵归位
同时，get_content()函数，用来反映鹤翼阵每一具体位置的详细情况

所有的阵型都在Ground上，因此Ground类应包含可用的场地，与设置不同阵型的能力
同时，一场战斗之后，需要打扫战场，因此，这也是Ground应该具备的功能。
private int n;
protected String [][]G;
void setloc(Formation f,int x,int y) {
    for(int i=0;i<f.wid;i++)
        for(int j=0;j<f.len;j++)
        G[i+x][j+y]=f.get_content(i,j);
}

void clear_ground() {
    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++) {
            G[i][j]=PLACE_GRASS;
        }
}




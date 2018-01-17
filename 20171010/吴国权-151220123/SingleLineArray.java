public class SingleLineArray implements Array{//长蛇阵
    @Override
    public void rank(Creature[] creatures,Matrix matrix){
        for (int i=0;i<creatures.length;i++){
            creatures[i].Moveto(matrix.getPosition(3+i,3));
        }
        new BubbleSorter().sort(creatures);
    }
}

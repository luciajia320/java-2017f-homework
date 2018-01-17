public class HengeArray implements Array {//衡轭阵
    @Override
    public void rank(Creature[] creatures,Matrix matrix){
        for (int i=0;i<creatures.length;i++){
            creatures[i].Moveto(matrix.getPosition(3+i,7-i%2));
        }
    }
}

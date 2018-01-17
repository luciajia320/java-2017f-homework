public class CraneArray implements Array {
    @Override
    public void rank(Creature[] creatures,Matrix matrix){
        for (int i=0;i<creatures.length;i++){
            if (i<4){
                creatures[i].Moveto(matrix.getPosition(3+i,9-i));
            }
            else {
                creatures[i].Moveto(matrix.getPosition(3+i,3+i));
            }
        }
    }
}

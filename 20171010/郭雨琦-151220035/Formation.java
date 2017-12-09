import java.util.ArrayList;
enum FORMATIONS{
    HEYI,YANXING,CHONGE,CHANGSHE,YULIN,FANGYUAN,YANYUE,FENGSHI
}

public class Formation {
  public static void buildYANXING(ArrayList<Creature> queue,Position[][] positions,int leaderX, int leaderY, boolean pn){
      for(int i = 0; i < queue.size(); ++i){
          if(queue.get(i).getPosition()!=null)
              queue.get(i).getPosition().setHolder(null);
      }
      queue.get(0).setPosition(positions[leaderX][leaderY]);
      if(pn){
          for(int i = 1; i <queue.size(); ++i){
              queue.get(i).setPosition(positions[leaderX+i][leaderY-i]);
          }
      }
      else{
          for(int i = 1; i <queue.size(); ++i){
              queue.get(i).setPosition(positions[leaderX-i][leaderY+i]);
          }
      }
  }
  public static void buildCHANGSHE(ArrayList<Creature> queue,Position[][] positions,int leaderX, int leaderY, boolean pn){
      for(int i = 0; i < queue.size(); ++i){
          if(queue.get(i).getPosition()!=null)
              queue.get(i).getPosition().setHolder(null);
      }
      queue.get(0).setPosition(positions[leaderX][leaderY]);
      for(int i = 1; i <queue.size(); ++i){
          if(i%2==1){
              queue.get(i).setPosition(positions[leaderX][leaderY-(i+1)/2]);
          }
          else{
              queue.get(i).setPosition(positions[leaderX][leaderY+i/2]);
          }
      }
  }
  public static void bulidHEYI(ArrayList<Creature> queue,Position[][] positions,int leaderX, int leaderY, boolean pn){
      for(int i = 0; i < queue.size(); ++i){
          if(queue.get(i).getPosition()!=null)
            queue.get(i).getPosition().setHolder(null);
      }
      queue.get(0).setPosition(positions[leaderX][leaderY]);
      if(pn){
          for(int i = 1; i <queue.size(); ++i){
              if(i%2==1){
                  queue.get(i).setPosition(positions[leaderX+(i+1)/2][leaderY-(i+1)/2]);
              }
              else{
                  queue.get(i).setPosition(positions[leaderX+i/2][leaderY+i/2]);
              }
          }
      }
      else{
          for(int i = 1; i <queue.size(); ++i){
              if(i%2==1){
                  queue.get(i).setPosition(positions[leaderX-(i+1)/2][leaderY-(i+1)/2]);
              }
              else{
                  queue.get(i).setPosition(positions[leaderX-i/2][leaderY+i/2]);
              }
          }
      }
  }
}


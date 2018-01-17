


public class yaoguai extends organism implements Runnable{


      yaoguai(space sp,String ip,String st){
           SetSpace(sp);SetPath(ip);SetName(st);
      }



      public void cheerfor(){
           System.out.println("Ê¤¾ûÅ·°ÍË§Ë§Ë§£¬"+GetName()+"±¦±¦°®°®°®£¡");
      }

     public void remove(int i,int j){
      if(this.GetSpace().GetPosition(i,j).holder.GetName()!="****")
           if((this instanceof yaoguai&&this.GetSpace().GetPosition(i,j).holder instanceof Huluwa)||(this instanceof yaoguai&&this.GetSpace().GetPosition(i,j).holder instanceof supporting))
                 this.attack(i,j);

      organism a=this.GetSpace().GetPosition(i,j).holder;
      this.GetSpace().GetPosition(i,j).holder=this;
      this.GetSpace().GetPosition(this.GetX(),this.GetY()).holder=a;
      this.SetXY(i,j);
     }


     public void attack(int i,int j){
      if(this instanceof yaoguai&&this.GetSpace().GetPosition(i,j).holder instanceof Huluwa){
            double a=Math.random();
           if(a>=0.3*this.GetLife()/this.GetSpace().GetPosition(i,j).holder.GetLife()){
              organism ground=new organism();
              ground.SetName("****");
              this.GetSpace().GetPosition(this.GetX(),this.GetY()).holder=ground;
              this.GetSpace().GetPosition(i,j).holder.SetLife(a*this.GetSpace().GetPosition(i,j).holder.GetLife());
      
           }
           else{
              organism ground=new organism();
              ground.SetName("****");
              this.GetSpace().GetPosition(i,j).holder=ground;                          this.remove(i,j);
              this.SetLife((1-a)*this.GetLife());
          }
      }
      if(this instanceof yaoguai&&this.GetSpace().GetPosition(i,j).holder instanceof supporting){
              organism ground=new organism();
              ground.SetName("****");
              this.GetSpace().GetPosition(i,j).holder=ground;                          this.remove(i,j);
      }
     }


      public void run(){
       synchronized(this){
         /*for(int i=1;i<8;i++){
            if(this.GetSpace().GetPosition(i+8,12).holder.GetName()=="****"){
               try{
                 this.GetSpace().SetST(this,i+8,12);this.SetXY(i+8,12);break;
               }catch(Exception e){
                  e.printStackTrace();
               }
            }
         }*/
       if(this.GetName()=="Ð«¾«")
       {this.GetSpace().SetST(this,13,12);this.SetXY(13,12);}  

       if(this.GetName()=="Éß¾«")
       {this.GetSpace().SetST(this,14,12);this.SetXY(14,12);}
       if(this.GetName()=="¸ò¼×")
       {this.GetSpace().SetST(this,8,12);this.SetXY(8,12);}  

       if(this.GetName()=="¸òÒÒ")
       {this.GetSpace().SetST(this,9,12);this.SetXY(9,12);}
       if(this.GetName()=="¸ò±û")
       {this.GetSpace().SetST(this,10,12);this.SetXY(10,12);}  

       if(this.GetName()=="¸ò¶¡")
       {this.GetSpace().SetST(this,11,12);this.SetXY(11,12);}
       if(this.GetName()=="¸òÎì")
       {this.GetSpace().SetST(this,12,12);this.SetXY(12,12);}  


       }
      }
}
     
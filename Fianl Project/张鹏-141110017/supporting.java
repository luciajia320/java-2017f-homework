

public class supporting extends organism implements Runnable{

      public supporting(space sp,String ip){
           SetSpace(sp);SetName("ÀÏºº");SetPath(ip);
      }

      public void cheerfor(){
           System.out.println("Ê¤¾ûÅ·°ÍË§Ë§Ë§£¬"+GetName()+"±¦±¦°®°®°®£¡");
      }




      
      public void run(){
       synchronized(this){
         
            if(this.GetSpace().GetPosition(8,0).holder.GetName()=="****"){
               try{
                 this.GetSpace().SetST(this,8,0);
                 /*notifyAll();  */
               }catch(Exception e){
                  e.printStackTrace();
               }
            }
         
       }
      }
}
     
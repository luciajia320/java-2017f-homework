
public enum COLOR{
        
      RED("红色"),ORANGE("橙色"),YELLOW("黄色"),GREEN("绿色"),CYAN("青色"),BLUE("蓝色"),PURPLE("紫色");
      private String name;
     
      private COLOR(String name){
            this.name=name;
      }
      public String GetCoName(){
         return name;
      }
      public void SetName(String name){
          this.name=name;
      }
}



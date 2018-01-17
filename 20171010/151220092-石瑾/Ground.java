
public class Ground {
    public static final String PLACE_GRASS = "🍀";
    
    private int n;
    protected String [][]G;
    
    Ground(){
    		n=0;
    }

    public Ground(int n) {
        this.n = n;
		G=new String[n][n];
		
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++) {
				G[i][j]=PLACE_GRASS;	
			}
				
    }
    
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
    void printout() {
    		for(int i=0;i<n;i++) {
    			for(int j=0;j<n;j++) {
    				System.out.print(G[i][j]);
    				if(j==n-1)
    					System.out.println(" ");
    			}
    		}		
    }
  
}

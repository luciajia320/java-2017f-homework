package Homework3;

import java.util.Random;

public class SpaceMap {
	
	static int N;
	
	static Space[][] spacemap;
	
	SpaceMap(){
		Random r = new Random();
		N = r.nextInt(10) + 10;
		
		spacemap = new Space[N][N];
		
		//set to empty
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				spacemap[i][j] = new Space();
	}
	
	public void Initialize(){
		Random r = new Random();
		int i,j;
		
		HuluBrother[] huluwas = new HuluBrother[7];
		for(int order=1;order<8;order++){
			huluwas[order-1] = new HuluBrother(order);
			do{
				i=r.nextInt(N);
				j=r.nextInt(N);	
			}
			while(Add(huluwas[order-1],i,j)!=true);
		}
		
		/*
		Grandpa grandpa = new Grandpa();
		do{
			i=r.nextInt(N);
			j=r.nextInt(N);	
		}
		while(Add(grandpa,i,j)!=true);
		
		Scorpion scorpion = new Scorpion();
		do{
			i=r.nextInt(N);
			j=r.nextInt(N);	
		}
		while(Add(scorpion,i,j)!=true);
		
		Snake snake = new Snake();
		do{
			i=r.nextInt(N);
			j=r.nextInt(N);	
		}
		while(Add(snake,i,j)!=true);
		
		int MinionNumber = r.nextInt(10)+10;
		for(int index=0;index<MinionNumber;index++){
			Minion minion = new Minion();
			do{
				i=r.nextInt(N);
				j=r.nextInt(N);	
			}
			while(Add(minion,i,j)!=true);
		}
		*/
	}
	
	public void QueueupHuluBrothers(){
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(spacemap[i][j].used == true 
							&& spacemap[i][j].usage.ID >= 5
							&& spacemap[i][j].usage.ID <= 11){
					Move(i,j,N-13+spacemap[i][j].usage.ID,N-2,true);
				}
			}
		}	
	}
	
	public void PlaceScorpionMinions(){
		
	}
	
	public boolean Add(Creature c, int i, int j){
		if(spacemap[i][j].used == true){
			System.out.println("There's already a creature.");
			return false;
		}
		else{
			spacemap[i][j].used = true;
			spacemap[i][j].usage = c;
			return true;
		}
	}
	
	public void Remove(int i, int j) throws Exception{
		if(spacemap[i][j].used == false){
			System.out.println("There's no creature, no need to remove.");
		}
		else{
			spacemap[i][j].used = false;
			//Ah, seems that I can't delete an object directly in java.
		}
	}
	
	public boolean Move(int Fromi, int Fromj, int Toi, int Toj, boolean enableSwap){
		if(spacemap[Toi][Toj].used == true){
			if(enableSwap == false){
				System.out.println("There's already a creature.");
				return false;
			}
			else{
				Space temp = new Space();
				temp.used = spacemap[Toi][Toj].used;
				temp.usage = spacemap[Toi][Toj].usage;
				
				spacemap[Toi][Toj].used = spacemap[Fromi][Fromj].used;
				spacemap[Toi][Toj].usage = spacemap[Fromi][Fromj].usage;
				
				spacemap[Fromi][Fromj].used = temp.used;
				spacemap[Fromi][Fromj].usage = temp.usage;
				return true;
			}
		}
		else{
			spacemap[Toi][Toj].used = spacemap[Fromi][Fromj].used;
			spacemap[Toi][Toj].usage = spacemap[Fromi][Fromj].usage;
			spacemap[Fromi][Fromj].used = false;
			return true;
		}
	}
	
	public void Print(){
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(spacemap[i][j].used==true){
					System.out.print(spacemap[i][j].usage.Name+ "     ");
				}
				else
					System.out.print(0 + "     ");
			}
			System.out.println();
		}
			
	}
}
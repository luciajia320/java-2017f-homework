package Homework3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Random;

enum Deployment{
	Crane,
	Goose,
	Yoke,
	Snake,
	Fish,
	Square,
	Moon,
	Arrow;
}

public class Universe {

	static int N;
	
	static Space[][] spacemap;
	
	Universe(){
		Random r = new Random();
		N = r.nextInt(10) + 15;
		
		spacemap = new Space[N][N];
		
		//set to empty
		for(int i=0;i<N;i++)
			for(int j=0;j<N;j++)
				spacemap[i][j] = new Space(i,j);
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
		
	}
	
	public void QueueupHuluBrothers(){
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(spacemap[i][j].used == true 
							&& spacemap[i][j].usage.returnID() >= 5
							&& spacemap[i][j].usage.returnID() <= 11){
					Move(i,j,N/2+spacemap[i][j].usage.returnID()-9,N/2+4,true);
				}
			}
		}	
	}
	
	public void ClearMinions(){
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(spacemap[i][j].used == true && spacemap[i][j].usage.returnID() == 3){
					Remove(i,j);
				}
			}
		}
	}
	
	public void DeployScorpion(){
		Scorpion scorpion = new Scorpion();
		int i,j;
		i=N/2;
		j=N/2;
		Add(scorpion,i,j); //Should check availability
	}
	
	public void DeploySnake(){
		Snake snake = new Snake();
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(spacemap[i][j].used == true && spacemap[i][j].usage.returnID() == 2){
					Add(snake,i+1,j+1);
				}
			}
		}
	}
	
	public void DeployGrandpa(){
		Grandpa grandpa = new Grandpa();
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(spacemap[i][j].used == true && spacemap[i][j].usage.returnID() == 5){
					Add(grandpa,i-1,j-1);
				}
			}
		}
	}
	
	public void DeployMinions(Deployment d){
		int i,j;
		i=N/2;
		j=N/2;
		
		ClearMinions();
		int MinionNumber;
		switch(d){
		case Crane:
			MinionNumber = 6;
			for(int index=0;index<MinionNumber/2;index++){
				Minion minion = new Minion();
				do{
					i++;
					j--;
				}
				while(Add(minion,i,j)!=true);
			}
			for(int index=0;index<MinionNumber/2;index++){
				Minion minion = new Minion();
				do{
					i--;
					j--;
				}
				while(Add(minion,i,j)!=true);
			}
			break;
		case Goose:
			MinionNumber = 4;
			for(int index=0;index<MinionNumber;index++){
				Minion minion = new Minion();
				do{
					i++;
					j--;
				}
				while(Add(minion,i,j)!=true);
			}
			break;
		case Yoke:
			MinionNumber = 5;
			for(int index=0;index<MinionNumber;index++){
				Minion minion = new Minion();
				do{
					if(index % 2 == 0){
						i++;
						j--;
					}
					else{
						i++;
						j++;
					}
					
				}
				while(Add(minion,i,j)!=true);
			}
			break;
		case Fish:
			MinionNumber = 9;
			for(int index=0;index<MinionNumber;index++){
				Minion minion = new Minion();
				do{
					if(index > 7){
						i--;
						j--;
					}
					else if(index > 5){
						j+=2;
					}
					else if(index > 2){
						i++;
						j--;
					}
					else{
						i--;
						j--;
					}
				}
				while(Add(minion,i,j)!=true);
			}
			Move(i-1,j-1,i+2,j,true);
			break;
		case Square:
			MinionNumber = 7;
			for(int index=0;index<MinionNumber;index++){
				Minion minion = new Minion();
				do{
					if(index > 5){
						i--;
						j++;
					}
					else if(index > 3){
						i++;
						j++;
					}
					else if(index > 1){
						i++;
						j--;
					}
					else{
						i--;
						j--;
					}
				}
				while(Add(minion,i,j)!=true); 
			}
			break;
		case Moon:
			MinionNumber = 18;
			for(int index=0;index<MinionNumber;index++){
				Minion minion = new Minion();
				do{
					if(index > 15){
						i--;
					}
					else if(index == 15){
						i-=2;
						j-=4;
					}
					else if(index > 12){
						i++;
						j++;
					}
					else if(index > 10){
						i++;
					}
					else if(index > 8){
						i++;
						j--;
					}
					else if(index == 8){
						i++;
						j-=3;
					}
					else if(index > 4){
						i--;
						j++;
					}
					else if(index > 2){
						i--;
					}
					else{
						i--;
						j--;
					}
				}
				while(Add(minion,i,j)!=true);
			}
			break;
		case Arrow:
			MinionNumber = 11;
			for(int index=0;index<MinionNumber;index++){
				Minion minion = new Minion();
				do{
					if(index > 6){
						i++;
					}
					else if(index == 6){
						i-=2;
						j+=3;
					}
					else if(index > 2){
						i++;
						j--;
					}
					else{
						i--;
						j--;
					}
				}
				while(Add(minion,i,j)!=true);
			}
			break;
		case Snake:
		{
			MinionNumber = 5;
			for(int index=0;index<MinionNumber;index++){
				Minion minion = new Minion();
				do{
					i++;
				}
				while(Add(minion,i,j)!=true);
			}
			break;
		}
		default:
		}
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
	
	public void Remove(int i, int j){
			spacemap[i][j].used = false;
			spacemap[i][j].usage = null;
			//Ah, seems that I can't delete an object directly in java.
	}
	
	public boolean Move(int Fromi, int Fromj, int Toi, int Toj, boolean enableSwap){
		if(spacemap[Toi][Toj].used == true){
			if(enableSwap == false){
				System.out.println("There's already a creature.");
				return false;
			}
			else{
				Space temp = new Space(Toi,Toj);
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
					System.out.print(spacemap[i][j].usage.returnName()+ "     ");
				}
				else
					System.out.print(0 + "     ");
			}
			System.out.println();
		}
			
	}
	
	public void PrinttoEmoji(){
		File file = new File("/before.html");
		if(file.exists()){
			//System.out.println("File already exists.");
			file = new File("/after.html");
		}
		try{
			Writer filewriter = new BufferedWriter(  
			        new OutputStreamWriter(  
			                new FileOutputStream(file), "UTF-8"));  
			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++){
					if(spacemap[i][j].used==false){
						filewriter.write("🌿");
					}
					else{
						switch(spacemap[i][j].usage.returnID()){
						case 1:
							filewriter.write("👴");
							break;
						case 2:
							filewriter.write("🦂");
							break;
						case 3:
							filewriter.write("🐊");
							break;
						case 4:
							filewriter.write("🐍");
							break;
						case 5:
							filewriter.write("①");
							break;
						case 6:
							filewriter.write("②");
							break;
						case 7:
							filewriter.write("③");
							break;
						case 8:
							filewriter.write("④");
							break;
						case 9:
							filewriter.write("⑤");
							break;
						case 10:
							filewriter.write("⑥");
							break;
						case 11:
							filewriter.write("⑦");
							break;
						default:
						}
					}
				}
				filewriter.write("\r");
			}
			filewriter.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
		
	}
}
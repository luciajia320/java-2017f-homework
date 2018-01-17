package niuxuFrame;

import nju.java.*;
import niuxuTool.*;
import java.util.ArrayList;
import java.util.Random;

import niuxuCharacter.Player;
import niuxuCharacter.alter_ego;
import niuxuCharacter.avenger;
import niuxuCharacter.ruler;
import niuxuCharacter.servant;
import niuxuStrategy.ArrowStrategy;
import niuxuStrategy.GapStrategy;
import niuxuStrategy.GooseStrategy;
import niuxuStrategy.RecStrategy;
import niuxuStrategy.SnakeStrategy;
import niuxuStrategy.strategy;

public class Team {
	private boolean isKind;
	private ArrayList players = new ArrayList();
	private strategy ourStrategy;
	public Team() {
		super();
	}
	public Team(boolean isKind) {
		this.isKind = isKind;
		Random rand = new Random();
		int choice = rand.nextInt(5);
		switch(choice) {
		case 0: {
			ourStrategy = new ArrowStrategy(isKind);
			break;
		}
		case 1: {
			ourStrategy = new GapStrategy(isKind);
			break;
		}
		case 2: {
			ourStrategy = new GooseStrategy(isKind);
			break;
		}
		case 3: {
			ourStrategy = new RecStrategy(isKind);
			break;
		}
		case 4: {
			ourStrategy = new SnakeStrategy(isKind);
			break;
		}
		}
	}
	public ArrayList getPlayers() {
		return players;
	}
	
	//按照随机出来的方法去向players添加对应坐标的player
	public void setPlayers(Field field) {
        Player b;
        if(isKind) {                        //创建好人队伍
        	for(int i=0; i<16; i++) {
        		for(int j=0; j<8; j++) {
        			switch(ourStrategy.getStra()[i][j]) {
        			case 0: {
        				break;
        			}
        			case 1: {
        				b = new servant(field.getPoints()[i][j],1,field);
        				players.add(b);
        				break;
        			}
        			case 2: {
        				b = new servant(field.getPoints()[i][j],2,field);
        				players.add(b);
        				break;
        			}
        			case 3: {
        				b = new servant(field.getPoints()[i][j],3,field);
        				players.add(b);
        				break;
        			}
        			case 4: {
        				b = new servant(field.getPoints()[i][j],4,field);
        				players.add(b);
        				break;
        			}
        			case 5: {
        				b = new servant(field.getPoints()[i][j],5,field);
        				players.add(b);
        				break;
        			}
        			case 6: {
        				b = new servant(field.getPoints()[i][j],6,field);
        				players.add(b);
        				break;
        			}
        			case 7: {
        				b = new servant(field.getPoints()[i][j],7,field);
        				players.add(b);
        				break;
        			}
        			case 8: {
        				b = new ruler(field.getPoints()[i][j],field);
        				players.add(b);
        				break;
        			}
        			}
        		}
        	}
        }
        else {                    //创建坏人队伍
        	for(int i=0; i<16; i++) {
        		for(int j=0; j<8; j++) {
        			switch(ourStrategy.getStra()[i][j]) {
        			case 0: {
        				break;
        			}
        			case 1: {
        				b = new alter_ego(field.getPoints()[i][j],field);
        				players.add(b);
        				break;
        			}
        			case 2: {
        				b = new avenger(field.getPoints()[i][j],field);
        				players.add(b);
        				break;
        			}
        			}
        		}
        	}
        }
	}
}
package Homework3;

public class Main {
	public static void main(String args[]){
		Universe universe = new Universe();
		universe.Initialize();
		universe.QueueupHuluBrothers();
		universe.DeployScorpion();
		universe.DeploySnake();
		universe.DeployGrandpa();
		universe.DeployMinions(Deployment.Arrow);
		universe.PrinttoEmoji();
		
		universe.DeployMinions(Deployment.Moon);
		universe.PrinttoEmoji();
	}
}

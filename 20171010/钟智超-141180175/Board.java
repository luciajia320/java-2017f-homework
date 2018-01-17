public class Board{
	private Position[][] position;
	
	public int x,y;

	public Board(int x,int y){
		this.x = x;
		this.y = y;
		this.position = new Position[x][y];
		for(int i = 0;i < x;i++){
			for(int j = 0;j < y;j++){
				this.position[i][j] = new Position(x,y);
			}
		}
	}

	public Position[][] getPosition(){
		return position;
	}

	public void boardInsertion(Board board,int x_ins,int y_ins){
		for(int i = x_ins;i < x_ins + board.x;i++){
			for(int j = y_ins;j < y_ins + board.y;j++){
				this.position[i][j].changePosition(board.position[i - x_ins][j - y_ins]);
				//error:
				//this.position[i][j].setHolder(board.position[i - x_ins][j - y_ins].getHolder());
				//memory error
			}
		}
	}

	public void output(){
		for(int i = 0;i < x;i++){
			for(int j = 0;j < y;j++){
				if(position[i][j].getHolder() != null){
					position[i][j].getHolder().outputPicture();
				}
				else if(position[i][j].ifBorder()){
					System.out.print("**");
				}
				else{
					System.out.print("  ");
				}
			}System.out.println("");
		}System.out.println("");
	}
}

public class test {

	public static void main(String[] args) {
		Map map=new Map(15);
		BadGuy bad=new BadGuy();
		Brothers bro=new Brothers();
		map.setBrothers(bro);
		map.setGrandpa(5, 0);
		map.setSnake(5, 14);
		map.brothersStrategy(2, 2);
		map.setBadGuy(bad);
		map.badGuyStrategy(5, 5);
		map.showMap();
	}

}

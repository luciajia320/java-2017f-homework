package huluwa2;

enum Species {
	HULUWA, GRANDPA, SOLDIERS, SNAKE, SCORPION,
}

public abstract class Creature {
	public Position position;
	public Species species;

	public abstract void report();

	Creature() {
		position = null;
	}

	public Position getPosition() {
		return this.position;
	}

	public void setPosition() {
		if (position.Empty()) {
			leavePosition();
			position.setHolder(this);
			this.position = position;
		} else
			;
	}

	public void leavePosition() {
		if (this.position != null) {
			position.out();
			this.position = null;

		}
	}
}

class Grandpa extends Creature {//爷爷继承生物类
	Grandpa() {
		this.species = Species.GRANDPA;
	}
	public void report() {
		System.out.print("爷爷");
	}
	class Snake extends Creature {//蛇精继承生物类
		Snake() {
			this.species = Species.SNAKE;
		}
		public void report() {
			System.out.print("蛇精");
		}
	}
}

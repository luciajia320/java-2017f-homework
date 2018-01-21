package nju.java;

public class Replayer implements Runnable{
	Field field;
	Record record;
	public Replayer(Field field,Record record){
		this.field=field;
		this.record=record;
		
	}
	public void run(){
		String str;
		str=record.readRecord();
		while(str!=null){
			field.perform(str);
			try {
               Thread.sleep(500);
               this.field.repaint();
			} catch (Exception e) {
			
			}
			str=record.readRecord();
		}
	}
}

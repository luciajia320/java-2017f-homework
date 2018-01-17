package com.gxs;



public  class Soldier extends Fighter
{
	private  Order order;
	
	Soldier(String name,int speed,int power,Camp camp)
	{
		super(name,speed,power,camp);
	}
	public void setOrder(Order order)
	{
		this.order=order;
	}
	public void run()
	{
		try {
			while(this.isAlive()&&!Thread.interrupted()) 
			{
				//等待命令
				order.waitForOrder();
				//执行命令
				if(!this.isAlive())
				{
					System.out.println("i am dead but i am awaken");
					break;
				}
				executeOrder();
				//报告完成
				order.carryOutOrder();
			}
		}catch(InterruptedException e)
		{
			System.out.println(getName()+":  exits via interruption");
			Thread.currentThread().interrupt();
		}
		System.out.println(this+" run over");
	}
	
	protected void KIA()
	{
		//killed in battle with mission unfinished 
		System.out.println(this+" KIA");
		if(order.hasOrder())
			order.carryOutOrder();
		this.killSelf();
	}
	//may override for different soldiers
	protected void executeOrder()
	{
		OrderItem item=order.getOrderItem();
		if(MoveToOrder.class.isInstance(item))
		{
			MoveToOrder m=(MoveToOrder)item;
			moveTo(m.getDest());
		}
		else if(FreeOrder.class.isInstance(item))
		{	
			freeMove();
		}
		else if(MarchOrder.class.isInstance(item))
		{
			MarchOrder m=(MarchOrder)item;
			int s=m.getSpeed()>getSpeed()?getSpeed():m.getSpeed();
			march(s,m.getDir());
		}
		else if(WinOrDieOrder.class.isInstance(item))
		{
			this.engageEnemy();
		}
		else
		{
			return;
		}
	}
}
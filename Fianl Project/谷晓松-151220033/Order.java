package com.gxs;

import java.util.ArrayList;

class OrderList
{
	private ArrayList<Order>orderList=new ArrayList<Order>();
	private int waiting=0;
	
	public Order add()
	{
		Order o=new Order(this);
		orderList.add(o);
		return o;
	}
	public int size()
	{
		return orderList.size();
	}
	public Order get(int i)
	{
		return orderList.get(i);
	}
	public void setOrder(int i,OrderItem o)
	{
		orderList.get(i).setOrder(o);
	}
	public synchronized void finishOne()
	{
		waiting--;
		notifyAll();
	}
	public synchronized void issueOrder(int index)
	{
		waiting++;
		orderList.get(index).issueOrder();
	}
	public synchronized void waitingForExecution() throws InterruptedException
	{
		System.out.println(waiting);
		while(waiting>0)
			wait();
	}
}
public class Order
{
	private OrderItem content;
	private boolean isValid=false;
	private OrderList belongto;
	
	Order(OrderList belongto)
	{
		this.belongto=belongto;
	}
	public boolean hasOrder()
	{
		return isValid;
	}
	public void setOrder(OrderItem c)
	{
		content=c;
	}
	public synchronized void issueOrder()
	{
		isValid=true;
		notifyAll();
	}
	public synchronized void carryOutOrder()
	{
		isValid=false;
		belongto.finishOne();
	}
	public synchronized void waitForOrder() throws InterruptedException
	{
		while(isValid==false)
			wait();
	}
	public OrderItem getOrderItem()
	{
		return content;
	}
}
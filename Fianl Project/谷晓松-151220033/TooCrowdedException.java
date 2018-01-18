package com.gxs;

public class TooCrowdedException extends Exception
{
	public TooCrowdedException(String msg)
	{
		super(msg);
	}
}

class LackOfCreatureException extends Exception
{
	public LackOfCreatureException(String msg)
	{
		super(msg);
	}
}
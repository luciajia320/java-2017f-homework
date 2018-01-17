class HuluBoy
{
	int rank;
	String name;
	String color;

	HuluBoy(int rank_var)
	{
		rank = rank_var;
		switch(rank_var)
		{
			case 1:name = "大娃";color = "红";break;
			case 2:name = "二娃";color = "橙";break;
			case 3:name = "三娃";color = "黄";break;
			case 4:name = "四娃";color = "绿";break;
			case 5:name = "五娃";color = "青";break;
			case 6:name = "六娃";color = "蓝";break;
			case 7:name = "七娃";color = "紫";break;
			default:System.out.println("Error ranking!");
		}
	}
}
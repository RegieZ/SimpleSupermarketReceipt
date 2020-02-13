package fundamentalEnhance;

import java.util.ArrayList;
import java.util.Scanner;

//商品类
class Goods {
	private String id;  //商品id
	private String name; //名称
	private double price; //单价
	private String unit;  //计价单位

	private int count; //数量

	//空参和带参构造方法
	public Goods() {
	}

	public Goods(String id, String name, double price, String unit) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.unit = unit;
	}

	//getter和setter方法
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}


//主方法运行类
public class SupermarketReceipt {
	public static void main(String[] args) {
		//创建4个商品对象
		ArrayList<Goods> list = new ArrayList<>();
		list.add(new Goods("001","少林核桃",15.5,"斤"));
		list.add(new Goods("002","尚康饼干",14.5,"包"));
		list.add(new Goods("003","移动硬盘",345.0,"个"));
		list.add(new Goods("004","高清无码",199.0,"G"));
		Scanner sc = new Scanner(System.in);

		while (true){
			System.out.println("请输入你要进行的操作：");
			System.out.println("1:购买商品  2:结算并打印小票   3:退出系统");
			int num = sc.nextInt();
			switch(num){
			case 1:  //购买商品
				showAllGoods(list);
				break;
			case 2:  //结算并打印小票
				payAndShow(list);
				break;
			case 3:
				System.out.println("感谢您使用超市购物系统，欢迎下次光临，拜拜");
				System.exit(0);
			default:
				System.out.println("输入无对应操作，请重新输入");
			}
		}


	}

	//定义方法，实现购买商品的操作
	public static void showAllGoods(ArrayList<Goods> list){
		System.out.println("---------------------------");
		System.out.println("          商品列表         ");
		System.out.println("商品id\t\t名称\t\t单价\t\t计价单位");
		for (int i = 0; i < list.size(); i++) {
			Goods g = list.get(i);
			System.out.println(g.getId()+"\t\t"+g.getName()+"\t\t"+g.getPrice()+"\t\t"+g.getUnit());
		}
		System.out.println("---------------------------");
		System.out.println("请输入您要购买的商品项（输入格式：商品id-购买数量）,输入end表示购买结束。");
		Scanner sc = new Scanner(System.in);

		while(true){
			String line = sc.nextLine();
			String[] good = line.split("-");

			if("end".equals(line)){
				System.out.println("购买结束");
				break;
			}
			//判断格式是否正确
			if(good.length!=2){
				System.out.println("你输入的购买姿势不对，请换个姿势再来一次(格式：商品id-购买数量)");
				continue;
			}
			//判断id是否存在
			if(!isIdExist(list,good[0])){
				System.out.println("您输入的商品id不存在，请重新输入！");
				continue;
			}

			//如果程序运行到这里，说明输入正确，添加到商品里面
			addGoods(list, good[0], Integer.valueOf(good[1]));
		}
	}

	//定义方法判断商品id是否存在
	public static boolean isIdExist(ArrayList<Goods> list,String id){
		for (int i = 0; i < list.size(); i++) {
			Goods g = list.get(i);
			if(g.getId().equals(id)){
				//遍历查找，找到返回true
				return true;
			}
		}
		//找不到返回false
		return false;
	}

	//定义方法，添加购买的商品数量
	public static void addGoods(ArrayList<Goods> list, String id, int count){
		//根据id遍历查找，更新数量
		for (int i = 0; i < list.size(); i++) {
			// 问题：构造方法中没有get()这个方法
			Goods g = list.get(i);
			if(g.getId().equals(id)){
				int oldCount = g.getCount();
				g.setCount(oldCount+count);
				return;
			}
		}
	}


	//定义方法，结算并打印小票
	public static void payAndShow(ArrayList<Goods> list){
		System.out.println("---------------------------");
		System.out.println("          欢迎光临         ");
		System.out.println("名称\t售价\t数量\t金额");

		//定义变量统计购买的商品种类
		int type = 0;
		//定义变量统计总共买了几件商品
		int all  = 0;
		//定义变量统计总金额
		double allMoney = 0.0;

		for (int i = 0; i < list.size(); i++) {
			Goods g = list.get(i);
			//判断数量是否为0，如果数量不为零，表示有购买该商品
			int count = g.getCount();
			if(count!=0) {
				//种类自增
				type++;
				//数量累加
				all+=count;
				double price = g.getPrice(); //获取单价
				double money = price * count; //每项商品的购买金额
				//总金额累加
				allMoney+=money;
				System.out.println(g.getName() + "\t" + price + "\t" + count + "\t" + money);
			}
		}

		System.out.println("---------------------------");
		System.out.println(type+"项商品");
		System.out.println("共计："+all+"件");
		System.out.println("共："+allMoney+"元");
		System.out.println("---------------------------");

		//注意：购买并打印完小票后，要对原先的购买记录清零
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setCount(0);
		}

	}

}
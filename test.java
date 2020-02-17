import java.util.ArrayList;
import java.util.Scanner;

/**
 * 简易超市小票
 *
 * @author Regino
 */
class Goods {
    private String id;
    private String name;
    private double price;
    private String unit;
    private int count;

    public Goods() {
    }

    public Goods(String id, String name, double price, String unit) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.unit = unit;
    }

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

public class test {
    public static void main(String[] args) {
        ArrayList<Goods> list = new ArrayList<>();
        list.add(new Goods("001", "核桃", 15.5, "斤"));
        list.add(new Goods("002", "饼干", 14.5, "包"));
        list.add(new Goods("003", "移动硬盘", 345.0, "个"));
        list.add(new Goods("004", "高清无码", 199.0, "G"));
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("请输入你要进行的操作：");
            System.out.println("1:购买商品  2:结算并打印小票   3:退出系统");
            int num = sc.nextInt();
            switch (num) {
                case 1:
                    showAllGoods(list);
                    break;
                case 2:
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
    public static void showAllGoods(ArrayList<Goods> list) {
        System.out.println("---------------------------");
        System.out.println("          商品列表         ");
        System.out.println("商品id\t\t名称\t\t单价\t\t计价单位");
        for (int i = 0; i < list.size(); i++) {
            Goods g = list.get(i);
            System.out.println(g.getId() + "\t\t" + g.getName() + "\t\t" + g.getPrice() + "\t\t" + g.getUnit());
        }
        System.out.println("---------------------------");
        System.out.println("请输入您要购买的商品项（输入格式：商品id-购买数量）,输入end表示购买结束。");
        Scanner sc = new Scanner(System.in);

        while (true) {
            String line = sc.nextLine();
            String[] good = line.split("-");

            if ("end".equals(line)) {
                System.out.println("购买结束");
                break;
            }
            if (good.length != 2) {
                System.out.println("你输入的购买姿势不对，请换个姿势再来一次(格式：商品id-购买数量)");
                continue;
            }
            if (!isIdExist(list, good[0])) {
                System.out.println("您输入的商品id不存在，请重新输入！");
                continue;
            }
            addGoods(list, good[0], Integer.valueOf(good[1]));
        }
    }

    public static boolean isIdExist(ArrayList<Goods> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            Goods g = list.get(i);
            if (g.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    //定义方法，添加购买的商品数量
    public static void addGoods(ArrayList<Goods> list, String id, int count) {
        for (int i = 0; i < list.size(); i++) {
            Goods g = list.get(i);
            if (g.getId().equals(id)) {
                int oldCount = g.getCount();
                g.setCount(oldCount + count);
                return;
            }
        }
    }

    //定义方法，结算并打印小票
    public static void payAndShow(ArrayList<Goods> list) {
        System.out.println("---------------------------");
        System.out.println("          欢迎光临         ");
        System.out.println("名称\t售价\t数量\t金额");
        int type = 0;
        int all = 0;
        double allMoney = 0.0;
        for (int i = 0; i < list.size(); i++) {
            Goods g = list.get(i);
            int count = g.getCount();
            if (count != 0) {
                type++;
                all += count;
                double price = g.getPrice();
                double money = price * count;
                allMoney += money;
                System.out.println(g.getName() + "\t" + price + "\t" + count + "\t" + money);
            }
        }
        System.out.println("---------------------------");
        System.out.println(type + "项商品");
        System.out.println("共计：" + all + "件");
        System.out.println("共：" + allMoney + "元");
        System.out.println("---------------------------");
        //购买并打印完小票后，要对原先的购买记录清零
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setCount(0);
        }
    }
}


package fundamentalEnhance;

import java.util.ArrayList;
import java.util.Scanner;

//��Ʒ��
class Goods {
	private String id;  //��Ʒid
	private String name; //����
	private double price; //����
	private String unit;  //�Ƽ۵�λ

	private int count; //����

	//�ղκʹ��ι��췽��
	public Goods() {
	}

	public Goods(String id, String name, double price, String unit) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.unit = unit;
	}

	//getter��setter����
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


//������������
public class SupermarketReceipt {
	public static void main(String[] args) {
		//����4����Ʒ����
		ArrayList<Goods> list = new ArrayList<>();
		list.add(new Goods("001","���ֺ���",15.5,"��"));
		list.add(new Goods("002","�п�����",14.5,"��"));
		list.add(new Goods("003","�ƶ�Ӳ��",345.0,"��"));
		list.add(new Goods("004","��������",199.0,"G"));
		Scanner sc = new Scanner(System.in);

		while (true){
			System.out.println("��������Ҫ���еĲ�����");
			System.out.println("1:������Ʒ  2:���㲢��ӡСƱ   3:�˳�ϵͳ");
			int num = sc.nextInt();
			switch(num){
			case 1:  //������Ʒ
				showAllGoods(list);
				break;
			case 2:  //���㲢��ӡСƱ
				payAndShow(list);
				break;
			case 3:
				System.out.println("��л��ʹ�ó��й���ϵͳ����ӭ�´ι��٣��ݰ�");
				System.exit(0);
			default:
				System.out.println("�����޶�Ӧ����������������");
			}
		}


	}

	//���巽����ʵ�ֹ�����Ʒ�Ĳ���
	public static void showAllGoods(ArrayList<Goods> list){
		System.out.println("---------------------------");
		System.out.println("          ��Ʒ�б�         ");
		System.out.println("��Ʒid\t\t����\t\t����\t\t�Ƽ۵�λ");
		for (int i = 0; i < list.size(); i++) {
			Goods g = list.get(i);
			System.out.println(g.getId()+"\t\t"+g.getName()+"\t\t"+g.getPrice()+"\t\t"+g.getUnit());
		}
		System.out.println("---------------------------");
		System.out.println("��������Ҫ�������Ʒ������ʽ����Ʒid-����������,����end��ʾ���������");
		Scanner sc = new Scanner(System.in);

		while(true){
			String line = sc.nextLine();
			String[] good = line.split("-");

			if("end".equals(line)){
				System.out.println("�������");
				break;
			}
			//�жϸ�ʽ�Ƿ���ȷ
			if(good.length!=2){
				System.out.println("������Ĺ������Ʋ��ԣ��뻻����������һ��(��ʽ����Ʒid-��������)");
				continue;
			}
			//�ж�id�Ƿ����
			if(!isIdExist(list,good[0])){
				System.out.println("���������Ʒid�����ڣ����������룡");
				continue;
			}

			//����������е����˵��������ȷ����ӵ���Ʒ����
			addGoods(list, good[0], Integer.valueOf(good[1]));
		}
	}

	//���巽���ж���Ʒid�Ƿ����
	public static boolean isIdExist(ArrayList<Goods> list,String id){
		for (int i = 0; i < list.size(); i++) {
			Goods g = list.get(i);
			if(g.getId().equals(id)){
				//�������ң��ҵ�����true
				return true;
			}
		}
		//�Ҳ�������false
		return false;
	}

	//���巽������ӹ������Ʒ����
	public static void addGoods(ArrayList<Goods> list, String id, int count){
		//����id�������ң���������
		for (int i = 0; i < list.size(); i++) {
			// ���⣺���췽����û��get()�������
			Goods g = list.get(i);
			if(g.getId().equals(id)){
				int oldCount = g.getCount();
				g.setCount(oldCount+count);
				return;
			}
		}
	}


	//���巽�������㲢��ӡСƱ
	public static void payAndShow(ArrayList<Goods> list){
		System.out.println("---------------------------");
		System.out.println("          ��ӭ����         ");
		System.out.println("����\t�ۼ�\t����\t���");

		//�������ͳ�ƹ������Ʒ����
		int type = 0;
		//�������ͳ���ܹ����˼�����Ʒ
		int all  = 0;
		//�������ͳ���ܽ��
		double allMoney = 0.0;

		for (int i = 0; i < list.size(); i++) {
			Goods g = list.get(i);
			//�ж������Ƿ�Ϊ0�����������Ϊ�㣬��ʾ�й������Ʒ
			int count = g.getCount();
			if(count!=0) {
				//��������
				type++;
				//�����ۼ�
				all+=count;
				double price = g.getPrice(); //��ȡ����
				double money = price * count; //ÿ����Ʒ�Ĺ�����
				//�ܽ���ۼ�
				allMoney+=money;
				System.out.println(g.getName() + "\t" + price + "\t" + count + "\t" + money);
			}
		}

		System.out.println("---------------------------");
		System.out.println(type+"����Ʒ");
		System.out.println("���ƣ�"+all+"��");
		System.out.println("����"+allMoney+"Ԫ");
		System.out.println("---------------------------");

		//ע�⣺���򲢴�ӡ��СƱ��Ҫ��ԭ�ȵĹ����¼����
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setCount(0);
		}

	}

}
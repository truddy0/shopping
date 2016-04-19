package page;

import java.util.ArrayList;

import dao.GoodsDao;
import entity.Goods;
import tools.QueryPrint;
import tools.ScannerChoice;

public final class GoodsPage extends ScannerChoice{
	/*
	 * 1. add Goods Page
	 */
	public static void addGoodsPage(){
		System.out.println("\t Adding goods \n");
		
		System.out.println("\n Input added Goods - name");
		String goodsName = ScannerInfoString();
		
		System.out.println("\n Input added Goods - price");
		double goodsPrice = ScannerInfo();
		
		System.out.println("\n Input added Goods - name");
		int goodsNumber = ScannerNum();
		
		Goods goods = new Goods(goodsName, goodsPrice, goodsNumber);
		boolean bool = new GoodsDao().addGoods(goods);
		
		if (bool) {
			System.out.println("\n\t Adding goods succeeded!");
		} else {
			System.out.println("\n\t Adding goods failed!");
		}
		changedInfoNext("addGoodsPage");
	}
	/*
	 * 2. change Goods Page
	 */
	public static void updateGoodsPage(){
		System.out.println("\t Updating goods\n");
		System.out.println("Input updated goods name");
		
		int gid = QueryPrint.query("updateGoodsPage");
		System.out.println("\n-----select updated content\n");
		System.out.println("\t1.Change goods-name");
		System.out.println("\t2.Change goods-price");
		System.out.println("\t3.Change goods-amount");
		System.out.println("\nInput option, or press 0 back");
		
		do {
			String choice = ScannerInfoString();
			String regex = "[0-3]";
			if(choice.matches(regex)){
				int info = Integer.parseInt(choice);
				switch(info){
				case 0: 
					MainPage.MaintenancePage();
					break;
				case 1:
					System.out.println("Input goods- new name");
					String gname = ScannerInfoString();
					Goods goodsName = new Goods(gid,gname);
					boolean boolName = new GoodsDao().updateGoods(1, goodsName);
					if (boolName) {
						System.out.println("\n\t Updating Succeeded!");
					} else {
						System.err.println("\n\t Updatint Failed");
					}
					changedInfoNext("updateGoodsPage");
					break;
				case 2:
					System.out.println("Input goods- new price");
					double gprice = ScannerInfo();
					Goods goodsPrice = new Goods(gid,gprice);
					boolean boolPrice = new GoodsDao().updateGoods(1, goodsPrice);
					if (boolPrice) {
						System.out.println("\n\t Updating Succeeded!");
					} else {
						System.err.println("\n\t Updatint Failed");
					}
					changedInfoNext("updateGoodsPage");
					break;
				case 3:
					System.out.println("Input goods- new amount");
					int gNum = ScannerNum();
					Goods goodsNum = new Goods(gid,gNum);
					boolean boolNum = new GoodsDao().updateGoods(1, goodsNum);
					if (boolNum) {
						System.out.println("\n\t Updating Succeeded!");
					} else {
						System.err.println("\n\t Updatint Failed");
					}
					changedInfoNext("updateGoodsPage");
					break;
					default:
						System.out.println("Input correct choice");
						break;
				}
			}
			System.err.println("Input Invalid");
			System.out.println("\nInput option, or press 0 back");
			
		}while(true);
	}
	/*
	 * 3. delete goods Page
	 */
	public static void deleteGoodsPage(){
		System.out.println("\t Deleting goods\n");
		System.out.println("Input deleted goods name");
		
		int gid = QueryPrint.query("deleteGoodsPage");
		
		do {
			System.out.println("Be sure to delete this goods? Y/N");
			String choice = ScannerInfoString();
			if ("y".equals(choice) || "Y".equals(choice)) {
				boolean boolDeleteGoods = new GoodsDao().deleteGoods(gid);
				if (boolDeleteGoods){
					System.err.println("\t Deleting Succeeded! \n");
				} else {
					System.err.println("\n\t Deleting Failed!");
				}
				changedInfoNext("deleteGoodsPage");
			} else if ("n".equals(choice) || "N".equals(choice)) {
				MainPage.MaintenancePage();
			}
			System.out.println("\t Input Invalid! Input again! \n");
		}while(true);
		
	}
	/*
	 * 4. query goods Page
	 */
	public static void queryGoodsPage(){
		System.out.println("\t\t Querying goods\n");
		System.out.println("\t\t1.Query goods-ascending amount");
		System.out.println("\t\t2.Query goods-ascending price");
		System.out.println("\t\t3.Query goods-goods keyword");
		System.out.println("\nInput option, or press 0 back");
		
		do{
			String info = ScannerInfoString();
			String regex = "[0-3]";
			if (info.matches(regex)){
				int choice = Integer.parseInt(info);
				switch (choice){
				case 0: 
					MainPage.salesManManagementPage();
					break;
				case 1:
				case 2:
				case 3:
					if (choice == 3){//keyword search, we need print out
						System.out.println("\t\t Searching goods keyword\n");
						System.out.println("\n Input goods keyword");
					}
					ArrayList<Goods> goodsList = new GoodsDao().queryGoods(choice);
					if (goodsList == null || goodsList.size() <= 0) {
						System.err.print("\n\t Goods does not exist! \n");
						queryGoodsPage();
					} else {
						if (choice == 1) {
							System.out.println("\t\t\t\t\t Goods List(Amount Ascending Order) \n\n");
						} else {
							System.out.println("\t\t\t\t\t Your searching goods:");
						}
						System.out.println("\tGoods ID\t\tGoods Name\t\tGoods Price\t\tGoods Amount\t\tNote\n");
						
						for (int i = 0, length = goodsList.size(); i < length; i++) {
							Goods goods = goodsList.get(i);
							System.out.println("\t" + goods.getGid()+"\t\t"+ goods.getGname()+"\t\t"+goods.getGprice()+"$\t\t"+goods.getGnum());
							int gnum = goods.getGnum();
							if (gnum == 0){
								System.out.println("\t\tThe goods is out of stock!");
							} else if (gnum < 10){
								System.out.println("\t\tThe amount of this goods is less than 10!");
							} else {
								System.out.println("\t\t-");
							}
							System.out.println("\t");
						}
						System.out.println("---------------------");
						do {
							System.out.println("Press 0 back");
							String choiceNext = ScannerInfoString();
							if ("0".equals(choiceNext)) {
								MainPage.MaintenancePage();
							}
							System.err.println("Input Invalid!");
						}while (true);
					}
					break;
					default:
						break;
				}
				break;
			}
			System.err.println("Input Invalid");
			System.out.println("Input Again, or press 0 back");
		}while(true);
		//the next step of query
		System.out.println("\n\n Press 0 back");
		boolean boolNext = true;
		do {
			String choice = ScannerInfoString();
			if ("0".equals(choice)){
				boolNext = false;
				queryGoodsPage();
			}
			System.err.println("Input Valid");
			System.out.println("Press 0 back");
		}while(boolNext);
	}
	/*
	 * Display Goods Information
	 */
	public static void displayGoodsPage(){
		System.out.println("\t\t\t\t\tGoods List\n");
		ArrayList<Goods> goodsList = new GoodsDao().displayGoods();
		if (goodsList.size() <= 0){
			System.err.println("Out of Stock!");
			MainPage.MaintenancePage();
		} else {
			System.out.println("\tGoods ID\t\tGoods Name\t\tGoods Price\t\tGoods Amount\t\tNote");
			for(int i = 0, length = goodsList.size(); i < length; i++) {
				Goods goods =  goodsList.get(i);
				System.out.println("\t"+goods.getGid()+"\t\t"+goods.getGname()+"\t\t"+goods.getGprice()+" $\t\t"+goods.getGnum());
				
				int gNum = goods.getGnum();
				if (gNum == 0) {
					System.out.println("\t\t Out of Stock");
				} else if (gNum < 10) {
					System.out.println("\t\tThe amount of goods is less than 10!");
				} else {
					System.out.println("\t\t-");
				}
				System.out.println("\t");
			}
			//Next Step
			System.out.println("---------------------");
			do
			{
				System.out.println("Input 0 back");
				String choice = ScannerInfoString();
				if ("0".equals(choice))
				{
					MainPage.MaintenancePage();
				}
				System.out.println("Input Invalid!");
			} while (true);
		}
	}

}

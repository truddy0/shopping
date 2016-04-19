package page;

import java.util.ArrayList;

import dao.GoodsDao;
import dao.GsalesDao;
import dao.SalesManDao;
import entity.Goods;
import entity.Gsales;
import entity.SalesMan;
import tools.Arith;
import tools.QueryPrint;
import tools.ScannerChoice;

/*
 * Main Page
 */
public final class MainPage extends ScannerChoice {
	public static void main(String[] args) {
		MainPage.mainPage();
	}
	
	public static void mainPage() {
		System.out.println("*****************************************\n");
		System.out.println("\t 1.Goods Maintenance Page\n");
		System.out.println("\t 2.Check-out Log Page\n");
		System.out.println("\t 3.commodity Management Page\n");
		System.out.println("****************************************");
		
		System.out.println("\nPress 0 back");
		do
		{
			 String choice = ScannerInfoString();
			 String regex = "[0-3]";//正则表达式
			 if (choice.matches(regex))
			 { 
				 int info = Integer.parseInt(choice);
				 switch (info)
				 {
				 case 0:
					 System.out.println("------------------");
					 System.out.println("Log out");
					 System.exit(1);//退出程序，返回值随便设置
					 break;
				 case 1:
					 MaintenancePage();
					 break;
				 case 2:
					 checkstandLogPage();
					 break;
				 case 3:
					 commodityManagementPage();
					 break;
				 default:
				 break;
				 }
			 }
			 System.err.println("!Input Invalid!");
			 System.out.println("Press again or Press 0 exit");
		} while (true);
		
	}
	
	public static void MaintenancePage(){
		System.out.println("***************************\n");
		System.out.println("\t 1.Add Goods\n");
		System.out.println("\t 2.Update Goods\n");
		System.out.println("\t 3.Delete Goods\n");
		System.out.println("\t 4.Query Goods\n");
		System.out.println("\t 5.Display Goods\n");
		System.out.println("***************************");
		
		System.out.println("\nSelect or Press 0 back");
		do
		{
			String choice = ScannerInfoString();
			String regex = "[0-5]";
			if (choice.matches(regex))
			{
				int info = Integer.parseInt(choice);
				switch (info)
				{
				case 0:
					mainPage();
					break;
				case 1:
					GoodsPage.addGoodsPage();
					break;
				case 2:
					GoodsPage.updateGoodsPage();
					break;
				case 3:
					GoodsPage.deleteGoodsPage();
					break;
				case 4:
					GoodsPage.queryGoodsPage();
					break;
				case 5:
					GoodsPage.displayGoodsPage();
					break;
				default:
					break;
				}
			}
			System.err.println("!Input Invalid!");
			System.out.println("Press again or Press 0 exit");
		}while(true);
		
	}
	public static void checkstandLogPage() {
		System.out.println("\n*******Check-out Management System*******\n");
		System.out.println("\t 1.Login Page\n");
		System.out.println("\t 2.Exit\n");
		System.out.println("-----------------------------");
		System.out.println("Select or Press 0 back");
		do{
			String choice = ScannerInfoString();
			String regex = "[0-2]";
			if(choice.matches(regex)){
				int info = Integer.parseInt(choice);
				switch(info){
				case 0: 
					mainPage();
					break;
				case 1:
					int logintimes = 3;// 3 times of trying login
					while(logintimes != 0){
						logintimes--;
						System.out.println("---username---");
						String sName = ScannerInfoString();
						System.out.println("---password---");
						String sPassword = ScannerInfoString();
						
						ArrayList<SalesMan> salesManInfo = new SalesManDao().checkstandLog(sName);
						if (salesManInfo == null || salesManInfo.size() == 0){//无️此用户
							System.err.println("\t!!Wrong username!!\n");
							System.out.println("\nLogin times remain:"+logintimes);
						} else {
							SalesMan salesMan = salesManInfo.get(0);//只返回一组值
							if (sPassword.equals(salesMan.getsPassWord())) {
								System.out.println("\t---Login Succeed!---");
								shoppingSettlementPage(salesMan.getsId());
							} else {
								System.err.println("\t!!Wrong password!!\n");
								System.out.println("\nLogin times remain:"+logintimes);
							}
						}
					}
					System.out.println("------------------");
					System.err.println("\t！！Required Logout!！！");
					System.exit(1);			
					break;
				case 2:
					System.out.println("------------------");
					System.out.println("Logout!");
					System.exit(-1);			
					break;
				default:
				break;
				}
			}
			System.err.println("!Input Invalid!");
			System.out.println("Press again or Press 0 exit");
		}while(true);
		
	}
	/*
	 * 3. Commodity Management Page
	 */
	public static void commodityManagementPage() {
		System.out.println("***************************\n");
		System.out.println("\t 1.SalesMan Management\n");
		System.out.println("\t 2.Daily Sales Page\n");
		System.out.println("***************************");
		
		System.out.println("\nSelect or Press 0 back");
		do
		{
			String choice = ScannerInfoString();
			String regex  = "[0-2]";
			if (choice.matches(regex))
			{
				int info = Integer.parseInt(choice);
				switch (info)
				{
					case 0:
						mainPage();
						break;
					case 1:
						salesManManagementPage();
						break;
					case 2:
						GsalesPage.dailySalesGoodsPage();
						break;
					default:
						break;
				}
			}
			System.err.println("!Input Invalid!");
			System.out.println("Press again or Press 0 exit");
		}while(true);
		
	}
	
	
	
	
	
	public static void shoppingSettlementPage(int salesManSid) {
		System.out.println("\n\t*******Check Out!*******\n");
		do{
			System.out.println("Press 'S' to start. Press 0 back to Login Page.");
			String choNext = ScannerInfoString();
			if ("0".equals(choNext)){
				checkstandLogPage();
			} else if ("s".equals(choNext) || "S".equals(choNext)){
				System.out.println("\n--Input Goods Keywords--");
				
				int gid = QueryPrint.querySettlement();
				
				switch(gid){
				case -3:
					break;
				case -1:
					System.err.println("\t--Goods is out of stock--");
					break;
				default:
					System.out.println("--Select goods according to GoodID");
					
					int shoppingGid = ScannerNum();
					ArrayList<Goods> goodsList = new QueryPrint().queryGoodsKey(shoppingGid,null);
					if (goodsList == null || goodsList.size() == 0) {
						System.err.println("\tNo Goods Found!\n");
					} else {
						Goods goods = goodsList.get(0);
						int gNum = goods.getGnum();
						double gPrice = goods.getGprice();
						
						System.out.println("--Input Amount--");
						do {
							int choicegoodsNum = ScannerNum();
							if (choicegoodsNum > gNum){
								System.err.println("\t Not Enough!");
								System.out.println("--Input Amount Againg--");
							} else {
								double allPrice = Arith.mul(choicegoodsNum, gPrice);
								System.out.println("\t\t\t Shopping cart Check out");
								System.out.println("\t\tGoods Name\t\tGoods Price\t\tGoods Amount\t\tSum\n");
								do {
									System.out.println("Claim to buy: Y/N");
									String choShopping = ScannerInfoString();
									if ("y".equals(choShopping) || "Y".equals(choShopping)) {
										System.out.println("\nSum:"+allPrice+" $");
										System.out.println("\nCosumer Payment:");
										do {
											double amount = ScannerInfo();
											double balance = Arith.sub(amount, allPrice);
											if (balance < 0) {
												System.err.println("\t Payment not Enough!");
												System.out.println("\nReinput payment:");
											} else {
												/*
												 * Change Database
												 * 1. change table goods number
												 * 2. increase table sales number
												 */
												//table sales
												Gsales gSales = new Gsales(goods.getGid(),salesManSid, choicegoodsNum);
												boolean insert = new GsalesDao().shoppingSettlement(gSales);
												//table goods
												int goodsNewNum = gNum - choicegoodsNum;
												Goods newGoods = new Goods(goods.getGid(),goodsNewNum);
												boolean update = new GoodsDao().updateGoods(3, newGoods);
												if (update && insert) {
													System.out.println("Change:"+balance);
													System.out.println("Thank you for shopping, welcome again!");
												} else {
													System.err.println("Payment Failed!");
												}
												shoppingSettlementPage(salesManSid);
											}
										}while(true);
									} else if ("N".equals(choShopping) || "n".equals(choShopping)) {
										shoppingSettlementPage(salesManSid);
									}
									System.err.println("\t Please Claim shopping!");
								}while(true);
							}
						}while(true);
					}
					break;	
				}
				
			}else {
				System.err.println("Please input valid character!");
			}
			
		}while(true);
		
	}

	public static void salesManManagementPage(){
		System.out.println("***************************\n");
		System.out.println("\t 1.Add Salesman\n");
		System.out.println("\t 2.Change Salesman\n");
		System.out.println("\t 3.Delete Salesman\n");
		System.out.println("\t 4.Search Salesman\n");
		System.out.println("\t 5.显Display Salesman\n");
		System.out.println("***************************");
		
		System.out.println("\nSelect or Press 0 back");
		
		do
		{
			String choice = ScannerInfoString();
			String regex  = "[0-5]";
			if (choice.matches(regex))
			{
				int info = Integer.parseInt(choice);
				switch (info)
				{
				case 0:
					commodityManagementPage();
					break;
				case 1:
					SalesManPage.addSalesManPage();
					break;
				case 2:
					SalesManPage.updateSalesManPage();
					break;
				case 3:
					SalesManPage.deleteSalesManPage();
					break;
				case 4:
					SalesManPage.querySalesManPage();
					break;
				case 5:
					SalesManPage.displaySalesManPage();
					break;
				default:
					break;
				}
			}
			System.err.println("\t!Invalid Input!");
			System.out.println("Press again or Press 0 exit");
		}while(true);
	}




	

}

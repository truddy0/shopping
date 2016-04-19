package tools;

import java.util.Scanner;

import page.GoodsPage;
import page.MainPage;
import page.SalesManPage;

/*
 * 1. after finishing operations, next step
 * 2. select
 */
public class ScannerChoice {
	/*
	 * @return double get price from keyboard(two digits after the decimal point)
	 */
	public static double ScannerInfo(){
		double num = 0.00;
		do {
			Scanner sc = new Scanner(System.in);
			System.out.print("Input price (two digits after the decimal point): ");
			String info = sc.next();
			
			String regex = "(([1-9][0-9]*)\\.([0-9]{2}))|[0]\\.([0-9]{2})";//two digits after the decimal point
			if (info.matches(regex)) {
				num = Double.parseDouble(info);
			} else {
				System.err.println("Input Error!");
				continue;
			}
			break;
		}while (true);
		return num;
	}
	/*
	 * @return int get number from keyboard
	 */
	public static int ScannerNum() {
		int num = 0;
		String regex = "([1-9])|([1-9][0-9]+)";//goods number
		do {
			Scanner sc = new Scanner(System.in);
			System.out.println("Input amount: ");
			String nums = sc.next();
			
			if (nums.matches(regex)) {
				num = Integer.parseInt(nums);
			} else {
				System.err.println("Input Error");
				continue;
			}
			break;
		}while (true);
		return num;
	}
	/*
	 * @return String acquire input from keyboard
	 */
	public static String ScannerInfoString(){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input: ");
		return scanner.next();
	}
	/*
	 * acquire user - change goods - next
	 * acquire user - delete goods - next
	 * acquire user - add goods - next
	 * @param caller
	 */
	public static void changedInfoNext(String oper) {
		do {
			System.out.println("Whether continue-current operation:(Y/N)");
			String choice = ScannerChoice.ScannerInfoString();
			//在JAVA中: Equals比较的是值,==比较的是地址
			if ("y".equals(choice) || "Y".equals(choice)) {
				if("updateGoodsPage".equals(oper)) {
					GoodsPage.updateGoodsPage();
				} else if ("deleteGoodsPage".equals(oper)) {
					GoodsPage.deleteGoodsPage();
				} else if ("addGoodsPage".equals(oper)) {
					GoodsPage.addGoodsPage();
				}
			} else if ("n".equals(choice) || "N".equals(choice)) {
				MainPage.MaintenancePage();
			}
			System.out.println("\n Input Error! Input Again");
		}while (true);
	}
	/*
	 * acquire user - change salesman - next
	 * acquire user - add salesman - next
	 * acquire user - query salesman - next
	 * acquire user - delete salesman - next
	 * @param caller
	 */
	public static void choiceSalesManNext(String oper) {
		do {
			System.out.println("Whether continue-current operation:(Y/N)");
			String choice = ScannerChoice.ScannerInfoString();
			if ("y".equals(choice) || "Y".equals(choice)) {
				if("updateSalesMan".equals(oper)) {
					SalesManPage.updateSalesManPage();
				} else if ("deleteSalesMan".equals(oper)) {
					SalesManPage.deleteSalesManPage();
				} else if ("addSalesMan".equals(oper)) {
					SalesManPage.addSalesManPage();
				} else if ("querySalesMan".equals(oper)) {
					SalesManPage.querySalesManPage();
				}
			} else if ("n".equals(choice) || "N".equals(choice)) {
				MainPage.salesManManagementPage();
			}
			System.err.println("\t Input Error!");
		}while(true);
	}
}

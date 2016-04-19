package page;

import java.util.ArrayList;

import tools.ScannerChoice;
import dao.GsalesDao;
import entity.Gsales;


/*
 * Daily Sales List
 */
public final class GsalesPage {
	public static void dailySalesGoodsPage(){
		System.out.println("\tDaily Sales List\n");
		ArrayList<Gsales> GsalesList = new GsalesDao().dailyGsales();
		if (GsalesList.size() <= 0) {
			System.err.println("\t No goods was sold today!");
			MainPage.commodityManagementPage();
		} else {
			System.out.println("\t\t\tToday's Sale List");
			System.out.println("\tGoods Name\t\tGoods Price\t\tGoods Amount\t\tSales Amount\t\tNate\n");
			for(int i = 0, length = GsalesList.size(); i < length; i++) {
				Gsales gSales = GsalesList.get(i);
				System.out.print("\t"+gSales.getgName()+"\t\t"+gSales.getgPrice()+" $\t\t"+gSales.getgNum()+"\t\t"+gSales.getAllSum());
				int gNum = gSales.getgNum();
				if(gNum == 0){
					System.out.println("\t\tThe goods is out of stock!");
				}else if(gNum < 10){
					System.out.println("\t\tThe goods amount is less than 10");
				} else {
					System.out.println("\t\t-");
				}
				System.out.println("\t");
			}
			do
			{
				System.out.println("\n\nPress 0  back");
				String choice = ScannerChoice.ScannerInfoString();
				if ("0".equals(choice))
				{
					MainPage.salesManManagementPage();
				}
			 MainPage.commodityManagementPage();
			} while (true);
		}
	}
}

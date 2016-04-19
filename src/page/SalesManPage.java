package page;

import java.util.ArrayList;

import dao.SalesManDao;
import entity.SalesMan;
import tools.QueryPrint;
import tools.ScannerChoice;

public final class SalesManPage extends ScannerChoice{
	/*
	 * 1. add salesman
	 */
	public static void addSalesManPage(){
		System.out.println("\tAdding Salesman\n");
		
		System.out.println("\nAdd salesman - Name");
		String sName = ScannerInfoString();
		
		System.out.println("\nAdd salesman - Password");
		String sPassword = ScannerInfoString();
		
		SalesMan salesMan = new SalesMan(sName, sPassword);
		boolean bool = new SalesManDao().addSalesMan(salesMan);
		
		if(bool){
			System.out.println("\n\tThe new salesman has been added");
		} else {
			System.out.println("Adding new salesman failed!");
		}
		choiceSalesManNext("addSalesMan");
		
	}
	/*
	 * 2. change salesman
	 */
	public static void updateSalesManPage(){
		System.out.println("\tUpdating saleman\n");
		System.out.println("Input salesman name:");
		String sName = ScannerInfoString();
		
		//call query salesman function
		ArrayList<SalesMan> salesManList = new QueryPrint().querySalesMan(sName);
		if (salesManList.size() <= 0){
			System.err.println("\t No one found!");
			choiceSalesManNext("updateSalesMan");
		}else{
			System.out.println("\t\t\tSalesman Information");
			System.out.println("\tSalesMan ID\t\tSalesman Name\t\tSalesman Password");
			
			SalesMan salesMan = salesManList.get(0);
			System.out.println("\t"+salesMan.getsId()+"\t\t\t"+salesMan.getsName()+"\t\t\t"+salesMan.getsPassWord());
			System.out.println();
			
			//select the content to be updated
			System.out.println("\nSelect Update Content\n");
			System.out.println("\t1.Change - Salesman Name");
			System.out.println("\t2.Change - Salesman Password");
			do{
				String choice = ScannerInfoString();
				String regex = "[0-2]";
				if (choice.matches(regex)) {
					int info = Integer.parseInt(choice);
					switch(info){
					case 0: 
						MainPage.salesManManagementPage();
						break;
					case 1:
						System.out.println("Change Salesman Name - New Name");
						String snewName = ScannerInfoString();
						
						SalesMan salesManName = new SalesMan(salesMan.getsId(), snewName, null);
						boolean boolsName = new SalesManDao().updateSalesMan(1, salesManName);
						
						if(boolsName){
							System.out.println("\n\tUpdating New SalesMan Name Succeeded!");
						} else {
							System.err.println("\n\tUpdating New SalesMan Name Failed!");
						}
						choiceSalesManNext("updateSalesMan");
						break;
					case 2:
						System.out.println("Chanve Salesman Password - New Password");
						String snewPassword = ScannerInfoString();
						
						SalesMan salesManPassword = new SalesMan(salesMan.getsId(),null,snewPassword);
						boolean boolspassword = new SalesManDao().updateSalesMan(2, salesManPassword);
						
						if (boolspassword){
							System.out.println("\n\tUpdating New SalesMan Password Succeeded!");
						} else {
							System.err.println("\n\tUpdating New SalesMan Password Failed!");
						}
						choiceSalesManNext("updateSalesMan");
						break;
					default:
						break;	
					}
				}
				System.err.println("!Input Invalid!");
				System.out.println("Press again or Press 0 exit");
			}while(true);
		}
		
	}
	/*
	 * delete salesman page
	 */
	public static void deleteSalesManPage(){
		System.out.println("\tDeleting saleman\n");
		System.out.println("Input salesman name:");
		String sName = ScannerInfoString();
		
		ArrayList<SalesMan> salesManList = new QueryPrint().querySalesMan(sName);
		if (salesManList.size() <= 0){
			System.err.println("\t No one found!");
			choiceSalesManNext("deleteSalesMan");
		}else{
			System.out.println("\t\t\tDelete Salesman Information");
			System.out.println("\tSalesMan ID\t\tSalesman Name\t\tSalesman Password");
			for(int i = 0; i < salesManList.size(); i++) {
				SalesMan salesMan = salesManList.get(i);
				System.out.println("\t"+salesMan.getsId()+"\t\t\t"+salesMan.getsName()+"\t\t\t"+salesMan.getsPassWord());
				System.out.println();
			}
			do {
				System.out.println("Claim to delete this salesman: Y/N");
				String choice = ScannerInfoString();
				if ("y".equals(choice) || "Y".equals(choice)){
					boolean boolDeleteSalesMan = new SalesManDao().deleteSalesMan(sName);
					if (boolDeleteSalesMan) {
						System.out.println("\n\tDeleting New SalesMan Password Succeeded!");
					} else {
						System.err.println("\n\tDeleting New SalesMan Password Failed!");
					}
					choiceSalesManNext("deleteGoods");
				} else if ("N".equals(choice) || "n".equals(choice)){
					MainPage.salesManManagementPage();
				}
				System.err.println("!Input Invalid! Please input again!");
			}while(true);
		}
		
	}
	/*
	 * 4. query salesman page
	 */
	public static void querySalesManPage(){
		System.out.println("\t\tSearching for the salesman\n");
		System.out.println("Input salesman keyword");
		String sName = ScannerInfoString();
		
		ArrayList<SalesMan> salesManList = new SalesManDao().querySalesMan(sName);
			
		if (salesManList.size() <=0)
		{
			System.err.println("\tNo one found");
		}else 
			{
			System.out.println("\t\t\tSalesman Information List");
			System.out.println("\tSalesMan ID\t\tSalesman Name\t\tSalesman Password");
				
				for (int i = 0,length = salesManList.size(); i < length; i++)
				{
					SalesMan salesMan = salesManList.get(i);
					System.out.println("\t"+salesMan.getsId()+"\t\t\t"+salesMan.getsId()+"\t\t\t"+salesMan.getsPassWord());
					System.out.println();
				}
			}
	 choiceSalesManNext("querySalesMan");
		
	}
	/*
	 * 5. display salesman page
	 */
	public static void displaySalesManPage(){
		ArrayList<SalesMan> salesManList = new SalesManDao().displaySalesMan();
		if (salesManList.size() <= 0)
		{
			System.err.println("\tNull Salesman Information List");
			MainPage.salesManManagementPage();
		}else 
			{
			System.out.println("\t\t\tSalesman Information List");
			System.out.println("\tSalesMan ID\t\tSalesman Name\t\tSalesman Password");
				
				for (int i = 0,length = salesManList.size(); i < length; i++)
				{
					SalesMan salesMan = salesManList.get(i);
					System.out.println("\t"+salesMan.getsId()+"\t\t\t"+salesMan.getsId()+"\t\t\t"+salesMan.getsPassWord());
					System.out.println();
				}
				do
				{
					System.out.println("Press again or Press 0 back");
					String choice = ScannerInfoString();
					
					if ("0".equals(choice))
					{
						MainPage.salesManManagementPage();
					}
					System.err.print("\tInvalid Input!");
				} while (true);
			}
	}

}

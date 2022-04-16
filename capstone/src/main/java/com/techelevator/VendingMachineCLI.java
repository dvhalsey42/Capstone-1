package com.techelevator;

import com.techelevator.view.Menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineCLI extends Import {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT= "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
		MAIN_MENU_OPTION_EXIT};

	private Menu menu;
	private Purchase purchase = new Purchase();
	private Log log = new Log();

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	//"Stocks" vending machine once at the start of the program
	public void run() {
		stockVendingMachine();
		Map<String, Integer> vendingStock = new HashMap<>();
		for(Map.Entry<String, List<String>> product:getVendingCategories().entrySet()){
			vendingStock.put(product.getKey(), 5);
		}

		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			//Displays vending machine items, and notes which items are sold out
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				Map<String, List<String>> vendingItems = getVendingCategories();
				for(Map.Entry<String, List<String>> item:vendingItems.entrySet()) {
					if (item.getKey().contains("*")) {
						System.out.println("SOLD OUT: " + item.getValue().get(0));
					} else {
						System.out.println(item.getKey() + ": " + item.getValue().get(0) +
								" " + item.getValue().get(1));
					}
				}
			//Moves user to the purchase menu
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				purchase.purchaseMenu(getVendingCategories(), vendingStock);
			}
			//deletes the contents of the log file and exits the program
			else if(choice.equals(MAIN_MENU_OPTION_EXIT)) {
				log.clearLog();
				break;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}

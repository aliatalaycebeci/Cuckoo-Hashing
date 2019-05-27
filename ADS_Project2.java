import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ADS_Project2 {
	 List<List<Integer>> hashTables;
	static int hashFunctionIndex,hashFunctionIndex2,hashFunctionIndex3,hashFunctionIndex4,hashFunctionIndex5;
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter table number");
		int tableNumber = scan.nextInt();
		while(tableNumber <2 || tableNumber >5) {
			tableNumber = scan.nextInt();
		}
		System.out.println("Please enter table size");
		int tableSize= scan.nextInt();
		List<List<Integer>> hashTables = initHashTables(tableNumber, tableSize);
		int option ;
		int value;
		boolean whileCondition = true;
		while(whileCondition) {
			option = menu();
			switch (option) {
			case 1:
				Scanner scan2= new Scanner(System.in);
				System.out.println("Enter value");
				value = scan2.nextInt();
				hashTables=insert(hashTables,value);
				System.out.println("My current table situation:");
				printTableFunc(hashTables,tableNumber,tableSize);
				
			break;
			case 2:
				System.out.println("Please enter the value you are looking for");
				Scanner scan3 = new Scanner(System.in);
				int keyValue = scan3.nextInt();
				searchInHashTables(hashTables, keyValue);
			break;
			case 3:
				System.out.println("Please enter the value to be deleted ");
				Scanner scan4 = new Scanner(System.in);
				int deletingValue = scan4.nextInt();
				List<Integer> list =searchInHashTables(hashTables, deletingValue);
				if(list.size() ==2)
				hashTables.get(list.get(0)).set(list.get(1), -1);
				System.out.println("My current table situation after deletion:");
				printTableFunc(hashTables, tableNumber, tableSize);
			break;
			case 4:
				System.out.println("Good-Bye");
				whileCondition = false;
			break;
			}
		}
			
	}
	public static int menu() {
		Scanner scan = new Scanner(System.in);
		System.out.println("1- For Insert");
		System.out.println("2- For Search");
		System.out.println("3- For Delete");
		System.out.println("4- For Exit");
	
		int menuOption = scan.nextInt();
		while(menuOption < 1 || menuOption > 4 ) {
			System.out.println("Please enter number between 1 and 4");
			menuOption = scan.nextInt();
		}
		return menuOption;
	}
	
	public static int h1(int valueFromUser, int tableSize) {
		int index = valueFromUser % tableSize;
		return index;
	}
	
	public static int h2(int valueFromUser, int tableSize) {
		int index = valueFromUser * 3 % tableSize;
		return index;
	}
	
	public static int h3(int valueFromUser, int tableSize) {
		int index = valueFromUser * 2 % tableSize;
		return index;
	}
	
	public static int h4(int valueFromUser, int tableSize) {
		int index = valueFromUser % 2*tableSize;
		return index;
	}
	
	public static int h5(int valueFromUser, int tableSize) {
		int index = valueFromUser % tableSize/2;
		return index;
	}
	
	public static int hashFunctions(int i,int value, int tableSize) {
		int index=0;
		switch (i) {
			case 0:
				index =h1(value, tableSize);
				break;
			case 1:
				index =h2(value, tableSize);
				break;
			case 2:
				index =h3(value, tableSize);
				break;
			case 3:
				index =h4(value, tableSize);
				break;
			case 4:
				index =h5(value, tableSize);
				break;
		}
		return index;
		
	}

	public static List<List<Integer>> initHashTables(int tableNumber, int tableSize) {
		
		
		List<List<Integer>> hashLists = new ArrayList<>();
		for (int i = 0; i < tableNumber; i++) {
			List<Integer> hashListTable = new ArrayList<Integer>();
			for (int j = 0; j < tableSize; j++) {
				hashListTable.add(-1);
				
			}	
			hashLists.add(hashListTable);
		}
		return hashLists;
		
	}

	public static List<List<Integer>> insert(List<List<Integer>>hashTables, int value) {
		int collision=0;
		for (int i = 0; i < hashTables.size(); i++) {
				 hashFunctionIndex = hashFunctions(i, value, hashTables.size());
				if(hashTables.get(i).get(hashFunctionIndex)==value) {
					System.out.println("FOUND - NO INSERT");
					break;
				}
				else if(hashTables.get(i).get(hashFunctionIndex) == -1) {
					System.out.println("HasFuncIndex: " + hashFunctionIndex);
					hashTables.get(i).set(hashFunctionIndex, value);
					System.out.println("No Collision");
					break;
				}
				else {
					System.out.println("Denemeasdasfasfasd");
						hashFunctionIndex2 = hashFunctions(i+1, hashTables.get(i).get(hashFunctionIndex), hashTables.size());
						if(i+1 < hashTables.size()) {		
							System.out.println("Serdar" + hashTables.get(i+1).get(hashFunctionIndex2));
							if(hashTables.get(i+1).get(hashFunctionIndex2)==-1) {
								collision=1;
								hashTables.get(i+1).set(hashFunctionIndex2, hashTables.get(i).get(hashFunctionIndex));
								System.out.println("Value: "+ value);
								hashTables.get(i).set(hashFunctionIndex, value);
								break;
							}
						}
						if(i+2 < hashTables.size()) {
							hashFunctionIndex3 = hashFunctions(i+2, hashTables.get(i).get(hashFunctionIndex2), hashTables.size());
							if(hashTables.get(i+2).get(hashFunctionIndex3)==-1){
								collision=2;
								hashTables.get(i+2).set(hashFunctionIndex3, hashTables.get(i+1).get(hashFunctionIndex2));
								hashTables.get(i+1).set(hashFunctionIndex2, hashTables.get(i).get(hashFunctionIndex));
								hashTables.get(i).set(hashFunctionIndex, value);
								break;
							}
						}
						if(i+3 < hashTables.size()) {
							hashFunctionIndex4 = hashFunctions(i+3, hashTables.get(i).get(hashFunctionIndex3), hashTables.size());
							 if(hashTables.get(i+3).get(hashFunctionIndex4)==-1){
								 collision=3;
								hashTables.get(i+3).set(hashFunctionIndex4, hashTables.get(i+2).get(hashFunctionIndex3));
								hashTables.get(i+2).set(hashFunctionIndex3, hashTables.get(i+1).get(hashFunctionIndex2));
								hashTables.get(i+1).set(hashFunctionIndex2, hashTables.get(i).get(hashFunctionIndex));
								hashTables.get(i).set(hashFunctionIndex, value);
								break;
							 }
						}
						if(i+4 < hashTables.size()) {
							hashFunctionIndex5 = hashFunctions(i+4, hashTables.get(i).get(hashFunctionIndex4), hashTables.size());
							if(hashTables.get(i+4).get(hashFunctionIndex5)==-1){
								collision=4;
							hashTables.get(i+4).set(hashFunctionIndex5, hashTables.get(i+3).get(hashFunctionIndex4));
							hashTables.get(i+3).set(hashFunctionIndex4, hashTables.get(i+2).get(hashFunctionIndex3));
							hashTables.get(i+2).set(hashFunctionIndex3, hashTables.get(i+1).get(hashFunctionIndex2));
							hashTables.get(i+1).set(hashFunctionIndex2, hashTables.get(i).get(hashFunctionIndex));
							hashTables.get(i).set(hashFunctionIndex, value);
							break;
							}
						}
				}
		}
		return hashTables;
		
	}
	public static void printTableFunc(List<List<Integer>>hashTables, int tableNumber, int tableSize) {
		double loadFactor=0;
		for (int i = 0; i < tableNumber; i++) {
			System.out.println("Table " + i);
			for (int j = 0; j < tableSize; j++) {
				System.out.println(hashTables.get(i).get(j));
				if(hashTables.get(i).get(j)!=-1) {
					loadFactor++;
				}
			}
			System.out.println("Load Factor of table " + i +" is: " + loadFactor / tableSize);
			loadFactor=0;
		}
	
	}
	public static List<Integer> searchInHashTables(List<List<Integer>> hashTables, int value) {
		List<Integer> returnList = new ArrayList<>();
		for (int i = 0; i < hashTables.size(); i++) {
			for (int j = 0; j < hashTables.get(i).size(); j++) {
				if(hashTables.get(i).get(j)==value) {
					System.out.println("Value " + value + " found in Table " + i + " in position " + j);
					returnList.add(i);
					returnList.add(j);
					return returnList;
					
				}
			}
		}
		System.out.println("The value " + value + " does not exist.");
		return returnList;
		
	}
	
	public static List<List<Integer>> deleteInHashTables(List<List<Integer>> hashTables, int value) {
		int indexOfDeletingValue;
		for (int i = 0; i < hashTables.size(); i++) {
			for (int j = 0; j < hashTables.get(i).size(); j++) {	
					hashTables.get(i).set(j, -1);	
					return hashTables;
			}
		}
		System.out.println("The value " + value + " does not exist.");
		return hashTables;
	}
}

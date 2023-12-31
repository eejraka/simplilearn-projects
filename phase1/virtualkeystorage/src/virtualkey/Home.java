package virtualkey;
import java.util.*;
import java.io.File;
import java.io.IOException;


interface References{
	Set<String> repo = new TreeSet<>();
	Scanner sc = new Scanner(System.in);
}

public class Home implements References {
	public static void delAll() {
		File dir = new File("C:\\Users\\cwc\\repos\\simplilearn-projects\\phase1\\virtualkeystorage\\src\\files");
		for(File file : dir.listFiles()) {
			if(!file.isDirectory()) {
				file.delete();
			}
		}
	}
	
	public static void printEle() {
		if(!repo.isEmpty()) {
			for(String temp : repo) {
				System.out.println(temp);
			}
		}
		else {
			System.out.println("Repository is empty. No keys to display.");
		}
		return;
	}
	
	public static void homeAbout(){
		System.out.println("LockedMe.com");
		System.out.println("Developed by Aayush Karjee");
		return;
	}
	
	public static void addKey() {
		System.out.println("Insert Key to add to repository: ");
		sc.nextLine();
		String temp = sc.nextLine();

		try {
			File anew = new File("C:\\Users\\cwc\\repos\\simplilearn-projects\\phase1\\virtualkeystorage\\src\\files\\"+temp+".txt");
			if(anew.createNewFile()) {
				System.out.println("SUCCESS: Key '"+temp+"' added to repository");
				repo.add(temp);
			}
			else {
				System.out.println("ERROR: Key already exists");
			}
		}
		catch(IOException e) {
			delAll();
			System.out.println("An error occured");
			System.exit(0);
		}
		
		return;
	}
	
	public static void delKey() {
		System.out.println("Insert Key to delete from repository: ");
		sc.nextLine();
		String t1 = sc.nextLine();
		if(repo.contains(t1)) {
			File rem = new File("C:\\Users\\cwc\\repos\\simplilearn-projects\\phase1\\virtualkeystorage\\src\\files\\"+t1+".txt");
			rem.delete();
			repo.remove(t1);
			System.out.println("SUCCESS: Key "+t1+" removed from repository");
		}
		else {
			System.out.println("ERROR: Repository does not contain that Key. Try again.");
		}
		return;
	}
	
	public static void srcKey() {
		System.out.println("Insert Key to search in repository: ");
		sc.nextLine();
		String k = sc.nextLine();
		if(repo.contains(k)) {
			System.out.println("SUCCESS: The repository contains that Key. Opening the file now.");
			try {
				String pt = "C:\\Users\\cwc\\repos\\simplilearn-projects\\phase1\\virtualkeystorage\\src\\files\\"+k+".txt";
				File op = new File(pt);
				if(op.exists()) {
					Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+pt);
					pro.waitFor();
				}
			}
			catch(Exception e) {
				System.out.println(e);
			}
		}
		else {
			System.out.println("ERROR: The repository does not contain that Key. Try again.");
		}
	}
	
	public static void subMenu() {
		while(true) {
			System.out.println(" ");
			System.out.println("Additional Operations");
			System.out.println("1. Add Key");
			System.out.println("2. Delete Key");
			System.out.println("3. Search for and Open Key");
			System.out.println("4. Return to Main Menu");
			int next_choice = 0;
			boolean done2 = false;
			while(!done2) {
				try{
					System.out.println("Select desired option by typing an integer: ");
					next_choice = sc.nextInt();
					done2=true;
				} catch (InputMismatchException e) {
					sc.nextLine();
					System.out.println("Invalid input. Please enter an integer as input.");
					
				}
			}
			switch(next_choice) {
			case 1:
				//add file
				addKey();
				break;
			case 2:
				//delete file
				delKey();
				break;
			case 3:
				//search file
				srcKey();
				break;
			case 4:
				return;
			default:
				System.out.println("Please make a valid selection.");
			}
			
		}
	}
	
	public static void menu() {
		while(true) {
			System.out.println(" ");
			System.out.println("Main Menu");
			System.out.println("1. Display all Keys");
			System.out.println("2. Additional Operations");
			System.out.println("3. Exit Application");
			

			int choice = 0;
			boolean done = false;
			while(!done) {
				try{
					System.out.println("Select desired option by typing an integer: ");
					choice = sc.nextInt();
					done = true;
					
				} catch (InputMismatchException e) {
					sc.nextLine();
					System.out.println("Invalid input. Please enter an integer as input.");
				}
			}
			
			switch(choice) {
			case 1:
				printEle();
					break;
			case 2:
				subMenu();
				break;
			case 3:
				System.out.println("Goodbye!");
				delAll();
				System.exit(0);
			default:
				System.out.println("Please make a valid selection.");
			}
		}
	}
	
	public static void main(String[] args) {		
		homeAbout();
		
		menu();
		
		return;
	}

}

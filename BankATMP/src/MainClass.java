import java.io.Console;
import java.util.Scanner;
import java.util.logging.ConsoleHandler;

import bean.Account;
import dao.ActionData;
import exception.Balence;
import exception.InvalidLogin;
import services.Bank;

public class MainClass {
	public static void main(String[] args) {
		Bank ac=new ActionData();
		Account act1;
		Byte bt=0;
		Scanner sc =new Scanner(System.in);
		
		while(true){
			System.err.println(":::::ATM :::::");
			System.out.println("Enter the ATM Number : ");
			int atmNum = sc.nextInt();
			System.out.println("Enter the ATM PIN : ");
			String atmPin = sc.next();
			
			try {
				act1 = ac.doLogin(atmNum, atmPin);
					System.out.println("OPTIONs : \n\t1. Change Pin\n\t"
							+ "2. Withdrawal Balance\n\t3. Check Balance");
					System.out.println("Enter Option :  ");
					switch(bt=sc.nextByte()){
					     case 1 : System.out.println("Change Pin");
					              System.out.println("Enter the new PIN");
					              ac.changePin(act1, sc.next());
					              System.out.println("PIN Changed ! Succefully !");
					    	      break;
					     case 2 : System.out.println("Withdarwal !");
					     		  System.out.println("Enter the Amount to Be Withdarwal");
					     		  ac.withdrwal(act1, sc.nextDouble());
					     		  System.out.println("Balance Withdrwwal ! Succefully !");
					    	      break;
					     case 3 : System.out.println("Check Balance");
					              ac.checkBal(act1);
					    	      break;
					     default :System.err.println("Invalid option !");
     			}
			} catch (InvalidLogin  e) {
				// TODO Auto-generated catch block
				System.err.println("Login Failed !  "+e);
				try {
					Thread.sleep(1000);
					
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (Balence e) {
				// TODO Auto-generated catch block
				System.err.println(e);
			}
		}
	}

}

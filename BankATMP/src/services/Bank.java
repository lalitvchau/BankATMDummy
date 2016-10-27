package services;

import bean.Account;
import exception.Balence;
import exception.InvalidLogin;

public interface Bank {
      Account doLogin(int accNum,String pass) throws InvalidLogin;
      void checkBal(Account ac);
      boolean withdrwal(Account ac,double money)throws Balence;
      boolean changePin(Account ac, String pin) throws InvalidLogin;
	
}

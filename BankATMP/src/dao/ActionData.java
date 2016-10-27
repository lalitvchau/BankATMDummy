package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bean.Account;
import exception.Balence;
import exception.InvalidLogin;
import services.Bank;
public class ActionData implements Bank{

	public ActionData() {
		// TODO Auto-generated constructor stub
	}

	public Account doLogin(int accNum, String pass) throws InvalidLogin {
		// TODO Auto-generated method stub
		boolean stm=false;
		Account ac=null;
		Connection con=null;
	    Statement st=null;
		//@SuppressWarnings("unused")
		ResultSet rs=null;
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
			st=con.createStatement();
	        rs=st.executeQuery("select * from bankAtm");
	        while(rs.next()){
				if(pass.equals(rs.getString("password"))&&accNum==rs.getInt("accountNum")){
					ac= new Account();
					ac.setAcNum(accNum);
					ac.setAddr(rs.getString("addr"));
					ac.setBalence(rs.getDouble("money"));
					ac.setName(rs.getString("name"));
				    stm=true;
					break;
				}
			}
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
	       System.out.println(e.getMessage());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			
		}
		finally{
			try {
			    if(con!=null)
				con.close();
			    if(st!=null)
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				
			}
			
			
		}
		if (stm)
		return ac;
		else{
			throw new InvalidLogin();
		}
	}

	@Override
	public boolean changePin(Account ac,String pin) throws InvalidLogin {
		double bal=ac.getBalence();
		boolean stm=false;
		Connection con=null;
	    PreparedStatement st=null;
			//@SuppressWarnings("unused")
		ResultSet rs=null;
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
				st=con.prepareStatement("update bankAtm set password =? where accountnum=?");
		        st.setString(1, pin);
		        st.setInt(2, ac.getAcNum());
		        if(st.executeUpdate()>=0) {
		        	stm=true;
		        	st.close();
		        	st=con.prepareStatement("commit");
		        	st.execute();
		        }
		               
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				throw new InvalidLogin();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				throw new InvalidLogin();
			}
			finally{
				try {
				    if(con!=null)
					con.close();
				    if(st!=null)
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
			        throw new InvalidLogin();		
				}
				
				
			}
		
			
		
			return true;
		
	}

	@Override
	public void checkBal(Account ac) {
		// TODO Auto-generated method stub
		System.out.println("Account Number : "+ac.getAcNum());
		System.out.println("Account Holder : "+ac.getName());
		System.out.println("Available Balance $: "+ac.getBalence());
	}

	@SuppressWarnings("resource")
	@Override
	public boolean withdrwal(Account ac, double money) throws Balence {
		// TODO Auto-generated method stub
		double bal=ac.getBalence();
		boolean stm=false;
		if(bal>=money){
			
			Connection con=null;
		    PreparedStatement st=null;
			//@SuppressWarnings("unused")
			ResultSet rs=null;
			try{
				Class.forName("oracle.jdbc.driver.OracleDriver");
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","oracle");
				st=con.prepareStatement("update bankAtm set money =? where accountnum=?");
		        st.setDouble(1, bal-money);
		        st.setInt(2, ac.getAcNum());
		        if(st.executeUpdate()>=0) {
		        	stm=true;
		        	st.close();
		        	st=con.prepareStatement("commit");
		        	st.execute();
		        	ac.setBalence(bal-money);
		        	checkBal(ac);
		        }
		               
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				
			}
			finally{
				try {
				    if(con!=null)
					con.close();
				    if(st!=null)
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
					
				}
				
				
			}
		
			
		}else{
			throw new Balence();
		}
		return stm;
	}

	

}


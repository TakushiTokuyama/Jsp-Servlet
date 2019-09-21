package registration;

public class loginCheckLogic {

	
	public boolean checkIdPass(String id , String password ) {

		if(id.matches("[0-9a-z]{6,8}") == true && password.matches("[0-9a-z]{8,10}") == true){
				return true;
		}else{
			    return false;
	    }	
	  }
}

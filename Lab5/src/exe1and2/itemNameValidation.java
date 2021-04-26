package exe1and2;

import java.io.Serializable;

public class itemNameValidation  {
	
	
	public String validateItemName(itemProduct item)
	{
		
		String result = "Valid Name. ", name = item.getName();
		
		for(int i=0; i<name.length();i++)
		{
		    
			char ch = name.charAt(i);
		    
			// To check if the character is not alphabet or space
			if (!(Character.isLetter(ch) || ch == ' ') )
			{
				
				result="Invalid Name. ";
				
		    }
		
		}
		return result;	
	

	}



}
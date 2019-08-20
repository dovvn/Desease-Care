package ttokk.com.swproject.algorithmn;

/*
 * 우울증
 */

public class Depression{

	int score;
	Depression(){}
	public Depression(int score) {
		this.score = score;
	}
	
	public String cal_depression() {
		String result = "";
		
	    if (score >= 0 && score <= 7)
	        result = "Normal";
	    else if (score >= 8 && score <= 13)
	        result = "Mild";
	    else if (score >= 14 && score <= 18)
	        result = "Moderate";
	    else if (score >= 19 && score <= 22)
	        result = "Severe";
	    else if (score >= 23)
	        result = "Very Severe";
	    
	    return result;
	}
}

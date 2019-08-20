package ttokk.com.swproject.algorithmn;

/*
 * 간염
 */

public class Hepatitis{

	int gender; //성별
	int age; //나이
	int HbA;
	int PT_INR;
	int bilirubin; //빌리루빈
	float creatinine; //크레아티닌
	int ammonia; //암모니아
	
	
	Hepatitis(){}
	public Hepatitis(int gender, int age, int HbA, int PT_INR, int bilirubin, float creatinine, int ammonia){
		this.gender = gender;
		this.age = age;
		this.HbA = HbA;
		this.PT_INR = PT_INR;
		this.bilirubin = bilirubin;
		this.creatinine = creatinine;
		this.ammonia = ammonia;
	}

	public double cal_hepatitis(){
		double score = -2.332;
	    score += this.age * 0.024;
	    score += this.HbA * -0.075;

		if (this.PT_INR > 3) score += 1.551;

		score += this.bilirubin * 0.054;

		if (this.gender == 0 && this.creatinine > 1.1) score += 0.495;
		else if (this.gender == 1 && this.creatinine > 1.2) score += 0.495;

		score += this.ammonia * 0.003;

		score = (1 - Math.pow(0.623, Math.exp(score))) * 100;
		return score;
	}
}

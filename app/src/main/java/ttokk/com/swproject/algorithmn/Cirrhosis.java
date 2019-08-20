package ttokk.com.swproject.algorithmn;

/*
 * 간경병증
 */

public class Cirrhosis {

    int gender; //성별
    int age; //나이
    int AFP;
    float albumin; //알부민
    int platelet; //혈소판
    int DLD_serve;
    int alcohol; //3년동안 80ml음주여부

    Cirrhosis() {
    }

    public Cirrhosis(int gender, int age, int AFP, float albumin, int platelet, int DLD_serve, int alcohol) {
        this.gender = gender;
        this.age = age;
        this.AFP = AFP;
        this.albumin = albumin;
        this.platelet = platelet;
        this.DLD_serve = DLD_serve;
        this.alcohol = alcohol;
    }

    public double cal_cirrhosis() {
        double score = -6.0993;

        if (this.gender == 1) score += 1.6230;

        if (this.age >= 40) score += 1.6463;

        if (this.platelet < 150000) score += 0.1513;

        if (this.albumin < 3.5) score += 0.1714;

        if (this.AFP >= 20) score += 1.0940;

        if (this.alcohol == 1) score += 0.8912;

        if (this.DLD_serve == 1) score += 1.8374;

        score = Math.exp(score);
        return (score / (1 + score));
    }
}

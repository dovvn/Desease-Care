package ttokk.com.swproject.model.sqliteDB;

import java.io.Serializable;

public class Water implements Serializable {
    public int water_data;
    public String date;

    public int getWater_data() {
        return water_data;
    }

    public void setWater_data(int water_data) {
        this.water_data = water_data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getWater_ml() {
        return water_ml;
    }

    public void setWater_ml(int water_ml) {
        this.water_ml = water_ml;
    }

    public int water_ml;
}

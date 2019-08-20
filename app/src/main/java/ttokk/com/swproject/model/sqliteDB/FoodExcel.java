package ttokk.com.swproject.model.sqliteDB;

import java.io.Serializable;

public class FoodExcel implements Serializable {
    public String name;
    public String category;
    public int kcal;
    public int carbo;
    public float protein;
    public int fat;
    public String sugar;
    public String salt;
    public String chole;
    public String saturfat;
    public int transfat;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getCarbo() {
        return carbo;
    }

    public void setCarbo(int carbo) {
        this.carbo = carbo;
    }

    public float getProtein() {
        return protein;
    }

    public void setProtein(float protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getChole() {
        return chole;
    }

    public void setChole(String chole) {
        this.chole = chole;
    }

    public String getSaturfat() {
        return saturfat;
    }

    public void setSaturfat(String saturfat) {
        this.saturfat = saturfat;
    }

    public int getTransfat() {
        return transfat;
    }

    public void setTransfat(int transfat) {
        this.transfat = transfat;
    }
}

package seohyeon.mobile.yoshisik;

public class DietData {
    private long _id;
    private String breakfast;
    private String lunch;
    private String dinner;
    private double carbs;
    private double protein;
    private double fat;
    private double salt;
    private double sugar;
    private double cal;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public String getLunch() {
        return lunch;
    }

    public void setLunch(String lunch) {
        this.lunch = lunch;
    }

    public String getDinner() {
        return dinner;
    }

    public void setDinner(String dinner) {
        this.dinner = dinner;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getSalt() {
        return salt;
    }

    public void setSalt(double salt) {
        this.salt = salt;
    }

    public double getSugar() {
        return sugar;
    }

    public void setSugar(double sugar) {
        this.sugar = sugar;
    }

    public double getCal() {
        return cal;
    }

    public void setCal(double cal) {
        this.cal = cal;
    }

    public DietData(String breakfast, String lunch, String dinner, double carbs, double protein, double fat, double salt, double sugar, double cal) {
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.carbs = carbs;
        this.protein = protein;
        this.fat = fat;
        this.salt = salt;
        this.sugar = sugar;
        this.cal = cal;
    }
}

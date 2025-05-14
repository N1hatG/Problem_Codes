public class Purchase {
    
    private String type;
    private int cash;
    private String wish;
    private int value;
    private String line;

    public Purchase(String type, int cash, String wish, int value, String line) {
        this.type = type;
        this.cash = cash;
        this.wish = wish;
        this.value = value;
        this.line = line;
    }
    //Getters
    public String getline(){
        return line;
    }
    public String getType(){
        return type;
    }
    public Integer getCash() {
        return cash;
    }
    public String getWish() {
        return wish;
    }
    public Integer getValue() {
        return value;
    }

    //Setters
    public void setType(String type){
        this.type = type;
    }
    public void setCash(int cash){
        this.cash = cash;
    }  
    public void setWish(String wish) {
        this.wish = wish;
    }
    public void setValue(int value){
        this.value = value;
    }
}

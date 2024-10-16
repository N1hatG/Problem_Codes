public class Road {
    private final String startPoint;
    private final String endPoint;
    private final int length;
    private final int id;

    public Road(String startPoint, String endPoint, int length, int id){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.length = length;
        this.id = id;
    }

    //Getters
    public int getLength(){
        return length;
    }
    public int getID(){
        return id;
    }
    public String getStartPoint(){
        return startPoint;
    }
    public String getEndPoint(){
        return endPoint;
    }

    @Override
    public String toString(){
        return startPoint + "\t" + endPoint +"\t" + length +"\t" + id;
    }
}

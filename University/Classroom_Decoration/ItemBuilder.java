// Builder Interface
interface ItemBuilder {
    void buildName(String name);
    void buildType(String type);
    void buildDimensions(double width, double length, double height);
    void buildWallDecoration(Decoration wallDecoration);
    void buildFlooringDecoration(Decoration flooringDecoration);
    void buildPrice(double price);
    void buildAreaPerUnit(double areaPerUnit);
    Object getItem();
}

/**
 * Concrete builder implementing ItemBuilder to build a Classroom object.
 */
class ClassroomBuilder implements ItemBuilder {
    private Classroom classroom;

    public ClassroomBuilder() {
        this.classroom = new Classroom("", "", 0, 0, 0);
    }

    @Override
    public void buildName(String name) {
        this.classroom.setName(name);
    }
    @Override
    public void buildType(String shape) {
        this.classroom.setType(shape);
    }

    @Override
    public void buildDimensions(double width, double length, double height) {
        this.classroom.setWidth(width);
        this.classroom.setLength(length);
        this.classroom.setHeight(height);
    }

    @Override
    public void buildWallDecoration(Decoration wallDecoration) {
        classroom.setWallDecoration(wallDecoration);
    }

    @Override
    public void buildFlooringDecoration(Decoration flooringDecoration) {
        classroom.setFlooringDecoration(flooringDecoration);
    }

    @Override
    public void buildPrice(double price){
        // Not applicable for Classroom
    }

    @Override
    public void buildAreaPerUnit(double areaPerUnit){
        // Not applicable for Classroom
    }
    @Override
    public Classroom getItem() {
        return this.classroom;
    }
}

/**
 * Concrete builder implementing ItemBuilder to build a Decoration object.
 */
class DecorationBuilder implements ItemBuilder {
    private Decoration decoration;

    public DecorationBuilder() {
        this.decoration = new Decoration("", "", 0, 0);
    }

    @Override
    public void buildName(String name) {
        this.decoration.setName(name);
    }

    @Override
    public void buildType(String type) {
        this.decoration.setType(type);
    }

    @Override
    public void buildDimensions(double width, double length, double height) {
        // Not applicable for Decoration
    }

    @Override
    public void buildWallDecoration(Decoration wallDecoration) {
        // Not applicable for Decoration
    }

    @Override
    public void buildFlooringDecoration(Decoration flooringDecoration) {
        // Not applicable for Decoration
    }

    @Override
    public void buildPrice(double price){
        this.decoration.setPrice(price);
    }
    
    @Override
    public void buildAreaPerUnit(double areaPerUnit){
        this.decoration.setAreaPerUnit(areaPerUnit);
    }
    @Override
    public Decoration getItem() {
        return this.decoration;
    }
}

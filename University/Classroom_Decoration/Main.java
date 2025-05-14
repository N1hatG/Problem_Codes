class Main{
    public static void main(String[] args){
        String itemsfilepath = args[0];
        String decorateFilePath = args[1];
        String outputFilePath = args[2];
        FileIO fileIO = new FileIO();

        fileIO.readFile(itemsfilepath, true, true, true);
        fileIO.readFile(decorateFilePath, true, true, false);
        
        fileIO.parseDecorations();
        fileIO.parseClassrooms();
        
        Decorator decorator = new Decorator(fileIO);
        decorator.Decorate(outputFilePath);
    }
}
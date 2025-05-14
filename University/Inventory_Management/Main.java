
class Main{
    public static void main(String args[]){

        if (args.length < 2) {
            System.err.println("Usage: java Main <inputFilePath> <outputFilePath>");
            System.exit(1); // Exit with a status code indicating an error
        }

        String inputFilePath = args[0];
        String outputFilePath = args[1];

        Processor<Command> processor = new Processor<>(inputFilePath, outputFilePath);
        try{
            processor.parseCommands();
            processor.executeCommands();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
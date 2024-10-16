class BNF{

    public static void main(String[] args) {
        String inputFilePath = args[0];
        String outputFilePath = args[1];
        FileIO fileIO = new FileIO();
        fileIO.readFile(inputFilePath, true, true);
        fileIO.parseInputs();
        // Instantiate simplifier with FileIO object
        simplifier simplifierObj = new simplifier(fileIO);
        String output = simplifierObj.simplify();
        fileIO.writeToFile(outputFilePath, output, false, false);

    }
}
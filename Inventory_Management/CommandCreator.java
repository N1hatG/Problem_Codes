public interface CommandCreator<T extends Command> {
    T createCommand(String[] parts) throws Exception;
}

class AddCommandCreator implements CommandCreator<AddCommand> {
    @Override
    public AddCommand createCommand(String[] parts) throws Exception {
        if (parts.length == 6) {
            return new AddCommand(parts[1], parts[2], parts[3], Integer.parseInt(parts[4]), Double.parseDouble(parts[5]));
        } else {
            throw new Exception("Incorrect number of arguments for ADD Command");
        }
    }
}
class RemoveCommandCreator implements CommandCreator<RemoveCommand> {
    @Override
    public RemoveCommand createCommand(String[] parts) throws Exception {
        if (parts.length == 2) {
            return new RemoveCommand(Integer.parseInt(parts[1]));
        } else {
            throw new Exception("Incorrect number of arguments for REMOVE command");
        }
    }
}
class SearchByBarcodeCommandCreator implements CommandCreator<SearchByBarcodeCommand> {
    @Override
    public SearchByBarcodeCommand createCommand(String[] parts) throws Exception {
        if (parts.length == 2) {
            return new SearchByBarcodeCommand(Integer.parseInt(parts[1]));
        } else {
            throw new Exception("Incorrect number of arguments for SEARCHBYBARCODE command");
        }
    }
}

class SearchByNameCommandCreator implements CommandCreator<SearchByNameCommand> {
    @Override
    public SearchByNameCommand createCommand(String[] parts) throws Exception {
        if (parts.length == 2) {
            return new SearchByNameCommand(parts[1]);
        } else {
            throw new Exception("Incorrect number of arguments for SEARCHBYNAME command");
        }
    }
}

class DisplayCommandCreator implements CommandCreator<DisplayCommand> {
    @Override
    public DisplayCommand createCommand(String[] parts) throws Exception {
        if (parts.length == 1) {
            return new DisplayCommand();
        } else {
            throw new Exception("Incorrect number of arguments for DISPLAY command");
        }
    }
}
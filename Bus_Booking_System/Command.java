/**
 * Represents a command in the booking system.
 */
public class Command {
    private String type;
    protected Integer voyageID;
    private Integer[] values;
    protected String[] originalCommand;


    /**
     * Constructs a command with the given parameters.
     * @param type The type of the command.
     * @param voyageID The ID of the voyage associated with the command.
     * @param values The values associated with the command (e.g., seat numbers).
     * @param originalCommand The original command as an array of strings.
     */
    public Command(String type, Integer voyageID, Integer[] values, String[] originalCommand){
        this.type = type;
        this.voyageID = voyageID;
        this.values = values;
        this.originalCommand = originalCommand;
    }

    //Getters
    public String getType(){
        return this.type;
    }
    public Integer getVoyageID(){
        return this.voyageID;
    }
    public Integer[] getValues(){
        return this.values;
    }
    public String getOriginalCommand(){
        String command = "";
        for(int i = 0; i< this.originalCommand.length; i++){
            if(i != originalCommand.length -1)
                command += originalCommand[i] + "\t";
            else
                command += originalCommand[i];
        }
        return command;
    }

    public String getValuesString(){
        String output = "";
        output += this.values[0];
        for(int i = 1; i<values.length; i++)
            output += "-" + this.values[i];
        return output;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Command: ");
        sb.append(type).append(" ");
        sb.append(voyageID).append("  ");
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                sb.append(values[i]);
                if (i < values.length - 1) {
                    sb.append(" ");
                }
            }
        } else {
            sb.append("null");
        }
        return sb.toString();
    }

}

/**
 * Represents an "INIT_VOYAGE" command in the booking system.
 */
class InitCommand extends Command{

    private String busType;
    private String departure;
    private String destination;
    private int rows;
    private double price;
    private double refundPolicy;
    private double premiumFee;


    /**
     * Constructs an InitCommand object with the given parameters.
     * @param parts An array containing the parts of the INIT_VOYAGE command.
     */
    public InitCommand(String[] parts){
        super("INIT_VOYAGE" , null, null, parts);
        this.busType = parts[1];
        super.voyageID = Integer.parseInt(parts[2]);
        this.departure = parts[3];
        this.destination = parts[4];
        this.rows = Integer.parseInt(parts[5]);
        this.price = Double.parseDouble(parts[6]);
        if(parts.length > 7)
            this.refundPolicy = Double.parseDouble(parts[7]);
        if (parts.length > 8)
            this.premiumFee = Double.parseDouble(parts[8]);
    }

    // Getters
    @Override
    public Integer getVoyageID(){
        return super.voyageID;
    }
    public String getBusType() {
        return busType;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public int getRows() {
        return rows;
    }

    public double getPrice() {
        return price;
    }

    public double getRefundPolicy() {
        return refundPolicy;
    }

    public double getPremiumFee() {
        return premiumFee;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Command type: " + super.getType());
        sb.append("Type:" + busType).append(" ");
        sb.append("ID:" +super.getVoyageID()).append(" ");
        sb.append(departure).append(" ");
        sb.append(destination).append(" ");
        sb.append(rows).append(" ");
        sb.append(price).append(" ");
        sb.append(refundPolicy).append(" ");
        sb.append(premiumFee);
        return sb.toString();
    }
}

/**
 * BusBuilder interface defines methods for building different types of buses.
 */
interface BusBuilder {
    void buildIsVoyageActive(boolean isVoyageActive);
    void buildBusType(String busType);
    void buildBusID(Integer busID);
    void buildDeparture(String departure);
    void buildDestination(String destination);
    void buildRows(int rows);
    void buildPrice(double price);
    void buildRefundPolicy(double refundPolicy);
    void buildPremiumFee(double premiumFee);
    void initializeSeatingPlan();
    Bus getBus();
}

/**
 * Concrete builder implementing BusBuilder to build a StandardBus object.
 */
class StandardBusBuilder implements BusBuilder{
    private StandardBus standardBus;

    /**
     * Constructs a StandardBusBuilder object and initializes a StandardBus instance.
     */
    public StandardBusBuilder(){
        this.standardBus = new StandardBus(false, null, null, null, null, 0, 0, 0);
    }

    @Override
    public void initializeSeatingPlan(){
        this.standardBus.initializeSeatingPlan();
    }

    @Override
    public void buildIsVoyageActive(boolean isVoyageActive){
        standardBus.setIsVoyageActive(isVoyageActive);
    }

    @Override
    public void buildBusType(String busType){
        standardBus.setBusType(busType);
    }

    @Override
    public void buildBusID(Integer busID){
        standardBus.setBusID(busID);
    }

    @Override
    public void buildDeparture(String departure){
        standardBus.setDeparture(departure);
    }

    @Override
    public void buildDestination(String destination){
        standardBus.setDestination(destination);
    }

    @Override 
    public void buildRows(int rows){
        standardBus.setRows(rows);
    }

    @Override
    public void buildPrice(double price){
        standardBus.setPrice(price);
    }

    @Override
    public void buildRefundPolicy(double refundPolicy){
        standardBus.setRefundPolicy(refundPolicy);
    }

    @Override
    public void buildPremiumFee(double premiumFee){
        //Not applicable for Standard bus
    }

    @Override
    public Bus getBus(){
        return this.standardBus;
    }
}

/**
 * Concrete builder implementing BusBuilder to build a PremiumBus object.
 */
class PremiumBusBuilder implements BusBuilder{
    private PremiumBus premiumBus;

    /**
     * Constructs a PremiumBusBuilder object and initializes a PremiumBus instance.
     */
    public PremiumBusBuilder(){
        this.premiumBus = new PremiumBus(false, null, null, null, null, 0, 0, 0, 0);
    }
    
    @Override
    public void initializeSeatingPlan(){
        this.premiumBus.initializeSeatingPlan();
    }

    @Override
    public void buildIsVoyageActive(boolean isVoyageActive){
        premiumBus.setIsVoyageActive(isVoyageActive);
    }

    @Override
    public void buildBusType(String busType){
        premiumBus.setBusType(busType);
    }

    @Override
    public void buildBusID(Integer busID){
        premiumBus.setBusID(busID);
    }

    @Override
    public void buildDeparture(String departure){
        premiumBus.setDeparture(departure);
    }

    @Override
    public void buildDestination(String destination){
        premiumBus.setDestination(destination);
    }

    @Override 
    public void buildRows(int rows){
        premiumBus.setRows(rows);
    }

    @Override
    public void buildPrice(double price){
        premiumBus.setPrice(price);
    }

    @Override
    public void buildRefundPolicy(double refundPolicy){
        premiumBus.setRefundPolicy(refundPolicy);
    }

    @Override
    public void buildPremiumFee(double premiumFee){
        premiumBus.setPremiumFee(premiumFee);
    }

    @Override
    public Bus getBus(){
        return this.premiumBus;
    }
}

/**
 * Concrete builder implementing BusBuilder to build a MiniBus object.
 */
class MiniBusBuilder implements BusBuilder{
    private MiniBus miniBus;

    /**
     * Constructs a MiniBusBuilder object and initializes a MiniBus instance.
     */
    public MiniBusBuilder(){
        this.miniBus = new MiniBus(false, null, null, null, null, 0, 0, 0);
    }
    
    @Override
    public void initializeSeatingPlan(){
        this.miniBus.initializeSeatingPlan();
    }
    @Override
    public void buildIsVoyageActive(boolean isVoyageActive){
        miniBus.setIsVoyageActive(isVoyageActive);
    }

    @Override
    public void buildBusType(String busType){
        miniBus.setBusType(busType);
    }

    @Override
    public void buildBusID(Integer busID){
        miniBus.setBusID(busID);
    }

    @Override
    public void buildDeparture(String departure){
        miniBus.setDeparture(departure);
    }

    @Override
    public void buildDestination(String destination){
        miniBus.setDestination(destination);
    }

    @Override 
    public void buildRows(int rows){
        miniBus.setRows(rows);
    }

    @Override
    public void buildPrice(double price){
        miniBus.setPrice(price);
    }

    @Override
    public void buildRefundPolicy(double refundPolicy){
        miniBus.setRefundPolicy(refundPolicy);
    }

    @Override
    public void buildPremiumFee(double premiumFee){
        //Not applicable for Minibus
    }

    @Override
    public Bus getBus(){
        return this.miniBus;
    }
}

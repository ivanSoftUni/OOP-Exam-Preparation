package fairyShop.models;

public class Sleepy extends BaseHelper {

    private static final int INITIAL_ENERGY = 50;
    private static final int WORK_DECREASES_ENERGY = 15;


    public Sleepy(String name) {
        super(name, INITIAL_ENERGY);
    }

    @Override
    public void work(){
        if (getEnergy() - WORK_DECREASES_ENERGY < 0) {
            setEnergy(0);
        } else {
            setEnergy(getEnergy() - WORK_DECREASES_ENERGY);
        }
    }


}

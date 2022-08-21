package fairyShop.models;

import java.util.ArrayList;
import java.util.Collection;

import static fairyShop.common.ExceptionMessages.HELPER_NAME_NULL_OR_EMPTY;

public abstract class BaseHelper implements Helper {

    private static final int WORK_DECREASES_ENERGY = 10;

    private String name;
    private int energy;
    private Collection<Instrument> instruments;

    public BaseHelper(String name, int energy) {
        setName(name);
        setEnergy(energy);
        this.instruments = new ArrayList<>();
    }


    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(HELPER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    void setEnergy(int energy) {
        this.energy = energy;
    }

    @Override
    public void work() {
        if (getEnergy() - WORK_DECREASES_ENERGY < 0) {
            setEnergy(0);
        } else {
            setEnergy(getEnergy() - WORK_DECREASES_ENERGY);
        }
    }

    @Override
    public void addInstrument(Instrument instrument) {
        instruments.add(instrument);
    }

    @Override
    public boolean canWork() {
        return getEnergy() > 0;
    }


    public String getName() {
        return name;
    }


    public int getEnergy() {
        return energy;
    }

    @Override
    public Collection<Instrument> getInstruments() {
        return instruments;
    }

}

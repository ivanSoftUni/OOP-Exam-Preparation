package fairyShop.models;

import static fairyShop.common.ExceptionMessages.INSTRUMENT_POWER_LESS_THAN_ZERO;

public class InstrumentImpl implements Instrument {

    private static final int DECREASE_INSTRUMENT_POWER = 10;

    private int power;

    public InstrumentImpl(int power) {
        setPower(power);
    }

    public void setPower(int power) {
        if (this.power < 0) {
            throw new IllegalArgumentException(INSTRUMENT_POWER_LESS_THAN_ZERO);
        }
        this.power = power;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public void use() {
        power -= DECREASE_INSTRUMENT_POWER;
        if (power < 0) {
            power = 0;
        }
    }

    @Override
    public boolean isBroken() {
        return power <= 0;
    }
}

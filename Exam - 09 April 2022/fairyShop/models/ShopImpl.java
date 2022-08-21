package fairyShop.models;

public class ShopImpl implements Shop {

    private Present present;

    public ShopImpl() {

    }

    @Override
    public void craft(Present present, Helper helper) {
        for (Instrument instrument : helper.getInstruments()) {

            while (!instrument.isBroken() && helper.canWork()) {
                present.getCrafted();
                helper.work();
                instrument.use();
                if (present.isDone()) {
                    return;
                }
                if (!helper.canWork()) {
                    return;
                }
            }
        }

    }
}

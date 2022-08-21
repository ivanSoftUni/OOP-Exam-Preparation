package fairyShop.core;

import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;

import static fairyShop.common.ConstantMessages.*;
import static fairyShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private HelperRepository helperRepository;
    private PresentRepository presentRepository;

    public ControllerImpl() {
        helperRepository = new HelperRepository();
        presentRepository = new PresentRepository();
    }

    @Override
    public String addHelper(String type, String helperName) {
        BaseHelper helper;

        if (type.equals("Happy")) {
            helper = new Happy(helperName);
        } else if (type.equals("Sleepy")) {
            helper = new Sleepy(helperName);
        } else {
            throw new IllegalArgumentException(HELPER_TYPE_DOESNT_EXIST);
        }
        helperRepository.add(helper);

        return String.format(ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Helper helper = helperRepository.getModels().stream()
                .filter(h -> h.getName().equals(helperName))
                .findFirst().orElse(null);
        if (helper != null) {
            Instrument instrument = new InstrumentImpl(power);
            helper.addInstrument(instrument);
        } else {
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }

        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        presentRepository.add(present);

        return String.format(SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {

        Helper helper = helperRepository.getModels().stream()
                .filter(h -> h.getEnergy() > 50)
                .findFirst().orElse(null);
        if (helper == null) {
            throw new IllegalArgumentException(NO_HELPER_READY);
        }

        Present present = presentRepository.findByName(presentName);

        Shop shop = new ShopImpl();
        shop.craft(present, helper);

        String done = present.isDone() ? "done" : "not done";
        long brokenInstruments = helper.getInstruments().stream()
                .filter(Instrument::isBroken).count();

        return String.format(PRESENT_DONE + COUNT_BROKEN_INSTRUMENTS, presentName, done, brokenInstruments);
    }

    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d presents are done!%n", presentRepository.getModels().stream().filter(Present::isDone).count()));
        sb.append(String.format("Helpers info:%n"));
        if (helperRepository.getModels().isEmpty()){
        }else {

            for (Helper helper : helperRepository.getModels()) {
                sb.append(String.format("Name: %s%n" +
                        "Energy: %d%n" +
                        "Instruments: %d not broken left", helper.getName(), helper.getEnergy(), helper.getInstruments().stream().filter(instrument -> !instrument.isBroken()).count()));
                sb.append(System.lineSeparator());
            }
        }


        return sb.toString();
    }
}

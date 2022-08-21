package fairyShop.repositories;

import fairyShop.models.BaseHelper;
import fairyShop.models.Helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HelperRepository implements Repository<BaseHelper> {

    private Collection<BaseHelper> helpers;

    public HelperRepository() {
        this.helpers = new ArrayList<>();
    }

    @Override
    public Collection<BaseHelper> getModels() {
        return Collections.unmodifiableCollection(this.helpers);
    }

    @Override
    public void add(BaseHelper helper) {
        helpers.add(helper);
    }

    @Override
    public boolean remove(BaseHelper helper) {
        return helpers.remove(helper);
    }

    @Override
    public BaseHelper findByName(String name) {
        return helpers.stream().filter(h -> h.getName().equals(name))
                .findFirst().orElse(null);
    }
}

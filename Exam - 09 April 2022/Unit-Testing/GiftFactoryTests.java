package gifts;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static junit.framework.TestCase.*;

public class GiftFactoryTests {

    private Gift gift;
    private Gift gift1;
    private GiftFactory giftFactory;
    private Collection<Gift> gifts;

    @Before
    public void setUp() {
        giftFactory = new GiftFactory();
        gift = new Gift("Toys", 10);
        gift1 = new Gift("Car", 100);
        gifts = new ArrayList<>();
    }

    @Test
    public void test_CreateGiftFactoryConstructor() {
        GiftFactory giftFactory = new GiftFactory();
        assertEquals(0, giftFactory.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_CreateGift_WithExistGift() {
        giftFactory.createGift(gift);
        giftFactory.createGift(gift);
    }

    @Test(expected = NullPointerException.class)
    public void test_RemoveGift_WhenNameIsNull() {
        giftFactory.removeGift(null);
    }

    @Test
    public void test_RemoveGift_Success() {
        giftFactory.createGift(gift);
        giftFactory.removeGift("Toys");
    }

    @Test
    public void test_getPresentWithLeastMagic() {
        giftFactory.createGift(gift);
        giftFactory.createGift(gift1);
        Gift actualGift = giftFactory.getPresentWithLeastMagic();
        assertEquals(gift, actualGift);
    }

    @Test
    public void test_getPresent_ShouldReturnGift() {
        assertEquals(0, giftFactory.getCount());
        giftFactory.createGift(gift);
        Gift actualGift = giftFactory.getPresent("Toys");
        assertEquals("Toys", actualGift.getType());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_getPresents_ShouldReturn_UnmodifiableCollections() {
        Collection<Gift> presents = giftFactory.getPresents();

        presents.add(gift);
    }
}

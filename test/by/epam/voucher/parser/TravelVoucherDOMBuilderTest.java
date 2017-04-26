package by.epam.voucher.parser;


import by.epam.voucher.exception.IllegalVoucherException;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TravelVoucherDOMBuilderTest {
    private static TravelVoucherDOMBuilder builder;

    @BeforeClass
    public static void initBuilder() throws IllegalVoucherException {
        builder = new TravelVoucherDOMBuilder();
        final String PATH = "data/tourist-vouchers.xml";
        builder.buildTravelVouchersSet(PATH);
    }
    @Test
    public void buildSetPeriodicalsTest() {
        assertEquals(16, builder.getTravelVouchers().size());
    }

}
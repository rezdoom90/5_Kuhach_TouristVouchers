package by.epam.voucher.parser;

import by.epam.voucher.entity.AirTravelVoucher;
import by.epam.voucher.entity.RoadTravelVoucher;
import by.epam.voucher.entity.TravelVoucher;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TravelVoucherStAXBuilderTest {

    private static TravelVoucherStAXBuilder builder;

    @BeforeClass
    public static void initBuilder() {
        builder = new TravelVoucherStAXBuilder();
        final String PATH = "data/tourist-vouchers.xml";
        builder.buildTravelVouchersSet(PATH);
    }

    @Test
    public void buildTravelVouchersSetTestSize() {
        assertEquals(16, builder.getTravelVouchers().size());
    }

    @Test
    public void buildTravelVouchersSetTest_ContainsAirTravelVoucher() {
        AirTravelVoucher airTravelVoucher = new AirTravelVoucher();
        airTravelVoucher.setHotelType("4FB1TVCN");
        airTravelVoucher.setType(TravelVoucher.Type.PHOTOGRAPHY);
        airTravelVoucher.setCost(572.8);
        airTravelVoucher.setDuration(7);
        airTravelVoucher.setCountry("USA");
        airTravelVoucher.setCommonAirliner(true);

        assertTrue(builder.getTravelVouchers().contains(airTravelVoucher));
    }

    @Test
    public void buildTravelVouchersSetTest_ContainsRoadTravelVoucher() {
        RoadTravelVoucher roadTravelVoucher = new RoadTravelVoucher();
        roadTravelVoucher.setHotelType("3BB2CN");
        roadTravelVoucher.setType(TravelVoucher.Type.CYCLING);
        roadTravelVoucher.setCost(322.1);
        roadTravelVoucher.setDuration(7);
        roadTravelVoucher.setCountry("Italy");
        roadTravelVoucher.setBus(false);

        assertTrue(builder.getTravelVouchers().contains(roadTravelVoucher));
    }

}
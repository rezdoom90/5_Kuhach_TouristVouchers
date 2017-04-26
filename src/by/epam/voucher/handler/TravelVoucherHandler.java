package by.epam.voucher.handler;

import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import by.epam.voucher.entity.*;
import by.epam.voucher.enumeration.TravelVoucherSpecEnum;
import by.epam.voucher.exception.IllegalAttributeException;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class TravelVoucherHandler extends DefaultHandler {
    static final int HOTELTYPE_ATTR = 0;
    static final int TYPE_ATTR = 1;
    private Set<TravelVoucher> travelVouchers;
    private TravelVoucher current;
    private TravelVoucherSpecEnum currentEnum;
    private EnumSet<TravelVoucherSpecEnum> withText;

    public TravelVoucherHandler() {
        travelVouchers = new HashSet<>();
        withText = EnumSet.range(TravelVoucherSpecEnum.COST, TravelVoucherSpecEnum.RAILTRAVELVOUCHER);
    }

    public Set<TravelVoucher> getTravelVouchers() {
        return Collections.unmodifiableSet(travelVouchers);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        switch (localName) {
            case "roadTravelVoucher":
                current = new RoadTravelVoucher();
                current.setHotelType(attrs.getValue(HOTELTYPE_ATTR).intern());

                if (attrs.getLength() == 2) {
                    current.setType(TravelVoucher.Type.valueOf(attrs.getValue(TYPE_ATTR).toUpperCase()));
                }
                break;
            case "waterTravelVoucher":
                current = new WaterTravelVoucher();
                current.setHotelType(attrs.getValue(HOTELTYPE_ATTR).intern());

                if (attrs.getLength() == 2) {
                    current.setType(TravelVoucher.Type.valueOf(attrs.getValue(TYPE_ATTR).toUpperCase()));
                }
                break;
            case "airTravelVoucher":
                current = new AirTravelVoucher();
                current.setHotelType(attrs.getValue(HOTELTYPE_ATTR).intern());

                if (attrs.getLength() == 2) {
                    current.setType(TravelVoucher.Type.valueOf(attrs.getValue(TYPE_ATTR).toUpperCase()));
                }
                break;
            case "railTravelVoucher":
                current = new RailTravelVoucher();
                current.setHotelType(attrs.getValue(HOTELTYPE_ATTR).intern());

                if (attrs.getLength() == 2) {
                    current.setType(TravelVoucher.Type.valueOf(attrs.getValue(TYPE_ATTR).toUpperCase()));
                }
                break;
            default:
                TravelVoucherSpecEnum temp = TravelVoucherSpecEnum.valueOf(localName.toUpperCase());
                if (withText != null && withText.contains(temp)) {
                    currentEnum = temp;
                }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if ("roadTravelVoucher".equals(localName) || "waterTravelVoucher".equals(localName) ||
                "airTravelVoucher".equals(localName) || "railTravelVoucher".equals(localName)) {
            travelVouchers.add(current);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim().intern();
        if (currentEnum != null) {
            switch (currentEnum) {
                case COST:
                    current.setCost(Double.parseDouble(s));
                    break;
                case DURATION:
                    current.setDuration(Integer.valueOf(s));
                    break;
                case COUNTRY:
                    current.setCountry(s);
                    break;
                case COMMONAIRLINER:
                    ((AirTravelVoucher) current).setCommonAirliner(Boolean.valueOf(s));
                    break;
                case BUS:
                    ((RoadTravelVoucher) current).setBus(Boolean.valueOf(s));
                    break;
                case CRUISE:
                    ((WaterTravelVoucher) current).setCruise(Boolean.valueOf(s));
                    break;
                case BUSINESSCLASS:
                    ((RailTravelVoucher) current).setBusinessClass(Boolean.valueOf(s));
                    break;
                default:
                    throw new IllegalAttributeException(
                            currentEnum.getDeclaringClass(), currentEnum.name());
            }
        }
        currentEnum = null;
    }
}
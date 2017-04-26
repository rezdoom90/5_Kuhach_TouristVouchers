package by.epam.voucher.enumeration;

public enum TravelVoucherSpecEnum {
    TRAVELVOUCHERS("travelVoucher"),
    HOTELTYPE("hotelType"),
    TYPE("type"),
    COST("cost"),
    DURATION("duration"),
    COUNTRY("country"),
    ROADTRAVELVOUCHER("roadTravelVoucher"),
    BUS("bus"),
    WATERTRAVELVOUCHER("waterTravelVoucher"),
    CRUISE("cruise"),
    AIRTRAVELVOUCHER("airTravelVoucher"),
    COMMONAIRLINER("commonAirliner"),
    RAILTRAVELVOUCHER("railTravelVoucher"),
    BUSINESSCLASS("businessClass");


    private String value;

    private TravelVoucherSpecEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

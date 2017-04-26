package by.epam.voucher.entity;

public enum TravelVoucherSpecEnum {
    TRAVEL_VOUCHER("travelVoucher"),
    HOTEL_TYPE("hotelType"),
    TYPE("type"),
    COST("cost"),
    DURATION("duration"),
    COUNTRY("country"),
    COMMON_AIRLINER("commonAirliner"),
    BUS("bus"),
    CRUISE("cruise"),
    BUSINESS_CLASS("businessClass"),
    ROAD_TRAVEL_VOUCHER("roadTravelVoucher"),
    WATER_TRAVEL_VOUCHER("waterTravelVoucher"),
    AIR_TRAVEL_VOUCHER("airTravelVoucher"),
    RAIL_TRAVEL_VOUCHER("railTravelVoucher");


    private String value;

    private TravelVoucherSpecEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

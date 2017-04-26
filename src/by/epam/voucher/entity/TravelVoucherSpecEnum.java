package by.epam.voucher.entity;

public enum TravelVoucherSpecEnum {
    TRAVEL_VOUCHER("travelVoucher"),
    HOTEL_TYPE("hotelType"),
    TYPE("type"),
    COST("cost"),
    DURATION("duration"),
    COUNTRY("country"),
    TRANSPORT("transport");

    private String value;

    private TravelVoucherSpecEnum(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

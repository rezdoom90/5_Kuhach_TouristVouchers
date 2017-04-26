package by.epam.voucher.entity;

public abstract class TravelVoucher {
    public enum Type {
        HOLIDAY, CULTURE, MIXED, CYCLING, PHOTOGRAPHY, POLAR, WALKING, WILDLIFE, WINTER, UNSPECIFIED
    }

    private String hotelType;
    private Type type;
    private double cost;
    private int duration;
    private String country;

    public TravelVoucher() {
        type = Type.UNSPECIFIED;
        hotelType = "1RO1"; //1-star, room only, 1 person
        country = "unknown country";
        cost = 0.0;
        duration = 0;
    }

    public static TravelVoucher getEmptyTravelVoucher() {
        return new TravelVoucher(){};
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TravelVoucher)) return false;

        TravelVoucher that = (TravelVoucher) o;

        if (Double.compare(that.cost, cost) != 0) return false;
        if (duration != that.duration) return false;
        if (!hotelType.equals(that.hotelType)) return false;
        if (type != that.type) return false;
        return country.equals(that.country);
    }

    @Override
    public int hashCode() {
        int result = 1;

        result = 31 * result + hotelType.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (int)cost;
        result = 31 * result + duration;
        result = 31 * result + country.hashCode();

        return result;
    }

    @Override
    public String toString() {
        return String.format("Voucher type: %s; hotel type: %s; country: %s; duration: %ds; cost: %f",
                type, hotelType, country, duration, cost);
    }
}

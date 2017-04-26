package by.epam.voucher.entity;

public class RoadTravelVoucher extends TravelVoucher {
    private boolean bus;

    public boolean isBus(){
        return bus;
    }

    public void setBus(boolean bus) {
        this.bus = bus;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        RoadTravelVoucher other = (RoadTravelVoucher) obj;
        return (bus == other.bus);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();

        result = 31 * result + (bus ? 1 : 0);

        return result;
    }

    @Override
    public String toString() {
        return String.format("%s %s travel voucher", super.toString(),
                bus ? "bus" : "car");
    }
}

package by.epam.voucher.entity;

public class WaterTravelVoucher extends TravelVoucher {
    private boolean cruise;

    public boolean isCruise(){
        return cruise;
    }

    public void setCruise(boolean cruise) {
        this.cruise = cruise;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        WaterTravelVoucher other = (WaterTravelVoucher) obj;
        return (cruise == other.cruise);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();

        result = 31 * result + (cruise ? 1 : 0);

        return result;
    }

    @Override
    public String toString() {
        return String.format("%s %s water travel voucher", super.toString(),
                cruise ? "cruise" : "non-cruise");
    }
}

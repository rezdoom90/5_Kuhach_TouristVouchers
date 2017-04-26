package by.epam.voucher.entity;

public class RailTravelVoucher extends TravelVoucher {
    private boolean businessClass;

    public boolean isBusinessClass(){
        return businessClass;
    }

    public void setBusinessClass(boolean businessClass) {
        this.businessClass = businessClass;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        RailTravelVoucher other = (RailTravelVoucher) obj;
        return (equals(other) && businessClass == other.businessClass);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();

        result = 31 * result + (businessClass ? 1 : 0);

        return result;
    }

    @Override
    public String toString() {
        return String.format("%s %s rail travel voucher", super.toString(),
                businessClass ? "business class" : "common class");
    }
}

package by.epam.voucher.entity;

public class AirTravelVoucher extends TravelVoucher {
    private boolean commonAirliner;

    public boolean isCommonAirliner(){
        return commonAirliner;
    }

    public void setCommonAirliner(boolean commonAirliner) {
        this.commonAirliner = commonAirliner;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        AirTravelVoucher other = (AirTravelVoucher) obj;
        return (commonAirliner == other.commonAirliner);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();

        result = 31 * result + (commonAirliner ? 1 : 0);

        return result;
    }

    @Override
    public String toString() {
        return String.format("%s %s air travel voucher", super.toString(),
                commonAirliner ? "common airliner" : "non-common airliner");
    }
}

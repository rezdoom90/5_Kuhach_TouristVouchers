package by.epam.voucher.exception;

public class IllegalAttributeException extends EnumConstantNotPresentException {
    public IllegalAttributeException(Class<? extends Enum> enumType, String constantName) {
        super(enumType, constantName);
    }

    @Override
    public Class<? extends Enum> enumType() {
        return super.enumType();
    }

    @Override
    public String constantName() {
        return super.constantName();
    }
}

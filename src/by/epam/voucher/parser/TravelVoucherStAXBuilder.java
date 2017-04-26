package by.epam.voucher.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.epam.voucher.entity.*;
import by.epam.voucher.enumeration.TravelVoucherSpecEnum;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.apache.logging.log4j.Level.ERROR;

public class TravelVoucherStAXBuilder {
    static final Logger logger = LogManager.getLogger();
    private HashSet<TravelVoucher> travelVouchers = new HashSet<>();
    private XMLInputFactory inputFactory;

    public TravelVoucherStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public Set<TravelVoucher> getTravelVouchers() {
        return travelVouchers;
    }

    public void buildTravelVouchersSet(String fileName) {
        try (FileInputStream inputStream = new FileInputStream(new File(fileName))) {
            XMLStreamReader reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                if (reader.next() == XMLStreamConstants.START_ELEMENT) {
                    switch (reader.getLocalName()) {
                        case "roadTravelVoucher":
                            travelVouchers.add(buildPeriodical(reader, new RoadTravelVoucher()));
                            break;
                        case "waterTravelVoucher":
                            travelVouchers.add(buildPeriodical(reader, new WaterTravelVoucher()));
                            break;
                        case "airTravelVoucher":
                            travelVouchers.add(buildPeriodical(reader, new AirTravelVoucher()));
                            break;
                        case "railTravelVoucher":
                            travelVouchers.add(buildPeriodical(reader, new RailTravelVoucher()));
                            break;
                    }
                }
            }
        } catch (XMLStreamException e) {
            logger.log(ERROR, "StAX parsing error! " + e.getMessage());
        } catch (FileNotFoundException e) {
            logger.log(ERROR, "File " + fileName + " not found! " + e);
        } catch (IOException e) {
            logger.log(ERROR, "Error while closing InputStream" + e);
        }
    }

    protected TravelVoucher buildPeriodical(XMLStreamReader reader, TravelVoucher travelVoucher) throws XMLStreamException {
        travelVoucher.setHotelType(reader.getAttributeValue(null, TravelVoucherSpecEnum.HOTELTYPE.getValue()));
        if (reader.getAttributeCount() == 2) {
            travelVoucher.setType(TravelVoucher.Type.valueOf(
                    reader.getAttributeValue(null, TravelVoucherSpecEnum.TYPE.getValue()).toUpperCase()));
        }
        while (reader.hasNext()) {
            switch (reader.next()) {
                case XMLStreamConstants.START_ELEMENT:
                    String name = reader.getLocalName();
                    switch (TravelVoucherSpecEnum.valueOf(name.toUpperCase())) {
                        case COST:
                            travelVoucher.setCost(Double.parseDouble(getXMLText(reader)));
                            break;
                        case DURATION:
                            travelVoucher.setDuration(Integer.parseInt(getXMLText(reader)));
                            break;
                        case COUNTRY:
                            travelVoucher.setCountry(getXMLText(reader));
                            break;
                        case COMMONAIRLINER:
                            ((AirTravelVoucher) travelVoucher).setCommonAirliner(Boolean.valueOf(getXMLText(reader)));
                            break;
                        case BUS:
                            ((RoadTravelVoucher) travelVoucher).setBus(Boolean.valueOf(getXMLText(reader)));
                            break;
                        case CRUISE:
                            ((WaterTravelVoucher) travelVoucher).setCruise(Boolean.valueOf(getXMLText(reader)));
                            break;
                        case BUSINESSCLASS:
                            ((RailTravelVoucher) travelVoucher).setBusinessClass(Boolean.valueOf(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    TravelVoucherSpecEnum newEnum = TravelVoucherSpecEnum.valueOf(name.toUpperCase());
                    if (newEnum == TravelVoucherSpecEnum.ROADTRAVELVOUCHER ||
                            newEnum == TravelVoucherSpecEnum.WATERTRAVELVOUCHER ||
                            newEnum == TravelVoucherSpecEnum.AIRTRAVELVOUCHER ||
                            newEnum == TravelVoucherSpecEnum.RAILTRAVELVOUCHER) {
                        return travelVoucher;
                    }
            }
        }
        throw new XMLStreamException("Unknown element found");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}

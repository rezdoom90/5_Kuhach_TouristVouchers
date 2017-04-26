package by.epam.voucher.parser;

import java.io.IOException;
import java.util.Set;

import by.epam.voucher.entity.TravelVoucher;
import by.epam.voucher.handler.TravelVoucherHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import static org.apache.logging.log4j.Level.ERROR;

public class TravelVoucherSAXBuilder {
    static final Logger logger = LogManager.getLogger();
    private Set<TravelVoucher> travelVouchers;
    private TravelVoucherHandler handler;
    private XMLReader reader;

    public TravelVoucherSAXBuilder() {
        handler = new TravelVoucherHandler();

        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            logger.log(ERROR, "SAX parser error: " + e.getMessage());
        }
    }

    public Set<TravelVoucher> getTravelVouchers() {
        return travelVouchers;
    }

    public void buildSetTravelVouchers(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            logger.log(ERROR, "SAX parser error: " + e);
        } catch (IOException e) {
            logger.log(ERROR, "IO stream error: " + e);
        }

        travelVouchers = handler.getTravelVouchers();
    }
}
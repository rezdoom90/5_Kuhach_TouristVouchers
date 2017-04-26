package by.epam.voucher.validator;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import static org.apache.logging.log4j.Level.ERROR;

public class XsdValidator {
    static final Logger logger = LogManager.getLogger();
    private SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

    public boolean validate(String fileName, String schemaName) {
        File schemaLoc = new File(schemaName);

        try {
            Schema schema = factory.newSchema(schemaLoc);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(fileName));
            return true;
        } catch (SAXException|IOException|IllegalArgumentException e) {
            logger.log(ERROR, "Not valid: " + e.getMessage());
            return false;
        }
    }
}

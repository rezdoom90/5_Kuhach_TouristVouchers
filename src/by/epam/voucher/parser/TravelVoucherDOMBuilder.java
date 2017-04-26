package by.epam.voucher.parser;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import by.epam.voucher.entity.*;
import by.epam.voucher.exception.IllegalVoucherException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static org.apache.logging.log4j.Level.DEBUG;
import static org.apache.logging.log4j.Level.ERROR;


public class TravelVoucherDOMBuilder {
        private static final Logger logger = LogManager.getLogger(TravelVoucherDOMBuilder.class);
        private Set<TravelVoucher> travelVouchers;
        private DocumentBuilder docBuilder;

        public TravelVoucherDOMBuilder() {
            travelVouchers = new HashSet<TravelVoucher>();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                docBuilder = factory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                logger.log(ERROR, "Parser configuration exception: " + e);
            }
        }

        public Set<TravelVoucher> getTravelVouchers() {
            return travelVouchers;
        }

        public void buildTravelVouchersSet(String fileName) throws IllegalVoucherException {
            try {
                Document doc = docBuilder.parse(fileName);
                Element root = doc.getDocumentElement();
                buildSetByTagName("roadTravelVoucher", root);
                buildSetByTagName("waterTravelVoucher", root);
                buildSetByTagName("airTravelVoucher", root);
                buildSetByTagName("railTravelVoucher", root);

            } catch (IOException e) {
                logger.log(ERROR, "File error or I/O error: " + e);
            } catch (SAXException e) {
                logger.log(ERROR, "Parsing failure: " + e);
            } catch (IllegalArgumentException e) {
                logger.log(ERROR, "uri is null" + e);
            }
        }

        protected TravelVoucher buildTravelVoucher(Element travelVoucherElement) throws IllegalVoucherException {
            TravelVoucher travelVoucher;
            switch (travelVoucherElement.getTagName()) {
                case "roadTravelVoucher":
                    travelVoucher = new RoadTravelVoucher();
                    ((RoadTravelVoucher) travelVoucher).setBus(Boolean.valueOf(
                            getElementTextContent(travelVoucherElement, "bus")));
                    break;
                case "waterTravelVoucher":
                    travelVoucher = new WaterTravelVoucher();
                    ((WaterTravelVoucher) travelVoucher).setCruise(Boolean.valueOf(
                            getElementTextContent(travelVoucherElement, "cruise")));
                    break;
                case "airTravelVoucher":
                    travelVoucher = new AirTravelVoucher();
                    ((AirTravelVoucher) travelVoucher).setCommonAirliner(Boolean.valueOf(
                            getElementTextContent(travelVoucherElement, "commonAirliner")));
                    break;
                case "railTravelVoucher":
                    travelVoucher = new RailTravelVoucher();
                    ((RailTravelVoucher) travelVoucher).setBusinessClass(Boolean.valueOf(
                            getElementTextContent(travelVoucherElement, "businessClass")));
                    break;
                default:
                    throw new IllegalVoucherException();
            }

            travelVoucher.setHotelType(travelVoucherElement.getAttribute("hotelType"));
            if (travelVoucherElement.hasAttribute("type")) {
                travelVoucher.setType(TravelVoucher.Type.valueOf(
                        travelVoucherElement.getAttribute("type").toUpperCase()));
            }
            travelVoucher.setCost(Double.parseDouble(
                    getElementTextContent(travelVoucherElement, "cost")));
            travelVoucher.setDuration(Integer.parseInt(
                    getElementTextContent(travelVoucherElement,"duration")));
            travelVoucher.setCountry(getElementTextContent(travelVoucherElement, "country"));

            return travelVoucher;
        }

        private void buildSetByTagName(String tagName, Element root) throws IllegalVoucherException {
            logger.log(DEBUG, (root.getElementsByTagName(tagName)).getLength());
            NodeList travelVouchersList = root.getElementsByTagName(tagName);
            for (int i = 0; i < travelVouchersList.getLength(); i++) {
                Element travelVoucherElement = (Element) travelVouchersList.item(i);
                TravelVoucher travelVoucher = buildTravelVoucher(travelVoucherElement);
                travelVouchers.add(travelVoucher);
            }
        }

        private static String getElementTextContent(Element element, String elementName) {
            NodeList nList = element.getElementsByTagName(elementName);
            Node node = nList.item(0);
            return node.getTextContent();
        }
    }
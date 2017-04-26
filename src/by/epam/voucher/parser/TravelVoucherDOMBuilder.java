package by.epam.voucher.parser;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import by.epam.voucher.entity.TravelVoucher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


    public class TravelVoucherDOMBuilder {
        static final Logger logger = LogManager.getLogger(TravelVoucherDOMBuilder.class);
        private Set<TravelVoucher> travelVouchers;
        private DocumentBuilder docBuilder;

        public TravelVoucherDOMBuilder() {
            travelVouchers = new HashSet<TravelVoucher>();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            try {
                docBuilder = factory.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                logger.error("Parser configuration exception: " + e);
            }
        }

        public Set<TravelVoucher> getTravelVouchers() {
            return travelVouchers;
        }

        public void buildSetPeriodicals(String fileName) {
            try {
                // parsing XML-document and creation of tree model
                Document doc = docBuilder.parse(fileName);
                Element root = doc.getDocumentElement();
                buildSetByTagName("newspaper", root);
                buildSetByTagName("magazine", root);

            } catch (IOException e) {
                logger.error("File error or I/O error: " + e);
            } catch (SAXException e) {
                logger.error("Parsing failure: " + e);
            } catch (IllegalArgumentException e) {
                logger.error("uri is null" + e);
            }
        }

        protected TravelVoucher buildPeriodical(Element periodicalElement) {
//                            throws PeriodicalElementNotPresentException {
            TravelVoucher periodical = null;
            switch (periodicalElement.getTagName()) {
                case "newspaper":
                    periodical = new Newspaper();
                    ((Newspaper) periodical).setColored(Boolean.valueOf(
                            getElementTextContent(periodicalElement, "colored")));
                    break;
                case "magazine":
                    periodical = new Magazine();
                    ((Magazine) periodical).setGlossy(Boolean.valueOf(
                            getElementTextContent(periodicalElement, "glossy")));
                    break;
                default:
                    // gotta think about this, probably not good
                    return TravelVoucher.getEmptyPeriodical();
//                throw new PeriodicalElementNotPresentException();
            }

            periodical.setIssn(periodicalElement.getAttribute("issn")); // проверка на null
            if (periodicalElement.hasAttribute("period")) {
                periodical.setPeriod(Periodical.Period.valueOf(
                        periodicalElement.getAttribute("period").toUpperCase()));
            }
            periodical.setTitle(getElementTextContent(periodicalElement, "title"));
            periodical.setVolume(Integer.parseInt(
                    getElementTextContent(periodicalElement,"volume")));

            return periodical;
        }

        private void buildSetByTagName(String tagName, Element root) {
            logger.debug((root.getElementsByTagName(tagName)).getLength());
            NodeList periodicalsList = root.getElementsByTagName(tagName);
            for (int i = 0; i < periodicalsList.getLength(); i++) {
                Element periodicalElement = (Element) periodicalsList.item(i);
                TravelVoucher periodical = buildPeriodical(periodicalElement);
                travelVouchers.add(periodical);
            }
        }

        private static String getElementTextContent(Element element, String elementName) {
            NodeList nList = element.getElementsByTagName(elementName);
            Node node = nList.item(0);
            return node.getTextContent();
        }
    }
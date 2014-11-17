
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;

import org.xml.sax.SAXException;

import xml_new.Qproperties;

public class XMLValidator {

  File schemaFile;

  public XMLValidator() throws MalformedURLException {
    schemaFile = new File("schemas/sample_prop_schema_new.xsd");
  }

  public boolean validate(File xmlFile) throws IOException, SAXException {
    Source xml = new StreamSource(xmlFile);
    SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
    Schema schema = schemaFactory.newSchema(schemaFile);
    Validator validator = schema.newValidator();
    try {
      validator.validate(xml);
      System.out.println(xml.getSystemId() + " is valid");
      return true;
    } catch (SAXException e) {
      System.out.println(xml.getSystemId() + " is NOT valid");
      System.out.println("Reason: " + e.getLocalizedMessage());
      return false;
    }
  }

  public static void main(String[] args) throws IOException, SAXException, JAXBException {
    XMLValidator x = new XMLValidator();
    File file = new File("examples/sample_prop_example_new.xml");
    // InputStream stream = new ByteArrayInputStream()
    x.validate(file);
    JAXBContext jc = JAXBContext.newInstance("xml_new");

    Unmarshaller unmarshaller = jc.createUnmarshaller();
    JAXBElement<Qproperties> feed =
        unmarshaller.unmarshal(new StreamSource(file), Qproperties.class);
    Parser p = new Parser();
    System.out.println(p.getMap(feed));
    Marshaller marshaller = jc.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.marshal(feed, System.out);
  }
}

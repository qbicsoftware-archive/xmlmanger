
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
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

import xml.Qproperties;

public class XMLValidator {

  File schemaFile;

  public XMLValidator() throws MalformedURLException {
    schemaFile = new File("schemas/sample_prop_schema.xsd");
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
    File file = new File("examples/sample_prop_example.xml");
    x.validate(file);
    JAXBContext jc = JAXBContext.newInstance("xml");

    Unmarshaller unmarshaller = jc.createUnmarshaller();
    JAXBElement<Qproperties> root =
        unmarshaller.unmarshal(new StreamSource(file), Qproperties.class);
    Parser p = new Parser();
    System.out.println(p.getMap(root));
    Marshaller marshaller = jc.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.marshal(root, System.out);
  }
}

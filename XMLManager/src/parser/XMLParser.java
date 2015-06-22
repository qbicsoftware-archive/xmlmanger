package parser;

import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import properties.Factor;
import properties.Qcategorical;
import properties.Qcontinous;
import properties.Qfactors;
import properties.Qproperties;
import properties.Qproperty;



public class XMLParser {

  public Map<String, String> getMap(JAXBElement<properties.Qproperties> root) {
    Map<String, String> map = new HashMap<String, String>();
    if (root.getValue().getQfactors() != null) {
      Qfactors factors = root.getValue().getQfactors();
      for (Qcategorical cat : factors.getQcategorical()) {
        map.put(cat.getLabel(), cat.getValue());
      }
      for (Qcontinous cont : factors.getQcontinous()) {
        map.put(cont.getLabel(), cont.getValue() + " " + cont.getUnit());
      }
    }
    if (root.getValue().getQproperty() != null) {
      List<Qproperty> props = root.getValue().getQproperty();
      for (Qproperty prop : props) {
        map.put(prop.getLabel(), prop.getValue() + " " + prop.getUnit());
      }
    }
    return map;
  }

  public List<Factor> getFactors(JAXBElement<properties.Qproperties> root) {
    List<Factor> res = new ArrayList<Factor>();
    if (root.getValue().getQfactors() != null) {
      Qfactors factors = root.getValue().getQfactors();
      for (Qcategorical cat : factors.getQcategorical()) {
        res.add(new Factor(cat.getLabel(), cat.getValue()));
      }
      for (Qcontinous cont : factors.getQcontinous()) {
        res.add(new Factor(cont.getLabel(), cont.getValue().toString(), cont.getUnit()));
      }
    }
    if (root.getValue().getQproperty() != null) {
      List<Qproperty> props = root.getValue().getQproperty();
      for (Qproperty prop : props) {
        res.add(new Factor(prop.getLabel(), prop.getValue().toString(), prop.getUnit()));
      }
    }
    return res;
  }

  public String addFactorsToXMLString(String xml, List<Factor> factors) throws JAXBException {
    return toString(addFactors(parseXMLString(xml), factors));
  }

  public JAXBElement<properties.Qproperties> addFactors(JAXBElement<properties.Qproperties> root,
      List<Factor> factors) {
    if (root.getValue().getQfactors() == null)
      root.getValue().setQfactors(new Qfactors());
    Qfactors factorRoot = root.getValue().getQfactors();
    List<Qcategorical> cats = factorRoot.getQcategorical();
    List<Qcontinous> cont = factorRoot.getQcontinous();
    for (Factor f : factors) {
      String label = f.getLabel();
      if (f.hasUnit()) {
        Qcontinous q = new Qcontinous();
        String str = f.getValue().replaceAll(",", "");
        BigDecimal value = new BigDecimal(str);
        q.setLabel(label);
        q.setValue(value);
        q.setUnit(f.getUnit());
        cont.add(q);
      } else {
        Qcategorical q = new Qcategorical();
        q.setLabel(label);
        q.setValue(f.getValue());
        cats.add(q);
      }
    }
    return root;
  }

  public List<Factor> getFactorsFromXML(String xml) throws JAXBException {
    List<Factor> res = new ArrayList<Factor>();
    Qproperties props = parseXMLString(xml).getValue();
    if (props != null) {
      Qfactors fact = props.getQfactors();
      if (fact != null) {
        for (Qcategorical cat : fact.getQcategorical())
          res.add(new Factor(cat.getLabel(), cat.getValue(), ""));
        for (Qcontinous cont : fact.getQcontinous())
          res.add(new Factor(cont.getLabel(), cont.getValue().toString(), cont.getUnit()));
      }
      List<Qproperty> pLis = props.getQproperty();
      if (pLis != null) {
        for (Qproperty prop : pLis) {
          res.add(new Factor(prop.getLabel(), prop.getValue(), prop.getUnit()));
        }
      }
    }
    return res;
  }

  public JAXBElement<Qproperties> createXMLFromFactors(List<Factor> factors) throws JAXBException {
    return addFactors(getEmptyXML(), factors);
  }

  public JAXBElement<properties.Qproperties> parseXMLString(String xml) throws JAXBException {
    if (xml == null)
      return getEmptyXML();
    JAXBContext jc = JAXBContext.newInstance("properties");
    Unmarshaller unmarshaller = jc.createUnmarshaller();
    JAXBElement<Qproperties> root =
        unmarshaller.unmarshal(new StreamSource(new StringReader(xml)), Qproperties.class);
    return root;
  }

  public JAXBElement<properties.Qproperties> getEmptyXML() throws JAXBException {
    JAXBContext jc = JAXBContext.newInstance("properties");
    Unmarshaller unmarshaller = jc.createUnmarshaller();
    JAXBElement<Qproperties> root =
        unmarshaller.unmarshal(new StreamSource(new StringReader(
            "<?properties version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                + "<qproperties>" + "</qproperties>")), Qproperties.class);
    return root;
  }

  public String toString(JAXBElement<properties.Qproperties> root) throws JAXBException {
    JAXBContext jc = JAXBContext.newInstance("properties");
    Marshaller marshaller = jc.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    StringWriter sw = new StringWriter();
    marshaller.marshal(root, sw);
    return sw.toString();
  }

}

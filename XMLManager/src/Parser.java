import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import xml.Factor;
import xml.Qcategorical;
import xml.Qcontinous;
import xml.Qfactors;
import xml.Qproperties;
import xml.Qproperty;


public class Parser {

  public Map<String, String> getMap(JAXBElement<xml.Qproperties> root) {
    Map<String, String> map = new HashMap<String, String>();
    Qfactors factors = root.getValue().getQfactors();
    List<Qproperty> props = root.getValue().getQproperty();
    for (Qcategorical cat : factors.getQcategorical()) {
      map.put(cat.getLabel(), cat.getValue());
    }
    for (Qcontinous cont : factors.getQcontinous()) {
      map.put(cont.getLabel(), cont.getValue() + " " + cont.getUnit());
    }
    for (Qproperty prop : props) {
      map.put(prop.getLabel(), prop.getValue() + " " + prop.getUnit());
    }
    return map;
  }

  public JAXBElement<xml.Qproperties> addFactors(JAXBElement<xml.Qproperties> root,
      List<Factor> factors) {
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
  
  public JAXBElement<Qproperties> createXMLFromFactors(List<Factor> factors) throws JAXBException {
    return addFactors(getEmptyXML(), factors);
  }

  public JAXBElement<xml.Qproperties> getEmptyXML() throws JAXBException {
    JAXBContext jc = JAXBContext.newInstance("xml");
    Unmarshaller unmarshaller = jc.createUnmarshaller();
    JAXBElement<Qproperties> root =
        unmarshaller.unmarshal(new StreamSource(new StringReader(
            "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" + "<qproperties>"
                + "<qfactors>" + "    </qfactors>" + "</qproperties>")), Qproperties.class);
    return root;
  }

  public static void main(String[] args) throws JAXBException {
    Parser p = new Parser();
    p.toOneLineString(p.createXMLFromFactors(new ArrayList<Factor>(Arrays.asList(new Factor("label","20.235","y"),new Factor("label","cancer",""),new Factor("label","120.235","mg")))));
  }

  public String toOneLineString(JAXBElement<xml.Qproperties> root) throws JAXBException {
    JAXBContext jc = JAXBContext.newInstance("xml");
    Marshaller marshaller = jc.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
    marshaller.marshal(root, System.out);
    return null;
  }

}

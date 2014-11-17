import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;

import xml_new.Qcategorical;
import xml_new.Qcontinous;
import xml_new.Qfactors;
import xml_new.Qproperties;
import xml_new.Qproperty;


public class Parser {

  public Map<String, String> getMap(JAXBElement<Qproperties> feed) {
    Map<String, String> map = new HashMap<String, String>();
    List<Qfactors> factors = feed.getValue().getQfactors();
    List<Qproperty> props = feed.getValue().getQproperty();
    for (Qfactors factor : factors) {
      if(factor.getQcategorical()!=null) {
        Qcategorical cat = factor.getQcategorical();
        map.put(cat.getLabel(),cat.getValue());
      } else {
        Qcontinous cont = factor.getQcontinous();
        map.put(cont.getLabel(), cont.getValue()+" "+cont.getUnit());

      }
    }
    for(Qproperty prop : props) {
      map.put(prop.getLabel(), prop.getValue()+" "+prop.getUnit());
    }
    return map;
  }

}

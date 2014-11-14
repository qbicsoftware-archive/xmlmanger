import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;

import xml.Qcategorical;
import xml.Qcontinous;
import xml.Qfactors;
import xml.Qproperties;
import xml.Qproperty;


public class Parser {

  public Map<String, String> getMap(JAXBElement<Qproperties> properties) {
    Map<String, String> map = new HashMap<String, String>();
    Qfactors factors = properties.getValue().getQfactors();
    List<Qproperty> props = properties.getValue().getQproperty();
    for (Qcategorical cat : factors.getQcategorical()) {
      map.put(cat.getLabel(),cat.getValue());
    }
    for (Qcontinous cont : factors.getQcontinous()) {
      map.put(cont.getLabel(), cont.getValue()+" "+cont.getUnit());
    }
    for(Qproperty prop : props) {
      map.put(prop.getLabel(), prop.getValue()+" "+prop.getUnit());
    }
    return map;
  }

}

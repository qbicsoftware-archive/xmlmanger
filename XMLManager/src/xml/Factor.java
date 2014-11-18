package xml;

public class Factor {
  
  private String label;
  private String value;
  private String unit;
  private boolean hasUnit;

  public Factor(String label, String val, String unit) {
    this.label = label;
    this.value = val;
    this.unit = unit;
    if(unit.equals(""))
      hasUnit = false;
    else
      hasUnit = true;
  }
  
  public String toString() {
    return label+": "+value+unit;
  }
  
  public String getLabel() {
    return label;
  }

  public String getValue() {
    return value;
  }

  public String getUnit() {
    return unit;
  }
  
  public boolean hasUnit() {
    return hasUnit;
  }

}

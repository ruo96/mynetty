
package com.wrh.resttemplate;
import java.util.List;

/**
 * @author Ever
 */
public class Address_components {

    private String long_name;
    private String short_name;
    private List<String> types;
    public void setLong_name(String long_name) {
         this.long_name = long_name;
     }
     public String getLong_name() {
         return long_name;
     }

    public void setShort_name(String short_name) {
         this.short_name = short_name;
     }
     public String getShort_name() {
         return short_name;
     }

    public void setTypes(List<String> types) {
         this.types = types;
     }
     public List<String> getTypes() {
         return types;
     }

}
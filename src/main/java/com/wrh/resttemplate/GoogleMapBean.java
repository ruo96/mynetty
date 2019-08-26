/**
  * Copyright 2018 bejson.com 
  */
package com.wrh.resttemplate;
import java.util.List;

/**
 * @author Ever
 */
public class GoogleMapBean {

    private Plus_code plus_code;
    private List<Results> results;
    private String status;
    public void setPlus_code(Plus_code plus_code) {
         this.plus_code = plus_code;
     }
     public Plus_code getPlus_code() {
         return plus_code;
     }

    public void setResults(List<Results> results) {
         this.results = results;
     }
     public List<Results> getResults() {
         return results;
     }

    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

}
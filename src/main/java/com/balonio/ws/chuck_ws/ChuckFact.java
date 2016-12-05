
package com.balonio.ws.chuck_ws;

import java.util.ArrayList;
import java.util.List;


public class ChuckFact {

    private List<String> category = new ArrayList<String>();
    private String icon_url;
    private String id;
    private String url;
    private String value;

    /**
     * 
     * @return
     *     The category
     */
    public List<String> getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *     The category
     */
    public void setCategory(List<String> category) {
        this.category = category;
    }

    /**
     * 
     * @return
     *     The icon_url
     */
    public String geticon_url() {
        return icon_url;
    }

    /**
     * 
     * @param icon_url
     *     The icon_url
     */
    public void seticon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The value
     */
    public String getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    public void setValue(String value) {
        this.value = value;
    }

}

package client.webServiceDep;

public class RequestClass {
    private String description = null;
    private String label = null;
    private String value = null;
    
    public RequestClass() {
        super();
    }

    /** 
     * @param label
     * @param value
     */
    public RequestClass(String label, String value){
        this(label, value, null);       
    }

    /**
     * @param label
     * @param value
     * @param description
     */
    public RequestClass(String label, String value, String description){
        super();
        setLabel(label);
        setValue(value);  
        setDescription(description);
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}

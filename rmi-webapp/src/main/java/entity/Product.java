/**
 * Product.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package entity;

public class Product  implements java.io.Serializable {
    private boolean availability;

    private java.lang.String category;

    private java.lang.String description;

    private int id;

    private java.lang.String image;

    private java.lang.String inventoryName;

    private java.lang.String inventoryStatus;

    private java.lang.String name;

    private int price;

    private int rating;

    private java.lang.String state;

    public Product() {
    }

    public Product(
           boolean availability,
           java.lang.String category,
           java.lang.String description,
           int id,
           java.lang.String image,
           java.lang.String inventoryName,
           java.lang.String inventoryStatus,
           java.lang.String name,
           int price,
           int rating,
           java.lang.String state) {
           this.availability = availability;
           this.category = category;
           this.description = description;
           this.id = id;
           this.image = image;
           this.inventoryName = inventoryName;
           this.inventoryStatus = inventoryStatus;
           this.name = name;
           this.price = price;
           this.rating = rating;
           this.state = state;
    }


    /**
     * Gets the availability value for this Product.
     * 
     * @return availability
     */
    public boolean isAvailability() {
        return availability;
    }


    /**
     * Sets the availability value for this Product.
     * 
     * @param availability
     */
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }


    /**
     * Gets the category value for this Product.
     * 
     * @return category
     */
    public java.lang.String getCategory() {
        return category;
    }


    /**
     * Sets the category value for this Product.
     * 
     * @param category
     */
    public void setCategory(java.lang.String category) {
        this.category = category;
    }


    /**
     * Gets the description value for this Product.
     * 
     * @return description
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this Product.
     * 
     * @param description
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the id value for this Product.
     * 
     * @return id
     */
    public int getId() {
        return id;
    }


    /**
     * Sets the id value for this Product.
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }


    /**
     * Gets the image value for this Product.
     * 
     * @return image
     */
    public java.lang.String getImage() {
        return image;
    }


    /**
     * Sets the image value for this Product.
     * 
     * @param image
     */
    public void setImage(java.lang.String image) {
        this.image = image;
    }


    /**
     * Gets the inventoryName value for this Product.
     * 
     * @return inventoryName
     */
    public java.lang.String getInventoryName() {
        return inventoryName;
    }


    /**
     * Sets the inventoryName value for this Product.
     * 
     * @param inventoryName
     */
    public void setInventoryName(java.lang.String inventoryName) {
        this.inventoryName = inventoryName;
    }


    /**
     * Gets the inventoryStatus value for this Product.
     * 
     * @return inventoryStatus
     */
    public java.lang.String getInventoryStatus() {
        return inventoryStatus;
    }


    /**
     * Sets the inventoryStatus value for this Product.
     * 
     * @param inventoryStatus
     */
    public void setInventoryStatus(java.lang.String inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }


    /**
     * Gets the name value for this Product.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this Product.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the price value for this Product.
     * 
     * @return price
     */
    public int getPrice() {
        return price;
    }


    /**
     * Sets the price value for this Product.
     * 
     * @param price
     */
    public void setPrice(int price) {
        this.price = price;
    }


    /**
     * Gets the rating value for this Product.
     * 
     * @return rating
     */
    public int getRating() {
        return rating;
    }


    /**
     * Sets the rating value for this Product.
     * 
     * @param rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }


    /**
     * Gets the state value for this Product.
     * 
     * @return state
     */
    public java.lang.String getState() {
        return state;
    }


    /**
     * Sets the state value for this Product.
     * 
     * @param state
     */
    public void setState(java.lang.String state) {
        this.state = state;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Product)) return false;
        Product other = (Product) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.availability == other.isAvailability() &&
            ((this.category==null && other.getCategory()==null) || 
             (this.category!=null &&
              this.category.equals(other.getCategory()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            this.id == other.getId() &&
            ((this.image==null && other.getImage()==null) || 
             (this.image!=null &&
              this.image.equals(other.getImage()))) &&
            ((this.inventoryName==null && other.getInventoryName()==null) || 
             (this.inventoryName!=null &&
              this.inventoryName.equals(other.getInventoryName()))) &&
            ((this.inventoryStatus==null && other.getInventoryStatus()==null) || 
             (this.inventoryStatus!=null &&
              this.inventoryStatus.equals(other.getInventoryStatus()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            this.price == other.getPrice() &&
            this.rating == other.getRating() &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += (isAvailability() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getCategory() != null) {
            _hashCode += getCategory().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        _hashCode += getId();
        if (getImage() != null) {
            _hashCode += getImage().hashCode();
        }
        if (getInventoryName() != null) {
            _hashCode += getInventoryName().hashCode();
        }
        if (getInventoryStatus() != null) {
            _hashCode += getInventoryStatus().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        _hashCode += getPrice();
        _hashCode += getRating();
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Product.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://entity", "Product"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("availability");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "availability"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("category");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "category"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("image");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "image"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inventoryName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "inventoryName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inventoryStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "inventoryStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("price");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "price"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rating");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "rating"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "state"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}

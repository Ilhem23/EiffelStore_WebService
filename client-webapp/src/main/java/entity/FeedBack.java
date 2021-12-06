/**
 * FeedBack.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package entity;

public class FeedBack  implements java.io.Serializable {
    private java.lang.String comment;

    private java.util.Calendar dateComment;

    private java.lang.String employeeName;

    private int productId;

    private int rating;

    public FeedBack() {
    }

    public FeedBack(
           java.lang.String comment,
           java.util.Calendar dateComment,
           java.lang.String employeeName,
           int productId,
           int rating) {
           this.comment = comment;
           this.dateComment = dateComment;
           this.employeeName = employeeName;
           this.productId = productId;
           this.rating = rating;
    }


    /**
     * Gets the comment value for this FeedBack.
     * 
     * @return comment
     */
    public java.lang.String getComment() {
        return comment;
    }


    /**
     * Sets the comment value for this FeedBack.
     * 
     * @param comment
     */
    public void setComment(java.lang.String comment) {
        this.comment = comment;
    }


    /**
     * Gets the dateComment value for this FeedBack.
     * 
     * @return dateComment
     */
    public java.util.Calendar getDateComment() {
        return dateComment;
    }


    /**
     * Sets the dateComment value for this FeedBack.
     * 
     * @param dateComment
     */
    public void setDateComment(java.util.Calendar dateComment) {
        this.dateComment = dateComment;
    }


    /**
     * Gets the employeeName value for this FeedBack.
     * 
     * @return employeeName
     */
    public java.lang.String getEmployeeName() {
        return employeeName;
    }


    /**
     * Sets the employeeName value for this FeedBack.
     * 
     * @param employeeName
     */
    public void setEmployeeName(java.lang.String employeeName) {
        this.employeeName = employeeName;
    }


    /**
     * Gets the productId value for this FeedBack.
     * 
     * @return productId
     */
    public int getProductId() {
        return productId;
    }


    /**
     * Sets the productId value for this FeedBack.
     * 
     * @param productId
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }


    /**
     * Gets the rating value for this FeedBack.
     * 
     * @return rating
     */
    public int getRating() {
        return rating;
    }


    /**
     * Sets the rating value for this FeedBack.
     * 
     * @param rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FeedBack)) return false;
        FeedBack other = (FeedBack) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.comment==null && other.getComment()==null) || 
             (this.comment!=null &&
              this.comment.equals(other.getComment()))) &&
            ((this.dateComment==null && other.getDateComment()==null) || 
             (this.dateComment!=null &&
              this.dateComment.equals(other.getDateComment()))) &&
            ((this.employeeName==null && other.getEmployeeName()==null) || 
             (this.employeeName!=null &&
              this.employeeName.equals(other.getEmployeeName()))) &&
            this.productId == other.getProductId() &&
            this.rating == other.getRating();
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
        if (getComment() != null) {
            _hashCode += getComment().hashCode();
        }
        if (getDateComment() != null) {
            _hashCode += getDateComment().hashCode();
        }
        if (getEmployeeName() != null) {
            _hashCode += getEmployeeName().hashCode();
        }
        _hashCode += getProductId();
        _hashCode += getRating();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FeedBack.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://entity", "FeedBack"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "comment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateComment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "dateComment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("employeeName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "employeeName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("productId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "productId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rating");
        elemField.setXmlName(new javax.xml.namespace.QName("http://entity", "rating"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
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

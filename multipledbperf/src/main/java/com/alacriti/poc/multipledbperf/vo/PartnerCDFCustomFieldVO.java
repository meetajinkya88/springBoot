/**
 *
 */
package com.alacriti.poc.multipledbperf.vo;

import java.io.Serializable;

/**
 * @author sudhak
 *
 */
public class PartnerCDFCustomFieldVO implements Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private long fieldMetaId;
    private int customFieldType;
    private long partnerId;

    private String cdfFieldId;

    private boolean calcFlag;
    private boolean passThrough;

    private int dataType;
    private long fieldKeyId;
    private long hintFieldKeyId;
    private int fieldMinLength;
    private int fieldMaxLength;
    private boolean isMandatory;
    private int fieldUIType;

    private boolean secureField;
    private boolean displayFieldWhenNull;

    private int errorCode;

    public boolean isDisplayFieldWhenNull()
    {
        return displayFieldWhenNull;
    }

    public void setDisplayFieldWhenNull(boolean displayFieldWhenNull)
    {
        this.displayFieldWhenNull = displayFieldWhenNull;
    }

    public int getCustomFieldType()
    {
        return customFieldType;
    }

    public void setCustomFieldType(int customFieldType)
    {
        this.customFieldType = customFieldType;
    }

    public long getPartnerId()
    {
        return partnerId;
    }

    public void setPartnerId(long partnerId)
    {
        this.partnerId = partnerId;
    }

    public String getCdfFieldId()
    {
        return cdfFieldId;
    }

    public void setCdfFieldId(String cdfFieldId)
    {
        this.cdfFieldId = cdfFieldId;
    }

    public boolean isCalcFlag()
    {
        return calcFlag;
    }

    public void setCalcFlag(boolean calcFlag)
    {
        this.calcFlag = calcFlag;
    }

    public boolean isPassThrough()
    {
        return passThrough;
    }

    public void setPassThrough(boolean passThrough)
    {
        this.passThrough = passThrough;
    }

    public int getDataType()
    {
        return dataType;
    }

    public void setDataType(int dataType)
    {
        this.dataType = dataType;
    }

    public long getFieldKeyId()
    {
        return fieldKeyId;
    }

    public void setFieldKeyId(long fieldKeyId)
    {
        this.fieldKeyId = fieldKeyId;
    }

    public long getHintFieldKeyId()
    {
        return hintFieldKeyId;
    }

    public void setHintFieldKeyId(long hintFieldKeyId)
    {
        this.hintFieldKeyId = hintFieldKeyId;
    }

    public int getFieldMaxLength()
    {
        return fieldMaxLength;
    }

    public void setFieldMaxLength(int fieldMaxLength)
    {
        this.fieldMaxLength = fieldMaxLength;
    }

    public boolean isMandatory()
    {
        return isMandatory;
    }

    public void setMandatory(boolean isMandatory)
    {
        this.isMandatory = isMandatory;
    }

    public int getFieldUIType()
    {
        return fieldUIType;
    }

    public void setFieldUIType(int fieldUIType)
    {
        this.fieldUIType = fieldUIType;
    }

    public long getFieldMetaId()
    {
        return fieldMetaId;
    }

    public void setFieldMetaId(long fieldMetaId)
    {
        this.fieldMetaId = fieldMetaId;
    }

    public boolean isSecureField()
    {
        return secureField;
    }

    public void setSecureField(boolean secureField)
    {
        this.secureField = secureField;
    }

    public int getFieldMinLength()
    {
        return fieldMinLength;
    }

    public void setFieldMinLength(int fieldMinLength)
    {
        this.fieldMinLength = fieldMinLength;
    }

    public int getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(int errorCode)
    {
        this.errorCode = errorCode;
    }

    @Override
    public String toString()
    {
        return fieldMetaId + "&" + customFieldType + "&" + partnerId + "&" + cdfFieldId + "&" + calcFlag + "&"
                + passThrough + "&" + dataType + "&" + fieldKeyId + "&" + hintFieldKeyId + "&" + fieldMinLength + "&"
                + fieldMaxLength + "&" + isMandatory + "&" + fieldUIType + "&" + secureField + "&"
                + displayFieldWhenNull + "&" + errorCode;
    }

}

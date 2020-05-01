package com.alacriti.poc.multipledbperf.vo;

import java.io.Serializable;

public class CDFFieldDataTypeVO implements Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 5014595953946763962L;
    protected int cdfFieldDataType;
    protected String cdfFieldDataTypeDesc;
    protected String pattern;
    protected String sourceFormat;
    protected String uiFormat;

    public int getCdfFieldDataType()
    {
        return cdfFieldDataType;
    }

    public void setCdfFieldDataType(int cdfFieldDataType)
    {
        this.cdfFieldDataType = cdfFieldDataType;
    }

    public String getCdfFieldDataTypeDesc()
    {
        return cdfFieldDataTypeDesc;
    }

    public void setCdfFieldDataTypeDesc(String cdfFieldDataTypeDesc)
    {
        this.cdfFieldDataTypeDesc = cdfFieldDataTypeDesc;
    }

    public String getPattern()
    {
        return pattern;
    }

    public void setPattern(String pattern)
    {
        this.pattern = pattern;
    }

    public String getSourceFormat()
    {
        return sourceFormat;
    }

    public void setSourceFormat(String sourceFormat)
    {
        this.sourceFormat = sourceFormat;
    }

    public String getUiFormat()
    {
        return uiFormat;
    }

    public void setUiFormat(String uiFormat)
    {
        this.uiFormat = uiFormat;
    }

    @Override
    public String toString()
    {
        return cdfFieldDataType + "&" + cdfFieldDataTypeDesc + "&" + pattern + "&" + sourceFormat + "&" + uiFormat;
    }

    /*
     * @Override public String toString() { return "CDFFieldDataTypeVO [cdfFieldDataType=" +
     * cdfFieldDataType + ", cdfFieldDataTypeDesc=" + cdfFieldDataTypeDesc + ", pattern=" + pattern
     * + ", sourceFormat=" + sourceFormat + ", uiFormat=" + uiFormat + ", getCdfFieldDataType()=" +
     * getCdfFieldDataType() + ", getCdfFieldDataTypeDesc()=" + getCdfFieldDataTypeDesc() +
     * ", getPattern()=" + getPattern() + ", getSourceFormat()=" + getSourceFormat() +
     * ", getUiFormat()=" + getUiFormat() + "]"; }
     */

}

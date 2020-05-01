package com.alacriti.poc.multipledbperf.util;

import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import javax.crypto.BadPaddingException;



public class ExceptionUtil 
{
   
    public static String getStackTrace(Throwable throwable)
    {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        throwable.printStackTrace(printWriter);
        return result.toString();
    }

    public static void closeAndIgnoreBadPaddingException(Reader reader) throws Exception
    {
        try
        {
            if (reader != null)
                reader.close();
        }

        catch (Exception e)
        {
            if (e instanceof BadPaddingException)
            {

            }
            else
                throw e;
        }
    }
}

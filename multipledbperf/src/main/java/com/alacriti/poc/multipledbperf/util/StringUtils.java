package com.alacriti.poc.multipledbperf.util;

import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.CharacterIterator;
import java.text.Normalizer;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.alacriti.poc.multipledbperf.constants.Constants;


public class StringUtils  
 {
    public static String getLastNChars(String strA, int nChars)
    {
        String retStr = "";
        int strLength = strA.length();
        if (strLength > nChars)
        {
            for (int i = 0; i < strLength - nChars; i++)
            {
                retStr += "X";
            }
            retStr += strA.substring(strLength - nChars);
        }
        else
            retStr = strA;
        return retStr;
    }

    public static String selectSubString(int beginIndex, int endIndex, String str)
    {
        String finalString = "";

        String str1 = noNullTrim(str);
        finalString = str1.substring(beginIndex, endIndex);

        return finalString;
    }

    public static String selectVariableString(int firstOrLast, int size, String str)
    {
        String finalString = "";

        String str1 = noNullTrim(str);

        if (isEmpty(str1) || str1.length() < size)
        {
            return str1;
        }
        // first
        if (firstOrLast == 1)
        {
            finalString = selectSubString(0, size, str1);
        }
        // last
        else if (firstOrLast == 2)
        {
            finalString = selectSubString(str1.length() - size, str1.length(), str1);
        }

        return finalString;
    }

    public static String getXMLString(String aText)
    {

        if (aText == null)
            return "";
        else if ("null".equalsIgnoreCase(aText.trim()))
            return "";

        if (!isEmpty(aText))
        {
            StringBuilder result = new StringBuilder();
            StringCharacterIterator iterator = new StringCharacterIterator(aText);
            char character = iterator.current();
            while (character != CharacterIterator.DONE)
            {
                if (character == '<')
                {
                    result.append("&lt;");
                }
                else if (character == '>')
                {
                    result.append("&gt;");
                }
                else if (character == '\"')
                {
                    result.append("&quot;");
                }
                else if (character == '\'')
                {
                    result.append("&#039;");
                }
                else if (character == '&')
                {
                    result.append("&amp;");
                }
                else
                {
                    // the char is not a special one
                    // add it to the result as is
                    result.append(character);
                }
                character = iterator.next();
            }
            return result.toString().trim();
        }
        else
            return "";
    }

    public static String selectVariableIndexString(int beginIndex, int endIndex, String str, String appendPfx,
            String appendSfx)
    {
        String finalString = "";
        String str1 = noNullTrim(str);
        if (beginIndex > endIndex)
            return finalString;
        if (str1.length() <= beginIndex && beginIndex == 0)
            return (appendPfx + str1 + appendSfx);
        else if (str1.length() > beginIndex && str1.length() <= endIndex)
            return (appendPfx + selectSubString(beginIndex, str1.length(), str1) + appendSfx);
        else if (str1.length() > endIndex)
            return (appendPfx + selectSubString(beginIndex, endIndex, str1) + appendSfx);

        return finalString;
    }

    public static String repeatedString(String source, int number)
    {
        String result = source;
        for (int i = 0; i < number - 1; i++)
        {
            result += source;
        }
        return result;
    }

    public static String displayMemo(String str, int length)
    {
        if (isEmpty(str))
            return nullToNATrim(str);

        System.out.println(str.length());

        if (str.length() < length)
            return str;

        String modifiedMemo = new String();

        int startIndex = 0;
        int endIndex = length;

        try
        {
            while (endIndex < str.length())
            {
                modifiedMemo += str.substring(startIndex, endIndex) + "<br>";
                startIndex = endIndex;
                endIndex = endIndex + length;

                if (endIndex >= str.length())
                {
                    endIndex = str.length();
                    modifiedMemo += str.substring(startIndex, endIndex) + "<br>";
                }
            }
        }
        catch (Exception e)
        {
            return nullToNATrim(str);
        }
        return modifiedMemo;

    }

    public static String displayMemowithWordWrap(String str, int length)
    {
        if (isEmpty(str))
            return nullToNATrim(str);

        if (str.length() < length)
            return str;

        String modifiedMemo = new String();

        int startIndex = 0;
        int spaceOccurance = str.lastIndexOf(" ", length);
        int endIndex = length;
        if (spaceOccurance > 0)
            endIndex = spaceOccurance;
        try
        {
            while (endIndex < str.length())
            {
                modifiedMemo += str.substring(startIndex, endIndex) + "<br>";
                startIndex = endIndex;
                if (endIndex + length >= str.length())
                {
                    endIndex = str.length();
                    modifiedMemo += str.substring(startIndex, endIndex) + "<br>";
                }
                else if (str.lastIndexOf(" ", endIndex + length) > 0
                        && str.lastIndexOf(" ", endIndex + length) > startIndex)
                {
                    endIndex = str.lastIndexOf(" ", endIndex + length);
                }
                else
                {
                    endIndex = endIndex + length;
                }
            }
        }
        catch (Exception e)
        {
            return nullToNATrim(str);
        }
        return modifiedMemo;

    }

    public static String enclose(String valueStr, String wrapCharacter)
    {
        valueStr = noNullTrim(valueStr);
        if (!isEmpty(valueStr))
            return new StringBuffer(wrapCharacter).append(valueStr).append(wrapCharacter).toString();
        else
            return "\"\"";
    }

    public static String disClose(String valueStr)
    {
        valueStr = noNullTrim(valueStr);
        if (!isEmpty(valueStr))
        {
            int length = valueStr.length();
            return valueStr.substring(1, (length - 1));
        }
        else
            return "";
    }

    public static String[] split(String strToSplit, String delimiterStr, String encloseStr, String escapeChar)
    {
        String[] strSplitArr = null;

        if (isEmpty(strToSplit))
        {
            return new String[0];
        }

        if (isEmpty(delimiterStr))
        {
            strSplitArr = new String[1];
            strSplitArr[0] = unEnclose(strToSplit, encloseStr, delimiterStr);
            return strSplitArr;
        }
        strToSplit = unEnclose(strToSplit, encloseStr, delimiterStr);
        String[] strTokenToSplit = strToSplit.split(encloseStr + delimiterStr + encloseStr);
        int noOfCols = strTokenToSplit.length;
        strSplitArr = new String[noOfCols];
        int strArrIndex = 0;
        for (String str : strTokenToSplit)
        {
            strSplitArr[strArrIndex++] = str;
        }
        return strSplitArr;
    }

    public static String unEnclose(String strToSplit, String encloseStr, String delimiterStr)
    {
        if (isEmpty(strToSplit) || isEmpty(encloseStr))
        {
            return noNullTrim(strToSplit);
        }

        strToSplit = noNullTrim(strToSplit);

        if (strToSplit.startsWith(noNullTrim(encloseStr)))
        {
            strToSplit = strToSplit.replaceFirst(encloseStr, "");
        }

        if (strToSplit.endsWith(noNullTrim(encloseStr)))
        {
            strToSplit = strToSplit.substring(0, strToSplit.lastIndexOf(encloseStr));
        }
        if (strToSplit.contains(encloseStr + " " + delimiterStr)
                || strToSplit.contains(delimiterStr + " " + encloseStr))
        {
            strToSplit = strToSplit.replace(encloseStr + " " + delimiterStr, encloseStr + delimiterStr);
            strToSplit = strToSplit.replace(delimiterStr + " " + encloseStr, delimiterStr + encloseStr);
        }

        return strToSplit;
    }

    public static String removeEscapeChars(String str)
    {
        String escStr = null;
        if (str.contains("\""))
            escStr = str.replace("\"", "\"\"");
        else
            escStr = str;
        return escStr;
    }

    public static String getPartners(String currentStr)
    {
        String target = "-";
        String replacement = ",";

        return (currentStr.replace(target, replacement)).substring(1, currentStr.length() - 1);
    }

    public static String replaceChars(String inputString, String origChars, String replaceChars)
    {
        if (inputString.contains(origChars))
        {
            inputString = inputString.replace(origChars, replaceChars);
        }

        return inputString;
    }

    // Added by praveen for tokenizing based on string length

    public static List<String> tokenizeByLength(List<String> partStrings, String input, int splitLength)
    {
        partStrings = partStrings == null ? new ArrayList<String>() : partStrings;

        if (splitLength < input.length())
        {
            // split first
            partStrings.add(input.substring(0, splitLength));
            tokenizeByLength(partStrings, input.substring(splitLength), splitLength);
        }
        else
        {
            partStrings.add(input);
        }
        // input

        return partStrings;
    }

    private static char[] charData =
        { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
                'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        private static SecureRandom random = new SecureRandom();

        public static boolean isEmpty(String str)
        {
            return (str == null) || str.trim().length() == 0;
        }

        public static boolean isValid(String str, int maxLength, boolean isMandatory)
        {
            if (isEmpty(str))
            {
                if (isMandatory)
                    return false;
            }
            else if (str.length() > maxLength)
                return false;
            return true;
        }

        public static boolean isNumeric(String s)
        {
            return Pattern.matches("[0-9 ]+", s);
        }

        public static String getXmlNode(String nodeName, String value, boolean valueIsOptional)
        {
            if (!isEmpty(nodeName) && !isEmpty(value))
                return (getXmlStartTag(nodeName) + getXmlText(value) + getXmlEndTag(nodeName));
            else if (!isEmpty(nodeName) && !valueIsOptional)
                return (getXmlStartTag(nodeName) + getXmlEndTag(nodeName));
            else
                return "";
        }

        public static String getXmlNode(String nodeName, String value, boolean valueIsOptional,
                HashMap<String, String> atts)
        {
            if (!isEmpty(nodeName) && !isEmpty(value))
                return (getXmlStartTag(nodeName, atts) + getXmlText(value) + getXmlEndTag(nodeName));
            else if (!isEmpty(nodeName) && !valueIsOptional)
                return (getXmlStartTag(nodeName, atts) + getXmlEndTag(nodeName));
            else
                return "";
        }

        public static String getXmlStartTag(String nodeName, HashMap<String, String> atts)
        {
            StringBuffer attStr = new StringBuffer(" ");
            if (atts != null)
            {
                for (Entry<String, String> entry : atts.entrySet())
                {
                    if (!StringUtils.isEmpty(entry.getKey()))
                        attStr.append(entry.getKey() + "=\"" + entry.getValue() + "\" ");
                }
            }
            return "<" + nodeName + attStr + ">";
        }

        public static String getXmlStartTag(String nodeName)
        {
            return "<" + nodeName + ">";
        }

        public static String getXmlEndTag(String nodeName)
        {
            return "</" + nodeName + ">";
        }

        public static String getXmlText(String value)
        {
            if (isEmpty(value))
            {
                return "";
            }
            else
            {
                StringBuilder result = new StringBuilder();
                StringCharacterIterator iterator = new StringCharacterIterator(value);
                char character = iterator.current();
                while (character != CharacterIterator.DONE)
                {
                    if (character == '<')
                    {
                        result.append("&lt;");
                    }
                    else if (character == '>')
                    {
                        result.append("&gt;");
                    }
                    else if (character == '\"')
                    {
                        result.append("&quot;");
                    }
                    else if (character == '\'')
                    {
                        result.append("&#039;");
                    }
                    else if (character == '&')
                    {
                        result.append("&amp;");
                    }
                    else
                    {
                        // the char is not a special one
                        // add it to the result as is
                        result.append(character);
                    }
                    character = iterator.next();
                }
                return result.toString();
            }
        }

        /**
         * This method returns a random AlphaeNumericString
         *
         * @param size
         *            - the size of the cookie requested
         * @return the cookie
         */
        public static String getRandomAlphaNumericString(int size)
        {
            return getCookieOfSize(size);

            /*
             * StringBuffer buff = new StringBuffer(size);
             *
             * for (int i = 0; i < size; i++) buff.append(charData[random.nextInt(charData.length)]);
             *
             * return buff.toString();
             */
        }

        public static String noNullTrim(String str)
        {
            return noNullTrim(str, Constants.EMPTY_STRING);
        }

        public static String nullToNATrim(String str)
        {
            return noNullTrim(str, Constants.NA_STRING);
        }

        public static String noNullTrim(String str, String defaultStr)
        {
            return isEmpty(str) ? defaultStr : str.trim();
        }

        public static String getInUpperCase(String str)
        {
            return noNullTrim(str).toUpperCase();
        }

        public static String concatWithTokens(String token, boolean noTrim, String... stringsToConcat)
        {
            String finalString = "";

            for (String string : stringsToConcat)
            {
                finalString += (isEmpty(finalString) ? "" : token) + (noTrim ? string : noNullTrim(string));
            }

            return finalString;
        }

        public static String concatWithTokens(String token, String... stringsToConcat)
        {
            return concatWithTokens(token, true, stringsToConcat);
        }

        public static String concatWithTokens(String token, boolean noTrim, List<String> stringsToConcat)
        {
            String finalString = "";
            if (stringsToConcat != null)
            {
                for (String string : stringsToConcat)
                {
                    finalString += (isEmpty(finalString) ? "" : token) + (noTrim ? string : noNullTrim(string));
                }
            }

            return finalString;

        }

        public static String concatWithTokens(String token, List<String> stringsToConcat)
        {
            return concatWithTokens(token, true, stringsToConcat);
        }

        public static String toString(Object obj)
        {
            return obj == null ? Constants.EMPTY_STRING : obj.toString();
        }

        public static boolean areEqual(String strA, String strB)
        {
            return areEqual(strA, strB, false);
        }

        public static boolean areEqual(String strA, String strB, boolean isCaseSensitive)
        {
            strA = noNullTrim(strA);
            strB = noNullTrim(strB);
            return isCaseSensitive ? strA.equals(strB) : strA.equalsIgnoreCase(strB);
        }

        public static boolean find(String strA, String strB)
        {
            strA = noNullTrim(strA);
            strB = noNullTrim(strB);
            return strA.contains(strB);
        }

        public static String findReplace(String strA, String strB, String strC)
        {
            strA = noNullTrim(strA);
            strB = noNullTrim(strB);
            strC = noNullTrim(strC);
            return find(strA, strB) ? strA.replace(strB, strC) : strA;
        }

        public static String noEmpty(String str)
        {
            return isEmpty(str) ? Constants.NULL : str.trim();
        }

        public static boolean isArrayEmpty(String[] strArray)
        {
            return strArray == null || strArray.length == 0;
        }

        public static String displayMemo(String str, int length, String splitString)
        {
            if (isEmpty(str))
                return nullToNATrim(str);

            if (str.length() < length)
                return str;

            String modifiedMemo = new String("");

            int startIndex = 0;
            int endIndex = length;

            try
            {
                while (endIndex < str.length())
                {
                    modifiedMemo += str.substring(startIndex, endIndex) + splitString;
                    startIndex = endIndex;
                    endIndex = endIndex + length;

                    if (endIndex > str.length())
                    {
                        endIndex = str.length();
                        modifiedMemo += str.substring(startIndex, endIndex) + splitString;
                    }
                }
            }
            catch (Exception e)
            {
                return nullToNATrim(str);
            }

            return modifiedMemo;
        }

        public static String trimToSize(String string, int maxSize)
        {
            String newString = nullToNATrim(string);

            if (newString.length() > maxSize)
                return newString.substring(0, maxSize);
            else
                return newString;
        }

        /**
         * This method returns replaced accented characters by their unaccented equivalent and special
         * characters with empty space.
         *
         * @param string
         *
         * @return unaccented string
         */

        public static String removeAccents(String string)
        {
            if (!isEmpty(string))
            {
                String result = Normalizer.normalize(string, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
                result = result.replaceAll("[^a-zA-Z0-9 ]", "");

                return result;
            }
            return "";
        }

        public static String returnPaddedString(String literal, int size)
        {
            StringBuffer s = new StringBuffer();
            for (int i = 0; i < size; i++)
                s.append(literal);
            return s.toString();
        }

        /**
         *
         * This method formats the Input text value by replacing the carriage return character(\r\n)
         * with a simple new line(\n) character.
         *
         * @param string
         * @return formattedString
         */

        public static String formatText(String inputText)
        {

            inputText = StringUtils.noNullTrim(inputText);

            String formattedText = inputText.replaceAll("\r\n", "\n");

            return formattedText;
        }

        public static String getCookieOfSize(int size)
        {
            StringBuilder buff = new StringBuilder(size);

            for (int i = 0; i < size; i++)
                buff.append(charData[createSecureRandom().nextInt(62)]);

            return buff.toString();
        }

        public static SecureRandom createSecureRandom()
        {
            SecureRandom sr = null;
            StringBuilder buff = new StringBuilder();
            int k = 0;

            try
            {
                sr = SecureRandom.getInstance("SHA2");
            }
            catch (NoSuchAlgorithmException e)
            {
                sr = new SecureRandom();
            }
            for (int i = 0; i < 12; i++)
            {
                k = sr.nextInt(10);
                buff.append(k);
            }

            sr.setSeed(System.currentTimeMillis() + Long.parseLong(buff.toString()));

            return sr;
        }

        public static String resolveEnvironmentAndSystemProperties(String value)
        {
            Map<String, String> sysProps = readEnvironmentAndSystemProperties();
            Set<String> sysPropRefs = sysProps.keySet();

            for (String ref : sysPropRefs)
            {
                if (value.contains(ref))
                {
                    value = value.replace(ref, sysProps.get(ref));
                }
            }
            return value;
        }

        public static void resolveEnvironmentAndSystemProperties(Properties props)
        {
            Map<String, String> sysProps = readEnvironmentAndSystemProperties();
            Set<String> sysPropRefs = sysProps.keySet();

            Enumeration names = props.propertyNames();
            while (names.hasMoreElements())
            {
                String name = (String) names.nextElement();
                String value = props.getProperty(name);
                for (String ref : sysPropRefs)
                {
                    if (value.contains(ref))
                    {
                        value = value.replace(ref, sysProps.get(ref));
                    }
                }
                props.setProperty(name, value);
            }
        }

        private static Map<String, String> readEnvironmentAndSystemProperties()
        {
            Properties props = System.getProperties();
            Map<String, String> propsMap = new HashMap<String, String>(props.size() * 2);

            Enumeration names = props.propertyNames();
            while (names.hasMoreElements())
            {
                String name = (String) names.nextElement();
                propsMap.put("${" + name + "}", props.getProperty(name));
            }

            Map<String, String> envProps = System.getenv();
            for (Map.Entry<String, String> entry : envProps.entrySet())
            {
                propsMap.put("${env:" + entry.getKey() + "}", envProps.get(entry.getKey()));
            }
            // log.info(propsMap);
            return propsMap;
        }

        public static String decryptEncodedData(String secret, String message)
        {
            String computedHash = "";
            try
            {

                Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
                SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
                sha256_HMAC.init(secret_key);

                Encoder encoder = Base64.getEncoder();
                
                //String base64Hash = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
                
                String base64Hash = encoder.encodeToString(sha256_HMAC.doFinal(message.getBytes()));

                computedHash = URLEncoder.encode(base64Hash, "UTF-8");

            }
            catch (Exception e)
            {
                System.out.println("Some exception while encoding the data");
            }

            return computedHash;
        }

}

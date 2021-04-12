package com.ajt.kafkaPubSub.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Util {

    private static final Logger log = LoggerFactory.getLogger(Util.class);

    /**
     * @param list holds the list of string values
     * @return list by removing empty and null values
     */
    public static List<String> removeEmptyNullValuesInList(List<String> list) {
        try {
            if (list != null) {
                list.removeAll(Arrays.asList(null, ""));

            }
            return list;
        } catch (Exception ex) {
            log.error("error occurred while removing empty, null values from list  " + '"' + list + '"');
            return null;
        }
    }

    public static Boolean contains(List<String> input, String inputKey) {
        Set<String> set = new TreeSet<String>(String.CASE_INSENSITIVE_ORDER);
        set.addAll(input);
        return set.contains(inputKey);
    }

    public static List<String> commaSeparatedStringToList(String input) {
        if (Util.isNullOrEmpty(input)) {
            return Collections.emptyList();
        } else {
            // Remove whitespace and split by comma
            return Arrays.asList(input.split("\\s*,\\s*"));
        }
    }

    public static String getTimeStamp(String format) {
        format = format == null ? "yyyy-MM-dd HH:mm:ss.SSS" : format;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date now = new Date();
        String strDate = sdf.format(now);
        return strDate;
    }


    /**
     * Converts an Object to a byte array.
     *
     * @param object, the Object to serialize.
     * @return, the byte array that stores the serialized object.
     */
    public static <T> byte[] serialize(T object) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        try {
            out = new ObjectOutputStream(bos);
            out.writeObject(object);

            byte[] byteArray = bos.toByteArray();
            return byteArray;

        } catch (IOException e) {
            log.error("IOException occurred while serializing the object" + e.getMessage(), e.fillInStackTrace());
            return null;

        } finally {
            try {
                if (out != null)
                    out.close();
            } catch (IOException ex) {
                log.error("IOException occurred while serializing the object" + ex.getMessage(), ex.fillInStackTrace());
            }
            try {
                bos.close();
            } catch (IOException ex) {
                log.error("IOException occurred while serializing the object" + ex.getMessage(), ex.fillInStackTrace());
            }
        }

    }

    /**
     * Converts a byte array to an Object.
     *
     * @param byteArray, a byte array that represents a serialized Object.
     * @return, an instance of the Object class.
     */
    public static Object deserialize(byte[] byteArray) {
        ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
        ObjectInput in = null;
        try {
            in = new ObjectInputStream(bis);
            Object o = in.readObject();
            return o;

        } catch (ClassNotFoundException | IOException e) {
            log.error("ClassNotFoundException or IOException  occurred while deserializing the object" + e.getMessage(), e.fillInStackTrace());

            return null;
        } finally {
            try {
                bis.close();
            } catch (IOException ex) {
                log.error("IOException occurred while deserializing the object" + ex.getMessage(), ex.fillInStackTrace());
            }
            try {
                if (in != null)
                    in.close();
            } catch (IOException ex) {
                log.error("IOException occurred while deserializing the object" + ex.getMessage(), ex.fillInStackTrace());
            }
        }
    }

    public static boolean isNullOrEmpty(String str) {
        if (str != null && !str.isEmpty())
            return false;
        return true;
    }

    public static <E> boolean isListNullOrEmpty(List<E> lst) {
        if (lst != null && lst.size() > 0)
            return false;
        return true;
    }
}

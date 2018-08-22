package com.sk.hide.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class String2DateConverter implements Converter<String, Date> {


    /**  
     * dateFormat:日期时间格式.  
     */
    private static final String dateFormat      = "yyyy-MM-dd HH:mm:ss";
    /**  
     * shortDateFormat:日期格式.  
     */
    private static final String shortDateFormat = "yyyy-MM-dd";


    /**  
     * 字符串转换为日期.  
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)  
     */
    @Override
    public Date convert(String source) {
        if (source==null || source.isEmpty()) {
            return null;
        }
        source = source.trim();
        try {
            if (source.contains("-")) {
                SimpleDateFormat formatter;
                if (source.contains(":")) {
                    formatter = new SimpleDateFormat(dateFormat);
                } else {
                    formatter = new SimpleDateFormat(shortDateFormat);
                }
                Date dtDate = formatter.parse(source);
                return dtDate;
            } else if (source.matches("^\\d+$")) {
                Long lDate = new Long(source);
                return new Date(lDate);
            }
        } catch (Exception e) {
            throw new RuntimeException(String.format("parser %s to Date fail", source));
        }
        throw new RuntimeException(String.format("parser %s to Date fail", source));
    }
}
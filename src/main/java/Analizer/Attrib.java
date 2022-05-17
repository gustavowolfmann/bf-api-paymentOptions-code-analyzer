package Analizer;


import lombok.Data;

import java.util.Locale;
import java.util.Optional;

@Data
public class Attrib {

    private String name;
    private String type;
    private Optional<String> firstGenericType = Optional.empty();
    private Optional<String> secondGenericType = Optional.empty();

    public String toStringJava(){
        return getJavaType() + " " + getJavaName();
    }

    public String getJavaName(){
        return name;
    }

    public String getJavaType(){
        String javaType = mapJavaType();
        return javaType + genericToStringJava();
    }

    private String genericToStringJava(){
        return firstGenericType.map(f -> "<" + f +
                secondGenericType.map( s -> ","+s).orElse("")
                + ">").orElse("");
    }

    private String mapJavaType(){
        switch (type.toLowerCase(Locale.ROOT)) {
            case "collection":  {
                return "List";
            }
            default : {
                return type;
            }
        }
    }

    public String getNameOfClass(){
        return firstGenericType.isPresent() ? firstGenericType.get() : type;
    }

    public Boolean isNative(){
        switch (getNameOfClass().toLowerCase(Locale.ROOT)) {
            case "boolean" :
            case "bigdecimal":
            case "string":
            case "int":
            case "long":
            case "integer":
            case "date": {return Boolean.TRUE;}
            default: {return Boolean.FALSE;}
        }
    }

}

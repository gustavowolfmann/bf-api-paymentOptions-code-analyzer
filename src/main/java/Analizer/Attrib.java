package Analizer;


import lombok.Data;

import java.util.Locale;
import java.util.Optional;

@Data
public class Attrib {

    private String name;
    private String type;
    private Optional<String> genericType = Optional.empty();

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
        return genericType.isPresent() ? "<"+genericType.get()+">" : "";
    }

    private String mapJavaType(){
        switch (type.toLowerCase(Locale.ROOT)) {
            case "collection" : {return "List";}
            default : {return type;}
        }
    }

}

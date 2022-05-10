package Analizer;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClassToParse {

    private String name;
    private String path;
    private Boolean parsed;

}

package utils;

import java.util.HashMap;
import java.util.Map;

public class Headers {
    public HashMap<String,String> generalHeaders(){
        Map<String,String> headers=new HashMap<>();
        headers.put("Authorization",token);

    }
}

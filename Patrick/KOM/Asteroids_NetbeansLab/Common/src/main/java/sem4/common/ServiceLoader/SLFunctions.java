package sem4.common.ServiceLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;

public class SLFunctions {

    public <T> ArrayList<T> loadServices(Class<T> service) {
        ArrayList<T> modules = new ArrayList<>();
        
        for (T mod : ServiceLoader.load(service)) {
            modules.add(mod);
        }
        
        return modules;
    }
    
}
package sem4.osgicore;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        Game g = new Game();
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}

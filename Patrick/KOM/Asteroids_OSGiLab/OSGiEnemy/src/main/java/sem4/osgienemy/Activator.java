package sem4.osgienemy;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import sem4.common.services.IGamePluginService;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        context.registerService(IGamePluginService.class.getName(), new EnemyPlugin(), null);
    }

    public void stop(BundleContext context) throws Exception {
        // TODO add deactivation code here
    }

}

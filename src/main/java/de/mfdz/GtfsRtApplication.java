package de.mfdz;

import de.mfdz.resource.CarpoolResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class GtfsRtApplication extends Application<GtfsRtConfiguration> {

    public static void main(String[] args) throws Exception {
        new GtfsRtApplication().run(args);
    }

    @Override
    public void run(GtfsRtConfiguration configuration, Environment environment) throws Exception {
        registerResources(environment);
    }

    private void registerResources(Environment environment) {
        environment.jersey().register(CarpoolResource.class);
    }
}

package de.mfdz;

import io.dropwizard.Configuration;
import io.dropwizard.logging.LoggingFactory;

public class GtfsRtConfiguration extends Configuration {

    @Override
    public LoggingFactory getLoggingFactory() {
        return new LogbackAutoConfigLoggingFactory();
    }
}

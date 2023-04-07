package spc;

import org.glassfish.jersey.server.ResourceConfig;

public class Main extends ResourceConfig {

    public Main() {
        packages(Main.class.getPackageName());
    }

}

package example;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import example.HelloWorldResource;
// import example.TemplateHealthCheck;

public class Main extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new Main().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment e) {
                //****** Dropwizard security - custom classes ***********/
        e.jersey().register(new AuthDynamicFeature(new BasicCredentialAuthFilter.Builder<User>()
                    .setAuthenticator(new AppBasicAuthenticator())
                    .setAuthorizer(new AppAuthorizer())
                    .setRealm("BASIC-AUTH-REALM")
                    .buildAuthFilter()));
        e.jersey().register(RolesAllowedDynamicFeature.class);
        e.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));

        // nothing to do yet
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        e.jersey().register(resource);
    }

}

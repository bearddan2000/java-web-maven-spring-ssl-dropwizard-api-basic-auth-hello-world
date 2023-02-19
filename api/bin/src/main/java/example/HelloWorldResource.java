package example;

import example.Saying;

import io.dropwizard.auth.Auth;
import com.codahale.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Optional;
import javax.annotation.security.PermitAll;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @PermitAll
    @GET
    @Timed
    public Saying sayHello(@Auth User user, @QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.orElse(user.getName()));
        return new Saying(counter.incrementAndGet(), value);
    }
}

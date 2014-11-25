package main.java.pollseed.tool.gen.resources;

import io.dropwizard.jersey.caching.CacheControl;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import main.java.pollseed.tool.gen.core.Saying;
import main.java.pollseed.tool.gen.core.Template;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class TempleResources {
    private final Template template;
    private final AtomicLong counter;

    public TempleResources(Template template) {
        this.template = template;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed(name = "get-requests")
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        return new Saying(counter.incrementAndGet(), template.render(name));
    }

    @POST
    public void receiveHello(@Valid Saying saying) {
        System.out.println(String.format("Received a saying: {%d}", saying));
    }
}

package main.java.pollseed.tool.gen;

import main.java.pollseed.tool.gen.core.Template;
import main.java.pollseed.tool.gen.health.TemplateHealthCheck;
import main.java.pollseed.tool.gen.resources.TempleResources;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class Generator extends Application<Configuration> {
    public static void main(String[] args) throws Exception {
        new Generator().run(args);
    }

    @Override
    public void initialize(io.dropwizard.setup.Bootstrap<Configuration> bootstrap) {
    };

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        Template template = configuration.buildTemplate();
        // ヘルスチェック登録
        environment.healthChecks().register("template", new TemplateHealthCheck(template));
        environment.jersey().register(new TempleResources(template));

    };

}

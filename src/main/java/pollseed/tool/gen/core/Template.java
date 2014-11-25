package main.java.pollseed.tool.gen.core;

import com.google.common.base.Optional;

public class Template {
    private final String content;
    private final String defaultName;

    public Template(String content, String defaultName) {
        this.content = content;
        this.defaultName = defaultName;
    }

    public String render(Optional<String> name) {
        return String.format(content, name.or(defaultName));
    }
}

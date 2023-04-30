package markdowntohtml.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Link implements IRule {
    /**
     * The regular expression which looks for markdown links ([text](text)).
     */
    private static final String LINK_REGEX = "\\[([^\\n]+)\\]\\(([^\\n]+)\\)";
    /**
     * The replacement text with html `a` tags.
     */
    private static final String LINK_REPLACEMENT = "<a href=\\\"$2\\\">$1</a>";

    private final Pattern pattern;
    private final Integer priority;

    public Link() {
        this.pattern = Pattern.compile(LINK_REGEX, Pattern.MULTILINE);
        this.priority = 1;
    }

    /**
     * The replace method used by parser to add html a tags.
     *
     * @param text The Markdown text to convert
     * @return     The Markdown text with html links added
     */
    public String replace(String text) {
        Matcher matcher = pattern.matcher(text);
        return matcher.replaceAll(LINK_REPLACEMENT);
    }

    public Integer getPriority() {
        return this.priority;
    }
}

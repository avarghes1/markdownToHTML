package markdowntohtml.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paragraph implements IRule {
    /**
     * The regular expression which looks for newline to newline to add `p` html tag.
     */
    private static final String PARAGRAPH_REGEX = "\n?([^\n]+)\n?";
    /**
     * The replacement text with html `p` tags.
     */
    private static final String PARAGRAPH_REPLACEMENT = "<p>%s</p>";

    private final Pattern pattern;
    private final Integer priority;

    public Paragraph() {
        this.pattern = Pattern.compile(PARAGRAPH_REGEX, Pattern.MULTILINE);
        this.priority = 9;
    }

    /**
     * The replace method used by parser to add html p tags.
     *
     * @param text The Markdown text to convert
     * @return     The Markdown text with paragraph html tags added
     */
    public String replace(String text) {
        // check for already added html tags.
        Pattern tagPattern = Pattern.compile("^</?(h|p)");
        Matcher matcher = pattern.matcher(text);
        StringBuilder sb = new StringBuilder(text.length());
        while (matcher.find()) {
            Matcher tagMatcher = tagPattern.matcher(matcher.group(1));
            if (tagMatcher.find()) {
                // ignore already converted markdown
                matcher.appendReplacement(sb, Matcher.quoteReplacement("\n" + matcher.group(1) + "\n"));
                continue;
            }
            String replacement = PARAGRAPH_REPLACEMENT.formatted(matcher.group(1));
            matcher.appendReplacement(sb, "\n" + Matcher.quoteReplacement(replacement) + "\n");
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public Integer getPriority() {
        return this.priority;
    }
}

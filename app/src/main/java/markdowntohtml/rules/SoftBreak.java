package markdowntohtml.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoftBreak implements IRule {
    /**
     * The regular expression which looks for double spaces and html paragraph tags.
     */
    private static final String BREAK_REGEX = "^(.*)[\s]{2}</p>\\n\\n<p>";
    /**
     * The replacement text replaces extra html p tags with newline.
     */
    private static final String BREAK_REPLACEMENT = "%s\n";

    private final Pattern pattern;
    private final Integer priority;

    public SoftBreak() {
        this.pattern = Pattern.compile(BREAK_REGEX, Pattern.MULTILINE);
        this.priority = 10;
    }

    /**
     * The replace method used by parser to add soft breaks.
     *
     * @param text The Markdown text to convert
     * @return     The Markdown text with soft breaks added to paragraph tags.
     */
    public String replace(String text) {
        Matcher matcher = pattern.matcher(text);
        StringBuilder sb = new StringBuilder(text.length());
        while (matcher.find()) {
            String breakText = matcher.group(1).trim();
            String replacement = BREAK_REPLACEMENT.formatted(breakText);
            matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public Integer getPriority() {
        return this.priority;
    }
}

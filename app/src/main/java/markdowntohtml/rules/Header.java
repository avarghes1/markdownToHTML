package markdowntohtml.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Header implements IRule {
    /**
     * The regular expression which looks for `#` symbol.
     */
    private static final String HEADER_REGEX = "[\n]?(#+)(.*)\n?";
    /**
     * The replacement text with html `h` tags.
     */
    private static final String HEADER_REPLACEMENT = "<h%d>%s</h%d>";

    private final Pattern pattern;
    private final Integer priority;

    public Header() {
        this.pattern = Pattern.compile(HEADER_REGEX, Pattern.MULTILINE);
        this.priority = 1;
    }

    /**
     * The replace method used by parser to add html header tags.
     *
     * @param text The Markdown text to convert
     * @return     The Markdown text with header html tags added
     */
    public String replace(String text) {
        Matcher matcher = pattern.matcher(text);
        StringBuilder sb = new StringBuilder(text.length());
        while (matcher.find()) {
            // we need to count the number of `#` symbols
            int headerLength = matcher.group(1).length();
            String headerText = matcher.group(2).trim();
            String replacement = HEADER_REPLACEMENT.formatted(headerLength, headerText, headerLength);
            matcher.appendReplacement(sb, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    public Integer getPriority() {
        return this.priority;
    }
}

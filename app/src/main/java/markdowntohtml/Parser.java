package markdowntohtml;

import markdowntohtml.rules.*;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * The parser class which is used by the markdowntohtml app to convert
 * markdown to html.
 *
 */
public class Parser {
    private final PriorityQueue<IRule> rules;
    public Parser() {
        this.rules = new PriorityQueue<>(Comparator.comparingInt(IRule::getPriority));
        this.rules.add(new Header());
        this.rules.add(new Link());
        this.rules.add(new Paragraph());
        this.rules.add(new SoftBreak());
    }

    /**
     * The parser will use the different rules to parse and convert markdown to html.
     *
     * @param  text Markdown text
     * @return      the converted html for the provided markdown
     */
    public String parse(String text) {
        for (IRule rule : rules) {
            text = rule.replace(text);
        }
        return text;
    }
}

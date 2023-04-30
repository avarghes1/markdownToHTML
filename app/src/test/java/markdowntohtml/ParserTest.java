package markdowntohtml;

import org.junit.Test;
import static org.junit.Assert.*;

public class ParserTest {
    @Test
    public void parseEmptyString() {
        Parser parser = new Parser();
        String markdown = "";
        String expectedHTML = "";
        assertEquals(expectedHTML, parser.parse(markdown));
    }

    @Test
    public void parseText() {
        Parser parser = new Parser();
        String markdown = "text";
        String expectedHTML = "\n<p>text</p>\n";
        assertEquals(expectedHTML, parser.parse(markdown));
    }

    @Test
    public void parseHeader() {
        Parser parser = new Parser();
        String markdown = "# Header one";
        String expectedHTML = "\n<h1>Header one</h1>\n";
        assertEquals(expectedHTML, parser.parse(markdown));
    }

    @Test
    public void parseLink() {
        Parser parser = new Parser();
        String markdown = "[Mailchimp](https://www.mailchimp.com)";
        String expectedHTML = "\n<p><a href=\"https://www.mailchimp.com\">Mailchimp</a></p>\n";
        assertEquals(expectedHTML, parser.parse(markdown));
    }

    @Test
    public void parseParagraph() {
        Parser parser = new Parser();
        String markdown = "\n" +"Hello!\n";
        String expectedHTML = "\n<p>Hello!</p>\n";
        assertEquals(expectedHTML, parser.parse(markdown));
    }

    @Test
    public void parseMarkdown() {
        Parser parser = new Parser();
        String markdown = "# Sample Document\n" +
                "\n" +
                "Hello!\n" +
                "\n" +
                "This is sample markdown for the [Mailchimp](https://www.mailchimp.com) homework assignment.";
        String expectedHTML = "\n<h1>Sample Document</h1>\n" +
                "\n" +
                "<p>Hello!</p>\n" +
                "\n" +
                "<p>This is sample markdown for the <a href=\"https://www.mailchimp.com\">Mailchimp</a> homework assignment.</p>\n";
        assertEquals(expectedHTML, parser.parse(markdown));
    }

    @Test
    public void parseInlineMarkdown() {
        Parser parser = new Parser();
        String markdown = "# Header one\n" +
                "\n" +
                "Hello there\n" +
                "\n" +
                "How are you?  \n" +
                "What's going on?\n" +
                "\n" +
                "## Another Header\n" +
                "\n" +
                "This is a paragraph [with an inline link](http://google.com). Neat, eh?\n" +
                "\n" +
                "## This is a header [with a link](http://yahoo.com)";
        String expectedHTML = "\n<h1>Header one</h1>\n" +
                "\n" +
                "<p>Hello there</p>\n" +
                "\n" +
                "<p>How are you?\n" +
                "What's going on?</p>\n" +
                "\n" +
                "<h2>Another Header</h2>" +
                "\n" +
                "\n<p>This is a paragraph <a href=\"http://google.com\">with an inline link</a>. Neat, eh?</p>\n" +
                "\n" +
                "<h2>This is a header <a href=\"http://yahoo.com\">with a link</a></h2>" +
                "\n";
        assertEquals(expectedHTML, parser.parse(markdown));

    }
}

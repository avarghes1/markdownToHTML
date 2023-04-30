package markdowntohtml.rules;

public interface IRule {
    String replace(String text);

    Integer getPriority();
}

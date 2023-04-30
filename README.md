# Markdown => HTML converter

The Markdown to HTML converter uses regular expressions to parse the Markdown text and convert it into HTML code.
The converter works by scanning the Markdown text for specific syntax patterns, such as headings, links & soft breaks. 
When it finds a pattern, it applies the appropriate HTML code to the text, converting it into HTML format.

The solution is broken into a parser which uses rules (headers, links, paragraph etc) for different markdown tokens.

To run the code, enter the terminal command

`` gradle run ``

To run tests, enter the terminal command

`` gradle test ``

NOTE

1. The MarkdownToHTML.java file has a variable called markdown (line 13). This is the text that is 
converted to html. In order to run other markdown this variable needs to be changed.

1. The solution does not validate markdown syntax.

1. In order to satisfy 

```
How are you?
What's going on?
```

to

```
<p>How are you?
What's going on?</p>
```

the parser looks for double space in the end of the line to format the text.

package gc.garcol.simplebloomfilter;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class CliCommand {

  private final BloomFilter<String> textFilter;

  @ShellMethod(key = "add")
  public String add(@ShellOption(help = "Word to be added") String word) {
    textFilter.add(word);
    return "Added " + word;
  }

  @ShellMethod(key = "check")
  public String check(@ShellOption(help = "World to be checked") String word) {
    return textFilter.maybeContain(word)
        ? String.format("Might contain '%s'", word)
        : String.format("Definitely not contain '%s'", word);
  }
}

package tools.parser;

public class App {
	public static void main(String[] args) {
		LogParser logParser = new LogParser();
		logParser.parse(new CliParser().parse(args));
		System.out.println("done");
	}
}

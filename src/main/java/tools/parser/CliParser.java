package tools.parser;

import org.apache.commons.cli.*;

import java.util.HashMap;
import java.util.Map;

public class CliParser {
	public static final String PATH = "p";
	public static final String LEFT = "l";
	public static final String RIGHT = "r";
	private Map<String, String> inputMap;
	private Options options;

	public CliParser() {
		options = new Options();
		options.addOption(Option.builder(PATH)
				.desc("path")
				.hasArg()
				.required()
				.type(PatternOptionBuilder.STRING_VALUE)
				.build());
		options.addOption(Option.builder(LEFT)
				.desc("left")
				.hasArg()
				.required()
				.type(PatternOptionBuilder.STRING_VALUE)
				.build());
		options.addOption(Option.builder(RIGHT)
				.desc("right")
				.hasArg()
				.required()
				.type(PatternOptionBuilder.STRING_VALUE)
				.build());
		inputMap = new HashMap<>();
		inputMap.put(PATH, "");
		inputMap.put(LEFT, "");
		inputMap.put(RIGHT, "");
	}

	public Map<String, String> parse(String[] input) {
		HelpFormatter help = new HelpFormatter();
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine cmd = parser.parse(options, input);
			inputMap.put(PATH, cmd.getOptionValue(PATH));
			inputMap.put(LEFT, cmd.getOptionValue(LEFT));
			inputMap.put(RIGHT, cmd.getOptionValue(RIGHT));
		} catch (Exception e) {
			help.printHelp("keys", options);
			System.exit(0);
		}
		return inputMap;
	}

	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}
}

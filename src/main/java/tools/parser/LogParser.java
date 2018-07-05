package tools.parser;


import org.apache.commons.text.StringEscapeUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {

	private static final String POSTFIX = "_parsed.txt";
	private static final String ENCODING = "UTF-8";

	public void parse(Map<String, String> args) {
		String fileName = args.get(CliParser.PATH);
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String currentLine;
			StringBuilder sb = new StringBuilder();
			while ((currentLine = br.readLine()) != null) {
				sb.append(currentLine);
			}
			saveParsedLog(parseLog(StringEscapeUtils.unescapeHtml4(sb.toString()), args.get(CliParser.LEFT), args.get(CliParser.RIGHT)), fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void saveParsedLog(List<String> lines, String path) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(path + POSTFIX, ENCODING);
		lines.forEach(writer::write);
		writer.close();
	}

	private List<String> parseLog(String source, String left, String right) {
		List<String> result = new ArrayList<>();
		String regexString = Pattern.quote(left) + "(.*?)" + Pattern.quote(right);
		Pattern pattern = Pattern.compile(regexString, Pattern.DOTALL | Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
		Matcher matcher = pattern.matcher(source);
		while (matcher.find()) {
			result.add(matcher.group(1) + "\n");
		}
		return result;
	}
}

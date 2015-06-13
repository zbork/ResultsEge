import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ResultParser {

	public static String parse (String page) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%-11s|%7s|%6s\n", "Предмет", "Процент", "Балл"));
		sb.append(String.format("%-11s|%7s|%6s\n", "-----------", "-------", "------"));
		
		Pattern p = Pattern.compile("res_main\" align=\"center\"><td>(.*)</td><td>(.*)</td><td>(.*)</td><td>(.*)</td><td>(.*)</td><td>(.*)</td><td>(.*)</td><td>(.*)</td><td>(.*)</td><td>(.*)</td><td>(.*)</td><td>(.*)<br/>",
				Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
		Matcher m = p.matcher(page);
		
		while(m.find()) {
			sb.append(String.format("%-11s|%7s|%6s\n", m.group(12), m.group(6), m.group(10)));
		}
		
		
		return sb.toString();
	}

}

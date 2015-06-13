import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.ClientProtocolException;

public class Requester {

	private String fam = "";
	private String ser = "";
	private String num = "";
	
	private static final String sub = EncodingUtil.encodeURIComponent("Найти");
	public String result = "";

	public Requester(String fam, String ser, String num)
			throws ClientProtocolException, IOException {
		this.fam = EncodingUtil.encodeURIComponent(fam);
		this.ser = ser;
		this.num = num;

		initialize();
	}

	private Requester initialize() throws ClientProtocolException, IOException {

		URL url = new URL(String.format("http://www.uledu.ru/results?fam=%s&doc_ser=%s&doc_number=%s&submit=%s&send=Y&breg",
				fam, ser, num, sub));
		URLConnection con = url.openConnection();
		Pattern p = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
		Matcher m = p.matcher(con.getContentType());
		/*
		 * If Content-Type doesn't match this pre-conception, choose default and
		 * hope for the best.
		 */
		String charset = m.matches() ? m.group(1) : "ISO-8859-1";
		Reader r = new InputStreamReader(con.getInputStream(), charset);
		StringBuilder buf = new StringBuilder();
		while (true) {
			int ch = r.read();
			if (ch < 0)
				break;
			buf.append((char) ch);
		}
		String str = buf.toString();
		result = ResultParser.parse(str);

		return this;
	}

	public String getResult() {
		return result;
	}

}

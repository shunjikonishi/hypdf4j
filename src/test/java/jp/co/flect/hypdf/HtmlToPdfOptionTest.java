package jp.co.flect.hypdf;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;

import jp.co.flect.hypdf.model.HtmlToPdfOption;
import jp.co.flect.hypdf.model.LengthUnit;

public class HtmlToPdfOptionTest {

	@Test
	public void test() {
		Map<String, Object> map = new HashMap<String, Object>();
		HtmlToPdfOption option = new HtmlToPdfOption();
		option.test = true;
		option.title = "hoge";
		option.copies = 3;
		option.margin_bottom = LengthUnit.inch(3);
		option.orientation = HtmlToPdfOption.Orientation.Portrait;

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("aaa", "bbb");
		headers.put("ccc", "ddd");
		option.custom_header = headers;
		option.s3setting = new HtmlToPdfOption.S3Setting("bucket", "key", true);

		option.apply(map);

		System.out.println(map);
		assertEquals(9, map.size());
		assertEquals(Boolean.TRUE, map.get("test"));
		assertEquals("hoge", map.get("title"));
		assertEquals(3, map.get("copies"));
		assertEquals("3.0in", map.get("margin_bottom"));
		assertEquals("Portrait", map.get("orientation"));
		assertEquals("bucket", map.get("bucket"));
		assertEquals("key", map.get("key"));
		assertEquals(Boolean.TRUE, map.get("public"));
		String[] array = (String[])map.get("custom_header");
		assertEquals(2, array.length);
		assertEquals("aaa bbb", array[0]);
		assertEquals("ccc ddd", array[1]);
	}

}
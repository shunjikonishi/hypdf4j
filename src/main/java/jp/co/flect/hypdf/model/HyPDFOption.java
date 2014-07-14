package jp.co.flect.hypdf.model;

import java.util.Map;
import java.net.URL;
import java.lang.reflect.Field;


public abstract class HyPDFOption {
	public enum Orientation {
		Landscape, 
		Portrait;
	}

	public enum PageSize {
		A0, A1, A2, A3, A4, A5, A6, A7, A8, A9, A10,
		B0, B1, B2, B3, B4, B5, B6, B7, B8, B9, B10,
		C5E, 
		Comm10E, 
		DLE, 
		Executive, 
		Folio, 
		Ledger, 
		Legal, 
		Letter, 
		Tabloid;
	}

	public void apply(Map<String, Object> params) {
		mapping(this, params);
	}

	/*
	test	true, false	Use test mode.

	bucket	String	Upload created PDF to this AWS S3 bucket.
	key	String	File name.
	public	true, false	Grant everyone read access to uploaded file.

	callback	String (URL)	Create PDF in the background and post results to this URL.
	disable_links	true	If present, render plain text instead of links.
	no_collate	true	If present, do not collate when printing multiple copies
	copies	Integer	Number of copies to print into the pdf file (default 1)
	grayscale	true	If present, PDF will be generated in grayscale
	lowquality	true	Generates lower quality pdf/ps. Useful to shrink the result document space
	dpi	Integer	Change the dpi explicitly
	image_dpi	Integer	When embedding images scale them down to this dpi (default 600)
	image_quality	Integer	When jpeg compressing images use this quality (default 94)

	margin_bottom	String (“0.5in”, “15mm”, “2cm” etc)	Set the page bottom margin (default “0.75in”)
	margin_left	String (“0.5in”, “15mm”, “2cm” etc)	Set the page left margin (default “0.75in”)
	margin_right	String (“0.5in”, “15mm”, “2cm” etc)	Set the page right margin (default “0.75in”)
	margin_top	String (“0.5in”, “15mm”, “2cm” etc)	Set the page top margin (default “0.75in”)

	orientation	Landscape, Portrait	Set orientation (default “Portrait”)

	page_height	String (“8.27in”, “297mm”, “21cm” etc)	Page height
	page_width	String (“8.27in”, “297mm”, “21cm” etc)	Page width

	page_size	A0..B10, C5E, Comm10E, DLE, Executive, Folio, Ledger, Legal, Letter, Tabloid	Set paper size (default “Letter”)

	title	String	The title of the generated pdf file (The title of the first document is used if not specified)
	no_background	true	If present, do not print background

	cookie	String (“name value”)	Set an additional cookie (repeatable)
	custom_header	String (“name value”)	Set an additional HTTP header (repeatable)

	no_custom_header_propagation	true	If present, do not add HTTP headers specified by custom_header for each resource request.
	encoding	String	Set the default text encoding, for input (default “UTF-8”)
	no_images	true	If present, do not load or print images
	disable_javascript	true	If present, do not allow web pages to run javascript
	javascript_delay	Integer	Wait some milliseconds for javascript finish (default 200)
	no_stop_slow_scripts	true	If present, do not Stop slow running javascripts
	run_script	String (code)	Run this additional javascript after the page is done loading (repeatable)
	minimum_font_size	Integer	Minimum font size
	page_offset	Integer	Set the starting page number (default 0)
	password	String	HTTP Authentication password
	username	String	HTTP Authentication username
	user_style_sheet	String	Specify a URL to user style sheet, to load with every page
	window_status	String	Wait until window.status is equal to this string before rendering page
	zoom	Float	Use this zoom factor (default 1)

	header_left	String	Left aligned header text.
	header_right	String	Right aligned header text.
	header_center	String	Centered header text.
	header_font_name	String	Set header font name (default Arial).
	header_font_size	Integer	Set header font size (default 12).
	header_line	true	Display line below the header.
	header_html	String (URL of HTML document)	Adds a html header (overrides any other header_* options listed above).
	header_spacing	Float	Spacing between header and content in mm (default 0).

	footer_left	String	Left aligned footer text.
	footer_right	String	Right aligned footer text.
	footer_center	String	Centered footer text.
	footer_font_name	String	Set footer font name (default Arial).
	footer_font_size	Integer	Set footer font size (default 12).
	footer_line	true	Display line above the footer.
	footer_html	String (URL of HTML document)	Adds a html footer (overrides any other footer_* options listed above).
	footer_spacing	Float	Spacing between content and footer in mm (default 0).
	*/
	public static class HtmlToPdf extends HyPDFOption {

		public boolean test = false;
		public S3Setting s3setting = null;

		public URL callback = null;
		public boolean disable_links = false;
		public boolean no_collate = false;
		public Integer copies = null;
		public boolean grayscale = false;
		public boolean lowquality = false;
		public Integer dpi = null;
		public Integer image_dpi = null;
		public Integer image_quality = null;

		public LengthUnit margin_bottom = null;
		public LengthUnit margin_left = null;
		public LengthUnit margin_right = null;
		public LengthUnit margin_top = null;

		public void setMargin(LengthUnit tb, LengthUnit lr) {
			this.margin_top = tb;
			this.margin_bottom = tb;
			this.margin_left = lr;
			this.margin_right = lr;
		}

		public void setMargin(LengthUnit t, LengthUnit r, LengthUnit b, LengthUnit l) {
			this.margin_top = t;
			this.margin_bottom = b;
			this.margin_left = l;
			this.margin_right = r;
		}

		public Orientation orientation = null;

		public LengthUnit page_height = null;
		public LengthUnit page_width = null;

		public PageSize page_size = null;

		public String title = null;
		public boolean no_background = false;

		public Map<String, String> cookie = null;
		public Map<String, String> custom_header = null;

		public boolean no_custom_header_propagation = false;
		public String encoding = null;
		public boolean no_images = false;
		public boolean disable_javascript = false;
		public Integer javascript_delay = null;
		public boolean no_stop_slow_scripts = false;
		public String run_script = null;
		public Integer minimum_font_size = null;
		public Integer page_offset = null;
		public String password = null;
		public String username = null;
		public String user_style_sheet = null;
		public String window_status = null;
		public Float zoom = null;

		public Header header = null;
		public Footer footer = null;

	}

	/*
	first_page	Integer	First page to convert.
	last_page	Integer	Last page to convert.
	no_page_break	true	If present, don’t insert page breaks between pages.
	crop_x	Integer	x-coordinate of the crop area top left corner.
	crop_y	Integer	y-coordinate of the crop area top left corner.
	crop_width	Integer	Width of crop area in pixels.
	crop_height	Integer	Height of crop area in pixels.
	*/
	public static class PdfToText extends HyPDFOption {
		public Integer first_page = null;
		public Integer last_page = null;
		public boolean no_page_break = false;
		public Integer crop_x = null;
		public Integer crop_y = null;
		public Integer crop_width = null;
		public Integer crop_height = null;
	}

	/*
	first_page	Integer	First page to extract.
	last_page	Integer	Last page to extract.
	test	true, false	Use test mode.
	bucket	String	Upload created PDF to this AWS S3 bucket.
	key	String	File name.
	public	true, false	Grant everyone read access to uploaded file.
	*/
	public static class PdfExtract extends HyPDFOption {
		public Integer first_page = null;
		public Integer last_page = null;
		public boolean test = false;
		public S3Setting s3setting = null;
	}

	/*
	test	true, false	Use test mode.
	bucket	String	Upload created PDF to this AWS S3 bucket.
	key	String	File name.
	public	true, false	Grant everyone read access to uploaded file.
	*/
	public static class PdfUnite extends HyPDFOption {
		public boolean test = false;
		public S3Setting s3setting = null;
	}

	public static class S3Setting {
		public String bucket = null;
		public String key = null;
		public boolean readAccess = false;

		public S3Setting(String bucket, String key, boolean readAccess) {
			this.bucket = bucket;
			this.key = key;
			this.readAccess = readAccess;
		}

		public void apply(Map<String, Object> params) {
			if (bucket != null && key != null) {
				params.put("bucket", bucket);
				params.put("key", key);
				if (readAccess) {
					params.put("public", readAccess);
				}
			}
		}
	}

	public static abstract class HeaderOrFooter {
		public abstract String prefix();

		public String left = null;
		public String right = null;
		public String center = null;
		public String font_name = null;
		public Integer font_size = null;
		public boolean line = false;
		public String html = null;
		public Float spacing = null;

		public void apply(Map<String, Object> params) {
			mapping(prefix(), this, params);
		}
	}

	public static class Header extends HeaderOrFooter {
		@Override
		public String prefix() { return "header_";}
	}

	public static class Footer extends HeaderOrFooter {
		@Override
		public String prefix() { return "footer_";}
	}
	
	private static void mapping(Object obj, Map<String, Object> map) {
		mapping("", obj, map);
	}

	private static void mapping(String prefix, Object obj, Map<String, Object> map) {
		Class clazz = obj.getClass();
		try {
			for (Field f : clazz.getFields()) {
				String name = prefix + f.getName();
				Class type = f.getType();
				Object value = f.get(obj);
				if (value == null) {
					continue;
				}

				if (type == boolean.class) {
					if (f.getBoolean(obj)) {
						map.put(name, value);
					}
				} else if (type == String.class || type == URL.class || type == LengthUnit.class) {
					map.put(name, value.toString());
				} else if (type == Integer.class || type == Float.class) {
					map.put(name, value);
				} else if (type.isEnum()) {
					map.put(name, value.toString());
				} else if (type == Map.class) {
					Map<String, String> values = (Map<String, String>)value;
					String[] strs = new String[values.size()];
					int idx = 0;
					for (Map.Entry<String, String> entry : values.entrySet()) {
						String v = entry.getKey() + " " + entry.getValue();
						strs[idx++] = v;
					}
					map.put(name, strs);
				} else if (type == S3Setting.class) {
					S3Setting setting = (S3Setting)value;
					setting.apply(map);
				} else if (type == Header.class || type == Footer.class) {
					HeaderOrFooter hf = (HeaderOrFooter)value;
					hf.apply(map);
				} else {
					System.out.println("mapping: " + name + ", Unknown");
				}
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}

	}
}
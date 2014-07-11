package jp.co.flect.hypdf.model;

import java.util.Map;

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
public class HtmlToPdfOption {

	public void apply(Map<String, Object> params) {

	}
}
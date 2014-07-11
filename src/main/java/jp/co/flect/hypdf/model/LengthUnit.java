package jp.co.flect.hypdf.model;

public class LengthUnit {

	public static LengthUnit inch(double n) { return new LengthUnit(n, "in");}
	public static LengthUnit mm(double n) { return new LengthUnit(n, "mm");}
	public static LengthUnit cm(double n) { return new LengthUnit(n, "cm");}

	private double n;
	private String unit;

	private LengthUnit(double n, String unit) {
		this.n = n;
		this.unit = unit;
	}
}
package tools;

import java.math.BigDecimal;

public class Arith {
	private static final int DEF_DIV_SCALE = 2;//除法精度为两位小数
	private Arith(){
		
	}
	public static double add(double v1, double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	public static double sub(double v1, double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	public static double mul(double v1, double v2) {
		// TODO Auto-generated method stub
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	
	public static double div(double v1,double v2)
	{   
		return div(v1,v2,DEF_DIV_SCALE);   
	}
	public static double div(double v1, double v2, int scale) {
		if (scale < 0){
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();//BigDecimal.ROUND_HALF_UP 向“最接近的”数字舍入，如果与两个相邻数字的距离相等，则为向上舍入的舍入模式。
	}
	
	public static double round(double v, int scale){
		if (scale < 0){
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}


}

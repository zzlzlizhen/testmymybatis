package lz.junittest;

import java.math.BigDecimal;

public class TestMy {
	public static final BigDecimal DAYS_FOR_MONTH = new BigDecimal(21.75);
	public static final BigDecimal HOURS_FOR_DAT = new BigDecimal(8);
	public static final BigDecimal FXYJ = new BigDecimal(1200);
	public static final BigDecimal MIN_REVENUE = new BigDecimal(3500);
	public static final BigDecimal REVENUE_PERCENT = new BigDecimal(0.25);
	public static final BigDecimal REVENUE_REMOVE = new BigDecimal(1005);
	
	public static void main(String [] args) {
		BigDecimal mySalary = getMySalary(new BigDecimal(16000),new BigDecimal(0),new BigDecimal(20),new BigDecimal(2),new BigDecimal(400));
		System.out.println(mySalary.doubleValue());
	}
	/*
	 * 基本工资，请假小时，双倍加班小时，1.5倍加班小时，饭补
	 */
	public static BigDecimal getMySalary(BigDecimal baseSalary,BigDecimal leaveHours,BigDecimal overtimeHoursForDouble,BigDecimal overtimeHoursForOneAndHalf,BigDecimal assistance){
		BigDecimal daySalary = baseSalary.divide(TestMy.DAYS_FOR_MONTH,2);
		BigDecimal HourSalary = daySalary.divide(HOURS_FOR_DAT,2);
		if(assistance!=null){
			baseSalary = baseSalary.add(assistance);
		}
		if(leaveHours!=null){
			baseSalary = baseSalary.subtract(HourSalary.multiply(leaveHours));
		}
		if(overtimeHoursForDouble!=null){
			baseSalary = baseSalary.add(HourSalary.multiply(overtimeHoursForDouble).multiply(new BigDecimal(2)));
		}
		if(overtimeHoursForOneAndHalf!=null){
			baseSalary = baseSalary.add(HourSalary.multiply(overtimeHoursForOneAndHalf).multiply(new BigDecimal(1.5)));
		}
		baseSalary = baseSalary.subtract(FXYJ);
		baseSalary = baseSalary.subtract((baseSalary.subtract(MIN_REVENUE)).multiply(REVENUE_PERCENT).subtract(REVENUE_REMOVE));
		return baseSalary;
	}
}

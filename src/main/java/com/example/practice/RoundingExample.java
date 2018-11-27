package com.example.practice;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class RoundingExample {

    public static void main(String[] args) {
        double l = 1.6;
        
        //similar like RegEx but don't have much options
        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println(df.format(l));
        
        //2 decimal places rounding with half up rounding mode
        System.out.println(BigDecimal.valueOf(l).setScale(2, RoundingMode.HALF_UP));
        
        //3 decimal places rounding with ceiling rounding mode
        System.out.println(BigDecimal.valueOf(l).setScale(3, RoundingMode.CEILING));
        System.out.println(BigDecimal.valueOf(l).setScale(0, RoundingMode.CEILING));
        
        //integer rounding with floor rounding mode
        System.out.println(BigDecimal.valueOf(l).setScale(0, RoundingMode.FLOOR));
        
        
        df.setRoundingMode(RoundingMode.DOWN);
		df.setMinimumFractionDigits(2);
		System.out.println(df.format(l).replaceAll("\\.", ""));
    }

}
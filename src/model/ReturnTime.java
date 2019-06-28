package model;

import java.util.Calendar;

public class ReturnTime {

    private static Calendar now = Calendar.getInstance();

    public static String ReturnNoTime(){
        //时间
        String yearString=now.get(Calendar.YEAR)+"";

        String monthString=now.get(Calendar.MONTH) + 1 + "";
        if(monthString.length()==1)
            monthString=0+monthString;

        String dayString=now.get(Calendar.DAY_OF_MONTH) + "";
        if(dayString.length()==1)
            dayString=0+dayString;

        String hString=now.get(Calendar.HOUR_OF_DAY)+ "";
        if(hString.length()==1)
            hString=0+hString;

        String fString=now.get(Calendar.MINUTE)+ "";
        if(fString.length()==1)
            fString=0+fString;

        String mString=now.get(Calendar.SECOND) + "";
        if(mString.length()==1)
            mString=0+mString;

        return (yearString+monthString+dayString+hString+fString+mString);
    }

    public static String ReturnYesTime(){
        //时间
        String yearString=now.get(Calendar.YEAR)+"";

        String monthString=now.get(Calendar.MONTH) + 1 + "";
        if(monthString.length()==1)
            monthString=0+monthString;

        String dayString=now.get(Calendar.DAY_OF_MONTH) + "";
        if(dayString.length()==1)
            dayString=0+dayString;

        String hString=now.get(Calendar.HOUR_OF_DAY)+ "";
        if(hString.length()==1)
            hString=0+hString;

        String fString=now.get(Calendar.MINUTE)+ "";
        if(fString.length()==1)
            fString=0+fString;

        String mString=now.get(Calendar.SECOND) + "";
        if(mString.length()==1)
            mString=0+mString;

        return (yearString+"-"+monthString+"-"+dayString+" "+hString+":"+fString+":"+mString);
    }
}

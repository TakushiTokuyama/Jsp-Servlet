package schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

public class inputCheckLogic {

public static boolean planTimeCalc(String planTime){
		//予定の時間 yyyy-mm-dd
		LocalDate futureTime = LocalDate.parse(planTime);
		//現在の時間 yyyy-mm-dd
		LocalDate currentTime = LocalDate.now();

			//予定の年
			int futureYear = futureTime.getYear();
			//予定の月
		    int futureMonth = futureTime.getMonthValue();
		    //予定の日
			int futureDay = futureTime.getDayOfMonth();

			//現在の年
			int currentYear = currentTime.getYear();
			//現在の月
			int currentMonth =currentTime.getMonthValue();
			//現在の日
			int currentDay = currentTime.getDayOfMonth();

			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();

			cal.set(futureYear,futureMonth, futureDay );
			cal2.set(currentYear,currentMonth,currentDay);

			//秒に変換
			long FutureMill = cal.getTimeInMillis();
			long CurrentMill = cal2.getTimeInMillis();

			if(CurrentMill >= FutureMill ) {
				return false;
			}else {
				return true;
			}
	}

		public static boolean timeCalc(String firstTime , String lastTime){

			LocalTime oneTime = LocalTime.parse(firstTime);
			LocalTime endTime = LocalTime.parse(lastTime);

			int oneTimeHour = oneTime.getHour();
			int endTimeHour = endTime.getHour();

			int oneTimeMinute = oneTime.getMinute();
			int endTimeMinute = endTime.getMinute();

			Calendar cal3 = Calendar.getInstance();
			Calendar cal4 = Calendar.getInstance();

			cal3.set(0,0,0,oneTimeHour,oneTimeMinute);
			cal4.set(0,0,0,endTimeHour,endTimeMinute);

			long oneTimeMill = cal3.getTimeInMillis();
			long endTimeMill = cal4.getTimeInMillis();

			if(oneTimeMill >= endTimeMill) {
				return false;
			}else {
				return true;
			}
	}
}

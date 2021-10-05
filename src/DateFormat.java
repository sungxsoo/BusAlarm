
public class DateFormat {
	
	int totalMin;
	String time;
	//DateFormat으로 받은 HH:MM format을 비교할 수 있도록 분으로 변환한다.
	//08:00 = 8 x 60 = 480 , 12:30 = 12 X 60 + 30 = 750, 14:20 = 14 X 60 + 20 = 860 ...
	public DateFormat(String date){
		time = date;
		String[] time = date.split(":");
		totalMin = Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);

	}
	public int getTotalMin() {
		return totalMin;
	}
	public String getTime(){
		return time;
	}
}

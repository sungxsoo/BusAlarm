import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class DateFormat {
	
	int totalMin;
	String time;
	
	public DateFormat(String date){

		time = date;
		String[] time = date.split(":");
		//8:00이라
		//time[0] 은 8,time[1]은 00 
		totalMin = Integer.parseInt(time[0])*60 + Integer.parseInt(time[1]);

	}
	public int getTotalMin() {
		return totalMin;
	}
	public String getTime(){
		return time;
	}
}


public class Main {

	public static String[] stationNames = {"버스관리사무소", "상공회의소", "진입로", "동부경찰서", "용인시장", "중앙주차장"};
	public static int[] stationTimes = {3, 2, 1, 3, 4, 2};
	public static DateFormat[]stationStart = new DateFormat[11];

	public static void main(String[] args) {
		getCurtrentBusTime("ss");
	}

	//현재 시간과 매칭되는 버스 시간대 구하기
	public static void getCurtrentBusTime(String stationName) {

		int stationIndex = 0;

		//임시 버스 시작 데이터 
		stationStart[0] = new DateFormat("8:00");
		stationStart[1] = new DateFormat("8:10");
		stationStart[2] = new DateFormat("9:05");
		stationStart[3] = new DateFormat("10:30");
		stationStart[4] = new DateFormat("11:20");
		stationStart[5] = new DateFormat("13:10");
		stationStart[6] = new DateFormat("14:50");
		stationStart[7] = new DateFormat("15:50");
		stationStart[8] = new DateFormat("16:00");
		stationStart[9] = new DateFormat("16:30");
		stationStart[10] = new DateFormat("16:50");
		
		//매칭되는 정류장의 이름의 index를 구한다. 
		for(int i =0; i<stationNames.length; i++) {
			if(stationNames[i].equals(stationName)) {
				stationIndex = i;
			}
		}
		
		//현재 시간 구해서 비교할 포멧으로 만들기
		long now = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date date = new Date(now);
		DateFormat targetTime = new DateFormat(format.format(date));
		
		//현재 시간과 가장 가까운 시간대 버스 구하기
		int index = 0;
		while(stationStart[index].getTotalMin() <= targetTime.getTotalMin()&&index<stationStart.length-1){
			index++;
		}

		System.out.println(stationStart[index].getTime());
	}
}

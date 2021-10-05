import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;




public class Main {

	public static String[] stationNames = {"버스관리사무소", "상공회의소", "진입로", "동부경찰서", "용인시장", "중앙주차장"};
	public static int[] stationTimes = {3, 2, 1, 3, 4, 2};
	public static DateFormat[]stationStart;


	public static int getCurrentBusTime() {
		int index = 0;

		long now = System.currentTimeMillis();
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date date = new Date(now);

		//현재시간을 비교 포멧으로 변환
		DateFormat targetTime = new DateFormat(format.format(date));

		//현재 시간과 가장 가까운 시간대 버스 구하기
		while(stationStart[index].getTotalMin() <= targetTime.getTotalMin()&&index<stationStart.length-1){
			index++;
		}


		return stationStart[index].getTotalMin();
	}

	// 현재시간에서 가장 가까운 출발대의 버스시간에 파라메터로 주어진 정류장부터 종착지까지 예상 시간을 반환한다.
	public static void getArrivalTime(String stationName) {

		int stationIndex = 0;
		int arrivalTime = 0;

		//매칭되는 정류장의 이름의 index를 구한다.
		for(int i =0; i<stationNames.length; i++) {
			if(stationNames[i].equals(stationName)) {
				stationIndex = i;
			}
		}

		//도착 시간 = 버스 출발 시간 + 현재 정류장 부터 도착지까지 걸리는 시간
		arrivalTime = getCurrentBusTime();
		for(int i =stationIndex+1; i<stationTimes.length; i++) {
			arrivalTime =+ stationTimes[i];
		}

	}
	public static void main(String[] args) throws IOException {
		CSVReader reader = new CSVReader();
		reader.read(".//csv//명지대역.csv");

		stationStart = reader.getLinesInFormat();

		System.out.println(getCurrentBusTime());
	}
}

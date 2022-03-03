import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FairBilling {

	public static void main(String[] args) {

		if (args != null && args.length > 1) {
			System.out.println("Expected only one input parameter");
		} else {
			String input = args[0];

			List<String> lsValue = new ArrayList<>();
			try {
				lsValue = Files.readAllLines(Paths.get(input));
			} catch (IOException e) {
				e.printStackTrace();
			}

			List<logRecord> lsRecord = new ArrayList<>();

			for (String item : lsValue) {

				if (ValidateData(item)) {
					String[] data = item.split(" ");
					lsRecord.add(new logRecord(data[0].trim(), data[1].trim(), data[2].trim()));
				}
			}

			Map<String, Result> userActivity = new HashMap<>();
			logRecord LastlogRecord = null;
			for (logRecord item : lsRecord) {

				Result result = userActivity.get(item.getUser());

				if (result == null) {
					int duration = 0;
					List<logRecord> lsRec = new ArrayList<>();
					if ("End".equalsIgnoreCase(item.getState())) {
						if (LastlogRecord != null) {
							String[] start = null;
							String[] end = null;

							start = LastlogRecord.getProcessTime().split(":");
							end = item.getProcessTime().split(":");
							
							duration = LocalTime.of(Integer.parseInt(end[0]), Integer.parseInt(end[1]), Integer.parseInt(end[2])).toSecondOfDay() - LocalTime.of(Integer.parseInt(start[0]), Integer.parseInt(start[1]), Integer.parseInt(start[2])).toSecondOfDay();
						}
					} else {
						lsRec.add(item);
					}

					userActivity.put(item.getUser(), new Result(item.getUser(), 1, duration, item.getState(), lsRec));
				} else {
					if ("Start".equalsIgnoreCase(item.getState())) {
						result.setSession(result.getSession() + 1);
						result.getLsLogRecord().add(item);

						userActivity.put(item.getUser(), result);
					} else if ("End".equalsIgnoreCase(item.getState())) {
						String[] start = null;
						String[] end = null;

						int duration = 0;

						if (result.getLsLogRecord() != null && result.getLsLogRecord().size() == 0) {
							start = LastlogRecord.getProcessTime().split(":");
							end = item.getProcessTime().split(":");

							duration = LocalTime.of(Integer.parseInt(end[0]), Integer.parseInt(end[1]), Integer.parseInt(end[2])).toSecondOfDay() - LocalTime.of(Integer.parseInt(start[0]), Integer.parseInt(start[1]), Integer.parseInt(start[2])).toSecondOfDay();

							result.setSession(result.getSession() + 1);
						} else {
							start = result.getLsLogRecord().get(0).getProcessTime().split(":");
							end = item.getProcessTime().split(":");

							duration = LocalTime.of(Integer.parseInt(end[0]), Integer.parseInt(end[1]), Integer.parseInt(end[2])).toSecondOfDay() - LocalTime.of(Integer.parseInt(start[0]), Integer.parseInt(start[1]), Integer.parseInt(start[2])).toSecondOfDay();

							result.getLsLogRecord().remove(0);
						}

						result.setLastState(item.getState());
						result.setTime(result.getTime() + duration);

						userActivity.put(item.getUser(), result);
					}
				}

				LastlogRecord = item;
			}

			userActivity.forEach((k, v) -> System.out.println(k + "  " + v.getSession() + "  " + v.getTime()));
		}
	}

	private static boolean ValidateData(String input) {
		String[] data = input.split(" ");
		String[] time = data[0].split(":");
		boolean result = true;

		try {
			if (Integer.valueOf(time[0].trim()) > 23) {
				System.out.println("Invalid hours, So skipped from processing " + input);
				result = false;
			}

			if (Integer.valueOf(time[1].trim()) > 59) {
				System.out.println("Invalid minutes, So skipped from processing " + input);
				result = false;
			}

			if (Integer.valueOf(time[2].trim()) > 59) {
				System.out.println("Invalid seconds, So skipped from processing " + input);
				result = false;
			}
		} catch (Exception e) {
			System.out.println("Invalid time data..." + input);
			result = false;
		}

		if (!data[2].equalsIgnoreCase("Start") && !data[2].equalsIgnoreCase("End")) {
			System.out.println("Invalid State. Expected (Start / End), So skipped from processing  " + input);
			result = false;
		}

		return result;
	}
}

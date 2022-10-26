import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class DateManager {

    private String fileName = "";
    private final ArrayList<String> sdates = new ArrayList<String>();
    private final List<LocalDate> dates = new ArrayList<LocalDate>();
    private LocalDate avrgDate;
    private LocalDate maxData;
    private LocalDate minDate;

    public ArrayList<String> getDates() {
        return sdates;
    }

    public DateManager(String fileName) {
        this.fileName = fileName;
        fetchDatesFromFile();
        avrgDate = AvrgLocalDate();

        var allDates = dates.stream().sorted().toList();
        minDate = allDates.get(0);
        maxData = allDates.get(allDates.size() - 1);

        System.out.println("Min Date: " + minDate);
        System.out.println("Max Date: " + maxData);
        System.out.println("Average Date: " + avrgDate);

        PresentWithAvrgDateUpperCase();
    }

//    String kk = tmp.replace("/", "PPP");

    private void fetchDatesFromFile() {
        try (FileReader reader = new FileReader(fileName)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                sdates.addAll(
                    GetWordsOf(scan.nextLine().toCharArray()).stream().filter(
                        word -> IsDate(word.toCharArray())
                    ).toList()
                );
            }

            dates.addAll(sdates.stream().map(date ->
                    LocalDate.parse(
                            date.replace('.', '/')
                                    .replace('-', '/'),
                            DateTimeFormatter.ofPattern("dd/MM/yyyy")
                    )
            ).toList());

            System.out.print("All dates:");
            System.out.println(sdates);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void PresentWithAvrgDateUpperCase() {
        try (FileReader reader = new FileReader(fileName)) {
            Scanner scan = new Scanner(reader);
            while (scan.hasNextLine()) {
                var line = scan.nextLine();
                for (String sdate: sdates) {
                    if (line.contains(sdate)) {
                        System.out.println(line.replace(sdate, avrgDate.toString()).toUpperCase());
                    }
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private List<String> GetWordsOf(char[] str) {
        List<String> strs = new ArrayList<String>();
        int begin = 0;
        int end = 0;

        for(int i = 0; i < str.length; i++) {
            if (str[i] == ' ')
                end = i;

            if (i == str.length - 1)
                    end = i + (str[i] != '.' ? 1 : 0);

            if (begin != end) {
                String string = new String(Arrays.copyOfRange(str, begin, end));
                strs.add(string);
                begin = ++end;
            }
        }

        return strs;
    }

    private LocalDate AvrgLocalDate() {
        double avrgDays = dates.stream().map(
                date -> date.getYear() * 365 + date.getDayOfYear()
        ).mapToDouble(numb -> numb).average().getAsDouble();

        int year = (int) (avrgDays / 365);
        int day = ((int) avrgDays) % 365;

        return LocalDate.ofYearDay(year, day);
    }

    public boolean IsDate(char[] chars) {
        if (chars.length == 10) {
            for (int i = 0; i < chars.length; i++) {
                int charNumb = ((int) chars[i]);

                if (i == 2 || i == 5) {
                    if (charNumb < 45 || charNumb > 47) {
                        return false;
                    } else {
                        continue;
                    }
                }

                if (!Character.isDigit(chars[i]))
                    return false;
            }

            return true;
        }

        return false;
    }
}

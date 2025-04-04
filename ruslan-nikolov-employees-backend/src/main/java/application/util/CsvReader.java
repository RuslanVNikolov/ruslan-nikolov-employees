package application.util;

import application.model.Job;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvReader {
    public List<Job> readJobsFromFilePath(String resourcePath) throws IOException {
        List<Job> jobs = new ArrayList<>();

        InputStream inputStream = getClass().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new IOException("Resource not found: " + resourcePath);
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine && line.trim().startsWith("EmpID")) {
                    isFirstLine = false;
                    continue;
                }
                isFirstLine = false;

                String[] tokens = line.split(",");
                if (tokens.length < 4) continue;

                String employeeId = tokens[0].trim();
                String projectId = tokens[1].trim();
                String dateFromStr = tokens[2].trim();
                String dateToStr = tokens[3].trim();

                DateTime dateFrom = parseDate(dateFromStr);
                DateTime dateTo = parseDate(dateToStr);

                Job job = new Job(employeeId, projectId, dateFrom, dateTo);
                jobs.add(job);
            }
        }
        return jobs;
    }

    public List<Job> readJobsFromInputStream(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new IOException("InputStream is null");
        }

        List<Job> jobs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            boolean isFirstLine = true;
            while ((line = br.readLine()) != null) {
                if (isFirstLine && line.trim().startsWith("EmpID")) {
                    isFirstLine = false;
                    continue;
                }
                isFirstLine = false;

                String[] tokens = line.split(",");
                if (tokens.length < 4) continue;

                String employeeId = tokens[0].trim();
                String projectId = tokens[1].trim();
                String dateFromStr = tokens[2].trim();
                String dateToStr = tokens[3].trim();

                DateTime dateFrom = parseDate(dateFromStr);
                DateTime dateTo = parseDate(dateToStr);

                Job job = new Job(employeeId, projectId, dateFrom, dateTo);
                jobs.add(job);
            }
        }
        return jobs;
    }

    private DateTime parseDate(String dateStr) {
        if ("NULL".equalsIgnoreCase(dateStr)) {
            return DateTime.now();
        }
        String[] dateFormats = {
                "yyyy-MM-dd",
                "MM/dd/yyyy",
                "dd-MM-yyyy",
                "dd/MM/yyyy",
                "yyyy/MM/dd",
                "MM-dd-yyyy",
                "dd.MM.yyyy"
        };

        for (String format : dateFormats) {
            try {
                DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
                return formatter.parseDateTime(dateStr);
            } catch (IllegalArgumentException ignored) {
            }
        }
        throw new IllegalArgumentException("Unable to parse date: " + dateStr);
    }

}
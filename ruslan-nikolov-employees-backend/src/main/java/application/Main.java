package application;


import application.model.Colleagues;
import application.model.Job;
import application.service.EmployeeService;
import application.util.CsvReader;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        CsvReader reader = new CsvReader();
        EmployeeService service = new EmployeeService();
        List<Job> jobs = reader.readJobsFromFilePath("/jobs.csv");
        System.out.println("The jobs parsed:");
        jobs.forEach(System.out::println);
        Colleagues colleagues = service.getBestEmployeePair(jobs);
        System.out.printf("Best employee pair: %s + %s%n", colleagues.getFirstEmployeeId(), colleagues.getSecondEmployeeId());
        colleagues.getProjects().forEach((project, days) -> System.out.printf("Project %s days together %s%n", project, days));
        System.out.printf("Total days together: %s", colleagues.getTotalDaysTogether());
    }
}

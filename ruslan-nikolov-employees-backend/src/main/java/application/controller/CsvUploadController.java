package application.controller;

import application.model.Colleagues;
import application.model.Job;
import application.service.EmployeeService;
import application.util.CsvReader;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/upload")
@CrossOrigin(origins = "*")
public class CsvUploadController {

    private EmployeeService employeeService;
    private CsvReader csvReader;

    @PostMapping
    public ResponseEntity<Colleagues> handleCsvUpload(@RequestParam("file") MultipartFile file) throws IOException {
            System.out.println("Received CSV file.");
            List<Job> jobs = csvReader.readJobsFromInputStream(file.getInputStream());
            Colleagues colleagues = employeeService.getBestEmployeePair(jobs);
            System.out.printf("Best employee pair: %s + %s%n", colleagues.getFirstEmployeeId(), colleagues.getSecondEmployeeId());
            colleagues.getProjects().forEach((project, days) -> System.out.printf("Project %s days together %s%n", project, days));
            System.out.printf("Total days together: %s%n", colleagues.getTotalDaysTogether());
            return ResponseEntity.ok(colleagues);
    }
}

package application.service;

import application.model.Colleagues;
import application.model.Job;
import application.model.PartnershipId;
import lombok.RequiredArgsConstructor;
import org.joda.time.Interval;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EmployeeService {

    public Colleagues getBestEmployeePair(List<Job> jobs) {
        Map<String, Colleagues> colleaguesMap = new HashMap<>();
        Map<String, List<Job>> jobsByProject = jobs.stream()
                .collect(Collectors.groupingBy(Job::getProjectId));

        for (List<Job> projectJobs : jobsByProject.values()) {
            for (int i = 0; i < projectJobs.size(); i++) {
                for (int j = i + 1; j < projectJobs.size(); j++) {
                    Job job1 = projectJobs.get(i);
                    Job job2 = projectJobs.get(j);
                    if (!job1.getEmployeeId().equals(job2.getEmployeeId())) {
                        Interval job1Interval = new Interval(job1.getDateFrom(), job1.getDateTo());
                        Interval job2Interval = new Interval(job2.getDateFrom(), job2.getDateTo());
                        Interval overlap = job1Interval.overlap(job2Interval);
                        if (overlap != null) {
                            PartnershipId pid = new PartnershipId(new HashSet<>(Arrays.asList(job1.getEmployeeId(), job2.getEmployeeId())));
                            Colleagues colleagues = colleaguesMap.getOrDefault(pid.getId(), new Colleagues(pid, new HashMap<>()));

                            int currentDays = colleagues.getProjects().getOrDefault(job1.getProjectId(), 0);
                            currentDays += overlap.toDuration().getStandardDays();
                            colleagues.getProjects().put(job1.getProjectId(), currentDays);
                            colleaguesMap.put(pid.getId(), colleagues);
                        }
                    }
                }
            }
        }

        return colleaguesMap.values().stream().max(Comparator.comparing(Colleagues::getTotalDaysTogether)).get();
    }
}

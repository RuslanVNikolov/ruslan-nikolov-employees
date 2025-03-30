package application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    private String employeeId;
    private String projectId;
    private DateTime dateFrom;
    private DateTime dateTo;
}

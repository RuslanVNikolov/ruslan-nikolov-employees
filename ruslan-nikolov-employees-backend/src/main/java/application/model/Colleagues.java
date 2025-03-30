package application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Colleagues {
    private String firstEmployeeId;
    private String secondEmployeeId;
    private Map<String, Integer> projects = new HashMap<>();
    private Integer totalDaysTogether;
}

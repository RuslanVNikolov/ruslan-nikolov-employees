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
    private PartnershipId id;
    private Map<String, Integer> projects = new HashMap<>();

    public Integer getTotalDaysTogether() {
       return projects.values().stream().mapToInt(Integer::intValue).sum();
    }
}

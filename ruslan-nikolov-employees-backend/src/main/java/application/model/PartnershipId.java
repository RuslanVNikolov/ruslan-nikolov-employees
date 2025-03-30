package application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartnershipId {
    private Set<String> employeeIds;

    public String getId() {
        return employeeIds.stream().sorted().toString();
    }
}

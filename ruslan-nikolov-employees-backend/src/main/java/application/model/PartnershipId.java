package application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartnershipId {
    private String firstEmployeeId;
    private String secondEmployeeId;

    public String getId() {
        List<String> sortedIds = Stream.of(firstEmployeeId, secondEmployeeId).sorted().collect(Collectors.toList());
        return sortedIds.get(0) + "," + sortedIds.get(1);
    }
}

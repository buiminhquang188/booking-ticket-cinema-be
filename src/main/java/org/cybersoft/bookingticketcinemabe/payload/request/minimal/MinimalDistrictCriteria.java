package org.cybersoft.bookingticketcinemabe.payload.request.minimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MinimalDistrictCriteria extends MinimalCriteria {
    private String provinceName;
}

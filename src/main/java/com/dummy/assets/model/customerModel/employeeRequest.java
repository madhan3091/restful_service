package com.dummy.assets.model.customerModel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "employee_name",
        "employee_salary",
        "employee_age"
})
@Getter
@Setter
@RequiredArgsConstructor

public class employeeRequest {
    @JsonProperty("name")
    private String Name;
    @JsonProperty("salary")
    private String Salary;
    @JsonProperty("age")
    private String Age;


}
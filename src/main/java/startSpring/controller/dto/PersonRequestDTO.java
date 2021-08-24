package startSpring.controller.dto;

import lombok.Data;

@Data
public class PersonRequestDTO {

    private String name;

    private Integer age;

    public PersonRequestDTO(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public PersonRequestDTO() {
    }
}

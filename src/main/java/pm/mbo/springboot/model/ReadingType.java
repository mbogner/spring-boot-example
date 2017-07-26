package pm.mbo.springboot.model;

import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@ToString(exclude = {"readings"})
@EqualsAndHashCode(exclude = {"readings"})
@Entity
@Table(name = "reading_types", uniqueConstraints = {
        @UniqueConstraint(name = "uc_reading_types__name", columnNames = {"name"})
})
public class ReadingType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String name;

    @OneToMany(mappedBy = "readingType")
    private List<Reading> readings;
}

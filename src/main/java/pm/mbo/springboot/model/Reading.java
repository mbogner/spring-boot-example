package pm.mbo.springboot.model;

import lombok.Data;
import lombok.Generated;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Generated
@Entity
@Table(name = "readings")
public class Reading implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private BigDecimal value;

    @ManyToOne(optional = false)
    @JoinColumn(name = "reading_type_id", nullable = false)
    private ReadingType readingType;

}

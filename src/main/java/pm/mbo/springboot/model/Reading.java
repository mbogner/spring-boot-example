package pm.mbo.springboot.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "readings")
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private BigDecimal value;

}

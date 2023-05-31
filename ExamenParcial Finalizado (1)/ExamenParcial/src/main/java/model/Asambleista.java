package model;
import org.hibernate.annotations.Check;

import javax.persistence.*;

@Entity
@Table(name = "ASAMBLEISTA_ECU")
public class Asambleista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ASAMBLEISTA_ID")
    private int asambleistaId;

    @Column(name = "CLASIFICACION", nullable = false)
    @Check(constraints = "CLASIFICACION IN ('N', 'P', 'E')")
    private char clasi;

    @OneToOne
    @JoinColumn(name = "VOTO_ID", referencedColumnName = "VOTO_ID")
    private Voto voto;

    public Asambleista() {
    }

    public void setVoto(Voto voto) {
        this.voto = voto;
    }
}


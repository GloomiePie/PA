package model;

import javax.persistence.*;

@Entity
@Table(name = "VOTO")
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VOTO_ID")
    private Integer id;

    @Column(name = "TIPO_VOTO", nullable = false)
    private char tipoVoto;


    public Voto() {
    }

    public void setTipoVoto(char tipoVoto) {
        this.tipoVoto = tipoVoto;
    }

}


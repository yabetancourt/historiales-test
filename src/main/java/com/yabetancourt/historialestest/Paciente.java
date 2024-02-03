package com.yabetancourt.historialestest;

import jakarta.persistence.*;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Table(name = "paciente", schema = "sm")
@Audited
@AuditTable(value = "historial_paciente", schema = "sm")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotAudited
    private String nombre;

    @Column(nullable = false)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    private Usuario modificadoPor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getModificadoPor() {
        return modificadoPor;
    }

    public void setModificadoPor(Usuario modificadoPor) {
        this.modificadoPor = modificadoPor;
    }
}

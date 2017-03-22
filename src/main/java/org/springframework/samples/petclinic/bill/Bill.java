package org.springframework.samples.petclinic.bill;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Digits;

import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.visit.Visit;



@Entity
public class Bill {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Digits(integer= 2, fraction = 0)
	Long id;
	
	@ManyToOne
    @JoinColumn(name="ownerBill")

    private Owner ownerBill;

	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "visitBill", cascade = CascadeType.ALL)
	private Visit visitBill;
	
	@Column(name = "fecha_pago")
	@Temporal(TemporalType.DATE)
	Date fecha;
	
	@Column(name = "cuantia")
	Long precio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getPrecio() {
		return precio;
	}

	public void setPrecio(Long precio) {
		this.precio = precio;
	}
	
	
	
}

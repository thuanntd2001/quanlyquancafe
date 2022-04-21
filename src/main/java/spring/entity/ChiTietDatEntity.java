package spring.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;



class ChiTietDatPK implements Serializable {
    private BanEntity bans;
    private DatBanEntity datBan;
	public ChiTietDatPK(BanEntity bans, DatBanEntity datBan) {
		super();
		this.bans = bans;
		this.datBan = datBan;
	}
	public ChiTietDatPK() {
		super();
		// TODO Auto-generated constructor stub
	}
}


@Entity
@IdClass(ChiTietDatPK.class)
@Table(name="CHITIETDAT")
public class ChiTietDatEntity {
	
	@Id
	@ManyToOne
	@JoinColumn(name="IDBAN")
	private BanEntity bans;

	@Id
	@ManyToOne
	@JoinColumn(name="MADAT")
	private DatBanEntity datBan;


	public BanEntity getBans() {
		return bans;
	}

	public void setBans(BanEntity bans) {
		this.bans = bans;
	}

	public DatBanEntity getDatBan() {
		return datBan;
	}

	public void setDatBan(DatBanEntity datBan) {
		this.datBan = datBan;
	}

	public ChiTietDatEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ChiTietDatEntity(BanEntity bans, DatBanEntity datBan) {
		super();
		this.bans = bans;
		this.datBan = datBan;
	}

	
	
	
}

package spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CHITIETDAT")
public class ChiTietDatEntity {
	@Id
	@GeneratedValue
	@Column(name="ID1")
	private Long id1;
	
	@ManyToOne
	@JoinColumn(name="ID")
	private BanEntity bans;

	
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

	public Long getId1() {
		return id1;
	}

	public void setId1(Long id1) {
		this.id1 = id1;
	}
	
	
}

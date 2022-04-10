package spring.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CHITIETDAT")
public class ChiTietDatEntity {
	@Id
	@ManyToOne
	@JoinColumn(name="ID")
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

	
	
}

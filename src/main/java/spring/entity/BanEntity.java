package spring.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="BAN")
public class BanEntity {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	@Column(name = "SOGHE")
	private int soGhe;
	
	@ManyToOne
	@JoinColumn(name = "LOAI")
	private LoaiBanEntity loaiBan;

	@OneToMany(mappedBy="ban", fetch = FetchType.EAGER)
	private Collection<HoaDonEntity> hoaDon;
	
	@OneToMany(mappedBy="bans", fetch = FetchType.EAGER)
	private Collection<ChiTietDatEntity> chiTietDat;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getSoGhe() {
		return soGhe;
	}

	public void setSoGhe(int soGhe) {
		this.soGhe = soGhe;
	}

	public LoaiBanEntity getLoaiBan() {
		return loaiBan;
	}

	public void setLoaiBan(LoaiBanEntity loaiBan) {
		this.loaiBan = loaiBan;
	}

	public Collection<HoaDonEntity> getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(Collection<HoaDonEntity> hoaDon) {
		this.hoaDon = hoaDon;
	}

	public Collection<ChiTietDatEntity> getChiTietDat() {
		return chiTietDat;
	}

	public void setChiTietDat(Collection<ChiTietDatEntity> chiTietDat) {
		this.chiTietDat = chiTietDat;
	}
	
}

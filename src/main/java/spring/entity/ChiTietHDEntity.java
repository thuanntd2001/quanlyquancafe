package spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="CHITIETHD")
public class ChiTietHDEntity {
	@Id
	
	
	@Column(name="SOLUONG")
	private int soLuong;
	
	@ManyToOne
	@JoinColumn(name="MASP")
	private ThucDonEntity thucDon;
	
	@ManyToOne
	@JoinColumn(name="MAHD")
	private HoaDonEntity hoaDon;

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public ThucDonEntity getThucDon() {
		return thucDon;
	}

	public void setThucDon(ThucDonEntity thucDon) {
		this.thucDon = thucDon;
	}

	public HoaDonEntity getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDonEntity hoaDon) {
		this.hoaDon = hoaDon;
	}

	
	
}

package spring.bean;

import spring.entity.HoaDonEntity;
import spring.entity.LoaiThucUongEntity;
import spring.entity.ThucDonEntity;

public class BanHoaDonModel {
	public BanHoaDonModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	private long idBan=0;
	private LoaiThucUongEntity loai;
	private ThucDonEntity thucDon;
	private HoaDonEntity hoaDon;
	public long getIdBan() {
		return idBan;
	}
	public void setIdBan(long idBan) {
		this.idBan = idBan;
	}
	public HoaDonEntity getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDonEntity hoaDon) {
		this.hoaDon = hoaDon;
	}
	public LoaiThucUongEntity getLoai() {
		return loai;
	}
	public void setLoai(LoaiThucUongEntity loai) {
		this.loai = loai;
	}
	public ThucDonEntity getThucDon() {
		return thucDon;
	}
	public void setThucDon(ThucDonEntity thucDon) {
		this.thucDon = thucDon;
	}

}

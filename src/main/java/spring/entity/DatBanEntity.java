package spring.entity;


import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="DATBAN")
public class DatBanEntity {
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	
	@Column(name="HOTEN")
	private String hoTen;
	
	@Column(name="SDT")
	private String sdt;
	
	@Column(name="TIENCOC")
	private Integer tienCoc;
	
	@Column(name="NGAYDAT")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date ngayDat;
	
	public BanEntity getBan() {
		return ban;
	}

	public void setBan(BanEntity ban) {
		this.ban = ban;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	@Column(name="TGDUKIEN")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Timestamp tgDuKien;
	

	@ManyToOne
	@JoinColumn(name="BAN")
	private BanEntity ban;


	
	@ManyToOne
	@JoinColumn(name="NVTHUCHIEN")
	private NhanVienEntity dbnv;
	
	@Column(name="TRANGTHAI")
	private int trangThai;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public Integer getTienCoc() {
		return tienCoc;
	}

	public void setTienCoc(Integer tienCoc) {
		this.tienCoc = tienCoc;
	}

	public Date getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}

	public Timestamp getTgDuKien() {
		return tgDuKien;
	}

	public void setTgDuKien(Timestamp tgDuKien) {
		this.tgDuKien = tgDuKien;
	}



	public NhanVienEntity getDbnv() {
		return dbnv;
	}

	public void setDbnv(NhanVienEntity dbnv) {
		this.dbnv = dbnv;
	}


	
	
	
}

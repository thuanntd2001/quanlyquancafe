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
@Table(name="THUCDON")
public class ThucDonEntity {
	@GeneratedValue
	@Column(name="ID1")
	private Long id1;
	@Id
	@Column(name="ID")
	private String id;
	@OneToMany(mappedBy="thucDon", fetch=FetchType.EAGER)
	private Collection<ChiTietHDEntity> chiTietHD;
	@Column(name="TEN")
	private String ten;

	@ManyToOne
	@JoinColumn(name="LOAI")
	private LoaiThucUongEntity loaiThucUong;
	
	@Column(name="GIA")
	private Integer gia;

	public Long getId1() {
		return id1;
	}

	public void setId1(Long id1) {
		this.id1 = id1;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public LoaiThucUongEntity getLoaiThucUong() {
		return loaiThucUong;
	}

	public void setLoaiThucUong(LoaiThucUongEntity loaiThucUong) {
		this.loaiThucUong = loaiThucUong;
	}

	public Integer getGia() {
		return gia;
	}

	public void setGia(Integer gia) {
		this.gia = gia;
	}

	public Collection<ChiTietHDEntity> getChiTietHD() {
		return chiTietHD;
	}

	public void setChiTietHD(Collection<ChiTietHDEntity> chiTietHD) {
		this.chiTietHD = chiTietHD;
	}
	
}

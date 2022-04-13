USE [master]
GO
/****** Object:  Database [QUANLYQUANCAFEHIGHLAND]    Script Date: 13-Apr-22 11:44:39 AM ******/
CREATE DATABASE [QUANLYQUANCAFEHIGHLAND]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'QUANLYQUANCAFEHIGHLAND', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\QUANLYQUANCAFEHIGHLAND.mdf' , SIZE = 3264KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'QUANLYQUANCAFEHIGHLAND_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\QUANLYQUANCAFEHIGHLAND_log.ldf' , SIZE = 816KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [QUANLYQUANCAFEHIGHLAND].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET ARITHABORT OFF 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET  ENABLE_BROKER 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET  MULTI_USER 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET DB_CHAINING OFF 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'QUANLYQUANCAFEHIGHLAND', N'ON'
GO
USE [QUANLYQUANCAFEHIGHLAND]
GO
/****** Object:  Table [dbo].[BAN]    Script Date: 13-Apr-22 11:44:39 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BAN](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[SOGHE] [int] NOT NULL,
	[LOAI] [bigint] NOT NULL,
 CONSTRAINT [PK__BAN__3214EC27365E0C5E] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[CHIPHI]    Script Date: 13-Apr-22 11:44:39 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CHIPHI](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[TENNL] [nvarchar](50) NOT NULL,
	[NGAYNHAP] [datetime] NOT NULL,
	[SOLUONG] [int] NOT NULL,
	[GIAMOIDV] [int] NOT NULL,
	[LOAI] [nvarchar](3) NOT NULL,
	[DV] [nvarchar](10) NOT NULL,
	[NHACUNGCAP] [nvarchar](50) NULL,
	[GHICHU] [nvarchar](50) NULL,
	[NVTAO] [bigint] NULL,
 CONSTRAINT [PK_CHIPHI] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[CHITIETDAT]    Script Date: 13-Apr-22 11:44:39 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CHITIETDAT](
	[ID] [bigint] NOT NULL,
	[MADAT] [bigint] NOT NULL,
 CONSTRAINT [PK_CHITIETDAT] PRIMARY KEY CLUSTERED 
(
	[MADAT] ASC,
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[CHITIETHD]    Script Date: 13-Apr-22 11:44:39 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[CHITIETHD](
	[MAHD] [bigint] NOT NULL,
	[MASP] [varchar](10) NOT NULL,
	[SOLUONG] [int] NOT NULL,
	[TONGTIEN] [int] NOT NULL,
 CONSTRAINT [PK] PRIMARY KEY CLUSTERED 
(
	[MAHD] ASC,
	[MASP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[CHUCVU]    Script Date: 13-Apr-22 11:44:39 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CHUCVU](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[TENCHUCVU] [nvarchar](30) NOT NULL,
 CONSTRAINT [PK__CHUCVU__006568E958DDDE19] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[DATBAN]    Script Date: 13-Apr-22 11:44:39 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[DATBAN](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[HOTEN] [nvarchar](50) NOT NULL,
	[SDT] [varchar](10) NOT NULL,
	[TIENCOC] [int] NOT NULL,
	[NGAYDAT] [date] NOT NULL,
	[TGDUKIEN] [datetime] NOT NULL,
	[NVTHUCHIEN] [bigint] NOT NULL,
 CONSTRAINT [PK__DATBAN__77CD11CE46986D6C] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[HOADON]    Script Date: 13-Apr-22 11:44:39 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HOADON](
	[ID] [bigint] IDENTITY(0,1) NOT NULL,
	[NGAYTHUCHIEN] [datetime] NOT NULL,
	[BAN] [bigint] NOT NULL,
	[NVTHUCHIEN] [bigint] NOT NULL,
	[TINHTRANG] [int] NOT NULL,
 CONSTRAINT [PK__HOADON__603F20CEFBED6B49] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[LOAIBAN]    Script Date: 13-Apr-22 11:44:39 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LOAIBAN](
	[ID] [bigint] IDENTITY(1,1) NOT NULL,
	[TENLOAI] [nvarchar](20) NOT NULL,
	[GIADAT] [int] NOT NULL,
 CONSTRAINT [PK_LOAIBAN] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[LOAITHUCUONG]    Script Date: 13-Apr-22 11:44:39 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[LOAITHUCUONG](
	[ID1] [bigint] IDENTITY(1,1) NOT NULL,
	[ID] [varchar](10) NOT NULL,
	[TENLOAI] [nvarchar](50) NOT NULL,
	[DONVI] [nvarchar](10) NOT NULL,
 CONSTRAINT [PK__LOAITHUC__3214EC27EE943000] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NHANVIEN]    Script Date: 13-Apr-22 11:44:39 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NHANVIEN](
	[MANV] [bigint] IDENTITY(1,1) NOT NULL,
	[HOTEN] [nvarchar](50) NOT NULL,
	[NGAYSINH] [date] NOT NULL,
	[GIOITINH] [bit] NOT NULL,
	[LUONG] [int] NOT NULL,
	[SDT] [varchar](10) NOT NULL,
	[CMND] [varchar](15) NOT NULL,
	[DIACHI] [nvarchar](50) NULL,
	[NGAYVAOLAM] [date] NOT NULL,
	[DANGHI] [bit] NOT NULL,
 CONSTRAINT [PK__NHANVIEN__603F5114708D8A0A] PRIMARY KEY CLUSTERED 
(
	[MANV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[THUCDON]    Script Date: 13-Apr-22 11:44:39 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[THUCDON](
	[ID1] [bigint] IDENTITY(1,1) NOT NULL,
	[ID] [varchar](10) NOT NULL,
	[TEN] [nvarchar](50) NOT NULL,
	[LOAI] [varchar](10) NOT NULL,
	[GIA] [int] NOT NULL,
 CONSTRAINT [PK__THUCDON__3214EC27AEAED7EA] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[USERTB]    Script Date: 13-Apr-22 11:44:39 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[USERTB](
	[USERNAME] [varchar](20) NOT NULL,
	[PASSWD] [varchar](50) NOT NULL,
	[ID] [bigint] NOT NULL,
	[ROLEID] [bigint] NOT NULL,
	[STATUS] [int] NOT NULL,
	[EMAIL] [nvarchar](50) NOT NULL,
	[ICON] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_USERTB_1] PRIMARY KEY CLUSTERED 
(
	[USERNAME] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[BAN] ON 

INSERT [dbo].[BAN] ([ID], [SOGHE], [LOAI]) VALUES (1, 1, 1)
INSERT [dbo].[BAN] ([ID], [SOGHE], [LOAI]) VALUES (2, 1, 1)
INSERT [dbo].[BAN] ([ID], [SOGHE], [LOAI]) VALUES (3, 1, 1)
INSERT [dbo].[BAN] ([ID], [SOGHE], [LOAI]) VALUES (4, 1, 4)
INSERT [dbo].[BAN] ([ID], [SOGHE], [LOAI]) VALUES (5, 2, 2)
INSERT [dbo].[BAN] ([ID], [SOGHE], [LOAI]) VALUES (6, 2, 2)
INSERT [dbo].[BAN] ([ID], [SOGHE], [LOAI]) VALUES (7, 2, 3)
INSERT [dbo].[BAN] ([ID], [SOGHE], [LOAI]) VALUES (8, 10, 3)
INSERT [dbo].[BAN] ([ID], [SOGHE], [LOAI]) VALUES (9, 10, 3)
INSERT [dbo].[BAN] ([ID], [SOGHE], [LOAI]) VALUES (10, 20, 5)
SET IDENTITY_INSERT [dbo].[BAN] OFF
SET IDENTITY_INSERT [dbo].[CHIPHI] ON 

INSERT [dbo].[CHIPHI] ([ID], [TENNL], [NGAYNHAP], [SOLUONG], [GIAMOIDV], [LOAI], [DV], [NHACUNGCAP], [GHICHU], [NVTAO]) VALUES (1, N'BẢO TRÌ', CAST(N'2021-01-01 00:00:00.000' AS DateTime), 0, 500000, N'VT', N'MÁY LẠNH', NULL, N'THAY ỐNG XẢ', 1)
INSERT [dbo].[CHIPHI] ([ID], [TENNL], [NGAYNHAP], [SOLUONG], [GIAMOIDV], [LOAI], [DV], [NHACUNGCAP], [GHICHU], [NVTAO]) VALUES (2, N'CÀ PHÊ RANG', CAST(N'2021-01-03 00:00:00.000' AS DateTime), 10, 30000, N'NL', N'KG', NULL, NULL, 2)
INSERT [dbo].[CHIPHI] ([ID], [TENNL], [NGAYNHAP], [SOLUONG], [GIAMOIDV], [LOAI], [DV], [NHACUNGCAP], [GHICHU], [NVTAO]) VALUES (3, N'TÁCH SỨ', CAST(N'2021-01-03 00:00:00.000' AS DateTime), 50, 20000, N'VT', N'CÁI', NULL, NULL, 2)
INSERT [dbo].[CHIPHI] ([ID], [TENNL], [NGAYNHAP], [SOLUONG], [GIAMOIDV], [LOAI], [DV], [NHACUNGCAP], [GHICHU], [NVTAO]) VALUES (4, N'TÁCH SỨ', CAST(N'2022-01-02 00:00:00.000' AS DateTime), -3, 20000, N'VT', N'CÁI', NULL, N'NV LÀM VỠ', 3)
SET IDENTITY_INSERT [dbo].[CHIPHI] OFF
INSERT [dbo].[CHITIETDAT] ([ID], [MADAT]) VALUES (1, 1)
INSERT [dbo].[CHITIETDAT] ([ID], [MADAT]) VALUES (2, 1)
INSERT [dbo].[CHITIETDAT] ([ID], [MADAT]) VALUES (3, 1)
INSERT [dbo].[CHITIETDAT] ([ID], [MADAT]) VALUES (6, 2)
INSERT [dbo].[CHITIETDAT] ([ID], [MADAT]) VALUES (7, 3)
INSERT [dbo].[CHITIETDAT] ([ID], [MADAT]) VALUES (8, 3)
INSERT [dbo].[CHITIETHD] ([MAHD], [MASP], [SOLUONG], [TONGTIEN]) VALUES (1, N'CPD', 3, 0)
INSERT [dbo].[CHITIETHD] ([MAHD], [MASP], [SOLUONG], [TONGTIEN]) VALUES (1, N'CPS', 2, 0)
SET IDENTITY_INSERT [dbo].[CHUCVU] ON 

INSERT [dbo].[CHUCVU] ([ID], [TENCHUCVU]) VALUES (1, N'ADMIN')
INSERT [dbo].[CHUCVU] ([ID], [TENCHUCVU]) VALUES (2, N'Nhan Vien Pha Che')
INSERT [dbo].[CHUCVU] ([ID], [TENCHUCVU]) VALUES (3, N'Nhan Vien Phuc Vu')
INSERT [dbo].[CHUCVU] ([ID], [TENCHUCVU]) VALUES (4, N'Nhan Vien IT')
INSERT [dbo].[CHUCVU] ([ID], [TENCHUCVU]) VALUES (5, N'Tap Vu')
INSERT [dbo].[CHUCVU] ([ID], [TENCHUCVU]) VALUES (6, N'Bao Ve')
SET IDENTITY_INSERT [dbo].[CHUCVU] OFF
SET IDENTITY_INSERT [dbo].[DATBAN] ON 

INSERT [dbo].[DATBAN] ([ID], [HOTEN], [SDT], [TIENCOC], [NGAYDAT], [TGDUKIEN], [NVTHUCHIEN]) VALUES (1, N'LE VAN GIAU', N'099999999', 2000, CAST(N'2021-12-23' AS Date), CAST(N'2022-01-01 09:00:00.000' AS DateTime), 1)
INSERT [dbo].[DATBAN] ([ID], [HOTEN], [SDT], [TIENCOC], [NGAYDAT], [TGDUKIEN], [NVTHUCHIEN]) VALUES (2, N'NHO LAI', N'345345345', 345555, CAST(N'2021-02-02' AS Date), CAST(N'2022-01-02 00:00:00.000' AS DateTime), 2)
INSERT [dbo].[DATBAN] ([ID], [HOTEN], [SDT], [TIENCOC], [NGAYDAT], [TGDUKIEN], [NVTHUCHIEN]) VALUES (3, N'KHO THI KHAN', N'918479843', 200000, CAST(N'2022-01-01' AS Date), CAST(N'2022-03-01 16:00:00.000' AS DateTime), 3)
SET IDENTITY_INSERT [dbo].[DATBAN] OFF
SET IDENTITY_INSERT [dbo].[HOADON] ON 

INSERT [dbo].[HOADON] ([ID], [NGAYTHUCHIEN], [BAN], [NVTHUCHIEN], [TINHTRANG]) VALUES (0, CAST(N'2022-01-04 18:14:33.710' AS DateTime), 1, 2, 0)
INSERT [dbo].[HOADON] ([ID], [NGAYTHUCHIEN], [BAN], [NVTHUCHIEN], [TINHTRANG]) VALUES (1, CAST(N'2022-01-04 18:15:58.967' AS DateTime), 2, 3, 0)
INSERT [dbo].[HOADON] ([ID], [NGAYTHUCHIEN], [BAN], [NVTHUCHIEN], [TINHTRANG]) VALUES (2, CAST(N'2022-01-04 18:16:16.230' AS DateTime), 3, 3, 0)
SET IDENTITY_INSERT [dbo].[HOADON] OFF
SET IDENTITY_INSERT [dbo].[LOAIBAN] ON 

INSERT [dbo].[LOAIBAN] ([ID], [TENLOAI], [GIADAT]) VALUES (1, N'THƯỜNG', 10000)
INSERT [dbo].[LOAIBAN] ([ID], [TENLOAI], [GIADAT]) VALUES (2, N'ĐÔI', 20000)
INSERT [dbo].[LOAIBAN] ([ID], [TENLOAI], [GIADAT]) VALUES (3, N'LỚN', 50000)
INSERT [dbo].[LOAIBAN] ([ID], [TENLOAI], [GIADAT]) VALUES (4, N'KHÔNG HÚT THUỐC', 20000)
INSERT [dbo].[LOAIBAN] ([ID], [TENLOAI], [GIADAT]) VALUES (5, N'VIP', 100000)
INSERT [dbo].[LOAIBAN] ([ID], [TENLOAI], [GIADAT]) VALUES (6, N'SIÊU LỚN', 100000)
SET IDENTITY_INSERT [dbo].[LOAIBAN] OFF
SET IDENTITY_INSERT [dbo].[LOAITHUCUONG] ON 

INSERT [dbo].[LOAITHUCUONG] ([ID1], [ID], [TENLOAI], [DONVI]) VALUES (1, N'B', N'BÁNH', N'CÁI')
INSERT [dbo].[LOAITHUCUONG] ([ID1], [ID], [TENLOAI], [DONVI]) VALUES (2, N'CFP', N'CÀ PHÊ PHIN', N'LY')
INSERT [dbo].[LOAITHUCUONG] ([ID1], [ID], [TENLOAI], [DONVI]) VALUES (3, N'FZ', N'FREEZE', N'LY')
INSERT [dbo].[LOAITHUCUONG] ([ID1], [ID], [TENLOAI], [DONVI]) VALUES (4, N'GH', N'GIAO HÀNG TỪ XA', N'SP')
INSERT [dbo].[LOAITHUCUONG] ([ID1], [ID], [TENLOAI], [DONVI]) VALUES (5, N'K', N'KHÁC', N'SP')
INSERT [dbo].[LOAITHUCUONG] ([ID1], [ID], [TENLOAI], [DONVI]) VALUES (6, N'NN', N'NƯỚC NGỌT', N'CHAI/LON')
INSERT [dbo].[LOAITHUCUONG] ([ID1], [ID], [TENLOAI], [DONVI]) VALUES (7, N'T', N'TRÀ', N'LY')
SET IDENTITY_INSERT [dbo].[LOAITHUCUONG] OFF
SET IDENTITY_INSERT [dbo].[NHANVIEN] ON 

INSERT [dbo].[NHANVIEN] ([MANV], [HOTEN], [NGAYSINH], [GIOITINH], [LUONG], [SDT], [CMND], [DIACHI], [NGAYVAOLAM], [DANGHI]) VALUES (1, N'NHAN', CAST(N'0445-01-04' AS Date), 1, 1000000, N'012125555 ', N'45460540654', N'11 NGUYỄN ĐÌNH CHIỂU', CAST(N'2020-01-01' AS Date), 0)
INSERT [dbo].[NHANVIEN] ([MANV], [HOTEN], [NGAYSINH], [GIOITINH], [LUONG], [SDT], [CMND], [DIACHI], [NGAYVAOLAM], [DANGHI]) VALUES (2, N'NGUYỄN LUNG LINH', CAST(N'2002-12-23' AS Date), 0, 160000, N'030815654 ', N'7084065406440', N'244 LÊ VĂN VIỆT', CAST(N'2020-02-02' AS Date), 0)
INSERT [dbo].[NHANVIEN] ([MANV], [HOTEN], [NGAYSINH], [GIOITINH], [LUONG], [SDT], [CMND], [DIACHI], [NGAYVAOLAM], [DANGHI]) VALUES (3, N'NGUYỄN TRẦN ĐỨC THUẬN', CAST(N'2001-10-03' AS Date), 1, 60000, N'030815654 ', N'7084065406440', N'55 NGUYỄN VĂN THỦ', CAST(N'2020-02-02' AS Date), 0)
INSERT [dbo].[NHANVIEN] ([MANV], [HOTEN], [NGAYSINH], [GIOITINH], [LUONG], [SDT], [CMND], [DIACHI], [NGAYVAOLAM], [DANGHI]) VALUES (4, N'ĐINH NHO NAM', CAST(N'2001-02-02' AS Date), 1, 60000, N'03453454  ', N'70840634440', N'55 NGUYỄN VĂN THỦ', CAST(N'2021-03-03' AS Date), 0)
INSERT [dbo].[NHANVIEN] ([MANV], [HOTEN], [NGAYSINH], [GIOITINH], [LUONG], [SDT], [CMND], [DIACHI], [NGAYVAOLAM], [DANGHI]) VALUES (5, N'TRẦN VỚ VẨN', CAST(N'1977-06-03' AS Date), 1, 80000, N'030815654 ', N'7084065240', N'97 MAN THIỆN', CAST(N'2022-01-01' AS Date), 0)
INSERT [dbo].[NHANVIEN] ([MANV], [HOTEN], [NGAYSINH], [GIOITINH], [LUONG], [SDT], [CMND], [DIACHI], [NGAYVAOLAM], [DANGHI]) VALUES (6, N'HUỲNH NGỌC DƯƠNG', CAST(N'2001-01-01' AS Date), 1, 60000, N'23415654  ', N'7084065406440', N'55 NGUYỄN VĂN THỦ', CAST(N'2021-06-06' AS Date), 0)
SET IDENTITY_INSERT [dbo].[NHANVIEN] OFF
SET IDENTITY_INSERT [dbo].[THUCDON] ON 

INSERT [dbo].[THUCDON] ([ID1], [ID], [TEN], [LOAI], [GIA]) VALUES (1, N'BMT', N'BÁNH MÌ THỊT', N'B', 15000)
INSERT [dbo].[THUCDON] ([ID1], [ID], [TEN], [LOAI], [GIA]) VALUES (2, N'CPD', N'Cà Phê Đen', N'CFP', 20000)
INSERT [dbo].[THUCDON] ([ID1], [ID], [TEN], [LOAI], [GIA]) VALUES (4, N'CPS', N'Cà Phê Sữa', N'CFP', 25000)
INSERT [dbo].[THUCDON] ([ID1], [ID], [TEN], [LOAI], [GIA]) VALUES (3, N'KMC', N'KEM MATCHA', N'FZ', 30000)
SET IDENTITY_INSERT [dbo].[THUCDON] OFF
INSERT [dbo].[USERTB] ([USERNAME], [PASSWD], [ID], [ROLEID], [STATUS], [EMAIL], [ICON]) VALUES (N'admin     ', N'123', 1, 1, 1, N'n19dccn203@student.ptithcm.edu.vn', N'logo_highland.png')
INSERT [dbo].[USERTB] ([USERNAME], [PASSWD], [ID], [ROLEID], [STATUS], [EMAIL], [ICON]) VALUES (N'nhanvien1 ', N'123', 2, 2, 1, N'n19dccn113@student.ptithcm.edu.vn', N'logo_highland.png')
INSERT [dbo].[USERTB] ([USERNAME], [PASSWD], [ID], [ROLEID], [STATUS], [EMAIL], [ICON]) VALUES (N'nhanvien2', N'123', 3, 3, 1, N'n19dccn043@student.ptithcm.edu.vn', N'logo_highland.png')
/****** Object:  Index [UQ__CHITIETD__C5683D3A372E9660]    Script Date: 13-Apr-22 11:44:39 AM ******/
ALTER TABLE [dbo].[CHITIETDAT] ADD UNIQUE NONCLUSTERED 
(
	[ID] ASC,
	[MADAT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__CHITIETH__563D086C67A0DCB1]    Script Date: 13-Apr-22 11:44:39 AM ******/
ALTER TABLE [dbo].[CHITIETHD] ADD UNIQUE NONCLUSTERED 
(
	[MAHD] ASC,
	[MASP] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__USERTB__161CF724576E62B2]    Script Date: 13-Apr-22 11:44:39 AM ******/
ALTER TABLE [dbo].[USERTB] ADD  CONSTRAINT [UQ__USERTB__161CF724576E62B2] UNIQUE NONCLUSTERED 
(
	[EMAIL] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__USERTB__B15BE12E7976E373]    Script Date: 13-Apr-22 11:44:39 AM ******/
ALTER TABLE [dbo].[USERTB] ADD  CONSTRAINT [UQ__USERTB__B15BE12E7976E373] UNIQUE NONCLUSTERED 
(
	[USERNAME] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[CHITIETHD] ADD  CONSTRAINT [DF_CHITIETHD_TONGTIEN]  DEFAULT ((0)) FOR [TONGTIEN]
GO
ALTER TABLE [dbo].[DATBAN] ADD  CONSTRAINT [DF__DATBAN__NGAYDAT__1CF15040]  DEFAULT (getdate()) FOR [NGAYDAT]
GO
ALTER TABLE [dbo].[HOADON] ADD  CONSTRAINT [DF__HOADON__NGAYTHUC__1FCDBCEB]  DEFAULT (getdate()) FOR [NGAYTHUCHIEN]
GO
ALTER TABLE [dbo].[HOADON] ADD  CONSTRAINT [DF_HOADON_BAN]  DEFAULT ((1)) FOR [BAN]
GO
ALTER TABLE [dbo].[HOADON] ADD  CONSTRAINT [DF_HOADON_TINHTRANG]  DEFAULT ((0)) FOR [TINHTRANG]
GO
ALTER TABLE [dbo].[NHANVIEN] ADD  CONSTRAINT [DF_NHANVIEN_GIOITINH]  DEFAULT ((1)) FOR [GIOITINH]
GO
ALTER TABLE [dbo].[NHANVIEN] ADD  CONSTRAINT [DF__NHANVIEN__NGAYVA__267ABA7A]  DEFAULT (getdate()) FOR [NGAYVAOLAM]
GO
ALTER TABLE [dbo].[NHANVIEN] ADD  CONSTRAINT [DF_NHANVIEN_DANGHI]  DEFAULT ((0)) FOR [DANGHI]
GO
ALTER TABLE [dbo].[USERTB] ADD  CONSTRAINT [DF_USERTB_EMAIL]  DEFAULT (N'n19dccn203@studen.ptithcm.edu.vn') FOR [EMAIL]
GO
ALTER TABLE [dbo].[USERTB] ADD  CONSTRAINT [DF_USERTB_ICON]  DEFAULT (N'logo_highland.png') FOR [ICON]
GO
ALTER TABLE [dbo].[BAN]  WITH CHECK ADD  CONSTRAINT [FK_BAN_LOAIBAN] FOREIGN KEY([LOAI])
REFERENCES [dbo].[LOAIBAN] ([ID])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[BAN] CHECK CONSTRAINT [FK_BAN_LOAIBAN]
GO
ALTER TABLE [dbo].[CHIPHI]  WITH CHECK ADD  CONSTRAINT [FK_CHIPHI_NHANVIEN] FOREIGN KEY([NVTAO])
REFERENCES [dbo].[NHANVIEN] ([MANV])
GO
ALTER TABLE [dbo].[CHIPHI] CHECK CONSTRAINT [FK_CHIPHI_NHANVIEN]
GO
ALTER TABLE [dbo].[CHITIETDAT]  WITH CHECK ADD  CONSTRAINT [FK__CHITIETDA__IDBAN__2E1BDC42] FOREIGN KEY([ID])
REFERENCES [dbo].[BAN] ([ID])
GO
ALTER TABLE [dbo].[CHITIETDAT] CHECK CONSTRAINT [FK__CHITIETDA__IDBAN__2E1BDC42]
GO
ALTER TABLE [dbo].[CHITIETDAT]  WITH CHECK ADD  CONSTRAINT [FK__CHITIETDA__MADAT__2F10007B] FOREIGN KEY([MADAT])
REFERENCES [dbo].[DATBAN] ([ID])
GO
ALTER TABLE [dbo].[CHITIETDAT] CHECK CONSTRAINT [FK__CHITIETDA__MADAT__2F10007B]
GO
ALTER TABLE [dbo].[CHITIETHD]  WITH CHECK ADD  CONSTRAINT [FK_CHITIETHD_HOADON] FOREIGN KEY([MAHD])
REFERENCES [dbo].[HOADON] ([ID])
GO
ALTER TABLE [dbo].[CHITIETHD] CHECK CONSTRAINT [FK_CHITIETHD_HOADON]
GO
ALTER TABLE [dbo].[CHITIETHD]  WITH CHECK ADD  CONSTRAINT [FK_CHITIETHD_THUCDON] FOREIGN KEY([MASP])
REFERENCES [dbo].[THUCDON] ([ID])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[CHITIETHD] CHECK CONSTRAINT [FK_CHITIETHD_THUCDON]
GO
ALTER TABLE [dbo].[DATBAN]  WITH CHECK ADD  CONSTRAINT [FK_DATBAN_NHANVIEN] FOREIGN KEY([NVTHUCHIEN])
REFERENCES [dbo].[NHANVIEN] ([MANV])
GO
ALTER TABLE [dbo].[DATBAN] CHECK CONSTRAINT [FK_DATBAN_NHANVIEN]
GO
ALTER TABLE [dbo].[HOADON]  WITH CHECK ADD  CONSTRAINT [FK_HOADON_BAN] FOREIGN KEY([BAN])
REFERENCES [dbo].[BAN] ([ID])
GO
ALTER TABLE [dbo].[HOADON] CHECK CONSTRAINT [FK_HOADON_BAN]
GO
ALTER TABLE [dbo].[HOADON]  WITH CHECK ADD  CONSTRAINT [FK_HOADON_NHANVIEN] FOREIGN KEY([NVTHUCHIEN])
REFERENCES [dbo].[NHANVIEN] ([MANV])
GO
ALTER TABLE [dbo].[HOADON] CHECK CONSTRAINT [FK_HOADON_NHANVIEN]
GO
ALTER TABLE [dbo].[THUCDON]  WITH CHECK ADD  CONSTRAINT [FK_THUCDON_LOAITHUCUONG] FOREIGN KEY([LOAI])
REFERENCES [dbo].[LOAITHUCUONG] ([ID])
GO
ALTER TABLE [dbo].[THUCDON] CHECK CONSTRAINT [FK_THUCDON_LOAITHUCUONG]
GO
ALTER TABLE [dbo].[USERTB]  WITH CHECK ADD  CONSTRAINT [FK_USERTB_CHUCVU] FOREIGN KEY([ROLEID])
REFERENCES [dbo].[CHUCVU] ([ID])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[USERTB] CHECK CONSTRAINT [FK_USERTB_CHUCVU]
GO
ALTER TABLE [dbo].[USERTB]  WITH CHECK ADD  CONSTRAINT [FK_USERTB_NHANVIEN] FOREIGN KEY([ID])
REFERENCES [dbo].[NHANVIEN] ([MANV])
GO
ALTER TABLE [dbo].[USERTB] CHECK CONSTRAINT [FK_USERTB_NHANVIEN]
GO
ALTER TABLE [dbo].[DATBAN]  WITH CHECK ADD  CONSTRAINT [CHECKDATE] CHECK  ((datepart(year,[NGAYDAT])>(2020)))
GO
ALTER TABLE [dbo].[DATBAN] CHECK CONSTRAINT [CHECKDATE]
GO
ALTER TABLE [dbo].[DATBAN]  WITH CHECK ADD  CONSTRAINT [NGAYDK] CHECK  ((CONVERT([date],[TGDUKIEN])>[NGAYDAT]))
GO
ALTER TABLE [dbo].[DATBAN] CHECK CONSTRAINT [NGAYDK]
GO
USE [master]
GO
ALTER DATABASE [QUANLYQUANCAFEHIGHLAND] SET  READ_WRITE 
GO

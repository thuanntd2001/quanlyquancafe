<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>

<head>
<jsp:include page="/common/admin/head.jsp" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css">
</head>
<body>

	<jsp:include page="/common/admin/header.jsp" />
	<jsp:include page="/common/admin/menubar.jsp" />
	
	<div class="container-fluid main">
        <div class="container">
            <div class="content">
                <div class="header-content d-flex justify-content-center">
                    THỐNG KÊ
                </div>
                <section class="section dashboard">

                    <!-- Left side columns -->
                    <div class="row">

                        <!-- Sales Card -->
                        <div class="col-md-4">
                            <div class="card info-card sales-card">

                                <div class="filter">
                                    <a class="icon" href="#" data-bs-toggle="dropdown"><i
                                            class="bi bi-three-dots"></i></a>
                                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                                        <li class="dropdown-header text-start">
                                            <h6>Lọc</h6>
                                        </li>

                                        <li><a class="dropdown-item" href="#">Hôm nay</a></li>
                                        <li><a class="dropdown-item" href="#">Tháng này</a></li>
                                        <li><a class="dropdown-item" href="#">Năm nay</a></li>
                                    </ul>
                                </div>

                                <div class="card-body">
                                    <h5 class="card-title">Lợi Nhuận <span>| Hôm nay</span></h5>

                                    <div class="d-flex align-items-center">
                                        <div
                                            class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                            <i class="bi bi-currency-dollar"></i>
                                        </div>
                                        <h6>2.244.000 đồng</h6>
                                    </div>
                                </div>

                            </div>
                        </div><!-- End Sales Card -->

                        <!-- Revenue Card -->
                        <div class="col-md-4">
                            <div class="card info-card revenue-card">

                                <div class="filter">
                                    <a class="icon" href="#" data-bs-toggle="dropdown"><i
                                            class="bi bi-three-dots"></i></a>
                                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow ">
                                        <li class="dropdown-header text-start">
                                            <h6>Lọc</h6>
                                        </li>

                                        <li><a class="dropdown-item" href="#">Hôm nay</a></li>
                                        <li><a class="dropdown-item" href="#">Tháng này</a></li>
                                        <li><a class="dropdown-item" href="#">Năm nay</a></li>
                                    </ul>
                                </div>

                                <div class="card-body">
                                    <h5 class="card-title">Doanh thu <span>| Tháng này</span></h5>

                                    <div class="d-flex align-items-center">
                                        <div
                                            class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                            <i class="bi bi-currency-dollar"></i>
                                        </div>
                                        <h6>3.264.444 đồng</h6>
                                    </div>
                                </div>

                            </div>
                        </div><!-- End Revenue Card -->

                        <!-- Customers Card -->
                        <div class="col-md-4">

                            <div class="card info-card customers-card">

                                <div class="filter">
                                    <a class="icon" href="#" data-bs-toggle="dropdown"><i
                                            class="bi bi-three-dots"></i></a>
                                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                                        <li class="dropdown-header text-start">
                                            <h6>Lọc</h6>
                                        </li>

                                        <li><a class="dropdown-item" href="#">Hôm nay</a></li>
                                        <li><a class="dropdown-item" href="#">Tháng này</a></li>
                                        <li><a class="dropdown-item" href="#">Năm nay</a></li>
                                    </ul>
                                </div>

                                <div class="card-body">
                                    <h5 class="card-title">Chi Phí <span>| Tháng này</span></h5>

                                    <div class="d-flex align-items-center">
                                        <div
                                            class="card-icon rounded-circle d-flex align-items-center justify-content-center">
                                            <i class="bi bi-currency-dollar"></i>
                                        </div>
                                        <h6>1.244.000 đồng</h6>
                                    </div>

                                </div>
                            </div>

                        </div><!-- End Customers Card -->

                        <!-- Reports -->
                        <div class="col-12">
                            <div class="card">

                                <div class="filter">
                                    <a class="icon" href="#" data-bs-toggle="dropdown"><i
                                            class="bi bi-three-dots"></i></a>
                                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                                        <li class="dropdown-header text-start">
                                            <h6>Lọc</h6>
                                        </li>

                                        <li><a class="dropdown-item" href="#">Hôm nay</a></li>
                                        <li><a class="dropdown-item" href="#">Tháng này</a></li>
                                        <li><a class="dropdown-item" href="#">Năm nay</a></li>
                                    </ul>
                                </div>

                                <div class="card-body">
                                    <h5 class="card-title">Thống kê <span>/Hôm nay</span></h5>

                                    <!-- Line Chart -->
                                    <div id="reportsChart"></div>

                                    <script>
                                        document.addEventListener("DOMContentLoaded", () => {
                                            new ApexCharts(document.querySelector("#reportsChart"), {
                                                series: [{
                                                    name: 'Lợi Nhuận',
                                                    data: [31, 40, 28, 51, 42, 82, 56],
                                                }, {
                                                    name: 'Doanh Thu',
                                                    data: [11, 32, 45, 32, 34, 52, 41]
                                                }, {
                                                    name: 'Chi Phí',
                                                    data: [15, 11, 32, 18, 9, 24, 11]
                                                }],
                                                chart: {
                                                    height: 350,
                                                    type: 'area',
                                                    toolbar: {
                                                        show: false
                                                    },
                                                },
                                                markers: {
                                                    size: 4
                                                },
                                                colors: ['#4154f1', '#2eca6a', '#ff771d'],
                                                fill: {
                                                    type: "gradient",
                                                    gradient: {
                                                        shadeIntensity: 1,
                                                        opacityFrom: 0.3,
                                                        opacityTo: 0.4,
                                                        stops: [0, 90, 100]
                                                    }
                                                },
                                                dataLabels: {
                                                    enabled: false
                                                },
                                                stroke: {
                                                    curve: 'smooth',
                                                    width: 2
                                                },
                                                xaxis: {
                                                    type: 'datetime',
                                                    categories: ["2018-09-19T00:00:00.000Z", "2018-09-19T01:30:00.000Z", "2018-09-19T02:30:00.000Z", "2018-09-19T03:30:00.000Z", "2018-09-19T04:30:00.000Z", "2018-09-19T05:30:00.000Z", "2018-09-19T06:30:00.000Z"]
                                                },
                                                tooltip: {
                                                    x: {
                                                        format: 'dd/MM/yy HH:mm'
                                                    },
                                                }
                                            }).render();
                                        });
                                    </script>
                                    <!-- End Line Chart -->

                                </div>

                            </div>
                        </div><!-- End Reports -->

                        <div class="col-12">
                            <div class="d-flex justify-content-center align-items-center">
                                <span class="form-check-inline">Tìm Kiếm:</span>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="flexRadioDefault"
                                        id="flexRadioDefault1">
                                    <label class="form-check-label" for="flexRadioDefault1">
                                        Theo Ngày
                                    </label>
                                </div>
                                <div class="form-check form-check-inline">
                                    <input class="form-check-input" type="radio" name="flexRadioDefault"
                                        id="flexRadioDefault2">
                                    <label class="form-check-label" for="flexRadioDefault2">
                                        Theo Tháng
                                    </label>
                                </div>
                                <div class="form-check form-check-inline    ">
                                    <input class="form-check-input" type="radio" name="flexRadioDefault"
                                        id="flexRadioDefault3">
                                    <label class="form-check-label" for="flexRadioDefault3">
                                        Theo Năm
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="col-12">
                            <div class="d-flex justify-content-center align-items-center">
                                <span>
                                    <select name="month" onchange="checkTotalDay()" class="mg-0-40">
                                        <option value="01">Tháng 1</option>
                                        <option value="02">Tháng 2</option>
                                        <option value="03">Tháng 3</option>
                                        <option value="04">Tháng 4</option>
                                        <option value="05">Tháng 5</option>
                                        <option value="06">Tháng 6</option>
                                        <option value="07">Tháng 7</option>
                                        <option value="08">Tháng 8</option>
                                        <option value="09">Tháng 9</option>
                                        <option value="10">Tháng 10</option>
                                        <option value="11">Tháng 11</option>
                                        <option value="12">Tháng 12</option>
                                    </select>
                                </span>
                                <span>
                                    <select name="day" class="mg-0-40">
                                        <option value="01">1</option>
                                        <option value="02">2</option>
                                        <option value="03">3</option>
                                        <option value="04">4</option>
                                        <option value="05">5</option>
                                        <option value="06">6</option>
                                        <option value="07">7</option>
                                        <option value="08">8</option>
                                        <option value="09">9</option>
                                        <option value="10">10</option>
                                        <option value="11">11</option>
                                        <option value="12">12</option>
                                        <option value="13">13</option>
                                        <option value="14">14</option>
                                        <option value="15">15</option>
                                        <option value="16">16</option>
                                        <option value="17">17</option>
                                        <option value="18">18</option>
                                        <option value="19">19</option>
                                        <option value="20">20</option>
                                        <option value="21">21</option>
                                        <option value="22">22</option>
                                        <option value="23">23</option>
                                        <option value="24">24</option>
                                        <option value="25">25</option>
                                        <option value="26">26</option>
                                        <option value="27">27</option>
                                        <option value="28">28</option>
                                        <option value="29">29</option>
                                        <option value="30">30</option>
                                        <option value="31">31</option>
                                    </select>
                                </span>
                                <input type="number" value="2022" name="year" onchange="checkTotalDay()"
                                    class="mg-0-40">
                                <button type="button" class="btn btn-primary">
                                    <i class="fas fa-search"></i>
                                </button>
                            </div>
                        </div>

                        <!-- Recent Sales -->
                        <div class="col-12">
                            <div class="card recent-sales overflow-auto">

                                <div class="filter">
                                    <a class="icon" href="#" data-bs-toggle="dropdown"><i
                                            class="bi bi-three-dots"></i></a>
                                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                                        <li class="dropdown-header text-start">
                                            <h6>Lọc</h6>
                                        </li>

                                        <li><a class="dropdown-item" href="#">Hôm nay</a></li>
                                        <li><a class="dropdown-item" href="#">Tháng này</a></li>
                                        <li><a class="dropdown-item" href="#">Năm nay</a></li>
                                    </ul>
                                </div>

                                <div class="card-body">
                                    <h5 class="card-title">Recent Sales <span>| Today</span></h5>

                                    <table class="table table-borderless datatable">
                                        <thead>
                                            <tr>
                                                <th scope="col">#</th>
                                                <th scope="col">Customer</th>
                                                <th scope="col">Product</th>
                                                <th scope="col">Price</th>
                                                <th scope="col">Status</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <th scope="row"><a href="#">#2457</a></th>
                                                <td>Brandon Jacob</td>
                                                <td><a href="#" class="text-primary">At praesentium minu</a></td>
                                                <td>$64</td>
                                                <td><span class="badge bg-success">Approved</span></td>
                                            </tr>
                                            <tr>
                                                <th scope="row"><a href="#">#2147</a></th>
                                                <td>Bridie Kessler</td>
                                                <td><a href="#" class="text-primary">Blanditiis dolor omnis
                                                        similique</a></td>
                                                <td>$47</td>
                                                <td><span class="badge bg-warning">Pending</span></td>
                                            </tr>
                                            <tr>
                                                <th scope="row"><a href="#">#2049</a></th>
                                                <td>Ashleigh Langosh</td>
                                                <td><a href="#" class="text-primary">At recusandae consectetur</a>
                                                </td>
                                                <td>$147</td>
                                                <td><span class="badge bg-success">Approved</span></td>
                                            </tr>
                                            <tr>
                                                <th scope="row"><a href="#">#2644</a></th>
                                                <td>Angus Grady</td>
                                                <td><a href="#" class="text-primar">Ut voluptatem id earum et</a>
                                                </td>
                                                <td>$67</td>
                                                <td><span class="badge bg-danger">Rejected</span></td>
                                            </tr>
                                            <tr>
                                                <th scope="row"><a href="#">#2644</a></th>
                                                <td>Raheem Lehner</td>
                                                <td><a href="#" class="text-primary">Sunt similique distinctio</a>
                                                </td>
                                                <td>$165</td>
                                                <td><span class="badge bg-success">Approved</span></td>
                                            </tr>
                                        </tbody>
                                    </table>

                                </div>

                            </div>
                        </div><!-- End Recent Sales -->

                        <!-- Top Selling -->
                        <div class="col-12">
                            <div class="card top-selling overflow-auto">

                                <div class="filter">
                                    <a class="icon" href="#" data-bs-toggle="dropdown"><i
                                            class="bi bi-three-dots"></i></a>
                                    <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow">
                                        <li class="dropdown-header text-start">
                                            <h6>Lọc</h6>
                                        </li>

                                        <li><a class="dropdown-item" href="#">Hôm nay</a></li>
                                        <li><a class="dropdown-item" href="#">Tháng này</a></li>
                                        <li><a class="dropdown-item" href="#">Năm nay</a></li>
                                    </ul>
                                </div>

                                <div class="card-body pb-0">
                                    <h5 class="card-title">Top Selling <span>| Today</span></h5>

                                    <table class="table table-borderless">
                                        <thead>
                                            <tr>
                                                <th scope="col">Preview</th>
                                                <th scope="col">Product</th>
                                                <th scope="col">Price</th>
                                                <th scope="col">Sold</th>
                                                <th scope="col">Revenue</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <th scope="row"><a href="#"><img src="assets/img/product-1.jpg"
                                                            alt=""></a>
                                                </th>
                                                <td><a href="#" class="text-primary fw-bold">Ut inventore ipsa
                                                        voluptas
                                                        nulla</a></td>
                                                <td>$64</td>
                                                <td class="fw-bold">124</td>
                                                <td>$5,828</td>
                                            </tr>
                                            <tr>
                                                <th scope="row"><a href="#"><img src="assets/img/product-2.jpg"
                                                            alt=""></a>
                                                </th>
                                                <td><a href="#" class="text-primary fw-bold">Exercitationem
                                                        similique
                                                        doloremque</a></td>
                                                <td>$46</td>
                                                <td class="fw-bold">98</td>
                                                <td>$4,508</td>
                                            </tr>
                                            <tr>
                                                <th scope="row"><a href="#"><img src="assets/img/product-3.jpg"
                                                            alt=""></a>
                                                </th>
                                                <td><a href="#" class="text-primary fw-bold">Doloribus nisi
                                                        exercitationem</a></td>
                                                <td>$59</td>
                                                <td class="fw-bold">74</td>
                                                <td>$4,366</td>
                                            </tr>
                                            <tr>
                                                <th scope="row"><a href="#"><img src="assets/img/product-4.jpg"
                                                            alt=""></a>
                                                </th>
                                                <td><a href="#" class="text-primary fw-bold">Officiis quaerat sint
                                                        rerum
                                                        error</a></td>
                                                <td>$32</td>
                                                <td class="fw-bold">63</td>
                                                <td>$2,016</td>
                                            </tr>
                                            <tr>
                                                <th scope="row"><a href="#"><img src="assets/img/product-5.jpg"
                                                            alt=""></a>
                                                </th>
                                                <td><a href="#" class="text-primary fw-bold">Sit unde debitis
                                                        delectus
                                                        repellendus</a></td>
                                                <td>$79</td>
                                                <td class="fw-bold">41</td>
                                                <td>$3,239</td>
                                            </tr>
                                        </tbody>
                                    </table>

                                </div>

                            </div>
                        </div><!-- End Top Selling -->

                    </div>

                </section>
            </div>

        </div>

    </div>
	
	<jsp:include page="/common/admin/footer.jsp" />
	
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
    <script src="<c:url value='/template/web/scipts.js'/>"></script>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Histogram</title>
    <link href="https://cdn.jsdelivr.net/npm/bootswatch@5.0.2/dist/darkly/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <style>
        .card {
            border-radius: 0 !important;
        }
    </style>
</head>

<body class="bg-dark">
<main>
    <c:if test="${success != null}">
        <div class="alert alert-success" role="alert" id="success">
            <c:out value="${success}"></c:out>
        </div>
    </c:if>

    <c:if test="${error != null}">
        <div class="alert alert-danger" role="alert" id="error">
            <c:out value="${error}"></c:out>
        </div>
    </c:if>

    <div class="container py-4">
        <header class="pb-3 mb-4 border-bottom">
            <h1 class="fs-4 text-light text-decoration-none">Histogram</h1>
        </header>

        <div class="row mb-4 align-items-md-stretch">
            <div class="col-md-6">
                <div class="h-100 p-5 card shadow bg-secondary">
                    <div class="card-body">
                        <h2>New Student</h2>
                        <form action="<c:url value="/students/create"/>" method="post">
                            <div class="mb-3">
                                <label class="form-label">Marks</label>
                                <input type="number" class="form-control" name="marks" max="100" min="1">
                            </div>
                            <button type="submit" class="btn btn-success">Submit</button>
                        </form>
                    </div>
                </div>
            </div>

            <div class="col-md-6">
                <div class="h-100 p-5 card shadow bg-secondary">
                    <div class="card-body">
                        <h2>Upload Marks File</h2>
                        <form method="POST" enctype="multipart/form-data" multiple="false" action="<c:url value="/files/upload"/>">
                            <div class="mb-3">
                                <br />
                                <input class="form-control" name="file" type="file">
                            </div>
                            <button type="submit" class="btn btn-success">Upload</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="p-5 card shadow bg-secondary" id="chart">
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/apexcharts"></script>
    <script>
        $(".alert").delay(4000).slideUp(200, function() {
            $(this).alert('close');
        });
    </script>
    <script th:inline="javascript">
        const options = {
            series: [{
                data: ${histogram.getData()}
            }],
            chart: {
                type: 'bar',
                height: 350,
                foreColor: '#FFF'
            },
            plotOptions: {
                bar: {
                    borderRadius: 4,
                    horizontal: true,
                }
            },
            dataLabels: {
                enabled: false
            },
            xaxis: {
                categories: [${histogram.getLabels()}],
            }
        };

        const chart = new ApexCharts(document.querySelector("#chart"), options);
        chart.render();
    </script>
</main>
</body>
</html>
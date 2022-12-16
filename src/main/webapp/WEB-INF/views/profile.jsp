<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Profile</title>

  <script src="<c:url value="/resources/js/write_random_quote.js"/>"></script>

  <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
</head>
<body>
  <%@include file="parts/_nav.jsp"%>

  <c:set var="profile" value="${sessionScope.profile}"></c:set>
  <c:set var="addresses" value="${requestScope.addresses}"></c:set>
  <c:set var="products" value="${requestScope.productsInOrder}"></c:set>

  <section>
    <div class="container py-5">
      <div class="row">
        <div class="col-lg-4">
          <div class="card mb-5 mt-5 shadow-lg">
            <div class="card-body text-center">
              <img src="<c:url value="/resources/images/default_avatar.jpg"/>" class="rounded-circle" style="width: 150px;">
              <h5 class="mt-3 mb-1"><c:out value="${profile.firstName}"></c:out> <c:out value="${profile.secondName}"/></h5>
              <p class="text-muted mb-3"><c:out value="${profile.email}"></c:out></p>
              <div class="d-flex justify-content-center mb-2">
                <a href="<c:url value="/signout"/>" class="btn btn-outline-dark">Sign out</a>
              </div>
            </div>
          </div>
        </div>
        <div class="col-lg-8">
          <div class="container">
            <h5 class="mt-5 col-md-6 offset-md-5">Order History</h5>
          </div>
          <table class="table table-hover mt-3 ms-4">
            <thead class="thead">
              <tr>
                <th class="table-dark text-center" scope="col">Id</th>
                <th class="table-dark text-center" scope="col">Date</th>
                <th class="table-dark text-center" scope="col">Price</th>
                <th class="table-dark text-center" scope="col">Delivery Address</th>
                <th class="table-dark text-center" scope="col">Products</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${requestScope.orders}" varStatus="status">
              <tr>
                <th class="text-center" scope="row">${order.id}</th>
                  <td class="text-center">${order.date}</td>
                  <td class="text-center">${order.price} ₽</td>
                  <td class="text-center">${addresses[status.index]}</td>
                  <td class="text-center">${products[status.index]}</td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </section>
</body>
</html>

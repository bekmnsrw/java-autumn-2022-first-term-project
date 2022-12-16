<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Cart</title>

  <script src="<c:url value="/resources/js/write_random_quote.js"/>"></script>

  <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
  <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/all.js"></script>

  <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
</head>
<body>
  <%@include file="parts/_nav.jsp"%>
  <c:set var="amount" value="${requestScope.amount}"></c:set>
  <section class="h-100">
    <div class="container h-100 py-5 mt-5">
      <div class="row d-flex justify-content-center align-items-center h-100">
        <div class="col-10">
          <div class="d-flex justify-content-between align-items-center mb-5">
            <h3 class="fw-bold mb-0 text-dark">Shopping Cart</h3>
            <h6 class="mb-0 text-muted me-2">
              <c:out value="Items: ${sessionScope.count}"></c:out>
            </h6>
          </div>
          <c:forEach var="product" items="${requestScope.productsInCart}" varStatus="status">
            <div class="card rounded-3 mb-4 shadow-lg">
              <div class="card-body p-4">
                <div class="row d-flex justify-content-between align-items-center">
                  <div class="col-md-2 col-lg-2 col-xl-2">
                    <img src="<c:url value="/upload-image/${product.id}"/>" class="img-fluid rounded-3">
                  </div>
                  <div class="col-md-3 col-lg-3 col-xl-3">
                    <p class="lead fw-normal mb-2">${product.name}</p>
                    <h6 class="text-muted">${product.category}</h6>
                  </div>

                  <div class="col-md-3 col-lg-3 col-xl-2 d-flex">
                    <form method="post">
                      <button name="btnPlus"
                              id="buttonPlus"
                              value="${product.id}"
                              class="btn btn-link px-2">
                        <i class="fas fa-plus text-muted"></i>
                      </button>
                      <h6 class="text-center text-dark">${amount[status.index]}</h6>
                      <button name="btnMinus"
                              id="buttonMinus"
                              value="${product.id}"
                              class="btn btn-link px-2">
                        <i class="fas fa-minus text-muted"></i>
                      </button>
                    </form>
                  </div>

                  <div class="col-md-3 col-lg-2 col-xl-2 offset-lg-1">
                    <h5 class="mb-0">${product.price * amount[status.index]} ₽</h5>
                  </div>
                  <div class="col-md-1 col-lg-1 col-xl-1 text-end me-2">
                    <form method="post">
                      <button type="submit" class="btn btn-outline-danger" name="deleteFromCart" value="${product.id}">Delete</button>
                    </form>
                  </div>
                </div>
              </div>
            </div>
          </c:forEach>
          <c:if test="${requestScope.productsInCart.size() > 0}">
            <div class="container mt-5">
              <div class="row">
                <div class="col text-center">
                  <form method="get" action="<c:url value="/order"/>">
                    <button name="toOrder" value="OK" class="btn btn-outline-dark mt-3 mb-5">Proceed to Pay</button>
                  </form>
                </div>
              </div>
            </div>
          </c:if>
        </div>
      </div>
    </div>
</section>
</body>
</html>

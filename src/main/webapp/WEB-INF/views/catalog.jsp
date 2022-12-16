<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Catalog</title>

  <script src="<c:url value="/resources/js/write_random_quote.js"/>"></script>

  <script src="<c:url value="/resources/js/write_random_quote.js"/>"></script>

  <link rel="stylesheet" href="<c:url value="/resources/css/image_scale.css"/>">
  <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
</head>
<body>
  <%@include file="parts/_nav.jsp"%>

  <form method="get">
    <div class="form-label col-sm-2 end-0" style="margin-top: 70px; margin-left: 10px">
      <select id="productFilter" name="productFilter" class="form-select">
        <option value="none">None</option>
        <option value="ascending">Price ascending</option>
        <option value="descending">Price descending</option>
        <option value="figures">Figures</option>
        <option value="manga">Manga</option>
        <option value="snacks">Snacks</option>
      </select>
    </div>
    <button type="submit" class="btn btn-outline-dark" style="margin-left: 10px">Filter</button>
  </form>

  <div class="container">
    <div class="row mt-1 ms-1">
      <c:forEach var="product" items="${requestScope.products}">
        <div class="col-md-3 my-3">
          <div class="card" style="width: 18rem;">
            <div class="scale">
              <img src="<c:url value="/upload-image/${product.id}"/>" class="card-img-top">
            </div>
            <div class="card-body">
              <h5 class="card-title">${product.name}</h5>
              <h6 class="card-text">${product.category}</h6>
              <br>
              <h6 class="card-text text-muted">${product.description}</h6>
              <br>
              <h5 class="card-text">${product.price} ₽</h5>
              <div class="d-grid gap-2 d-md-block">
                <form method="post">
                  <button type="submit" class="btn btn-outline-dark" name="addToCart" value="${product.id}">Add to cart</button>
                  <c:if test="${sessionScope.role == true}">
                    <button type="submit" name="deleteProduct" value="${product.id}" class="btn btn-outline-danger">Delete</button>
                  </c:if>
                </form>
              </div>
            </div>
          </div>
        </div>
      </c:forEach>
    </div>
  </div>
</body>
</html>

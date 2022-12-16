<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Add Product</title>

  <script src="<c:url value="/resources/js/write_random_quote.js"/>"></script>

  <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
</head>
<body>
  <%@include file="../parts/_nav.jsp"%>

  <section class="text-center mt-5">
    <div class="card mx-4 mx-md-5 shadow-lg p-2 mt-5" style="margin-top: 100px; background: hsla(0, 0%, 100%, 0.8); backdrop-filter: blur(30px);">
      <div class="card-body py-5 px-md-5 mt-5">
        <div class="row d-flex justify-content-center">
          <div class="col-lg-5">
            <h2 class="fw-bold mb-5">Add Product</h2>
            <form method="post" enctype="multipart/form-data">
              <div class="form-floating mb-4">
                <input type="text"
                       name="productName"
                       id="productName"
                       class="form-control"
                       placeholder="Name"
                       required />
                <label class="form-label" for="productName">Name</label>
              </div>
              <div class="form-floating mb-4">
                <input type="text"
                       name="productDescription"
                       id="productDescription"
                       class="form-control"
                       placeholder="Description"
                       required />
                <label class="form-label" for="productDescription">Description</label>
              </div>
              <div class="form-floating mb-4">
                <input type="number"
                       name="productPrice"
                       id="productPrice"
                       class="form-control"
                       placeholder="Price"
                       required />
                <label class="form-label" for="productPrice">Price</label>
              </div>
              <div class="form-label mb-4">
                <label for="productCategory" class="form-label">Category</label>
                <select id="productCategory" name="productCategory" class="form-select">
                  <option>Figures</option>
                  <option>Manga</option>
                  <option>Snacks</option>
                </select>
              </div>
              <input type="file"
                     name="productImage"
                     class="file"
                     required />
              <p></p>
              <button type="submit" class="btn btn-outline-dark btn-block mt-4">
                Add Product
              </button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </section>
</body>
</html>

<nav class="navbar navbar-dark navbar-expand-lg fixed-top bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand">
      <img src="<c:url value="/resources/images/logo.svg"/>" alt="Logo" width="30" height="30" class="d-inline-block align-text-top">
      AniStore
    </a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <c:if test="${sessionScope.profile != null}">
          <li class="nav-item">
            <a class="nav-link" href="<c:url value="/about"/>">About</a>
          </li>
          <li class="nav-item ms-2">
            <a class="nav-link" href="<c:url value="/catalog"/>">Catalog</a>
          </li>
          <li class="nav-item ms-2">
            <a class="nav-link position-relative" href="<c:url value="/cart"/>">Cart
              <c:if test="${sessionScope.count > 0}">
                <span class="position-absolute mt-2 top-0 start-100 translate-middle badge rounded-pill bg-danger">
                  <c:out value="${sessionScope.count}"></c:out>
                </span>
              </c:if>
            </a>
          </li>
          <li class="nav-item ms-2">
            <a class="nav-link" href="<c:url value="/profile"/>">Profile</a>
          </li>
          <c:if test="${sessionScope.role == true}">
            <li class="nav-item ms-2">
              <a class="nav-link" href="<c:url value="/add-product"/>">Add Product</a>
            </li>
          </c:if>
          <c:if test="${sessionScope.role == true}">
            <li class="nav-item ms-2">
              <a class="nav-link" href="<c:url value="/update-product"/>">Update Product</a>
            </li>
          </c:if>
        </c:if>
      </ul>
      <span class="navbar-text text-white">
        <script type="text/javascript">writeRandomQuote();</script>
      </span>
    </div>
  </div>
</nav>

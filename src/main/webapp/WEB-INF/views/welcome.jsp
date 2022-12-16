<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Welcome</title>

  <script src="<c:url value="/resources/js/write_random_quote.js"/>"></script>

  <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
</head>
<body class="class=d-flex flex-column min-vh-100">
  <%@include file="parts/_nav.jsp"%>
  <div class="cover-container d-flex mx-auto my-auto flex-column text-center text-black">
    <main class="position-absolute top-50 start-50 translate-middle">
      <h1>Welcome to Anistore, <span class="text-muted">Dear Guest!</span></h1>
      <p class="lead">
        GIVE YOURSELF A UNIQUE GIFT
      </p>
      <p class="lead">
        <a href="<c:url value="/about"/>" class="btn btn-outline-dark">Explore</a>
      </p>
    </main>
    <footer class="bg-dark text-center text-white fixed-bottom">
      <div class="container pt-2 pb-2">
        <div class="text-center text-white-50">© 2022 Copyright: @bekmnsrw</div>
      </div>
    </footer>
  </div>
</body>
</html>

<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>About</title>

  <script src="<c:url value="/resources/js/write_random_quote.js"/>"></script>

  <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
  <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/all.js"></script>

  <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
  <link rel="stylesheet" href="<c:url value="/resources/css/carousel.css"/>">
</head>
<body>
  <%@include file="parts/_nav.jsp"%>
  <main>
    <div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
      <div class="carousel-indicators">
        <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
      </div>
      <div class="carousel-inner">
        <div class="carousel-item active">
          <svg class="bd-placeholder-img" width="100%" height="100%" aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false">
            <img src="<c:url value="/resources/images/figures.jpeg"/>">
          </svg>
          <div class="container">
            <div class="carousel-caption text-start text-dark">
              <h1>FIGURES</h1>
              <p>COLLECT THEM ALL!</p>
            </div>
          </div>
        </div>
        <div class="carousel-item">
          <svg class="bd-placeholder-img" width="100%" height="100%" aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false">
          <img src="<c:url value="/resources/images/manga.jpg"/>">
          </svg>
          <div class="container">
            <div class="carousel-caption text-dark">
              <h1>MANGA</h1>
              <p>ONLY BESTSELLERS!</p>
            </div>
          </div>
        </div>
        <div class="carousel-item">
          <svg class="bd-placeholder-img" width="100%" height="100%" aria-hidden="true" preserveAspectRatio="xMidYMid slice" focusable="false">
            <img src="<c:url value="/resources/images/snacks.jpg"/>">
          </svg>
          <div class="container">
            <div class="carousel-caption text-end text-dark">
              <h1>SNACKS</h1>
              <p>ARE YOU HUNGRY?</p>
            </div>
          </div>
        </div>
      </div>
      <button class="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button class="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
    <div class="container marketing">
      <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading text-center pb-3">What is AniStore? <span class="text-muted">Let's get acquainted.</span></h2>
          <p class="lead text-center">
            AniStore is an online store for fans of Japanese culture and everything related to it.
          </p>
          <p class="lead text-center">
            However, even if you're not familiar with this subculture, we're sure you'll still find something that will attract your attention!
          </p>
          <p class="lead text-center">
            So, go ahead! It's time to immerse yourself into this fascinating world!
          </p>
        </div>
        <div class="col-md-5">
          <img class="rounded float-end" src="<c:url value="/resources/images/about.jpg"/>">
        </div>
      </div>
      <hr class="featurette-divider">
      <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading text-center pb-3">A huge variety of goods. <span class="text-muted">See for yourself.</span></h2>
          <p class="lead text-center">
            You can choose more than 1.000 goods to entertain yourself!
            We're sure you'll definitely find a thing that fits you perfectly!
          </p>
          <p class="lead text-center">
            Just enjoy shopping :)
          </p>
        </div>
        <div class="col-md-5">
          <img class="rounded float-end" src="<c:url value="/resources/images/store.jpg"/>">
        </div>
      </div>
      <hr class="featurette-divider">
      <div class="row featurette">
        <div class="col-md-7">
          <h2 class="featurette-heading text-center pb-3">Purchases & Delivery. <span class="text-muted">Fast & Simple.</span></h2>
          <p class="lead text-center">
            To make an order you only have to add goods you like to cart, specify the delivery address and pay!
            After that our courier will deliver the package directly into your hands within five days!
          </p>
          <p class="lead text-center">
            Quite simple, isn't it?
          </p>
        </div>
        <div class="col-md-5">
          <img class="rounded float-end" src="<c:url value="/resources/images/delivery.png"/>">
        </div>
      </div>
      <hr class="featurette-divider">
    </div>
  </main>

  <h2 class="featurette-heading text-center fw-normal">So, what are you waiting for? <span class="text-muted">Let's start!</span></h2>
  <div class="d-flex justify-content-center mb-5">
    <c:if test="${sessionScope.profile == null}">
      <a href="<c:url value="/signin"/>" class="btn btn-outline-dark mt-3 mb-5">Start</a>
    </c:if>
    <c:if test="${sessionScope.profile != null}">
      <a href="<c:url value="/profile"/>" class="btn btn-outline-dark mt-3 mb-5">Start</a>
    </c:if>
  </div>

  <footer class="text-center text-white position-absolute start-0 end-0 bg-dark">
    <div class="text-center text-white bg-dark p-3">
      Contact us
    </div>
    <div class="container">
      <section class="mb-4">
        <a class="btn btn-floating btn-outline-light m-1" href="https://vk.com/bekmnsrw" role="button" data-mdb-ripple-color="dark">
          <i class="fab fa-vk"></i>
        </a>
        <a class="btn btn-floating btn-outline-light m-1" href="https://t.me/bekmnsrw" role="button" data-mdb-ripple-color="dark">
          <i class="fab fa-telegram"></i>
        </a>
      </section>
    </div>
  </footer>
</body>
</html>

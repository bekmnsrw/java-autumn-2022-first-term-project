<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Sign Up</title>

  <script src="<c:url value="/resources/js/write_random_quote.js"/>"></script>

  <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>

  <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
</head>
<body>
  <%@include file="../parts/_nav.jsp"%>
  <section class="text-center">
    <div class="p-5 bg-image" style="background-image: url(<c:url value="/resources/images/signin.png"/>); height: 350px"></div>
    <div class="card mx-4 mx-md-5 shadow-lg p-2 mb-5" style="margin-top: -100px; background: hsla(0, 0%, 100%, 0.8); backdrop-filter: blur(30px);">
      <div class="card-body py-5 px-md-5">
        <div class="row d-flex justify-content-center">
          <div class="col-lg-5">
            <h2 class="fw-bold mb-5">Sign up</h2>
            <form method="post">
              <div class="form-floating mb-4">
                <input type="text" name="signUpFirstName" id="signUpFirstName" class="form-control" placeholder="First Name" required />
                <label class="form-label" for="signUpFirstName">First name</label>
              </div>
              <div class="form-floating mb-4">
                <input type="text" name="signUpSecondName" id="signUpSecondName" class="form-control" placeholder="Second Name" required />
                <label class="form-label" for="signUpSecondName">Second name</label>
              </div>
              <div class="form-floating mb-4">
                <input type="email" name="signUpEmail" id="signUpEmail" class="form-control" placeholder="Email" required />
                <label class="form-label" for="signUpEmail">Email</label>
              </div>
              <div class="form-floating mb-4">
                <input type="password" name="signUpPassword" id="signUpPassword" class="form-control" placeholder="Password" required />
                <label class="form-label" for="signUpPassword">Password</label>
              </div>
              <button type="submit" class="btn btn-outline-dark btn-block mb-4">
                Sign up
              </button>
            </form>
            <div class="text-center text-lg-center">
              <p class="small fw-bold">Already have an account?
                <a href="<c:url value="/signin"/>" class="link-danger">Sign In</a>
              </p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
</body>
</html>

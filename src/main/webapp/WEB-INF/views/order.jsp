<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Order</title>

  <script src="<c:url value="/resources/js/write_random_quote.js"/>"></script>

  <script src="<c:url value="/resources/js/bootstrap.min.js"/>"></script>
  <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/all.js"></script>

  <link rel="stylesheet" href="<c:url value="/resources/css/bootstrap.min.css"/>">
</head>
<body>
<%@include file="parts/_nav.jsp"%>

<section class="h-100 gradient-custom">
  <div class="container py-5">
    <div class="row d-flex justify-content-center my-4">
      <div class="col-md-8">
        <form method="post">
          <div class="card mb-4 shadow-lg">
            <div class="card-header py-3">
              <h5 class="mb-0 text-dark">Payment</h5>
            </div>

            <div class="card-body">
              <div class="form-floating mb-4">
                <input type="text"
                       pattern="^[0-9]*$"
                       name="cardNumber"
                       id="cardNumber"
                       class="form-control"
                       placeholder="Card Number"
                       minlength="16"
                       maxlength="16"
                       required />
                <label class="form-label" for="cardNumber">Card Number</label>
              </div>

              <div class="form-floating mb-4">
                <input type="text"
                       name="cardholderName"
                       id="cardholderName"
                       placeholder="Cardholder's Name"
                       class="form-control"
                       required />
                <label class="form-label" for="cardholderName">Cardholder's Name</label>
              </div>

              <div class="row">
                <div class="col-md-6">
                  <div class="form-floating ">
                    <input type="text"
                           pattern="^[0_9]{2}/[0_9]{2}$"
                           id="expiration"
                           name="expiration"
                           class="form-control"
                           placeholder="Expiration"
                           minlength="5"
                           maxlength="5"
                           required />
                    <label class="form-label" for="expiration">Expiration</label>
                  </div>
                </div>

                <div class="col-md-6">
                  <div class="form-floating">
                    <input type="password"
                           id="cvv"
                           class="form-control"
                           placeholder="CVV"
                           minlength="3"
                           maxlength="3"
                           required />
                    <label class="form-label" for="cvv">CVV</label>
                  </div>
                </div>
              </div>
            </div>
          </div>

          <div class="card mb-4 shadow-lg">
            <div class="card-header py-3">
              <h5 class="mb-0 text-dark">Delivery Address</h5>
            </div>

            <div class="card-body">
              <div class="form-floating mb-4">
                <input type="text"
                       name="deliveryCity"
                       id="deliveryCity"
                       class="form-control"
                       placeholder="City"
                       required />
                <label class="form-label" for="cardNumber">City</label>
              </div>

              <div class="form-floating mb-4">
                <input type="text"
                       name="deliveryStreet"
                       id="deliveryStreet"
                       placeholder="Street"
                       class="form-control"
                       required />
                <label class="form-label" for="cardholderName">Street</label>
              </div>

              <div class="form-floating mb-4">
                <input type="text"
                       name="deliveryHome"
                       id="deliveryHome"
                       placeholder="Home"
                       class="form-control"
                       required />
                <label class="form-label" for="cardholderName">Home</label>
              </div>

              <div class="form-floating mb-5">
                <input type="text"
                       name="deliveryFlat"
                       id="deliveryFlat"
                       placeholder="Flat"
                       class="form-control" />
                <label class="form-label" for="cardholderName">Flat</label>
              </div>
              <div class="container">
                <div class="row">
                  <div class="col text-center">
                    <button type="submit" class="btn btn-outline-dark">Make order</button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </form>
      </div>

    <div class="col-md-4">
      <div class="card mb-4 shadow-lg">
        <div class="card-header py-3">
          <h5 class="mb-0">Summary</h5>
        </div>
        <div class="card-body">
          <ul class="list-group list-group-flush">
            <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">
              Amount
              <span><c:out value="${requestScope.totalOrderAmount} items"></c:out></span>
            </li>
            <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">
              Total Price
              <span><c:out value="${requestScope.totalOrderPrice} ₽"></c:out></span>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>
</section>
</body>
</html>

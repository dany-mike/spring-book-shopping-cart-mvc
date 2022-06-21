$(document).ready(function () {
  $(".minusButton").on("click", function (e) {
    e.preventDefault();
    decreaseQuantity($(this));
  });

  $(".plusButton").on("click", function (e) {
    e.preventDefault();
    increaseQuantity($(this));
  });

  $(".link-remove").on("click", function (e) {
    e.preventDefault();
    removeFromCart($(this));
  });
  updateTotal();
});

function removeFromCart(link) {
  url = "http://localhost:9000" + link.attr("href");
  console.log(url);

  $.ajax({
    type: "POST",
    url: url,
  })
    .done(function (response) {
      if (response.includes("removed")) {
        alert(response);
        location.reload();
      }
    })
    .fail(function (err) {
      alert("Error while adding the product to shopping cart");
    });
}

function decreaseQuantity(link) {
  productId = link.attr("pid");
  qtyInput = $("#quantity" + productId);

  newQty = parseInt(qtyInput.val()) - 1;
  if (newQty > 0) {
    qtyInput.val(newQty);
    updateQuantity(productId, newQty);
  }
}

function increaseQuantity(link) {
  productId = link.attr("pid");
  qtyInput = $("#quantity" + productId);

  newQty = parseInt(qtyInput.val()) + 1;
  if (newQty <= 8) {
    qtyInput.val(newQty);
    updateQuantity(productId, newQty);
  }
}

function updateQuantity(bookId, quantity) {
  quantity = $("#quantity" + bookId).val();

  url = "http://localhost:9000/cart/update/" + bookId + "/" + quantity;

  $.ajax({
    type: "POST",
    url: url,
  })
    .done(function (newSubtotal) {
      updateSubtotal(newSubtotal, bookId);
      updateTotal();
    })
    .fail(function (err) {
      alert("Error while adding the product to shopping cart");
    });
}

function updateSubtotal(newSubtotal, productId) {
  $("#subtotal" + productId).text(newSubtotal);
}

function updateTotal() {
  total = 0.0;

  $(".productSubtotal").each(function (i, el) {
    total = total + parseFloat(el.innerHTML);
  });

  $("#totalAmount").text("Total amount " + total + "€");
}

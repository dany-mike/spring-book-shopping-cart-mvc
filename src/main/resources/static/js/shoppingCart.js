$(document).ready(function () {
  $(".minusButton").on("click", function (e) {
    e.preventDefault();
    decreaseQuantity($(this));
  });

  $(".plusButton").on("click", function (e) {
    e.preventDefault();
    increaseQuantity($(this));
  });
  updateTotal();
});

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
      updateSubtotal();
      updateTotal();
    })
    .fail(function (err) {
      alert("Error while adding the product to shopping cart");
    });
}

function updateSubtotal() {}

function updateTotal() {
  total = 0.0;

  $(".productSubtotal").each(function (i, el) {
    total = total + parseFloat(el.innerHTML);
  });

  $("#totalAmount").text("Total amount " + total + "€");
}

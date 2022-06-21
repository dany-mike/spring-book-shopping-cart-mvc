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
  console.log(newQty);
  if (newQty > 0) {
    updateQuantity();
  }
}

function increaseQuantity(link) {
  productId = link.attr("pid");
  qtyInput = $("#quantity" + productId);

  newQty = parseInt(qtyInput.val()) + 1;
  if (newQty <= 8) qtyInput.val(newQty);
}

function updateQuantity() {}

function updateTotal() {
  total = 0.0;

  $(".productSubtotal").each(function (i, el) {
    total = total + parseFloat(el.innerHTML);
  });

  $("#totalAmount").text("Total amount " + total + "€");
}

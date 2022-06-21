$(document).ready(function () {
  updateTotal();
});

function updateTotal() {
  total = 0.0;

  $(".productSubtotal").each(function (i, el) {
    total = total + parseFloat(el.innerHTML);
  });

  $("#totalAmount").text("Total amount " + total + "€");
}

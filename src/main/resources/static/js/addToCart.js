$(document).ready(function () {
  $("#buttonAdd2Cart").on("click", function () {
    addToCart();
  });
});

function addToCart() {
  quantity = $("#quantity" + bookId).val();

  url = "http://localhost:9000/cart/add/" + bookId + "/" + quantity;

  $.ajax({
    type: "POST",
    url: url,
  })
    .done(function (response) {
      alert(response);
    })
    .fail(function (err) {
      console.log(err);
      alert("Error while adding the product to shopping cart");
    });
}

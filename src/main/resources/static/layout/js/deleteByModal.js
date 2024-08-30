document.addEventListener("DOMContentLoaded", function ()
{
    const $deleleButtons = document.querySelectorAll(".delete-btn");
    const $closeButton = document.getElementById("close-btn");
    const confirmBtn = document.querySelector(".confirm-btn");
    const $madalContainer = document.getElementById("modal-background");

    let productToDelete = null;

    $deleleButtons.forEach((btn) => {
      btn.addEventListener("click", function (e) {
        e.preventDefault();
        productToDelete = btn.closest(".product-card");

        $madalContainer.style.display = "flex";
        const form = $madalContainer.querySelector("form")
        form.action = `${btn.dataset?.url}`
      });
    });

    confirmBtn?.addEventListener("click", () => {
      if (productToDelete) {
        productToDelete.remove();
        $madalContainer.style.display = "none";
        productToDelete = null;
      }
    });

    $closeButton.addEventListener("click", () => {
      $madalContainer.style.display = "none";
      productToDelete = null;
    });

    $madalContainer.addEventListener("click", (e) => {
      if (e.target === $madalContainer) {
        $madalContainer.style.display = "none";
        productToDelete = null;
      }
    });
})
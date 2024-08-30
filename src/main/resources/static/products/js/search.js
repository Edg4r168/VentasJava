const $inputSearch = document.getElementById("input-search")

$inputSearch.addEventListener("input", function (e) {

    const $products = document.querySelectorAll(".product-card")
    const keyword = e.target.value.trim().toLowerCase();

    [...$products].forEach(product => {
        const title =  product.querySelector(".title").textContent.trim().toLowerCase();

        if (title.startsWith(keyword)) {
            product.style.display = "";
        } else {
            product.style.display = "none";
        }
    })
})

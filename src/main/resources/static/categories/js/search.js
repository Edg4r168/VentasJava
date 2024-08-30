const $inputSearch = document.getElementById("input-search")
const $tbody = document.querySelector(".table tbody")

$inputSearch.addEventListener("input", function (e) {
    const keyword = e.target.value.trim().toLowerCase();
    const searchColumnIndex = Number(document.getElementById("searchBy")?.value ?? 0)
    const registers = $tbody.querySelectorAll("tr");

    registers.forEach(row => {
        const cellText = row.children[searchColumnIndex].textContent.trim().toLowerCase();
       
        if (cellText.startsWith(keyword)) {
            row.style.display = "";
        } else {
            row.style.display = "none";
        }
    })
})

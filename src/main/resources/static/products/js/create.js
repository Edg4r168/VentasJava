document.addEventListener("DOMContentLoaded", function () {
    const $file = document.getElementById("file-upload");
    const $nameImage = document.getElementById("name-image");

    $file.addEventListener("change", (e) => {
    const file = e.target.files[0];
    console.log({ file });
    $nameImage.textContent = file.name;
    });
})
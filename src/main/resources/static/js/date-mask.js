document.addEventListener("DOMContentLoaded", function () {

    const input = document.getElementById("releaseDate");

    input.addEventListener("input", function () {

        let value = this.value.replace(/\D/g, ""); // keep only digits

        if (value.length > 8) {
            value = value.substring(0, 8);
        }

        if (value.length > 4) {
            value = value.replace(/(\d{2})(\d{2})(\d+)/, "$1/$2/$3");
        } else if (value.length > 2) {
            value = value.replace(/(\d{2})(\d+)/, "$1/$2");
        }

        this.value = value;
    });

});
function toggleGameEdit(button) {

    const row = button.closest(".game-row");

    row.querySelectorAll(".view-mode")
        .forEach(element => {
            element.classList.toggle("hidden");
        });

    row.querySelectorAll(".edit-form")
        .forEach(element => {
            element.classList.toggle("hidden");
        });
}

function cancelGameEdit(button) {

    const row = button.closest(".game-row");

    row.querySelectorAll(".view-mode")
        .forEach(element => {
            element.classList.remove("hidden");
        });

    row.querySelectorAll(".edit-form")
        .forEach(element => {
            element.classList.add("hidden");
        });
}

async function saveGame(button) {

    console.log("Save clicked");

    const row = button.closest(".game-row");
    const gameId = row.dataset.gameId;

    console.log("Game ID:", gameId);

    const name = row.querySelector(".game-name-input").value.trim();
    const description = row.querySelector(".game-description-input").value.trim();
    const releaseDate = row.querySelector(".game-release-date-input").value.trim();
    const ratingValue = row.querySelector(".game-rating-input").value;
    const rating = ratingValue === "" ? null : Number(ratingValue);

    const genreIds = Array.from(
        row.querySelectorAll(".genre-checkbox:checked")
    ).map(checkbox => checkbox.value);

    const platformIds = Array.from(
        row.querySelectorAll(".platform-checkbox:checked")
    ).map(checkbox => checkbox.value);

    const request = {
        name: name,
        description: description,
        releaseDate: releaseDate,
        genreIds: genreIds,
        platformIds: platformIds,
        rating: rating
    };

    console.log("Sending:", request);

    try {
        const csrfToken = document.querySelector("#csrf-token").value;

        const response = await fetch(
            `/manage/games/edit/${gameId}`,
            {
                method: "PUT",

                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json",
                    "X-CSRF-TOKEN": csrfToken
                },

                body: JSON.stringify(request)
            }
        );

        if (!response.ok) {

            const errorText = await response.text();

            console.error(
                "Server response:",
                response.status,
                errorText
            );

            throw new Error(
                `Update failed: ${response.status}`
            );
        }

        const updatedGame = await response.json();

        console.log("Updated game:", updatedGame);

        cancelGameEdit(button);

    } catch (error) {

        console.error("Error updating game:", error);

        alert("Something went wrong while updating the game.");
    }
}
function toggleEdit(button){

    const card = button.closest(".game-card");

    card.querySelector(".view-mode")
        .classList
        .toggle("hidden");

    card.querySelector(".edit-form")
        .classList
        .toggle("hidden");
}
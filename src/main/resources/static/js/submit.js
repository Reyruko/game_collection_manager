document.querySelectorAll(".collection-form").forEach(form => {
    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const button = form.querySelector("button");

        const formData = new FormData(form);

        try {
            const response = await fetch(form.action, {method: "POST", body: formData});

            const text = await response.text();

            if (response.ok) {
                button.textContent = "Added ✓";
                button.disabled = true;
            } else if (response.status === 409) {
                button.textContent = "Already in collection."
                button.disabled = true;
            } else {
                button.textContent = "Failed";
            }
        } catch {
            button.textContent = "Error";
        }
    });
});
const form = document.getElementById("verify-form");

form.addEventListener("submit", async (e) => {
  e.preventDefault();
  const token = document.getElementById("token").value;
  const email = localStorage.getItem("email");

  try {
    const response = await fetch("http://localhost:8080/verify", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, token }),
    });

    const result = await response.json();

    if (response.ok) {
      window.location.href = "home.html";
    } else {
      alert(result.message || "Token inválido");
    }
  } catch (error) {
    alert("Erro ao verificar token");
  }
});

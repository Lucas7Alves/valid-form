const form = document.getElementById("verify-form");
const questionText = document.getElementById("question");
const email = localStorage.getItem("pendingEmail");

form.addEventListener("submit", async (e) => {
  e.preventDefault();
  const answer = document.getElementById("answer").value;

  try {
    const response = await fetch("http://localhost:8080/user/auth/verify", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, answer })
    });

    const data = await response.json();

    if (response.ok) {
      localStorage.setItem("token", data.token);
      window.location.href = "home.html";
    } else {
      alert(data.message || "Resposta incorreta");
    }
  } catch (error) {
    alert("Erro: " + error.message);
  }
});

// Ao carregar a página
window.onload = () => {
  const question = localStorage.getItem("securityQuestion");
  questionText.textContent = question;
};

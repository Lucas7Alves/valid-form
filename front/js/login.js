const form = document.getElementById("login-form");
const msg = document.getElementById("mensagem");

form.addEventListener("submit", async (e) => {
  e.preventDefault();

  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  if (!email || !password) {
    alert("Preencha todos os campos");
    return;
  }

  try {
    const response = await fetch("http://localhost:8080/user/auth", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, password }),
    });

    if (!response.ok) {
      throw new Error("Login inválido");
    }

    const data = await response.json();
    localStorage.setItem("securityQuestion", data.securityQuestion);
    localStorage.setItem("pendingEmail", data.email);
    window.location.href = "verify.html";
  } catch (err) {
    alert("Erro ao efetuar login: " + err.message);
  }
});


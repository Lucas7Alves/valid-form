const form = document.getElementById("formulario");

//post
async function enviarDados(event) {
  event.preventDefault();
  const formData = new FormData(event.target);
  const nome = formData.get("name");
  const email = formData.get("email");
  const senha = formData.get("password");

  if (nome.length < 3) {
    alert("Nome muito curto! No mínimo 3 caracteres!");
    return;
  }

  if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
    alert("Email inválido");
    return;
  }

  if (senha.length < 8) {
    alert("Senha muito curta! No mínimo 8 caracteres");
    return;
  }

  if (senha.length > 20) {
    alert("Senha muito longa! No máximo 20 caracteres");
    return;
  }
  if (!/[0-9]/.test(senha)) {
    alert("Falta um número na senha!");
    return;
  }
  if (!/[A-Z]/.test(senha)) {
    alert("Falta um letra maiúscula na senha!");
    return;
  }

  try {
    const response = await fetch("http://localhost:8080/user/cadastro", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        name: formData.get("name"),
        email: formData.get("email"),
        password: formData.get("password"),
      }),
    });

    const result = await response.json();

    if (response.ok) {
      alert("Cadastro realizado com sucesso! Redirecionando para login...");
      setTimeout(() => {
        window.location.href = "../front/pages/login.html";
      }, 2000);
    } else {
      alert(result.message || "Erro ao cadastrar usuário");
    }
  } catch (error) {
    console.log(error);
  }
}

form.addEventListener("submit", enviarDados);

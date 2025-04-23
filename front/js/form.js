const form = document.getElementById("formulario");

async function enviarDados(event) {
  event.preventDefault();
  const formData = new FormData(event.target);
  const nome = formData.get("name");
  const email = formData.get("email");
  const senha = formData.get("password");
  const pergunta = formData.get("securityQuestion");
  const resposta = formData.get("securityAnswer");

  // Validações básicas
  if (nome.length < 3 || nome.length > 15) {
    alert("Nome deve ter entre 3 e 15 caracteres.");
    return;
  }

  if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
    alert("Email inválido.");
    return;
  }

  if (senha.length < 8 || senha.length > 20) {
    alert("A senha deve ter entre 8 e 20 caracteres.");
    return;
  }

  if (!/[0-9]/.test(senha)) {
    alert("A senha deve conter pelo menos um número.");
    return;
  }

  if (!/[A-Z]/.test(senha)) {
    alert("A senha deve conter pelo menos uma letra maiúscula.");
    return;
  }

  if (!pergunta.trim()) {
    alert("Digite uma pergunta de segurança.");
    return;
  }

  if (!resposta.trim()) {
    alert("Digite uma resposta de segurança.");
    return;
  }

  // Enviar dados
  try {
    const response = await fetch("http://localhost:8080/user/cadastro", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        name: nome,
        email: email,
        password: senha,
        securityQuestion: pergunta,
        securityAnswer: resposta,
      }),
    });

    const result = await response.json();

    if (response.ok) {
      alert("Cadastro realizado com sucesso! Redirecionando para login...");
      setTimeout(() => {
        window.location.href = "pages/login.html";
      }, 2000);
    } else {
      alert(result.message || "Erro ao cadastrar usuário.");
    }
  } catch (error) {
    console.error("Erro na requisição:", error);
    alert("Erro ao conectar com o servidor.");
  }
}

form.addEventListener("submit", enviarDados);

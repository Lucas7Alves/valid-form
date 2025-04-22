const token = localStorage.getItem("token");

if (!token) {
  window.location.href = "login.html"; // Redireciona se não estiver logado
}

document.getElementById("logout").addEventListener("click", () => {
  localStorage.removeItem("token");
  window.location.href = "login.html";
});

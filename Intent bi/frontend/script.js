const url = "http://localhost:8080";
const baseUrl = "http://localhost:8080";
let headers;
let sortBy = "id";
let direction = "asc";
const itemsPerPage = 10;
let currentPage = 1;

document.addEventListener("DOMContentLoaded", function () {
  const loginForm = document.getElementById("login-form");

  loginForm.addEventListener("submit", function (event) {
    event.preventDefault();
    const username = document.getElementById("login-username").value;
    const password = document.getElementById("login-password").value;

    fetch(url + "/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ username, password }),
    })
      .then((response) => {
        if (response.ok) {
          window.location.href = "dashboard.html";
        } else {
          return response.json();
        }
      })
      .catch((error) => {
        console.error("Error:", error);
        alert("An error occurred while logging in");
      });
  });
});

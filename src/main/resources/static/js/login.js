const btnSignUp = document.querySelector(".btnSignUp");
const btnSignIn = document.querySelector(".btnSignIn");
const signInForm = document.querySelector(".signInForm");
const signUpForm = document.querySelector(".signUpForm");
const btnToast = document.querySelector(".btnToast");

btnSignUp.addEventListener("click", () => {
  signInForm.style.display = "none";
  signUpForm.style.display = "flex";
});

btnSignIn.addEventListener("click", () => {
  signUpForm.style.display = "none";
  signInForm.style.display = "flex";
});

btnToast.addEventListener("click", () => {
  const toast = document.getElementById("toast");
  toast.className = "show";
  setTimeout(() => {
    toast.className = toast.className.replace("show", "");
  }, 3000);
});

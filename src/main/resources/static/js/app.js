const openBtn = document.querySelector(".menu");
const closeBtn = document.querySelector(".close");
const navbar = document.querySelector(".navsbar");
const btnScrollToTop = document.querySelector("#btnScrollToTop");
const sticky = navbar.offsetTop;  

// Go to top
btnScrollToTop.addEventListener("click", () => {
  window.scrollTo({
    top: 0,
    left: 0,
    behavior: "smooth"
  });
});

window.onscroll = () => {
  changeSticky();
};

// function to show and disapear NavBar and Button Scroll to top 
function changeSticky() {
  if (window.pageYOffset >= sticky + 280) {
    navbar.classList.add("sticky");
    btnScrollToTop.style.display = "block";
  } else {
    navbar.classList.remove("sticky");
    btnScrollToTop.style.display = "none";
  }
}

// Mobile Menu
openBtn.addEventListener("click", () => {
  document.getElementById("mobile__menu").style.width = "100%";
});

closeBtn.addEventListener("click", () => {
  document.getElementById("mobile__menu").style.width = "0";
});

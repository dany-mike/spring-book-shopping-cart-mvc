console.log("MYACCOUNT.JS");
const signinTab = document.getElementById("signin-link");
const signupTab = document.getElementById("signup-link");
const passwordTab = document.getElementById("password-link");
const tabContainer = document.getElementById("tabs");
const tabPane1 = document.getElementById("tab1");
const tabPane2 = document.getElementById("tab2");
const tabPane3 = document.getElementById("tab3");

signinTab.addEventListener("click", (e) => {
  signupTab.classList.remove("active");
  passwordTab.classList.remove("active");
  tabPane2.classList.remove("active");
  tabPane3.classList.remove("active");
  signinTab.classList.add("active");
  tabPane1.classList.add("active");
});

signupTab.addEventListener("click", (e) => {
  e.preventDefault();
  passwordTab.classList.remove("active");
  signinTab.classList.remove("active");
  tabPane1.classList.remove("active");
  tabPane3.classList.remove("active");
  signupTab.classList.add("active");
  tabPane2.classList.add("active");
});

passwordTab.addEventListener("click", (e) => {
  e.preventDefault();
  signinTab.classList.remove("active");
  signupTab.classList.remove("active");
  tabPane1.classList.remove("active");
  tabPane2.classList.remove("active");
  passwordTab.classList.add("active");
  tabPane3.classList.add("active");
});

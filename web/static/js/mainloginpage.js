const  loginMsg = document.querySelector('.loginMsg');
const  loginFront = document.querySelector('.login-front');
const  signupMsg = document.querySelector('.signupMsg');
const  signup = document.querySelector('.signup');
const  frontbox = document.querySelector('.frontbox');
const  switch1 = document.querySelector('#switch1');
const  switch2 = document.querySelector('#switch2'); 

switch1.addEventListener('click',sw1);

function sw1() {
    loginMsg.classList.toggle("visibility");
    frontbox.classList.add("moving");
    signupMsg.classList.toggle("visibility");
  
    signup.classList.toggle('hide');
    loginFront.classList.toggle('hide');
}

switch2.addEventListener('click', sw2);

function sw2() {
    loginMsg.classList.toggle("visibility");
    frontbox.classList.remove("moving");
    signupMsg.classList.toggle("visibility");
  
    signup.classList.toggle('hide');
    loginFront.classList.toggle('hide');
  }
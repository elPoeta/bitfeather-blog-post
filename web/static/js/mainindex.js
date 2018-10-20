const login = document.querySelector('.login');

login.addEventListener('click', e => {
    if(document.querySelector('#login-header')){
        location.replace('login.html');
    }else
         if(document.querySelector('#logout-header'))
           {
             if(!document.querySelector('#logout')){
                 
                 Usuario.panelUser();
                 document.querySelector('.panel-usuario').classList.toggle('hide-panel-usuario');
            } 
            else{
               
               document.querySelector('.panel-usuario').classList.toggle('hide-panel-usuario');
              }
           
        }
  
});

let loadingsvg = document.querySelector("#loading");  
function loading(on) {
  
  if (on) {
      loadingsvg.style.display = "block";
  } else {
      loadingsvg.style.display = "none";
  }
}

class Main{
    static iniciar(){
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
Main.redirectPage();
 } 
 
 static redirectPage(){
         
        if (sessionStorage.getItem("history") === null || JSON.parse(sessionStorage.getItem("history")).url === 'index'){
             const urlHistory={"url":"index"};
             sessionStorage.setItem("history", JSON.stringify(urlHistory));
             Posteo.buscarPorCategoria(0);
        }
        else
            if(JSON.parse(sessionStorage.getItem("history")).url === 'crearPost'){
               Posteo.crearPost();
               const urlHistory={"url":"index"};
               sessionStorage.setItem("history", JSON.stringify(urlHistory));
            }
          else
            if(JSON.parse(sessionStorage.getItem("history")).url === 'miCuenta'){
                console.log("micuenta!!!!");
               MiCuenta.consultarCuenta();
               const urlHistory={"url":"index"};
               sessionStorage.setItem("history", JSON.stringify(urlHistory));
            } else
            if(JSON.parse(sessionStorage.getItem("history")).url === 'misPosts'){
                console.log("micuenta!!!!");
               MisPosts.consultarMisPosts();
               const urlHistory={"url":"index"};
               sessionStorage.setItem("history", JSON.stringify(urlHistory));
            } 
   
 }
 

}
  
Main.iniciar();

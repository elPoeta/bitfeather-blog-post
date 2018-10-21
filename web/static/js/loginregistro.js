class LoginRegistro{
    static async iniciarSesion(){
       
        let logEmail = document.querySelector('#log-email');
        let logPass = document.querySelector('#log-pass');
        const URL_LOGIN_SERVER = 'api/LoginServer';
     
        let loginUsuario ={};
        loginUsuario.email = logEmail.value;
         loginUsuario.password = logPass.value;
        console.log(loginUsuario);
       try
       {
           
        const log = await Http.post(URL_LOGIN_SERVER,loginUsuario); 
         const data = await log.json();
       
           if(data !== null && data !== 'error'){
              
              location.replace("index.html");
              
           }else{
                      LoginRegistro.msgLogueo("Error al iniciar sesion",'msg-color-error');

           }
                    
        }catch (error){
            console.log("error",error);
       LoginRegistro.msgLogueo("Error al iniciar sesion "+err,'msg-color-error');
        }
       
    
    }
    
    static async crearCuenta(){
        console.log('crear cuenta');
        let signNombre = document.querySelector('#sign-nombre');
        let signEmail = document.querySelector('#sign-email');
        let signPass = document.querySelector('#sign-pass');
        let signConfirm = document.querySelector('#sign-confirm');
         const URL_REGISTRO_SERVER = 'api/RegistroServer';
        let registroUsuario ={};
        registroUsuario.nombre = signNombre.value;
        registroUsuario.email = signEmail.value;
        registroUsuario.password = signPass.value;
        registroUsuario.confirmPassword = signConfirm.value;
        console.log(registroUsuario);
   try{
       const reg = await Http.post(URL_REGISTRO_SERVER,registroUsuario);
        const data = await reg.json(); 
           if(data !== null && data !== 'error'){
            //window.location.replace("index.html");
                    LoginRegistro.msgLogueo("Registro completado Inicia Sesion",'msg-color-ok')
                    sw2();
           }else{
                      LoginRegistro.msgLogueo("Error al crear cuenta",'msg-color-error');

           }
           
        } catch (err){
       console.log("error",err);
       LoginRegistro.msgLogueo("Error al crear cuenta "+err, 'msg-color-error');
     }
   

    }
    
   static msgLogueo(msg,color){
    let template = 
            `<div class="mensaje-login">
                 <p class="${color}">${msg}</p>
             </div>`;
    document.querySelector('#panel-login-msg').innerHTML=template;
    
  }
}
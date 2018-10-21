class MisPosts{
    
    static async consultarMisPosts(){
          const URL_MIS_POSTS_SERVER_PRIVADO = 'api/privado/MisPostsServer';
          let  template = ``;
        try{
              
          const autor = await Http.get(URL_MIS_POSTS_SERVER_PRIVADO);
           console.log('autor > ',autor);
             if(autor != 'error'){
                   document.querySelector('#panel-main').innerHTML = '<h1>Mis Posts</h1>';
	 
              }else{
                   const urlHistory={"url":"misPosts"};
                   sessionStorage.setItem("history", JSON.stringify(urlHistory));
                   window.location.replace("login.html");
              }
            }catch (exception) {
                   console.log(exception); 
              }
    }
    
}

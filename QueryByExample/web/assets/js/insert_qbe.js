/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//Obtenemos y agregamos un evento al boton del toolbar
var insertar_button = document.getElementById("insertar");
insertar_button.addEventListener("click", insertar);


function insertar(){    
    //recoleccion de datos
    tabla = Object.keys(objeto)[0].toString();
    into = Object.values(objeto)[0].toString();
    values = obtenerValues(Object.values(objeto)[0]);
    
    //ajax
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
          resultado(this.responseText)
       }
    };
    
    xhttp.open("POST", "insertartabla", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
    xhttp.send("tabla="+tabla+"&into="+into+"&values="+values);
    

}


function obtenerValues(arreglo){
    
    var values = [];
    arreglo.forEach((valor)=>{
        if(document.getElementById(valor).value!="")
        values.push(document.getElementById(valor).value);
        
    });
    
    console.log(values);
    
    return values.toString();
}

function resultado(mensaje){
    console.log(mensaje);
    if(mensaje>0){
        alert("Registro insertado exitosamente");
    }
    else{
        alert("Error al Insertar");
    }
}

/* 
DELETE FROM newcate 
WHERE cate_id='CA002';
 */

function eliminarDatos( event ){
    
    var tabla = Object.keys(objeto)[0];
    var valores;
    if( event.target.tagName == "I" ){
        // el padre -> button
        valores = event.target.parentElement.dataset.values
    }else{
        valores = event.target.dataset.values
    }
    // columnas = nombresColumnas
    
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
          resultadoEliminar(this.responseText)
       }
    };
    
    xhttp.open("POST", "eliminar", true);
    xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
    xhttp.send("tabla="+tabla+"&fields="+nombresColumnas+"&valores="+valores);

}



function resultadoEliminar(mensaje){
    console.log(mensaje);
    if(mensaje>0){
        alert("Registro Eliminado exitosamente");
        filtro();
    }
    else{
        alert("Error al Eliminar");
    }
}
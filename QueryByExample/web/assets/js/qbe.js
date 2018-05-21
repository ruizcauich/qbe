
var table_buttons = document.getElementsByClassName("table-button");

table_buttons = [].slice.call(table_buttons);

table_buttons.forEach(element => {
    element.addEventListener("click", interacTable)
});

function interacTable( e ){
    alert("Llendo por " +  e.target.innerHTML);
}

$(document).ready(function (){
    $("#locales").change(function (){
        let val = $("#locales option:selected").val();
        if(val != ""){
            changeLocale($("#locales option:selected").val());
        }
    });
})

function changeLocale(value){
    $.ajax({
        url: "/changeLocale",
        type: "POST",
        dataType: "text",
        data: {"language" : value},
        success: function (result) {
            window.location.href = result;
        },
        error: function (err) {
            console.log(err);
        }
    })
}
$(document).ready(function(){
    var formHandler=new ZYFormHandler({
        redirectUrl:"dggl/appVipUser/vipCustomerMgr",
        submitUrl:"#"
    })

    $("#myForm").validate({
        ignore:[],
        rules:{
            shopname:{
                required:true,
                maxlength:32
            }
        },
        messages:{
            shopname:{
                required:config.validErrors.required,
                maxlength:config.validErrors.maxLength.replace("${max}",32)
            }
        },
        submitHandler:function(form) {
            formHandler.submitForm(form,JSON.stringify($(form).serializeObject()),true);
        }
    });
});
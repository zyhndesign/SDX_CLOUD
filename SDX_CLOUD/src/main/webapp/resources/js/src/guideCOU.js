var guideCOU=(function(config,functions){
    return {
        initShop:function(shopId){
            $.ajax({
                "dataType":'json',
                "type":"post",
                "url":config.ajaxUrls.shopGetAll,
                "data":{
                    iDisplayLength:100000,
                    iDisplayStart:0,
                    sEcho:1
                },
                "success": function (response) {
                    if(response.success===false){
                        functions.ajaxReturnErrorHandler(response.message);
                    }else{
                        var list=response.aaData,arr=[];

                        for(var i= 0,len=list.length;i<len;i++){
                            if(shopId&&shopId==list[i].id){
                                arr.push('<option value="'+list[i].id+'" selected>'+list[i].shopname+'</li>');
                            }else{
                                arr.push('<option value="'+list[i].id+'">'+list[i].shopname+'</li>');
                            }
                        }

                        $("#allShop").append(arr.join(''));
                    }

                },
                error:function(){
                    functions.ajaxErrorHandler();
                }
            })
        }
    }
})(config,functions);

$(document).ready(function(){

    guideCOU.initShop(shopId);

    var formHandler=new ZYFormHandler({
        redirectUrl:"dggl/appUser/guideMgr",
        submitUrl:"#"
    })

    $("#myForm").validate({
        ignore:[],
        rules:{
            username:{
                required:true,
                maxlength:32
            },
            password:{
                required:true,
                maxlength:20
            }
        },
        messages:{
            username:{
                required:config.validErrors.required,
                maxlength:config.validErrors.maxLength.replace("${max}",32)
            },
            password:{
                required:config.validErrors.required,
                maxlength:config.validErrors.maxLength.replace("${max}",20)
            }
        },
        submitHandler:function(form) {
            formHandler.submitForm(form);
        }
    });
});

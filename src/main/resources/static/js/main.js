function pay() {
    if(validate()){
        log("Submitting request for Samsung Pay transaction to the backend side","success");
        $.ajax({
            type: 'GET',
            url: "/pay",
            contentType: 'application/json',
            data: {amount: $("#amount").val(),currency:$("#currency").val()},
            dataType: 'text',
            success: function (data) {
                var response = JSON.parse(data);
                log("Received transaction creation response","success");
                console.log(response);
                log("Forwarding to Samsung Pay Servers","success");
                SamsungPay.connect(
                    response.id, response.href, serviceId, callbackBaseUrl, callbackBaseUrl, "ch_en",
                    response.encInfo.mod, response.encInfo.exp, response.encInfo.keyId
                );
            },
            error: function (jqXHR, textStatus, errorThrown) {
                log(errorThrown, "error");
            }
        });
    }
}

function validate() {
    var amount = $("#amount").val();

    if(amount == null || !isNumeric(amount)){
        log("Invalid amount provided", "error");
        return false;
    }

    var currency = $("#currency").val();
    if(currency.length != 3){
        log("Invalid currency provided", "error");
        return false;
    }

    if(currency == "CHF" || currency == "USD" ||currency == "EUR") {
       return true;
    }else{
        log("Invalid currency provided, Currencies available: CHF,EUR or USD", "error");
        return false;
    }
}

function isNumeric(n) {
    return !isNaN(parseFloat(n)) && isFinite(n);
}

function log(message, type){
    $(".status").append("<p class='"+ type+"'>" + message +"</p>");
}
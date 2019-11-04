function rsaSign(signStr) {

}

function paymentSign(params, signParamsArr) {
	
	// println(signParamsArr);
	signParamsArr.sort();
	// println(signParamsArr);

	var signStr = "";
	for ( var i in signParamsArr) {
		var key = signParamsArr[i]
		println(key + " = " + params[key])
		var value = params[key];
		if (value != null && value != undefined && value != "") {
			value = encodeURI(value);
			if (signStr == "") {
				signStr = signStr + key + "=" + value;
			} else {
				signStr = signStr + "&" + key + "=" + value;
			}
		}
	}
	println(signStr);
};

var url = "10.200.3.232:14466/payment/pay/zbankInstalApp.do";

var params = {
		"userId" : "10058",
		"instalment" : "3",
		"productId" : "",
		"category" : "",
		"buCode" : "",
		"objectId" : "62971469",
		"objectType" : "",
		"amount" : "200000",
		"paymentType" : "",
		"bizType" : "",
		"royaltyParameters" : "",
		"clientIp" : "",
		"clientDfp" : "",

		'eqmtIdNo' : '009809S91202',
		'eqmtVersion' : 'Android',
		'eqmtType' : 'Huawei',
		'eqmtIP' : '10.1.23.123',
		'eqmtGPS' : '180.2345,32.2321'
	};

var signParamsArr = [ "userId", "instalment", "productId", "category", "buCode",
		"objectId", "objectType", "amount", "paymentType", "bizType",
		"royaltyParameters", "clientIp", "clientDfp" ]

var signMethodName = 'paymentSign'; // paymentSign、finfrontSign

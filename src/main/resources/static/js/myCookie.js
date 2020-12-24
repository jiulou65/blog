var Cookie = {
    set: function(key,value,expiresTime) {
        document.cookie = name+"="+value+";path=/";
        /*if(expiresTime)
        {
            var date = new Date();
            date.setTime(date.getTime()+expiresTime);
            var expiresStr = "expires="+date.toGMTString()+';';
        }else
        {
            var expiresStr='';
        }
        document.cookie = key+'='+escape(value)+';'+expiresStr;*/
    },
    get: function(name) {
        var arr = document.cookie.split("; ");
        for(var i=0; i<arr.length; i++){
            var arr2 = arr[i].split("=");
            if(arr2[0] == name){
                return unescape(arr2[1]);
            }
        }
        return "";
    }
};
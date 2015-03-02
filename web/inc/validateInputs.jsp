<%-- 
    Document   : validateInputs
    Created on : 13 Oct 2013, 11:53:57 PM
    Author     : Tumi

--%>

    <script type="text/javascript" language ="javascript">
        function subm(){
            var count = 0;
            var result = true;
            for(var i = 0; i < document.forms[0].length;i++ ){
                if((isNaN(document.forms[0].elements[i].value) || 
                        document.forms[0].elements[i].value < 0) && 
                        document.forms[0].elements[i].type !== "submit"){
                window.alert("Please enter positive numeric quantities only");
                document.forms[0].elements[i].focus();
                result = false;
                break;
                }else if(document.forms[0].elements[i].value > 0){
                    count++;
                } 
            }
            if(result){
                if(count === 0){
                    window.alert("You can not submit a blank form!");
                    result =  false;
                }
            }
            
            return result;
        }
    </script>

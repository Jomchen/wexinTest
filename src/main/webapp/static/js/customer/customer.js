$(function() {
    $("#anniu").click(function() {
        $.ajax({
            type: "post",
            url: "/customer/ajax",
            dataType: "json",
            data: { cid: 1 },
            success: function(result) {
                if (result.code == 1) {
                    alert(result.obj.cname);
                }
            },
            error: function(e) {

            }
        });
    });
});
$(function() {
    fadeIn();
    test();
});

/**
 * 提供测试
 */
function test() {
    $.ajax({
        url: "/customer/ajax",
        type: "post",
        dataType: "json",
        data: { age: 22 },
        success: function(result) {
            alert("成功了");
        },
        error: function() {
            alert("报500");
        }
    });
}

/**
 * 用于颜色框的鼠标经过事件
 */
function fadeIn() {
    $("#div00 div").hover(
        function() {
            $(this).fadeTo("fast", 0.5);
        },
        function() {
            $(this).fadeTo("fast", 1);
        }
    );
}
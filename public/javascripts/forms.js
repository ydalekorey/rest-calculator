define(['jquery'], function ($) {
        $('#v1-form').submit(function (e) {
            e.preventDefault();
            var v1 = $('#v1').val();
            $.get("/getf1v1", {v1: v1}, function (data) {
                $('#f2v1').text(data.value)
            });
        });

        $('#update-f2-form').submit(function (e) {
            e.preventDefault();
            var v2 = parseInt($('#v2').val());
            var v3 = parseInt($('#v3').val());
            var v4 = parseInt($('#v4').val());

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: '/postv2v3v4',
                data: JSON.stringify({v2: v2, v3: v3, v4: v4}),
                dataType: "json",
                success: function (data) {
                    $('#result').text(data.result)
                }
            });
        })
    }
);
$(function() {
    $.ajaxSetup({
        error: function(req) {
            if (req.status == 0) return;
            console.log(req);
        }
    });
});
(function($) {
    $.fn.row = function() {
        this.each(function() {
            var row = $(this);
            row._title = $('.row__cell__title', this);
            row._title_uri = $('.link', row._title);
            row._data = row.data();
            
            function render(row) {
                row._title_uri.text(row._data.name);
                row._title_uri.attr('href', row._data.uri);
            }
            
            render(row);
        });
    };
    
    $('.row').row();
})(jQuery);
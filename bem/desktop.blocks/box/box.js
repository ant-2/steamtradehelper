modules.define('box', ['i-bem__dom'], function(provide, BEMDOM) {

    provide(BEMDOM.decl('box', {
        onSetMod : {
            'closed': {
                'yes': function() {
                    this.domElem.animate({
                        'margin-left' : '54em'
                    }, 1000);
                },
                '': function() {
                    this.domElem.css({
                        'margin-left' : 'auto'
                    });
                }
            }
        }
    }));

});
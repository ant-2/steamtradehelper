block('call-button')(
    tag()('span'),
    content()(function() {
        return {
            elem: 'link',
            content: this.ctx.content
        };
    }),
    elem('link')(
        tag()('a')
    )
);
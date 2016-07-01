module.exports = {
    block: 'page',
    title: 'call-button',
    head: [
        {elem: 'css', url: 'call-button.min.css'}
    ],
    scripts: [{elem: 'js', url: 'call-button.min.js'}],
    content: [
        {
            block: 'call-button',
            js: true,
            content: 'Phone Call'
        }
    ]
};
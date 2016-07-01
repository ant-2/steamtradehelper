module.exports = {
    block: 'page',
    title: 'contact',
    head: [
        { elem: 'css', url: 'contact.min.css' }
    ],
    scripts: [{ elem: 'js', url: 'contact.min.js' }],
    content: [
       {
           block: 'content',
           content: [
               'block content'
           ]
       }
    ]
};

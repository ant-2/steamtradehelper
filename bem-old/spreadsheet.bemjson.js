[
    '<!DOCTYPE html>',
    {
        tag: 'html',
        attrs: {
            lang: 'en'
        },
        content: [
            {
                tag: 'head',
                content: [
                    {
                        tag: 'meta',
                        attrs: {
                            charset: 'UTF-8'
                        }
                    },
                    {
                        tag: 'title',
                        content: 'Title'
                    },
                    {
                        tag: 'link',
                        attrs: {
                            rel: 'stylesheet',
                            type: 'text/css',
                            href: '/css/styles.css'
                        }
                    }
                ]
            },
            {
                block: 'page',
                tag: 'body',
                content: [
                    {
                        block: 'header',
                        tag: 'section',
                        content: [
                            {
                                elem: 'title',
                                tag: 'h1',
                                content: {
                                    block: 'link',
                                    tag: 'a',
                                    attrs: {
                                        href: 'http://backpack.tf/api/docs/IGetPrices'
                                    },
                                    content: 'Backpack.tf API Prices'
                                }
                            },
                            {
                                elem: 'content',
                                tag: 'article',
                                content: [
                                    {
                                        tag: 'p',
                                        content: 'Здесь у нас будет информация об Backpack.tf API, что это такое и зачем это нужно'
                                    },
                                    {
                                        tag: 'p',
                                        content: 'Так же необходимо описать функционал предоставляемый данной страницей.  Жечь было наслаждением. Какое-то особое наслаждение видеть, как огонь пожирает вещи, как они чернеют и меняются.  Медный наконечник брандспойта зажат в кулаках, громадный питон изрыгает на мир ядовитую струю керосина, кровь стучит в висках,  а руки кажутся руками диковинного дирижера, исполняющего симфонию огня и разрушения, превращая в пепел изорванные, обуглившиеся страницы истории.  Символический шлем, украшенный цифрой 451, низко надвинут на лоб, глаза сверкают оранжевым пламенем при мысли о том, что должно сейчас произойти: он нажимает воспламенитель – и огонь жадно бросается на дом, окрашивая вечернее небо в багрово-желто-черные тона. '
                                    }
                                ]
                            }
                        ]
                    },
                    {
                        block: 'content',
                        tag: 'section',
                        content: [
                            {
                                block: 'search-block',
                                tag: 'section',
                                content: [
                                    {
                                        elem: 'header',
                                        tag: 'h2',
                                        content: 'Header for the search block'
                                    },
                                    {
                                        block: 'form',
                                        tag: 'section',
                                        content: [
                                            {
                                                elem: 'input',
                                                tag: 'input',
                                                attrs: {
                                                    type: 'text',
                                                    value: 'имя предмета'
                                                }
                                            },
                                            {
                                                block: 'button',
                                                tag: 'input',
                                                attrs: {
                                                    type: 'button',
                                                    value: 'Search'
                                                }
                                            },
                                            {
                                                block: 'button',
                                                tag: 'input',
                                                attrs: {
                                                    type: 'button',
                                                    value: 'Clear'
                                                }
                                            }
                                        ]
                                    }
                                ]
                            },
                            {
                                block: 'spreadsheet',
                                tag: 'section',
                                content: {
                                    elem: 'content',
                                    tag: 'section',
                                    content: {
                                        block: 'row',
                                        attrs: {
                                            'data-name': 'A random craft hat',
                                            'data-uri': 'http://backpack.tf/stats/Unique/Detonator/Tradable/Craftable'
                                        },
                                        content: [
                                            {
                                                elem: 'cell',
                                                cls: 'row__cell__title',
                                                content: {
                                                    block: 'link',
                                                    tag: 'a',
                                                    attrs: {
                                                        href: ''
                                                    },
                                                    content: 'Title'
                                                }
                                            },
                                            {
                                                elem: 'cell',
                                                cls: 'row__cell__price',
                                                content: 'Unique 10 ref'
                                            },
                                            {
                                                elem: 'cell',
                                                cls: 'row__cell__price',
                                                content: 'Strange 20 ref'
                                            }
                                        ]
                                    }
                                }
                            }
                        ]
                    },
                    {
                        tag: 'script',
                        attrs: {
                            src: 'https://yastatic.net/jquery/2.2.3/jquery.min.js'
                        }
                    },
                    {
                        tag: 'script',
                        attrs: {
                            src: '/js/app.js'
                        }
                    }
                ]
            }
        ]
    }
]
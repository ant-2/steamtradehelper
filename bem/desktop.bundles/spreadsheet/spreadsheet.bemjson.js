module.exports = {
    block: 'page',
    title: 'spreadsheet',
    head: [
        {elem: 'css', url: 'spreadsheet.min.css'}
    ],
    scripts: [{elem: 'js', url: 'spreadsheet.min.js'}],
    content: [
        {
            block: 'header',
            content: [
                {
                    elem: 'title',
                    content: {
                        block: 'link',
                        url: 'http://backpack.tf/api/docs/IGetPrices',
                        content: 'Backpack.tf API Prices'
                    }
                },
                {
                    elem: 'content',
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
            content: [
                {
                    block: 'search',
                    content: [
                        {
                            elem: 'title',
                            content: 'Параметры поиска'
                        },
                        {
                            elem: 'content',
                            content: [
                                {
                                    block: 'form',
                                    content: [
                                        {
                                            block: 'input',
                                            mods: {theme: 'islands', size: 'm', type: 'search', 'has-clear': true},
                                            placeholder: 'Введите имя предмета'
                                        },
                                        {
                                            block: 'button',
                                            text: 'Поиск',
                                            mods: {theme: 'islands', size: 'm'}
                                        }
                                    ]
                                }

                            ]
                        }
                    ]
                },
                {
                    block: 'spreadsheet',
                    content: [
                        {
                            block: 'row',
                            // attrs: {
                            //     'data-name': 'A random craft hat',
                            //     'data-uri': 'http://backpack.tf/stats/Unique/Detonator/Tradable/Craftable'
                            // },
                            content: [
                                {
                                    elem: 'cell',
                                    content: [{
                                        block: 'link',
                                        url: 'http://backpack.tf/stats/Unique/Rocket%20Launcher/Tradable/Craftable',
                                        content: 'Rocket Launcher'
                                    }]
                                },
                                {
                                    elem: 'cell',
                                    elemMods: {type: 'price'},
                                    content: {
                                        currency: 'metal',
                                        value: '1.33'
                                    }
                                }
                            ]
                        }
                    ]
                }
            ]
        }
    ]
};

package com.example.testapplication.data.model

data class CatItem(
    val text: String,
    val image: Image
)

data class Image(
    val url: String,
    val placeHolderText: String
)


val catPictures = listOf(
    Image(
        "https://cdn2.thecatapi.com/images/9x1zk_Qdr.jpg",
        "Abyssinian"
    ),
    Image(
        "https://cdn2.thecatapi.com/images/ozEvzdVM-.jpg",
        "Aegean"
    ),
    Image(
        "https://cdn2.thecatapi.com/images/d55E_KMKZ.jpg",
        "American Bobtail"
    ),
    Image(
        "https://cdn2.thecatapi.com/images/vJB8rwfdX.jpg",
        "American Curl"
    ),
    Image(
        "https://cdn2.thecatapi.com/images/OaTQfIktG.jpg",
        "Balinese"
    ),
    Image(
        "https://cdn2.thecatapi.com/images/Oaoo1ky3A.jpg",
        "Bambino"
    ),
    Image(
        "https://cdn2.thecatapi.com/images/Rl39SPjDO.png",
        "Bengal"
    ),
    Image(
        "https://cdn2.thecatapi.com/images/7isAO4Cav.jpg",
        "British Longhair"
    ),
    Image(
        "https://cdn2.thecatapi.com/images/OUZlUVIco.jpg",
        "Chartreux"
    ),
    Image(
        "https://cdn2.thecatapi.com/images/o81wWm6-Z.jpg",
        "Cornish Rex"
    ),
    Image(
        "https://cdn2.thecatapi.com/images/Z9zxhFVdw.jpg",
        "Donskoy"
    ),
    Image(
        "https://cdn2.thecatapi.com/images/BQMSld0A0.jpg",
        "Dragon Li"
    ),
    Image(
        "https://cdn2.thecatapi.com/images/khyg1KLOZ.jpg",
        "Exotic Shorthair"
    ),
    Image(
        "https://cdn2.thecatapi.com/images/wu8gMdmfo.jpg",
        "Himalayan"
    ),
    Image(
        "https://cdn2.thecatapi.com/images/R68eywp-W.jpg",
        "Japanese Bobtail"
    ),
    Image(
        "https://cdn2.thecatapi.com/images/us5eEBouq.jpg",
        "Javanese"
    ),
    Image(
        "https://cdn2.thecatapi.com/images/fA-hvyvz1.jpg",
        "LaPerm"
    ),
    Image(
        "https://cdn2.thecatapi.com/images/mZZzMlywy.jpg",
        "Munchkin"
    ),
    Image(
        "https://cdn2.thecatapi.com/images/OUfeISEoi.jpg",
        "Nebelung"
    ),
    Image(
        "https://cdn2.thecatapi.com/images/unPP08xOZ.jpg",
        "Sphynx"
    )
)
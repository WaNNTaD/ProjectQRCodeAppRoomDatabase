package be.heh.projectcodeqr_wanntad.database.hightechitem

class HighTechItem(i : Int){   // classe représentant un appareil high-tech

    // propriété d'identifiant unique (getter public, setter privé)
    var id: Int = 0
        public get
        private set

    // propriété de type d'appareil (getter public, setter privé)
    var type: String = "null"
        public get
        private set

    // propriété de modèle d'appareil (getter public, setter privé)
    var model: String = "null"
        public get
        private set

    // propriété de référence d'appareil (getter public, setter privé)
    var ref: String = "null"
        public get
        private set

    // propriété de lien du site du fabriquant de l'appareil (getter public, setter privé)
    var link: String = "null"
        public get
        private set

    // propriété d'état de disponibilité d'appareil (getter public, setter privé)
    var availability: Boolean = true
        public get
        private set

    // constructeur principal, prenant un identifiant en paramètre
    constructor(
        id: Int, type: String, model: String, ref: String, link: String, availability : Boolean) : this(id) {
        // initialiser les propriétés avec les valeurs passées en paramètres
        this.id = id
        this.type = type
        this.model = model
        this.ref = ref
        this.link = link
        this.availability = availability
    }
}

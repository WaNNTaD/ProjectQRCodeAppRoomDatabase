package be.heh.projectcodeqr_wanntad.database.hightechitem

class HighTechItem(i : Int){

    var id: Int = 0
        public get
        private set
    var type: String = "null"
        public get
        private set
    var model: String = "null"
        public get
        private set
    var ref: String = "null"
        public get
        private set
    var link: String = "null"
        public get
        private set
    var availability: Boolean = true
        public get
        private set

    constructor(
        id: Int, type: String, model: String, ref: String, link: String, availability : Boolean) : this(id) {
        this.id = id
        this.type = type
        this.model = model
        this.ref = ref
        this.link = link
        this.availability = availability
    }
    override fun toString() : String {
        val sb = StringBuilder()
        sb.append("ID : " + id.toString() +
                "\n" +
                "type : " + type + "\n" +
                "model : " + model + "\n"+
                "ref : " + ref + "\n" +
                "link : " + link)
        return sb.toString()
    }
}
package be.heh.projectcodeqr_wanntad.hightechitempage

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import be.heh.projectcodeqr_wanntad.R
import be.heh.projectcodeqr_wanntad.database.hightechitem.HighTechItemEntity

class ItemListAdapter(
    private val context: Activity,
    private val arrayList: ArrayList<HighTechItemEntity>
) : ArrayAdapter<HighTechItemEntity>(context, R.layout.custom_item_layout, arrayList) {

    @SuppressLint("MissingInflatedId")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.custom_item_layout, null)
        val textType: TextView = view.findViewById(R.id.text_type)
        val textModel: TextView = view.findViewById(R.id.text_model)
        val textRef: TextView = view.findViewById(R.id.text_ref)
        val textLink: TextView = view.findViewById(R.id.text_link)
        val textAvailability: TextView = view.findViewById(R.id.text_availability)

        textType.text = arrayList[position].type
        textModel.text = arrayList[position].model
        textRef.text = arrayList[position].ref
        textLink.text = arrayList[position].link
        if (arrayList[position].availability) textAvailability.text = "Disponible"
        else textAvailability.text = "Non disponible"

        view.setOnClickListener {
            val intent = Intent(context, ItemDetails::class.java)
            intent.putExtra("type", arrayList[position].type)
            intent.putExtra("model", arrayList[position].model)
            intent.putExtra("ref", arrayList[position].ref)
            intent.putExtra("link", arrayList[position].link)
            context.startActivity(intent)
        }


        return view
    }
}

package com.example.recipe.data

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.recipe.R
import com.squareup.picasso.Picasso
import org.json.JSONObject


class RecipeAdapter(private val dataSet: Array<JSONObject>) :
    RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recipeImage: ImageView
        val recipeName: TextView
        val recipeSubtext: TextView
        val recipeCategory: TextView

        init {
            recipeImage = view.findViewById(R.id.recipe_image)
            recipeName = view.findViewById(R.id.recipe_name)
            recipeSubtext = view.findViewById(R.id.recipe_subtext)
            recipeCategory = view.findViewById(R.id.recipe_category)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recipe_list_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Picasso.get()
            .load(dataSet[position]
            .getString("strMealThumb"))
            .into(viewHolder.recipeImage)
        viewHolder.recipeImage.setOnClickListener(View.OnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(dataSet[position].getString("strSource")))
            viewHolder.recipeImage.context.startActivity(intent)
        })
        viewHolder.recipeName.text = dataSet[position].getString("strMeal")
        viewHolder.recipeSubtext.text = dataSet[position].getString("strInstructions")
        viewHolder.recipeCategory.text = dataSet[position].getString("strCategory")
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}

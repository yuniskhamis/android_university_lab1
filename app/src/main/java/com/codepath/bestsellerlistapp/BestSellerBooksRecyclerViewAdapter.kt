package com.codepath.bestsellerlistapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.codepath.bestsellerlistapp.BestSellerBooksRecyclerViewAdapter.BookViewHolder
import com.codepath.bestsellerlistapp.R.id
import com.codepath.bestsellerlistapp.R.layout
import com.codepath.bestsellerlistapp.models.BestSellerBook

/**
 * [RecyclerView.Adapter] that can display a [BestSellerBook] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 */
class BestSellerBooksRecyclerViewAdapter(
    private val books: List<BestSellerBook>,
    private val mListener: OnListFragmentInteractionListener?
) : Adapter<BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(layout.fragment_best_seller_book, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.mItem = books[position]
        holder.mBookTitle.text = books[position].title
        holder.mBookAuthor.text = books[position].author
        holder.mBookDescription.text = books[position].description
        holder.mBookRating.text = books[position].rank.toString()

        Glide.with(holder.mView.context)
            .load(holder.mItem!!.bookImageUrl)
            .override(300, 200)
            .into(holder.mBookImage)
        holder.mBookImage

        holder.mView.setOnClickListener {
            holder.mItem?.let { book ->
                mListener?.onItemClick(book)
            }
        }
    }

    override fun getItemCount(): Int {
        return books.size
    }

    inner class BookViewHolder(val mView: View) : ViewHolder(mView) {
        val mBookTitle: TextView = mView.findViewById<View>(id.book_title) as TextView
        val mBookAuthor: TextView = mView.findViewById<View>(id.book_author) as TextView
        val mBookDescription: TextView = mView.findViewById(id.book_description) as TextView
        val mBookRating: TextView = mView.findViewById(id.ranking) as TextView
        val mBookImage: ImageView = mView.findViewById(id.book_image) as ImageView
        val mBuyBookButton: Button = mView.findViewById(id.buy_button) as Button

        var mItem: BestSellerBook? = null

        override fun toString(): String {
            return mBookTitle.toString() + " '" + mBookAuthor.text + "'"
        }
    }
}
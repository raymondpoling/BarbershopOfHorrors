package org.mousehole.americanairline.barbershopofhorrors

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.TextView
import androidx.cardview.widget.CardView

@SuppressLint("SetTextI18n")
class BarbershopCardView(context : Context, attributeSet: AttributeSet) : CardView(context, attributeSet) {
    private var customerNameTextView : TextView
    private var orderTextView : TextView

    private var serviceProgressBar : ProgressBar
    private var estimatedTimeTextView : TextView
    private var barberNameTextView : TextView

    private var layoutInflater : LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
            as LayoutInflater

    init {
        layoutInflater.inflate(R.layout.customer_layout, this, true)
        customerNameTextView = rootView.findViewById(R.id.customer_name_textview)
        orderTextView = rootView.findViewById(R.id.order_textview)

        serviceProgressBar = rootView.findViewById(R.id.order_progressbar)
        estimatedTimeTextView = rootView.findViewById(R.id.estimated_time_textview)
        barberNameTextView = rootView.findViewById(R.id.barber_name_textview)

        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.BarbershopCardView)

        customerNameTextView.text = typedArray.getString(R.styleable.BarbershopCardView_customer_name)
        orderTextView.text = typedArray.getString(R.styleable.BarbershopCardView_order)
        serviceProgressBar.progress = typedArray.getInt(R.styleable.BarbershopCardView_progress, 0)

        estimatedTimeTextView.text = typedArray.getInt(R.styleable.BarbershopCardView_estimated_time, 0).toString() + "min"
        barberNameTextView.text = typedArray.getString(R.styleable.BarbershopCardView_barber)

        typedArray.recycle()
    }
}
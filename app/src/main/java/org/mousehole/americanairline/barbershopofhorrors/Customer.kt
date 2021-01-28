package org.mousehole.americanairline.barbershopofhorrors

import android.os.Parcel
import android.os.Parcelable

data class Customer(val name: String, val order: String, val estimatedTime: Int, var barber : String = "Waiting...", var progress : Int = 0) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString()?:"",
            parcel.readString()?:"",
            parcel.readInt(),
            parcel.readString()?:"",
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(order)
        parcel.writeInt(estimatedTime)
        parcel.writeString(barber)
        parcel.writeInt(progress)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Customer> {
        override fun createFromParcel(parcel: Parcel): Customer {
            return Customer(parcel)
        }

        override fun newArray(size: Int): Array<Customer?> {
            return arrayOfNulls(size)
        }
    }
}